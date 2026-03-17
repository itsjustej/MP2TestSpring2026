import java.io.*;
import java.util.ArrayList;

public class Level3Test {
    public static void main(String[] args) {
        String initialInput = "platform_initial.txt";
        String savedOutput  = "platform_saved.txt";

        try {
            System.out.println("Starting Level 3: Platform ArrayList Methods & File I/O...");

            // ================================================================
            // PART 1 — ALL 6 ArrayList METHODS
            // ================================================================
            System.out.println("\n--- Testing ArrayList Methods ---");

            Platform p = new Platform("Aggie AI Agent Platform");

            AI             ai  = new AI("humanInput", "GPT-4");
            NarrowAI       n   = new NarrowAI("translation", "languageEvent", "temperature", "englishContext");
            GeneralAI      g   = new GeneralAI("imagePerception", "speechAudio", "logicSolving", "mapNavigation", "artCreativity");
            SymbolicAI     sym = new SymbolicAI("prologLogic", "semanticWeb", "deductiveReasoning", "backtracking");
            MachineLearning ml = new MachineLearning("supervisedLearning", "classificationProblem", "decisionTree");
            DeepLearning   d   = new DeepLearning("imagenetDataset", "cnnModel", 5, 3, 1, 800, 600);
            GenerativeAI   t   = new GenerativeAI("syntheticDataset", "diffusionModel", "patternMimicry", 1000);

            // --- 1. addAI ---
            p.addAI(ai);
            p.addAI(n);
            p.addAI(g);
            p.addAI(sym);
            p.addAI(ml);
            p.addAI(d);
            p.addAI(t);
            System.out.println("addAI (7 objects): PASS");

            // --- 2. getAISize ---
            int size = p.getAISize();
            if (size != 7)
                throw new Exception("getAISize() returned wrong value after adding 7 objects.\n" +
                    "  Expected : 7\n" +
                    "  Actual   : " + size);
            System.out.println("getAISize: PASS");

            // --- 3. getAI ---
            AI gotFirst = p.getAI(0);
            if (gotFirst != ai)
                throw new Exception("getAI(0) returned wrong object.\n" +
                    "  Expected : the AI object added first (input=\"humanInput\", model=\"GPT-4\")\n" +
                    "  Actual   : " + (gotFirst == null ? "null" : gotFirst.toString().replace("\n"," | ")));

            AI gotLast = p.getAI(6);
            if (gotLast != t)
                throw new Exception("getAI(6) returned wrong object.\n" +
                    "  Expected : the GenerativeAI added last (dataset=\"syntheticDataset\")\n" +
                    "  Actual   : " + (gotLast == null ? "null" : gotLast.toString().replace("\n"," | ")));
            System.out.println("getAI: PASS");

            // --- 4. setAI ---
            AI replacement = new AI("replacedInput", "replacedModel");
            p.setAI(2, replacement);

            AI afterSet = p.getAI(2);
            if (afterSet != replacement)
                throw new Exception("setAI(2, replacement) did not store the new object.\n" +
                    "  Expected : replacement AI (input=\"replacedInput\")\n" +
                    "  Actual   : " + (afterSet == null ? "null" : afterSet.toString().replace("\n"," | ")));

            int sizeAfterSet = p.getAISize();
            if (sizeAfterSet != 7)
                throw new Exception("setAI() must replace in place — it must NOT change the list size.\n" +
                    "  Expected size : 7\n" +
                    "  Actual size   : " + sizeAfterSet);
            System.out.println("setAI: PASS");

            // --- 5. removeAI ---
            AI removed = p.removeAI(2);
            if (removed != replacement)
                throw new Exception("removeAI(2) returned wrong object.\n" +
                    "  Expected : replacement AI (input=\"replacedInput\") that was set at index 2\n" +
                    "  Actual   : " + (removed == null ? "null" : removed.toString().replace("\n"," | ")));

            int sizeAfterRemove = p.getAISize();
            if (sizeAfterRemove != 6)
                throw new Exception("removeAI() must shrink the list by 1.\n" +
                    "  Expected size : 6\n" +
                    "  Actual size   : " + sizeAfterRemove);

            // Restore g so list is back to 7 for file tests
            p.addAI(g);
            System.out.println("removeAI: PASS");

            // --- 6. getAIList ---
            ArrayList<AI> narrowList = p.getAIList(NarrowAI.class);
            if (narrowList == null)
                throw new Exception("getAIList(NarrowAI.class) returned null. It must return an ArrayList, even if empty.");
            if (narrowList.size() != 1)
                throw new Exception("getAIList(NarrowAI.class) wrong count.\n" +
                    "  Expected : 1\n" +
                    "  Actual   : " + narrowList.size() + "\n" +
                    "  Note     : getAIList must match on exact class, not superclass.");
            if (narrowList.get(0) != n)
                throw new Exception("getAIList(NarrowAI.class) returned the wrong object at index 0.");

            ArrayList<AI> mlList = p.getAIList(MachineLearning.class);
            if (mlList == null || mlList.size() != 1)
                throw new Exception("getAIList(MachineLearning.class) wrong count.\n" +
                    "  Expected : 1\n" +
                    "  Actual   : " + (mlList == null ? "null" : mlList.size()) + "\n" +
                    "  Note     : Only the MachineLearning object itself should match, not DeepLearning or GenerativeAI.");

            ArrayList<AI> deepList = p.getAIList(DeepLearning.class);
            if (deepList == null || deepList.size() != 1)
                throw new Exception("getAIList(DeepLearning.class) wrong count.\n" +
                    "  Expected : 1\n" +
                    "  Actual   : " + (deepList == null ? "null" : deepList.size()));

            ArrayList<AI> genList = p.getAIList(GenerativeAI.class);
            if (genList == null || genList.size() != 1)
                throw new Exception("getAIList(GenerativeAI.class) wrong count.\n" +
                    "  Expected : 1\n" +
                    "  Actual   : " + (genList == null ? "null" : genList.size()));

            ArrayList<AI> aiOnlyList = p.getAIList(AI.class);
            if (aiOnlyList == null || aiOnlyList.size() != 1)
                throw new Exception("getAIList(AI.class) wrong count.\n" +
                    "  Expected : 1  (only the base AI object, not subclasses)\n" +
                    "  Actual   : " + (aiOnlyList == null ? "null" : aiOnlyList.size()) + "\n" +
                    "  Note     : getAIList must use == on getClass(), not instanceof.");
            System.out.println("getAIList: PASS");

            System.out.println("\nAll 6 ArrayList Methods: PASS");

            // ================================================================
            // PART 2 — FILE I/O ROUND-TRIP
            // ================================================================
            System.out.println("\n--- Testing File I/O ---");

            PrintWriter pw = new PrintWriter(new File(initialInput));
            pw.println("Aggie AI Agent Platform");
            pw.println("A");
            pw.println("humanInput");
            pw.println("GPT-4");
            pw.println("N");
            pw.println("translation");
            pw.println("languageEvent");
            pw.println("temperature");
            pw.println("englishContext");
            pw.println("G");
            pw.println("imagePerception");
            pw.println("speechAudio");
            pw.println("logicSolving");
            pw.println("mapNavigation");
            pw.println("artCreativity");
            pw.println("S");
            pw.println("prologLogic");
            pw.println("semanticWeb");
            pw.println("deductiveReasoning");
            pw.println("backtracking");
            pw.println("M");
            pw.println("supervisedLearning");
            pw.println("classificationProblem");
            pw.println("decisionTree");
            pw.println("D");
            pw.println("imagenetDataset");
            pw.println("cnnModel");
            pw.println("5");
            pw.println("3");
            pw.println("1");
            pw.println("800");
            pw.println("600");
            pw.println("T");
            pw.println("syntheticDataset");
            pw.println("diffusionModel");
            pw.println("patternMimicry");
            pw.println("1000");
            pw.close();
            System.out.println("Input File Written: PASS");

            // Load
            Platform loaded = new Platform();
            loaded.loadPlatform(initialInput);

            int loadedSize = loaded.getAISize();
            if (loadedSize != 7)
                throw new Exception("loadPlatform() read wrong number of objects.\n" +
                    "  Expected : 7\n" +
                    "  Actual   : " + loadedSize + "\n" +
                    "  File     : " + initialInput + "\n" +
                    "  Check    : your loadPlatform() loop and the type-char switch/if block.");
            System.out.println("loadPlatform (7 objects): PASS");

            // Spot-check first object
            String loadedInput = loaded.getAI(0).getInput();
            String loadedModel = loaded.getAI(0).getModel();
            if (!loadedInput.equals("humanInput"))
                throw new Exception("loadPlatform() loaded wrong value for AI[0].input.\n" +
                    "  Expected : \"humanInput\"\n" +
                    "  Actual   : \"" + loadedInput + "\"");
            if (!loadedModel.equals("GPT-4"))
                throw new Exception("loadPlatform() loaded wrong value for AI[0].model.\n" +
                    "  Expected : \"GPT-4\"\n" +
                    "  Actual   : \"" + loadedModel + "\"");
            System.out.println("loadPlatform Field Values: PASS");

            // Add a new object
            NarrowAI extra = new NarrowAI("speechRecognition", "voiceEvent", "sensitivity", "englishContext");
            extra.setInput("audioInput");
            extra.setModel("Whisper");
            loaded.addAI(extra);

            int sizeAfterAdd = loaded.getAISize();
            if (sizeAfterAdd != 8)
                throw new Exception("addAI() after loadPlatform() gave wrong size.\n" +
                    "  Expected : 8\n" +
                    "  Actual   : " + sizeAfterAdd);
            System.out.println("In-Memory Modification After Load: PASS");

            // Save
            loaded.savePlatform(savedOutput);
            File savedFile = new File(savedOutput);
            if (!savedFile.exists())
                throw new Exception("savePlatform() did not create the output file.\n" +
                    "  Expected file : " + savedOutput + "\n" +
                    "  Check         : the file path argument and your FileWriter/PrintWriter setup.");
            if (savedFile.length() == 0)
                throw new Exception("savePlatform() created an empty file.\n" +
                    "  File    : " + savedOutput + "\n" +
                    "  Check   : that you are actually writing data before closing the writer.");
            System.out.println("savePlatform (file created): PASS");

            // Reload
            Platform reloaded = new Platform();
            reloaded.loadPlatform(savedOutput);

            int reloadedSize = reloaded.getAISize();
            if (reloadedSize != 8)
                throw new Exception("Round-trip failed: reloaded wrong number of objects.\n" +
                    "  Expected : 8\n" +
                    "  Actual   : " + reloadedSize + "\n" +
                    "  Check    : savePlatform() is writing all 8 objects including the newly added one.");

            // Full toString consistency check
            String original  = loaded.toString();
            String roundTrip = reloaded.toString();
            if (!original.equals(roundTrip))
                throw new Exception("Round-trip consistency FAILED: toString() differs after save/reload.\n" +
                    "  This means savePlatform() and loadPlatform() are not perfectly symmetric.\n" +
                    "  Check   : field write order in savePlatform() matches read order in loadPlatform().\n" +
                    "--- ORIGINAL toString() ---\n" + original +
                    "\n--- RELOADED toString() ---\n" + roundTrip);
            System.out.println("Round-Trip Consistency: PASS");

            // Verify new object survived
            if (!roundTrip.contains("speechRecognition"))
                throw new Exception("Newly added NarrowAI did not survive the round-trip.\n" +
                    "  Missing value : \"speechRecognition\" (task field)\n" +
                    "  Check         : savePlatform() is writing the last-added object.");
            if (!roundTrip.contains("Whisper"))
                throw new Exception("Newly added NarrowAI model did not survive the round-trip.\n" +
                    "  Missing value : \"Whisper\" (model field)");
            System.out.println("New Object Survived Round-Trip: PASS");

            System.out.println("\nLEVEL 3 COMPLETE: 75/75");

        } catch (Exception e) {
            System.err.println("LEVEL 3 FAILED: " + e.getMessage());
            System.exit(1);
        }
    }
}
