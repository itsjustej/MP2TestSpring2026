public class Level2Test {

    static void checkStr(String cls, String field, String expected, String actual) throws Exception {
        if (!expected.equals(actual))
            throw new Exception(cls + ": " + field + " wrong.\n  Expected: \"" + expected + "\"\n  Actual  : \"" + actual + "\"");
    }

    static void checkInt(String cls, String field, int expected, int actual) throws Exception {
        if (expected != actual)
            throw new Exception(cls + ": " + field + " wrong.\n  Expected: " + expected + "\n  Actual  : " + actual);
    }

    static void checkDouble(String cls, String field, double expected, double actual) throws Exception {
        if (Double.compare(expected, actual) != 0)
            throw new Exception(cls + ": " + field + " wrong.\n  Expected: " + expected + "\n  Actual  : " + actual);
    }

    static void checkPrefix(String cls, char letter, String actual) throws Exception {
        String expected = letter + System.lineSeparator();
        if (!actual.startsWith(expected))
            throw new Exception(cls + ".toString() wrong prefix.\n  Expected to start with: '" + letter + "'");
    }

    static void checkContains(String cls, String field, String value, String actual) throws Exception {
        if (!actual.contains(value))
            throw new Exception(cls + ".toString() missing '" + field + "'.\n  Expected to contain: \"" + value + "\"\n  Full output:\n" + actual);
    }

    static void checkFieldDeclaredIn(Object obj, String fieldName, Class<?> expectedClass) throws Exception {
        try {
            expectedClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new Exception("Test setup error: field '" + fieldName + "' not in " + expectedClass.getSimpleName());
        }
        Class<?> sub = obj.getClass();
        if (!sub.equals(expectedClass)) {
            try {
                sub.getDeclaredField(fieldName);
                throw new Exception("Field '" + fieldName + "' is re-declared in " + sub.getSimpleName() +
                    " but should only be in " + expectedClass.getSimpleName() + ". Remove it and use super().");
            } catch (NoSuchFieldException e) { /* good */ }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting Level 2: AI Class Hierarchy Validation...");

            // ================================================================
            // AI (tested via NarrowAI since AI is abstract)
            // ================================================================
            System.out.println("\n--- Testing AI (via NarrowAI) ---");
            NarrowAI aiTest = new NarrowAI();
            aiTest.setInput("humanInput");
            aiTest.setModel("GPT-4");
            checkStr("AI", "getInput", "humanInput", aiTest.getInput());
            checkStr("AI", "getModel", "GPT-4",      aiTest.getModel());
            System.out.println("AI Setters/Getters: PASS");

            NarrowAI aiArgs = new NarrowAI("translation", "languageEvent", "temperature", "englishContext");
            aiArgs.setInput("textInput");
            aiArgs.setModel("BERT");
            checkStr("AI", "getInput", "textInput", aiArgs.getInput());
            checkStr("AI", "getModel", "BERT",      aiArgs.getModel());
            System.out.println("AI Args Constructor (via NarrowAI): PASS");

            NarrowAI aiStr = new NarrowAI();
            aiStr.setInput("textInput");
            aiStr.setModel("BERT");
            String aiOut = aiStr.toString();
            checkPrefix("AI/NarrowAI", 'N', aiOut);
            checkContains("AI", "input", "textInput", aiOut);
            checkContains("AI", "model", "BERT",      aiOut);
            System.out.println("AI toString (via NarrowAI): PASS");

            // ================================================================
            // NarrowAI
            // ================================================================
            System.out.println("\n--- Testing NarrowAI ---");
            NarrowAI n = new NarrowAI();
            n.setInput("queryInput");
            n.setModel("BERT");
            n.setTask("translation");
            n.setEvent("languageEvent");
            n.setParameter("temperature");
            n.setContext("englishContext");
            checkStr("NarrowAI", "getInput",     "queryInput",     n.getInput());
            checkStr("NarrowAI", "getModel",     "BERT",           n.getModel());
            checkStr("NarrowAI", "getTask",      "translation",    n.getTask());
            checkStr("NarrowAI", "getEvent",     "languageEvent",  n.getEvent());
            checkStr("NarrowAI", "getParameter", "temperature",    n.getParameter());
            checkStr("NarrowAI", "getContext",   "englishContext",  n.getContext());
            System.out.println("NarrowAI Setters/Getters: PASS");

            NarrowAI n2 = new NarrowAI("translation", "languageEvent", "temperature", "englishContext");
            n2.setInput("queryInput");
            n2.setModel("BERT");
            checkStr("NarrowAI", "getTask      [param 1]", "translation",    n2.getTask());
            checkStr("NarrowAI", "getEvent     [param 2]", "languageEvent",  n2.getEvent());
            checkStr("NarrowAI", "getParameter [param 3]", "temperature",    n2.getParameter());
            checkStr("NarrowAI", "getContext   [param 4]", "englishContext",  n2.getContext());
            System.out.println("NarrowAI Args Constructor: PASS");

            String nStr = n2.toString();
            checkPrefix("NarrowAI", 'N', nStr);
            checkContains("NarrowAI", "input",     "queryInput",    nStr);
            checkContains("NarrowAI", "model",     "BERT",          nStr);
            checkContains("NarrowAI", "task",      "translation",   nStr);
            checkContains("NarrowAI", "event",     "languageEvent", nStr);
            checkContains("NarrowAI", "parameter", "temperature",   nStr);
            checkContains("NarrowAI", "context",   "englishContext", nStr);
            System.out.println("NarrowAI toString: PASS");

            // ================================================================
            // GeneralAI
            // ================================================================
            System.out.println("\n--- Testing GeneralAI ---");
            GeneralAI g = new GeneralAI();
            g.setInput("visionInput");
            g.setModel("AGI-1");
            g.setVisualPerception("imagePerception");
            g.setAudioPerception("speechAudio");
            g.setProblemSolving("logicSolving");
            g.setNavigation("mapNavigation");
            g.setCreativity("artCreativity");
            checkStr("GeneralAI", "getInput",            "visionInput",    g.getInput());
            checkStr("GeneralAI", "getModel",            "AGI-1",          g.getModel());
            checkStr("GeneralAI", "getVisualPerception", "imagePerception", g.getVisualPerception());
            checkStr("GeneralAI", "getAudioPerception",  "speechAudio",    g.getAudioPerception());
            checkStr("GeneralAI", "getProblemSolving",   "logicSolving",   g.getProblemSolving());
            checkStr("GeneralAI", "getNavigation",       "mapNavigation",  g.getNavigation());
            checkStr("GeneralAI", "getCreativity",       "artCreativity",  g.getCreativity());
            System.out.println("GeneralAI Setters/Getters: PASS");

            GeneralAI g2 = new GeneralAI("imagePerception", "speechAudio", "logicSolving", "mapNavigation", "artCreativity");
            checkStr("GeneralAI", "getVisualPerception [param 1]", "imagePerception", g2.getVisualPerception());
            checkStr("GeneralAI", "getAudioPerception  [param 2]", "speechAudio",     g2.getAudioPerception());
            checkStr("GeneralAI", "getProblemSolving   [param 3]", "logicSolving",    g2.getProblemSolving());
            checkStr("GeneralAI", "getNavigation       [param 4]", "mapNavigation",   g2.getNavigation());
            checkStr("GeneralAI", "getCreativity       [param 5]", "artCreativity",   g2.getCreativity());
            System.out.println("GeneralAI Args Constructor: PASS");

            String gStr = g2.toString();
            checkPrefix("GeneralAI", 'G', gStr);
            checkContains("GeneralAI", "visualPerception", "imagePerception", gStr);
            checkContains("GeneralAI", "audioPerception",  "speechAudio",     gStr);
            checkContains("GeneralAI", "problemSolving",   "logicSolving",    gStr);
            checkContains("GeneralAI", "navigation",       "mapNavigation",   gStr);
            checkContains("GeneralAI", "creativity",       "artCreativity",   gStr);
            System.out.println("GeneralAI toString: PASS");

            // ================================================================
            // SymbolicAI
            // ================================================================
            System.out.println("\n--- Testing SymbolicAI ---");
            SymbolicAI s = new SymbolicAI();
            s.setInput("ruleInput");
            s.setModel("Expert-1");
            s.setLogicProgram("prologLogic");
            s.setSemanticNetwork("semanticWeb");
            s.setReasoning("deductiveReasoning");
            s.setProblemSolMethod("backtracking");
            checkStr("SymbolicAI", "getInput",            "ruleInput",          s.getInput());
            checkStr("SymbolicAI", "getModel",            "Expert-1",           s.getModel());
            checkStr("SymbolicAI", "getLogicProgram",     "prologLogic",        s.getLogicProgram());
            checkStr("SymbolicAI", "getSemanticNetwork",  "semanticWeb",        s.getSemanticNetwork());
            checkStr("SymbolicAI", "getReasoning",        "deductiveReasoning", s.getReasoning());
            checkStr("SymbolicAI", "getProblemSolMethod", "backtracking",       s.getProblemSolMethod());
            System.out.println("SymbolicAI Setters/Getters: PASS");

            SymbolicAI s2 = new SymbolicAI("prologLogic", "semanticWeb", "deductiveReasoning", "backtracking");
            checkStr("SymbolicAI", "getLogicProgram    [param 1]", "prologLogic",        s2.getLogicProgram());
            checkStr("SymbolicAI", "getSemanticNetwork [param 2]", "semanticWeb",        s2.getSemanticNetwork());
            checkStr("SymbolicAI", "getReasoning       [param 3]", "deductiveReasoning", s2.getReasoning());
            checkStr("SymbolicAI", "getProblemSolMethod[param 4]", "backtracking",       s2.getProblemSolMethod());
            System.out.println("SymbolicAI Args Constructor: PASS");

            String sStr = s2.toString();
            checkPrefix("SymbolicAI", 'S', sStr);
            checkContains("SymbolicAI", "logicProgram",     "prologLogic",        sStr);
            checkContains("SymbolicAI", "semanticNetwork",  "semanticWeb",        sStr);
            checkContains("SymbolicAI", "reasoning",        "deductiveReasoning", sStr);
            checkContains("SymbolicAI", "problemSolMethod", "backtracking",       sStr);
            System.out.println("SymbolicAI toString: PASS");

            // ================================================================
            // MachineLearning
            // ================================================================
            System.out.println("\n--- Testing MachineLearning ---");
            MachineLearning ml = new MachineLearning();
            ml.setInput("dataInput");
            ml.setModel("RandomForest");
            ml.setLearnType("supervisedLearning");
            ml.setProblem("classificationProblem");
            ml.setAlgorithm("decisionTree");
            checkStr("MachineLearning", "getInput",     "dataInput",             ml.getInput());
            checkStr("MachineLearning", "getModel",     "RandomForest",          ml.getModel());
            checkStr("MachineLearning", "getLearnType", "supervisedLearning",    ml.getLearnType());
            checkStr("MachineLearning", "getProblem",   "classificationProblem", ml.getProblem());
            checkStr("MachineLearning", "getAlgorithm", "decisionTree",          ml.getAlgorithm());
            System.out.println("MachineLearning Setters/Getters: PASS");

            MachineLearning ml2 = new MachineLearning("supervisedLearning", "classificationProblem", "decisionTree");
            checkStr("MachineLearning", "getLearnType [param 1]", "supervisedLearning",    ml2.getLearnType());
            checkStr("MachineLearning", "getProblem   [param 2]", "classificationProblem", ml2.getProblem());
            checkStr("MachineLearning", "getAlgorithm [param 3]", "decisionTree",          ml2.getAlgorithm());
            System.out.println("MachineLearning Args Constructor: PASS");

            String mStr = ml2.toString();
            checkPrefix("MachineLearning", 'M', mStr);
            checkContains("MachineLearning", "learnType", "supervisedLearning",    mStr);
            checkContains("MachineLearning", "problem",   "classificationProblem", mStr);
            checkContains("MachineLearning", "algorithm", "decisionTree",          mStr);
            System.out.println("MachineLearning toString: PASS");

            // ================================================================
            // DeepLearning
            // ================================================================
            System.out.println("\n--- Testing DeepLearning ---");
            DeepLearning d = new DeepLearning();
            d.setInput("imageInput");
            d.setModel("ResNet");
            d.setLearnType("deepLearning");
            d.setProblem("imageClassification");
            d.setAlgorithm("backpropagation");
            d.setDataset("imagenetDataset");
            d.setNnModel("cnnModel");
            d.setLayers(5);
            d.setTestSet(800.0);
            d.setTrainset(600.0);
            checkStr   ("DeepLearning", "getInput",     "imageInput",          d.getInput());
            checkStr   ("DeepLearning", "getModel",     "ResNet",              d.getModel());
            checkStr   ("DeepLearning", "getLearnType", "deepLearning",        d.getLearnType());
            checkStr   ("DeepLearning", "getProblem",   "imageClassification", d.getProblem());
            checkStr   ("DeepLearning", "getAlgorithm", "backpropagation",     d.getAlgorithm());
            checkStr   ("DeepLearning", "getDataset",   "imagenetDataset",     d.getDataset());
            checkStr   ("DeepLearning", "getNnModel",   "cnnModel",            d.getNnModel());
            checkInt   ("DeepLearning", "getLayers",    5,     d.getLayers());
            checkDouble("DeepLearning", "getTestSet",   800.0, d.getTestSet());
            checkDouble("DeepLearning", "getTrainset",  600.0, d.getTrainset());
            System.out.println("DeepLearning Setters/Getters: PASS");

            DeepLearning d2 = new DeepLearning("imagenetDataset", "cnnModel", 5, 800.0, 600.0);
            d2.setLearnType("deepLearning");
            d2.setProblem("imageClassification");
            d2.setAlgorithm("backpropagation");
            checkStr   ("DeepLearning", "getDataset  [param 1]", "imagenetDataset", d2.getDataset());
            checkStr   ("DeepLearning", "getNnModel  [param 2]", "cnnModel",        d2.getNnModel());
            checkInt   ("DeepLearning", "getLayers   [param 3]", 5,     d2.getLayers());
            checkDouble("DeepLearning", "getTestSet  [param 4]", 800.0, d2.getTestSet());
            checkDouble("DeepLearning", "getTrainset [param 5]", 600.0, d2.getTrainset());
            System.out.println("DeepLearning Args Constructor: PASS");

            String dStr = d2.toString();
            checkPrefix("DeepLearning", 'D', dStr);
            checkContains("DeepLearning", "dataset",   "imagenetDataset",     dStr);
            checkContains("DeepLearning", "nnModel",   "cnnModel",            dStr);
            checkContains("DeepLearning", "layers",    "5",                   dStr);
            checkContains("DeepLearning", "testSet",   "800.0",               dStr);
            checkContains("DeepLearning", "trainset",  "600.0",               dStr);
            checkContains("DeepLearning", "learnType", "deepLearning",        dStr);
            checkContains("DeepLearning", "problem",   "imageClassification", dStr);
            checkContains("DeepLearning", "algorithm", "backpropagation",     dStr);
            System.out.println("DeepLearning toString: PASS");

            // ================================================================
            // GenerativeAI
            // ================================================================
            System.out.println("\n--- Testing GenerativeAI ---");
            GenerativeAI t = new GenerativeAI();
            t.setInput("promptInput");
            t.setModel("GPT-4");
            t.setDataset("syntheticDataset");       // inherited from DeepLearning
            t.setGenerativeModels("diffusionModel");
            t.setLearnPatterns("patternMimicry");
            t.setGenTrainset(1000.0);               // GenerativeAI-specific
            t.setTrainset(500.0);                   // inherited DeepLearning trainset
            checkStr   ("GenerativeAI", "getInput",            "promptInput",     t.getInput());
            checkStr   ("GenerativeAI", "getModel",            "GPT-4",           t.getModel());
            checkStr   ("GenerativeAI", "getDataset",          "syntheticDataset",t.getDataset());
            checkStr   ("GenerativeAI", "getGenerativeModels", "diffusionModel",  t.getGenerativeModels());
            checkStr   ("GenerativeAI", "getLearnPatterns",    "patternMimicry",  t.getLearnPatterns());
            checkDouble("GenerativeAI", "getGenTrainset",      1000.0,            t.getGenTrainset());
            checkDouble("GenerativeAI", "getTrainset",         500.0,             t.getTrainset());
            System.out.println("GenerativeAI Setters/Getters: PASS");

            // Constructor: (dataset, generativeModels, learnPatterns, genTrainset)
            GenerativeAI t2 = new GenerativeAI("syntheticDataset", "diffusionModel", "patternMimicry", 1000.0);
            checkStr   ("GenerativeAI", "getDataset          [param 1]", "syntheticDataset", t2.getDataset());
            checkStr   ("GenerativeAI", "getGenerativeModels [param 2]", "diffusionModel",   t2.getGenerativeModels());
            checkStr   ("GenerativeAI", "getLearnPatterns    [param 3]", "patternMimicry",   t2.getLearnPatterns());
            checkDouble("GenerativeAI", "getGenTrainset      [param 4]", 1000.0,             t2.getGenTrainset());
            System.out.println("GenerativeAI Args Constructor: PASS");

            String tStr = t2.toString();
            checkPrefix("GenerativeAI", 'T', tStr);
            checkContains("GenerativeAI", "dataset",          "syntheticDataset", tStr);
            checkContains("GenerativeAI", "generativeModels", "diffusionModel",   tStr);
            checkContains("GenerativeAI", "learnPatterns",    "patternMimicry",   tStr);
            checkContains("GenerativeAI", "genTrainset",      "1000.0",           tStr);
            System.out.println("GenerativeAI toString: PASS");

            // ================================================================
            // Inheritance Chain
            // ================================================================
            System.out.println("\n--- Inheritance Chain ---");
            if (!(n  instanceof AI))              throw new Exception("NarrowAI must extend AI.");
            if (!(g  instanceof AI))              throw new Exception("GeneralAI must extend AI.");
            if (!(s  instanceof AI))              throw new Exception("SymbolicAI must extend AI.");
            if (!(ml instanceof AI))              throw new Exception("MachineLearning must extend AI.");
            if (!(d  instanceof MachineLearning)) throw new Exception("DeepLearning must extend MachineLearning.");
            if (!(d  instanceof AI))              throw new Exception("DeepLearning must descend from AI.");
            if (!(t  instanceof DeepLearning))    throw new Exception("GenerativeAI must extend DeepLearning.");
            if (!(t  instanceof MachineLearning)) throw new Exception("GenerativeAI must descend from MachineLearning.");
            if (!(t  instanceof AI))              throw new Exception("GenerativeAI must descend from AI.");
            System.out.println("Inheritance Chain: PASS");

            // ================================================================
            // Field Ownership Checks
            // ================================================================
            System.out.println("\n--- Field Ownership Checks ---");
            checkFieldDeclaredIn(n,  "input", AI.class);
            checkFieldDeclaredIn(n,  "model", AI.class);
            checkFieldDeclaredIn(g,  "input", AI.class);
            checkFieldDeclaredIn(g,  "model", AI.class);
            checkFieldDeclaredIn(s,  "input", AI.class);
            checkFieldDeclaredIn(s,  "model", AI.class);
            checkFieldDeclaredIn(ml, "input", AI.class);
            checkFieldDeclaredIn(ml, "model", AI.class);
            checkFieldDeclaredIn(d,  "input", AI.class);
            checkFieldDeclaredIn(d,  "model", AI.class);
            checkFieldDeclaredIn(t,  "input", AI.class);
            checkFieldDeclaredIn(t,  "model", AI.class);

            checkFieldDeclaredIn(d, "learnType", MachineLearning.class);
            checkFieldDeclaredIn(d, "problem",   MachineLearning.class);
            checkFieldDeclaredIn(d, "algorithm", MachineLearning.class);
            checkFieldDeclaredIn(t, "learnType", MachineLearning.class);
            checkFieldDeclaredIn(t, "problem",   MachineLearning.class);
            checkFieldDeclaredIn(t, "algorithm", MachineLearning.class);

            checkFieldDeclaredIn(t, "dataset",  DeepLearning.class);
            checkFieldDeclaredIn(t, "nnModel",  DeepLearning.class);
            checkFieldDeclaredIn(t, "layers",   DeepLearning.class);
            checkFieldDeclaredIn(t, "testSet",  DeepLearning.class);
            checkFieldDeclaredIn(t, "trainset", DeepLearning.class);
            System.out.println("Field Ownership Checks: PASS");

            System.out.println("\nLEVEL 2 COMPLETE: 50/50");

        } catch (Exception e) {
            System.err.println("LEVEL 2 FAILED: " + e.getMessage());
            System.exit(1);
        }
    }
}
