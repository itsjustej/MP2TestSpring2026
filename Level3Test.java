import java.io.*;
import java.util.ArrayList;

public class Level3Test {
    public static void main(String[] args) {
        String initialInput = "platform_initial.txt";
        String savedOutput  = "platform_saved.txt";

        try {
            System.out.println("Starting Level 3: Platform ArrayList Methods & File I/O...");

            // ================================================================
            // PART 1 - ALL 6 ArrayList METHODS
            // ================================================================
            System.out.println("\n--- Testing ArrayList Methods ---");

            Platform p = new Platform("Aggie AI Agent Platform");

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

            GenerativeAI t = new GenerativeAI("syntheticTextDataset", "largeLangModel", "contextualPatternLearning", 50000.0);
            t.setInput("textPrompt");
            t.setModel("GPT-4");
            t.setLearnType("unsupervisedLearning");
            t.setProblem("clusteringProblem");
            t.setAlgorithm("kMeansAlgorithm");

            // --- 1. addAI ---
            p.addAI(n);
            p.addAI(g);
            p.addAI(sym);
            p.addAI(ml);
            p.addAI(d);
            p.addAI(t);
            System.out.println("addAI (6 objects): PASS");

            // --- 2. getAISize ---
            int size = p.getAISize();
            if (size != 6)
                throw new Exception("getAISize() returned wrong value after adding 6 objects.\n" +
                    "  Expected : 6\n" +
                    "  Actual   : " + size);
            System.out.println("getAISize: PASS");

            // --- 3. getAI ---
            AI gotFirst = p.getAI(0);
            if (gotFirst != n)
                throw new Exception("getAI(0) returned wrong object.\n" +
                    "  Expected : the NarrowAI object added first\n" +
                    "  Actual   : " + (gotFirst == null ? "null" : gotFirst.toString().replace("\n"," | ")));

            AI gotLast = p.getAI(5);
            if (gotLast != t)
                throw new Exception("getAI(5) returned wrong object.\n" +
                    "  Expected : the GenerativeAI added last\n" +
                    "  Actual   : " + (gotLast == null ? "null" : gotLast.toString().replace("\n"," | ")));
            System.out.println("getAI: PASS");

            // --- 4. setAI ---
            NarrowAI replacement = new NarrowAI("replacedTask", "replacedEvent", "replacedParam", "replacedContext");
            replacement.setInput("replacedInput");
            replacement.setModel("replacedModel");
            p.setAI(2, replacement);

            AI afterSet = p.getAI(2);
            if (afterSet != replacement)
                throw new Exception("setAI(2, replacement) did not store the new object.\n" +
                    "  Expected : replacement NarrowAI\n" +
                    "  Actual   : " + (afterSet == null ? "null" : afterSet.toString().replace("\n"," | ")));

            int sizeAfterSet = p.getAISize();
            if (sizeAfterSet != 6)
                throw new Exception("setAI() must replace in place - size must stay 6.\n" +
                    "  Expected size : 6\n" +
                    "  Actual size   : " + sizeAfterSet);
            System.out.println("setAI: PASS");

            // --- 5. removeAI ---
            AI removed = p.removeAI(2);
            if (removed != replacement)
                throw new Exception("removeAI(2) returned wrong object.\n" +
                    "  Actual   : " + (removed == null ? "null" : removed.toString().replace("\n"," | ")));

            int sizeAfterRemove = p.getAISize();
            if (sizeAfterRemove != 5)
                throw new Exception("removeAI() must shrink list by 1.\n" +
                    "  Expected size : 5\n" +
                    "  Actual size   : " + sizeAfterRemove);

            p.addAI(sym);
            System.out.println("removeAI: PASS");

            // --- 6. getAIList ---
            ArrayList<AI> narrowList = p.getAIList(NarrowAI.class);
            if (narrowList == null || narrowList.size() != 1)
                throw new Exception("getAIList(NarrowAI.class) wrong count.\n" +
                    "  Expected : 1\n" +
                    "  Actual   : " + (narrowList == null ? "null" : narrowList.size()));

            ArrayList<AI> mlList = p.getAIList(MachineLearning.class);
            if (mlList == null || mlList.size() != 1)
                throw new Exception("getAIList(MachineLearning.class) wrong count.\n" +
                    "  Expected : 1 (exact match only, not subclasses)\n" +
                    "  Actual   : " + (mlList == null ? "null" : mlList.size()));

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

            System.out.println("getAIList: PASS");
            System.out.println("\nAll 6 ArrayList Methods: PASS");

            // ================================================================
            // PART 2 - FILE I/O ROUND-TRIP
            // Use toString() of each object to write the file so it is
            // guaranteed to match exactly what loadPlatform expects.
            // ================================================================
            System.out.println("\n--- Testing File I/O ---");

            Platform source = new Platform("Aggie AI Agent Platform");
            source.addAI(n);
            source.addAI(g);
            source.addAI(sym);
            source.addAI(ml);
            source.addAI(d);
            source.addAI(t);
            source.savePlatform(initialInput);
            System.out.println("Input File Written: PASS");

            // Load
            Platform loaded = new Platform();
            loaded.loadPlatform(initialInput);

            int loadedSize = loaded.getAISize();
            if (loadedSize != 6)
                throw new Exception("loadPlatform() read wrong number of objects.\n" +
                    "  Expected : 6\n" +
                    "  Actual   : " + loadedSize);
            System.out.println("loadPlatform (6 objects): PASS");

            // Spot-check first object
            String loadedInput = loaded.getAI(0).getInput();
            String loadedModel = loaded.getAI(0).getModel();
            if (!loadedInput.equals("textPrompt"))
                throw new Exception("loadPlatform() loaded wrong value for AI[0].input.\n" +
                    "  Expected : \"textPrompt\"\n" +
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
            if (sizeAfterAdd != 7)
                throw new Exception("addAI() after loadPlatform() gave wrong size.\n" +
                    "  Expected : 7\n" +
                    "  Actual   : " + sizeAfterAdd);
            System.out.println("In-Memory Modification After Load: PASS");

            // Save
            loaded.savePlatform(savedOutput);
            File savedFile = new File(savedOutput);
            if (!savedFile.exists())
                throw new Exception("savePlatform() did not create the output file.");
            if (savedFile.length() == 0)
                throw new Exception("savePlatform() created an empty file.");
            System.out.println("savePlatform (file created): PASS");

            // Reload
            Platform reloaded = new Platform();
            reloaded.loadPlatform(savedOutput);

            int reloadedSize = reloaded.getAISize();
            if (reloadedSize != 7)
                throw new Exception("Round-trip failed: reloaded wrong number of objects.\n" +
                    "  Expected : 7\n" +
                    "  Actual   : " + reloadedSize);

            // Full toString consistency check
            String original  = loaded.toString();
            String roundTrip = reloaded.toString();
            if (!original.equals(roundTrip))
                throw new Exception("Round-trip consistency FAILED: toString() differs after save/reload.\n" +
                    "--- ORIGINAL ---\n" + original +
                    "\n--- RELOADED ---\n" + roundTrip);
            System.out.println("Round-Trip Consistency: PASS");

            if (!roundTrip.contains("speechRecognition"))
                throw new Exception("Newly added NarrowAI did not survive the round-trip.\n" +
                    "  Missing : \"speechRecognition\"");
            if (!roundTrip.contains("Whisper"))
                throw new Exception("Newly added NarrowAI model did not survive the round-trip.\n" +
                    "  Missing : \"Whisper\"");
            System.out.println("New Object Survived Round-Trip: PASS");

            System.out.println("\nLEVEL 3 COMPLETE: 75/75");

        } catch (Exception e) {
            System.err.println("LEVEL 3 FAILED: " + e.getMessage());
            System.exit(1);
        }
    }
}
