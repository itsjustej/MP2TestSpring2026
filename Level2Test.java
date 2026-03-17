public class Level2Test {

    static void checkStr(String className, String field, String expected, String actual) throws Exception {
        if (!expected.equals(actual))
            throw new Exception(className + ": " + field + "() returned wrong value.\n" +
                "  Expected : \"" + expected + "\"\n" +
                "  Actual   : \"" + actual + "\"");
    }

    static void checkInt(String className, String field, int expected, int actual) throws Exception {
        if (expected != actual)
            throw new Exception(className + ": " + field + "() returned wrong value.\n" +
                "  Expected : " + expected + "\n" +
                "  Actual   : " + actual);
    }

    static void checkDouble(String className, String field, double expected, double actual) throws Exception {
        if (Double.compare(expected, actual) != 0)
            throw new Exception(className + ": " + field + "() returned wrong value.\n" +
                "  Expected : " + expected + "\n" +
                "  Actual   : " + actual);
    }

    static void checkToStringPrefix(String className, char letter, String actual) throws Exception {
        String expected = letter + System.lineSeparator();
        if (!actual.startsWith(expected))
            throw new Exception(className + ".toString() has wrong prefix.\n" +
                "  Expected to start with : '" + letter + "' + system line separator\n" +
                "  Actual first chars     : \"" + actual.substring(0, Math.min(actual.length(), 20)).replace("\n","\\n").replace("\r","\\r") + "\"");
    }

    static void checkToStringContains(String className, String fieldName, String value, String actual) throws Exception {
        if (!actual.contains(value))
            throw new Exception(className + ".toString() is missing the value for '" + fieldName + "'.\n" +
                "  Expected to contain : \"" + value + "\"\n" +
                "  Full toString output:\n" + actual);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting Level 2: AI Class Hierarchy Validation...");
            String sep = System.lineSeparator();

            // ================================================================
            // AI CLASS - tested via NarrowAI since AI is abstract
            // ================================================================
            System.out.println("\n--- Testing AI (via NarrowAI) ---");

            NarrowAI ai = new NarrowAI();
            System.out.println("AI No-Arg Constructor: PASS");

            ai.setInput("humanInput");
            ai.setModel("GPT-4");
            checkStr("AI", "getInput", "humanInput", ai.getInput());
            checkStr("AI", "getModel", "GPT-4",      ai.getModel());
            System.out.println("AI Setters/Getters: PASS");

            NarrowAI ai2 = new NarrowAI("translation", "languageEvent", "temperature", "englishContext");
            ai2.setInput("textInput");
            ai2.setModel("BERT");
            checkStr("AI", "getInput [args constructor param 1]", "textInput", ai2.getInput());
            checkStr("AI", "getModel [args constructor param 2]", "BERT",      ai2.getModel());
            System.out.println("AI Args Constructor: PASS");

            NarrowAI aiStr_obj = new NarrowAI();
            aiStr_obj.setInput("textInput");
            aiStr_obj.setModel("BERT");
            String aiStr = aiStr_obj.toString();
            checkToStringPrefix("AI", 'N', aiStr);
            checkToStringContains("AI", "input", "textInput", aiStr);
            checkToStringContains("AI", "model", "BERT",      aiStr);
            System.out.println("AI toString: PASS");

            // ================================================================
            // NarrowAI CLASS
            // ================================================================
            System.out.println("\n--- Testing NarrowAI ---");

            NarrowAI n = new NarrowAI();
            System.out.println("NarrowAI No-Arg Constructor: PASS");

            n.setInput("queryInput");
            n.setModel("BERT");
            n.setTask("translation");
            n.setEvent("languageEvent");
            n.setParameter("temperature");
            n.setContext("englishContext");
            checkStr("NarrowAI", "getInput",     "queryInput",    n.getInput());
            checkStr("NarrowAI", "getModel",     "BERT",          n.getModel());
            checkStr("NarrowAI", "getTask",      "translation",   n.getTask());
            checkStr("NarrowAI", "getEvent",     "languageEvent", n.getEvent());
            checkStr("NarrowAI", "getParameter", "temperature",   n.getParameter());
            checkStr("NarrowAI", "getContext",   "englishContext", n.getContext());
            System.out.println("NarrowAI Setters/Getters: PASS");

            NarrowAI n2 = new NarrowAI("translation", "languageEvent", "temperature", "englishContext");
            n2.setInput("queryInput");
            n2.setModel("BERT");
            checkStr("NarrowAI", "getTask      [args constructor param 1]", "translation",   n2.getTask());
            checkStr("NarrowAI", "getEvent     [args constructor param 2]", "languageEvent", n2.getEvent());
            checkStr("NarrowAI", "getParameter [args constructor param 3]", "temperature",   n2.getParameter());
            checkStr("NarrowAI", "getContext   [args constructor param 4]", "englishContext", n2.getContext());
            System.out.println("NarrowAI Args Constructor: PASS");

            String nStr = n2.toString();
            checkToStringPrefix("NarrowAI", 'N', nStr);
            checkToStringContains("NarrowAI", "input (inherited)",  "queryInput",    nStr);
            checkToStringContains("NarrowAI", "model (inherited)",  "BERT",          nStr);
            checkToStringContains("NarrowAI", "task",               "translation",   nStr);
            checkToStringContains("NarrowAI", "event",              "languageEvent", nStr);
            checkToStringContains("NarrowAI", "parameter",          "temperature",   nStr);
            checkToStringContains("NarrowAI", "context",            "englishContext", nStr);
            System.out.println("NarrowAI toString: PASS");

            // ================================================================
            // GeneralAI CLASS
            // ================================================================
            System.out.println("\n--- Testing GeneralAI ---");

            GeneralAI g = new GeneralAI();
            System.out.println("GeneralAI No-Arg Constructor: PASS");

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
            checkStr("GeneralAI", "getVisualPerception [args constructor param 1]", "imagePerception", g2.getVisualPerception());
            checkStr("GeneralAI", "getAudioPerception  [args constructor param 2]", "speechAudio",    g2.getAudioPerception());
            checkStr("GeneralAI", "getProblemSolving   [args constructor param 3]", "logicSolving",   g2.getProblemSolving());
            checkStr("GeneralAI", "getNavigation       [args constructor param 4]", "mapNavigation",  g2.getNavigation());
            checkStr("GeneralAI", "getCreativity       [args constructor param 5]", "artCreativity",  g2.getCreativity());
            System.out.println("GeneralAI Args Constructor: PASS");

            String gStr = g2.toString();
            checkToStringPrefix("GeneralAI", 'G', gStr);
            checkToStringContains("GeneralAI", "visualPerception", "imagePerception", gStr);
            checkToStringContains("GeneralAI", "audioPerception",  "speechAudio",    gStr);
            checkToStringContains("GeneralAI", "problemSolving",   "logicSolving",   gStr);
            checkToStringContains("GeneralAI", "navigation",       "mapNavigation",  gStr);
            checkToStringContains("GeneralAI", "creativity",       "artCreativity",  gStr);
            System.out.println("GeneralAI toString: PASS");

            // ================================================================
            // SymbolicAI CLASS
            // ================================================================
            System.out.println("\n--- Testing SymbolicAI ---");

            SymbolicAI s = new SymbolicAI();
            System.out.println("SymbolicAI No-Arg Constructor: PASS");

            s.setInput("ruleInput");
            s.setModel("Expert-1");
            s.setLogicProgram("prologLogic");
            s.setSemanticNetwork("semanticWeb");
            s.setReasoning("deductiveReasoning");
            s.setProblemSolMethod("backtracking");
            checkStr("SymbolicAI", "getInput",           "ruleInput",          s.getInput());
            checkStr("SymbolicAI", "getModel",           "Expert-1",           s.getModel());
            checkStr("SymbolicAI", "getLogicProgram",    "prologLogic",        s.getLogicProgram());
            checkStr("SymbolicAI", "getSemanticNetwork", "semanticWeb",        s.getSemanticNetwork());
            checkStr("SymbolicAI", "getReasoning",       "deductiveReasoning", s.getReasoning());
            checkStr("SymbolicAI", "getProblemSolMethod","backtracking",       s.getProblemSolMethod());
            System.out.println("SymbolicAI Setters/Getters: PASS");

            SymbolicAI s2 = new SymbolicAI("prologLogic", "semanticWeb", "deductiveReasoning", "backtracking");
            checkStr("SymbolicAI", "getLogicProgram    [args constructor param 1]", "prologLogic",        s2.getLogicProgram());
            checkStr("SymbolicAI", "getSemanticNetwork [args constructor param 2]", "semanticWeb",        s2.getSemanticNetwork());
            checkStr("SymbolicAI", "getReasoning       [args constructor param 3]", "deductiveReasoning", s2.getReasoning());
            checkStr("SymbolicAI", "getProblemSolMethod[args constructor param 4]", "backtracking",       s2.getProblemSolMethod());
            System.out.println("SymbolicAI Args Constructor: PASS");

            String sStr = s2.toString();
            checkToStringPrefix("SymbolicAI", 'S', sStr);
            checkToStringContains("SymbolicAI", "logicProgram",    "prologLogic",        sStr);
            checkToStringContains("SymbolicAI", "semanticNetwork", "semanticWeb",        sStr);
            checkToStringContains("SymbolicAI", "reasoning",       "deductiveReasoning", sStr);
            checkToStringContains("SymbolicAI", "problemSolMethod","backtracking",       sStr);
            System.out.println("SymbolicAI toString: PASS");

            // ================================================================
            // MachineLearning CLASS
            // ================================================================
            System.out.println("\n--- Testing MachineLearning ---");

            MachineLearning ml = new MachineLearning();
            System.out.println("MachineLearning No-Arg Constructor: PASS");

            ml.setInput("dataInput");
            ml.setModel("RandomForest");
            ml.setLearnType("supervisedLearning");
            ml.setProblem("classificationProblem");
            ml.setAlgorithm("decisionTree");
            checkStr("MachineLearning", "getInput",    "dataInput",             ml.getInput());
            checkStr("MachineLearning", "getModel",    "RandomForest",          ml.getModel());
            checkStr("MachineLearning", "getLearnType","supervisedLearning",    ml.getLearnType());
            checkStr("MachineLearning", "getProblem",  "classificationProblem", ml.getProblem());
            checkStr("MachineLearning", "getAlgorithm","decisionTree",          ml.getAlgorithm());
            System.out.println("MachineLearning Setters/Getters: PASS");

            MachineLearning ml2 = new MachineLearning("supervisedLearning", "classificationProblem", "decisionTree");
            checkStr("MachineLearning", "getLearnType [args constructor param 1]", "supervisedLearning",    ml2.getLearnType());
            checkStr("MachineLearning", "getProblem   [args constructor param 2]", "classificationProblem", ml2.getProblem());
            checkStr("MachineLearning", "getAlgorithm [args constructor param 3]", "decisionTree",          ml2.getAlgorithm());
            System.out.println("MachineLearning Args Constructor: PASS");

            String mStr = ml2.toString();
            checkToStringPrefix("MachineLearning", 'M', mStr);
            checkToStringContains("MachineLearning", "learnType", "supervisedLearning",    mStr);
            checkToStringContains("MachineLearning", "problem",   "classificationProblem", mStr);
            checkToStringContains("MachineLearning", "algorithm", "decisionTree",          mStr);
            System.out.println("MachineLearning toString: PASS");

            // ================================================================
            // DeepLearning CLASS
            // ================================================================
            System.out.println("\n--- Testing DeepLearning ---");

            DeepLearning d = new DeepLearning();
            System.out.println("DeepLearning No-Arg Constructor: PASS");

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
            checkStr   ("DeepLearning", "getInput",    "imageInput",          d.getInput());
            checkStr   ("DeepLearning", "getModel",    "ResNet",              d.getModel());
            checkStr   ("DeepLearning", "getLearnType","deepLearning",        d.getLearnType());
            checkStr   ("DeepLearning", "getProblem",  "imageClassification", d.getProblem());
            checkStr   ("DeepLearning", "getAlgorithm","backpropagation",     d.getAlgorithm());
            checkStr   ("DeepLearning", "getDataset",  "imagenetDataset",     d.getDataset());
            checkStr   ("DeepLearning", "getNnModel",  "cnnModel",            d.getNnModel());
            checkInt   ("DeepLearning", "getLayers",   5,     d.getLayers());
            checkDouble("DeepLearning", "getTestSet",  800.0, d.getTestSet());
            checkDouble("DeepLearning", "getTrainset", 600.0, d.getTrainset());
            System.out.println("DeepLearning Setters/Getters: PASS");

            DeepLearning d2 = new DeepLearning("imagenetDataset", "cnnModel", 5, 800.0, 600.0);
            d2.setLearnType("deepLearning");
            d2.setProblem("imageClassification");
            d2.setAlgorithm("backpropagation");
            checkStr   ("DeepLearning", "getDataset  [args constructor param 1]", "imagenetDataset", d2.getDataset());
            checkStr   ("DeepLearning", "getNnModel  [args constructor param 2]", "cnnModel",        d2.getNnModel());
            checkInt   ("DeepLearning", "getLayers   [args constructor param 3]", 5,     d2.getLayers());
            checkDouble("DeepLearning", "getTestSet  [args constructor param 4]", 800.0, d2.getTestSet());
            checkDouble("DeepLearning", "getTrainset [args constructor param 5]", 600.0, d2.getTrainset());
            System.out.println("DeepLearning Args Constructor: PASS");

            String dStr = d2.toString();
            checkToStringPrefix("DeepLearning", 'D', dStr);
            checkToStringContains("DeepLearning", "dataset",               "imagenetDataset",    dStr);
            checkToStringContains("DeepLearning", "nnModel",               "cnnModel",           dStr);
            checkToStringContains("DeepLearning", "layers",                "5",                  dStr);
            checkToStringContains("DeepLearning", "testSet",               "800.0",              dStr);
            checkToStringContains("DeepLearning", "trainset",              "600.0",              dStr);
            checkToStringContains("DeepLearning", "learnType (inherited)", "deepLearning",       dStr);
            checkToStringContains("DeepLearning", "problem (inherited)",   "imageClassification",dStr);
            checkToStringContains("DeepLearning", "algorithm (inherited)", "backpropagation",    dStr);
            System.out.println("DeepLearning toString: PASS");

            // ================================================================
            // GenerativeAI CLASS
            // ================================================================
            System.out.println("\n--- Testing GenerativeAI ---");

            GenerativeAI t = new GenerativeAI();
            System.out.println("GenerativeAI No-Arg Constructor: PASS");

            t.setInput("promptInput");
            t.setModel("GPT-4");
            t.setDataset("syntheticDataset");
            t.setGenerativeModels("diffusionModel");
            t.setLearnPatterns("patternMimicry");
            t.setTrainset(1000);
            checkStr("GenerativeAI", "getInput",            "promptInput",     t.getInput());
            checkStr("GenerativeAI", "getModel",            "GPT-4",           t.getModel());
            checkStr("GenerativeAI", "getDataset",          "syntheticDataset",t.getDataset());
            checkStr("GenerativeAI", "getGenerativeModels", "diffusionModel",  t.getGenerativeModels());
            checkStr("GenerativeAI", "getLearnPatterns",    "patternMimicry",  t.getLearnPatterns());
            checkInt("GenerativeAI", "getTrainset",         1000,              t.getTrainset());
            System.out.println("GenerativeAI Setters/Getters: PASS");

            GenerativeAI t2 = new GenerativeAI("syntheticDataset", "diffusionModel", "patternMimicry", 1000);
            checkStr("GenerativeAI", "getDataset          [args constructor param 1]", "syntheticDataset", t2.getDataset());
            checkStr("GenerativeAI", "getGenerativeModels [args constructor param 2]", "diffusionModel",   t2.getGenerativeModels());
            checkStr("GenerativeAI", "getLearnPatterns    [args constructor param 3]", "patternMimicry",   t2.getLearnPatterns());
            checkInt("GenerativeAI", "getTrainset         [args constructor param 4]", 1000,               t2.getTrainset());
            System.out.println("GenerativeAI Args Constructor: PASS");

            String tStr = t2.toString();
            checkToStringPrefix("GenerativeAI", 'T', tStr);
            checkToStringContains("GenerativeAI", "dataset",          "syntheticDataset", tStr);
            checkToStringContains("GenerativeAI", "generativeModels", "diffusionModel",   tStr);
            checkToStringContains("GenerativeAI", "learnPatterns",    "patternMimicry",   tStr);
            checkToStringContains("GenerativeAI", "trainset",         "1000",             tStr);
            System.out.println("GenerativeAI toString: PASS");

            // ================================================================
            // INHERITANCE CHAIN
            // ================================================================
            System.out.println("\n--- Inheritance Chain ---");
            if (!(n  instanceof AI))              throw new Exception("NarrowAI must extend AI.");
            if (!(g  instanceof AI))              throw new Exception("GeneralAI must extend AI.");
            if (!(s  instanceof AI))              throw new Exception("SymbolicAI must extend AI.");
            if (!(ml instanceof AI))              throw new Exception("MachineLearning must extend AI.");
            if (!(d  instanceof MachineLearning)) throw new Exception("DeepLearning must extend MachineLearning.");
            if (!(d  instanceof AI))              throw new Exception("DeepLearning must be a descendant of AI.");
            if (!(t  instanceof DeepLearning))    throw new Exception("GenerativeAI must extend DeepLearning.");
            if (!(t  instanceof MachineLearning)) throw new Exception("GenerativeAI must be a descendant of MachineLearning.");
            if (!(t  instanceof AI))              throw new Exception("GenerativeAI must be a descendant of AI.");
            System.out.println("Inheritance Chain: PASS");

            System.out.println("\nLEVEL 2 COMPLETE: 50/50");

        } catch (Exception e) {
            System.err.println("LEVEL 2 FAILED: " + e.getMessage());
            System.exit(1);
        }
    }
}
