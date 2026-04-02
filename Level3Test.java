import java.io.*;
import java.util.ArrayList;

public class Level3Test {
    public static void main(String[] args) {
        String initialInput = "platform_initial.txt";
        String savedOutput  = "platform_saved.txt";

        try {
            System.out.println("Starting Level 3: Platform ArrayList Methods & File I/O...");

            // ================================================================
            // Build test objects matching the input file values
            // ================================================================
            NarrowAI n = new NarrowAI("faceRecognition", "imageDetectedEvent", "confidenceThreshold", "securityContext");
            n.setInput("textPrompt");
            n.setModel("GPT-4");

            GeneralAI g = new GeneralAI("3DObjectDetection", "speechToText", "logicalDeduction", "autonomousDriving", "musicalComposition");
            g.setInput("textPrompt");
            g.setModel("GPT-4");

            SymbolicAI sym = new SymbolicAI("prologInference", "ontologyNetwork", "forwardChaining", "meansEndsAnalysis");
            sym.setInput("textPrompt");
            sym.setModel("GPT-4");

            MachineLearning ml = new MachineLearning("unsupervisedLearning", "clusteringProblem", "kMeansAlgorithm");
            ml.setInput("textPrompt");
            ml.setModel("GPT-4");

            DeepLearning d = new DeepLearning("imageNetDataset", "transformerModel", 12, 8000.0, 40000.0);
            d.setInput("textPrompt");
            d.setModel("GPT-4");
            d.setLearnType("unsupervisedLearning");
            d.setProblem("clusteringProblem");
            d.setAlgorithm("kMeansAlgorithm");

            // GenerativeAI: constructor takes (dataset, generativeModels, learnPatterns, genTrainset)
            // Inherited DeepLearning fields are set separately via setters
            GenerativeAI t = new GenerativeAI("syntheticTextDataset", "largeLangModel", "contextualPatternLearning", 50000.0);
            t.setInput("textPrompt");
            t.setModel("GPT-4");
            t.setLearnType("unsupervisedLearning");
            t.setProblem("clusteringProblem");
            t.setAlgorithm("kMeansAlgorithm");
            t.setNnModel("transformerModel");
            t.setLayers(12);
            t.setTestSet(8000.0);
            t.setTrainset(40000.0);  // inherited DeepLearning trainset

            // ================================================================
            // PART 1 - ArrayList Methods
            // ================================================================
            System.out.println("\n--- Testing ArrayList Methods ---");

            Platform p = new Platform("Aggie AI Agent Platform");
            p.addAI(n);
            p.addAI(g);
            p.addAI(sym);
            p.addAI(ml);
            p.addAI(d);
            p.addAI(t);
            System.out.println("addAI (6 objects): PASS");

            if (p.getAISize() != 6)
                throw new Exception("getAISize() wrong. Expected 6, got " + p.getAISize());
            System.out.println("getAISize: PASS");

            if (p.getAI(0) != n)
                throw new Exception("getAI(0) returned wrong object.");
            if (p.getAI(5) != t)
                throw new Exception("getAI(5) returned wrong object.");
            System.out.println("getAI: PASS");

            NarrowAI replacement = new NarrowAI("replacedTask", "replacedEvent", "replacedParam", "replacedContext");
            replacement.setInput("replacedInput");
            replacement.setModel("replacedModel");
            p.setAI(2, replacement);
            if (p.getAI(2) != replacement)
                throw new Exception("setAI(2) did not store the new object.");
            if (p.getAISize() != 6)
                throw new Exception("setAI() must keep size at 6, got " + p.getAISize());
            System.out.println("setAI: PASS");

            AI removed = p.removeAI(2);
            if (removed != replacement)
                throw new Exception("removeAI(2) returned wrong object.");
            if (p.getAISize() != 5)
                throw new Exception("removeAI() must shrink size to 5, got " + p.getAISize());
            p.addAI(sym);
            System.out.println("removeAI: PASS");

            ArrayList<AI> narrowList = p.getAIList(NarrowAI.class);
            if (narrowList == null || narrowList.size() != 1)
                throw new Exception("getAIList(NarrowAI.class) wrong. Expected 1, got " +
                    (narrowList == null ? "null" : narrowList.size()));
            ArrayList<AI> mlList = p.getAIList(MachineLearning.class);
            if (mlList == null || mlList.size() != 1)
                throw new Exception("getAIList(MachineLearning.class) wrong. Expected 1, got " +
                    (mlList == null ? "null" : mlList.size()));
            ArrayList<AI> deepList = p.getAIList(DeepLearning.class);
            if (deepList == null || deepList.size() != 1)
                throw new Exception("getAIList(DeepLearning.class) wrong. Expected 1, got " +
                    (deepList == null ? "null" : deepList.size()));
            ArrayList<AI> genList = p.getAIList(GenerativeAI.class);
            if (genList == null || genList.size() != 1)
                throw new Exception("getAIList(GenerativeAI.class) wrong. Expected 1, got " +
                    (genList == null ? "null" : genList.size()));
            System.out.println("getAIList: PASS");
            System.out.println("\nAll 6 ArrayList Methods: PASS");

            // ================================================================
            // PART 2 - File I/O
            // Write the corrected input file directly to match the exact format
            // ================================================================
            System.out.println("\n--- Testing File I/O ---");

            try (PrintWriter pw = new PrintWriter(new FileWriter(initialInput))) {
                pw.println("Aggie AI Agent Platform");
                pw.println("N");
                pw.println("textPrompt");
                pw.println("GPT-4");
                pw.println("faceRecognition");
                pw.println("imageDetectedEvent");
                pw.println("confidenceThreshold");
                pw.println("securityContext");
                pw.println("G");
                pw.println("textPrompt");
                pw.println("GPT-4");
                pw.println("3DObjectDetection");
                pw.println("speechToText");
                pw.println("logicalDeduction");
                pw.println("autonomousDriving");
                pw.println("musicalComposition");
                pw.println("S");
                pw.println("textPrompt");
                pw.println("GPT-4");
                pw.println("prologInference");
                pw.println("ontologyNetwork");
                pw.println("forwardChaining");
                pw.println("meansEndsAnalysis");
                pw.println("M");
                pw.println("textPrompt");
                pw.println("GPT-4");
                pw.println("unsupervisedLearning");
                pw.println("clusteringProblem");
                pw.println("kMeansAlgorithm");
                pw.println("D");
                pw.println("textPrompt");
                pw.println("GPT-4");
                pw.println("unsupervisedLearning");
                pw.println("clusteringProblem");
                pw.println("kMeansAlgorithm");
                pw.println("imageNetDataset");
                pw.println("transformerModel");
                pw.println("12");
                pw.println("8000.0");
                pw.println("40000.0");
                pw.println("T");
                pw.println("textPrompt");
                pw.println("GPT-4");
                pw.println("unsupervisedLearning");
                pw.println("clusteringProblem");
                pw.println("kMeansAlgorithm");
                pw.println("imageNetDataset");      // inherited DL dataset
                pw.println("transformerModel");     // inherited DL nnModel
                pw.println("12");                   // inherited DL layers
                pw.println("8000.0");               // inherited DL testSet
                pw.println("40000.0");              // inherited DL trainset
                pw.println("syntheticTextDataset"); // GenAI dataset
                pw.println("largeLangModel");       // GenAI generativeModels
                pw.println("contextualPatternLearning"); // GenAI learnPatterns
                pw.println("50000.0");              // GenAI genTrainset
            }
            System.out.println("Input File Written: PASS");

            Platform loaded = new Platform();
            loaded.loadPlatform(initialInput);

            if (loaded.getAISize() != 6)
                throw new Exception("loadPlatform() read wrong number of objects. Expected 6, got " + loaded.getAISize());
            System.out.println("loadPlatform (6 objects): PASS");

            if (!loaded.getAI(0).getInput().equals("textPrompt"))
                throw new Exception("loadPlatform() AI[0].input wrong. Expected \"textPrompt\", got \"" + loaded.getAI(0).getInput() + "\"");
            if (!loaded.getAI(0).getModel().equals("GPT-4"))
                throw new Exception("loadPlatform() AI[0].model wrong. Expected \"GPT-4\", got \"" + loaded.getAI(0).getModel() + "\"");
            System.out.println("loadPlatform Field Values: PASS");

            // Verify GenerativeAI loaded correctly
            GenerativeAI loadedGen = (GenerativeAI) loaded.getAI(5);
            if (!loadedGen.getDataset().equals("imageNetDataset"))
                throw new Exception("loadPlatform() GenerativeAI inherited dataset wrong. Expected \"imageNetDataset\", got \"" + loadedGen.getDataset() + "\"");
            if (loadedGen.getTrainset() != 40000.0)
                throw new Exception("loadPlatform() GenerativeAI inherited trainset wrong. Expected 40000.0, got " + loadedGen.getTrainset());
            if (!loadedGen.getGenerativeModels().equals("largeLangModel"))
                throw new Exception("loadPlatform() GenerativeAI generativeModels wrong. Expected \"largeLangModel\", got \"" + loadedGen.getGenerativeModels() + "\"");
            if (loadedGen.getGenTrainset() != 50000.0)
                throw new Exception("loadPlatform() GenerativeAI genTrainset wrong. Expected 50000.0, got " + loadedGen.getGenTrainset());
            System.out.println("loadPlatform GenerativeAI Fields: PASS");

            // Add a new object
            NarrowAI extra = new NarrowAI("speechRecognition", "voiceEvent", "sensitivity", "englishContext");
            extra.setInput("audioInput");
            extra.setModel("Whisper");
            loaded.addAI(extra);

            if (loaded.getAISize() != 7)
                throw new Exception("addAI() after load gave wrong size. Expected 7, got " + loaded.getAISize());
            System.out.println("In-Memory Modification After Load: PASS");

            loaded.savePlatform(savedOutput);
            File savedFile = new File(savedOutput);
            if (!savedFile.exists())
                throw new Exception("savePlatform() did not create the output file.");
            if (savedFile.length() == 0)
                throw new Exception("savePlatform() created an empty file.");
            System.out.println("savePlatform (file created): PASS");

            Platform reloaded = new Platform();
            reloaded.loadPlatform(savedOutput);

            if (reloaded.getAISize() != 7)
                throw new Exception("Round-trip failed: expected 7 objects, got " + reloaded.getAISize());

            String original  = loaded.toString();
            String roundTrip = reloaded.toString();
            if (!original.equals(roundTrip))
                throw new Exception("Round-trip consistency FAILED: toString() differs after save/reload.\n" +
                    "--- ORIGINAL ---\n" + original +
                    "\n--- RELOADED ---\n" + roundTrip);
            System.out.println("Round-Trip Consistency: PASS");

            if (!roundTrip.contains("speechRecognition"))
                throw new Exception("Newly added NarrowAI did not survive round-trip. Missing \"speechRecognition\".");
            if (!roundTrip.contains("Whisper"))
                throw new Exception("Newly added NarrowAI model did not survive round-trip. Missing \"Whisper\".");
            System.out.println("New Object Survived Round-Trip: PASS");

            System.out.println("\nLEVEL 3 COMPLETE: 75/75");

        } catch (Exception e) {
            System.err.println("LEVEL 3 FAILED: " + e.getMessage());
            System.exit(1);
        }
    }
}
