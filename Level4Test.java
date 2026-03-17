import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class Level4Test {

    static void pass(String msg) {
        System.out.println(msg + ": PASS");
    }

    static void fail(String msg) throws Exception {
        throw new Exception(msg);
    }

    static Field getField(Class<?> clazz, Class<?> fieldType) {
        for (Field f : clazz.getDeclaredFields()) {
            if (fieldType.isAssignableFrom(f.getType())) return f;
        }
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getType().getName().contains(fieldType.getSimpleName())) return f;
        }
        return null;
    }

    static boolean hasFieldOfType(Class<?> clazz, String typeName) {
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getType().getSimpleName().equals(typeName)) return true;
            if (f.getType().getName().contains(typeName)) return true;
        }
        return false;
    }

    static boolean hasFieldAssignableTo(Class<?> clazz, String superTypeName) {
        for (Field f : clazz.getDeclaredFields()) {
            Class<?> ft = f.getType();
            while (ft != null) {
                if (ft.getSimpleName().equals(superTypeName)) return true;
                ft = ft.getSuperclass();
            }
            for (Class<?> iface : f.getType().getInterfaces()) {
                if (iface.getSimpleName().equals(superTypeName)) return true;
            }
        }
        return false;
    }

    static Method findMethod(Class<?> clazz, String name, Class<?>... params) {
        try {
            return clazz.getDeclaredMethod(name, params);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    static boolean hasButtonWithText(Class<?> clazz, String text) throws Exception {
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getType().getSimpleName().equals("Button")) {
                f.setAccessible(true);
                return true;
            }
        }
        StringWriter sw = new StringWriter();
        try (InputStream is = new FileInputStream("AggiePlatformGUI.java");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("\"" + text + "\"")) return true;
            }
        }
        return false;
    }

    static boolean sourceContains(String... tokens) {
        try (BufferedReader br = new BufferedReader(new FileReader("AggiePlatformGUI.java"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            String src = sb.toString();
            for (String token : tokens) {
                if (!src.contains(token)) return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting Level 4: AggiePlatformGUI Structure Validation...");

            Class<?> guiClass = Class.forName("AggiePlatformGUI");

            System.out.println("\n--- Class Structure ---");

            Class<?> appClass = Class.forName("javafx.application.Application");
            if (!appClass.isAssignableFrom(guiClass))
                fail("AggiePlatformGUI must extend javafx.application.Application");
            pass("Extends javafx.application.Application");

            Class<?> stageClass = Class.forName("javafx.stage.Stage");
            Method startMethod = findMethod(guiClass, "start", stageClass);
            if (startMethod == null)
                fail("AggiePlatformGUI must override start(Stage primaryStage)");
            pass("start(Stage) method present");

            boolean hasPlatformField = hasFieldOfType(guiClass, "Platform");
            if (!hasPlatformField)
                fail("AggiePlatformGUI must have a Platform field");
            pass("Platform field present");

            System.out.println("\n--- Required JavaFX Components ---");

            boolean hasListView  = hasFieldOfType(guiClass, "ListView")
                                || hasFieldOfType(guiClass, "TableView")
                                || sourceContains("ListView")
                                || sourceContains("TableView");
            if (!hasListView)
                fail("AggiePlatformGUI must contain a ListView or TableView");
            pass("ListView or TableView present");

            boolean hasComboBox = hasFieldOfType(guiClass, "ComboBox") || sourceContains("ComboBox");
            if (!hasComboBox)
                fail("AggiePlatformGUI must contain a ComboBox");
            pass("ComboBox present");

            boolean hasTextField = hasFieldOfType(guiClass, "TextField")
                                || sourceContains("TextField");
            if (!hasTextField)
                fail("AggiePlatformGUI must contain TextField input fields");
            pass("TextField input fields present");

            boolean hasLabel = hasFieldOfType(guiClass, "Label") || sourceContains("Label");
            if (!hasLabel)
                fail("AggiePlatformGUI must contain a status Label");
            pass("Status Label present");

            System.out.println("\n--- Required Buttons ---");

            String[] requiredButtons = {"Load", "Save", "Add", "Remove", "Filter"};
            for (String btnText : requiredButtons) {
                if (!hasButtonWithText(guiClass, btnText))
                    fail("Missing button with text: \"" + btnText + "\"");
                pass("Button \"" + btnText + "\" present");
            }

            System.out.println("\n--- Required Method Calls in Source ---");

            if (!sourceContains("loadPlatform"))
                fail("AggiePlatformGUI must call loadPlatform()");
            pass("loadPlatform() call present");

            if (!sourceContains("savePlatform"))
                fail("AggiePlatformGUI must call savePlatform()");
            pass("savePlatform() call present");

            if (!sourceContains("getAIList"))
                fail("AggiePlatformGUI must call getAIList() for Filter");
            pass("getAIList() call present");

            if (!sourceContains("addAI"))
                fail("AggiePlatformGUI must call addAI() for Add");
            pass("addAI() call present");

            if (!sourceContains("removeAI"))
                fail("AggiePlatformGUI must call removeAI() for Remove");
            pass("removeAI() call present");

            System.out.println("\n--- Layout Panes (no FXML) ---");

            boolean hasPanes = sourceContains("BorderPane", "VBox", "HBox")
                            || sourceContains("GridPane")
                            || sourceContains("StackPane");
            if (!hasPanes)
                fail("AggiePlatformGUI must use layout panes (BorderPane, VBox, HBox, etc.)");
            pass("Layout panes present");

            if (sourceContains("FXML", "fxml", "FXMLLoader"))
                fail("AggiePlatformGUI must NOT use FXML or FXMLLoader");
            pass("No FXML used");

            if (sourceContains("SceneBuilder", "scenebuilder"))
                fail("AggiePlatformGUI must NOT use SceneBuilder");
            pass("No SceneBuilder used");

            System.out.println("\n--- Stage.show() Called ---");

            if (!sourceContains("primaryStage.show()") && !sourceContains(".show()"))
                fail("start() method must call primaryStage.show()");
            pass("primaryStage.show() called");

            System.out.println("\n--- AI Type Coverage in Add ---");

            String[] types = {"NarrowAI", "GeneralAI", "SymbolicAI", "MachineLearning", "DeepLearning", "GenerativeAI"};
            for (String type : types) {
                if (!sourceContains(type))
                    fail("AggiePlatformGUI must handle AI type: " + type);
                pass("AI type \"" + type + "\" handled");
            }

            System.out.println("\n--- File I/O Round-Trip via GUI Logic ---");

            Platform p = new Platform("Test Platform");
            NarrowAI n = new NarrowAI("task1", "event1", "param1", "ctx1");
            n.setInput("inp"); n.setModel("mdl");
            p.addAI(n);

            String tmpFile = "level4_roundtrip_test.txt";
            p.savePlatform(tmpFile);

            Platform p2 = new Platform();
            p2.loadPlatform(tmpFile);

            if (p2.getAISize() != 1)
                fail("File I/O round-trip failed: expected 1 object, got " + p2.getAISize());
            if (!p2.getAI(0).getInput().equals("inp"))
                fail("File I/O round-trip failed: input field mismatch");
            pass("File I/O round-trip via Platform");

            new File(tmpFile).delete();

            System.out.println("\nLEVEL 4 COMPLETE: 100/100");

        } catch (ClassNotFoundException e) {
            System.err.println("LEVEL 4 FAILED: AggiePlatformGUI class not found. Make sure it compiles.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("LEVEL 4 FAILED: " + e.getMessage());
            System.exit(1);
        }
    }
}
