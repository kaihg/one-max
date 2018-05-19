import model.AlgorithmModel;
import model.CompareModel;
import model.ModelFactory;

import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String algorithm = ModelFactory.HILL_CLIMBING;
        if (args.length > 0) {
            algorithm = args[0];
        }

        switch (algorithm) {
            case ModelFactory.TKP_COMPARE:
                onTKPCompare(args);
                break;
            case ModelFactory.PSO_ALGORITHM:
                startPSO(args);
                break;
            case ModelFactory.GENETIC_ALGORITHM_KMEAN:
                startFromFile(args);
                break;
            default:
                otherAlgorithm(args);
                break;
        }

    }

    private static void startFromFile(String[] args) throws FileNotFoundException {
        String algorithm = args[0];
        AlgorithmModel model = ModelFactory.createModel(algorithm, args[1]);

        System.out.println("Start " + ModelFactory.getAlgorithmName(algorithm));
        model.start();
        System.out.println("The result is " + model.getResult() + ", which score is " + model.getScore());
    }

    private static void startPSO(String[] args) throws FileNotFoundException {
        AlgorithmModel model = ModelFactory.createPSOModelFromFile(args[1], args[0]);
        System.out.println("Start " + ModelFactory.getAlgorithmName(args[0]) + " Iteration :");

        int seed = new Random().nextInt();
        System.out.println("seed : " + seed);
        model.init(seed);

        for (int i = 0; i < 1000; i++) {
            model.iterateOnce();
            if (i % 100 == 0) {
                System.out.println("iteration : " + i + ", cur best is " + model.getResult());
            }

        }

//        model.start();

        System.out.println("The result is " + model.getResult() + ", and the score is " + model.getScore());
    }

    private static void otherAlgorithm(String[] args) {
        int testN = 20; // default
        String algorithm = ModelFactory.HILL_CLIMBING;
        int runTimes = 30;
        int iterationCount = 1000;
        int neighborPIckCount = 1;

        AlgorithmModel model;
        double[] extras = new double[args.length - 5];
        testN = Integer.parseInt(args[1]);
        runTimes = Integer.parseInt(args[2]);
        iterationCount = Integer.parseInt(args[3]);
        neighborPIckCount = Integer.parseInt(args[4]);

        for (int i = 0; i < extras.length; i++) {
            extras[i] = Double.parseDouble(args[i + 5]);
        }
        model = ModelFactory.createModel(algorithm, testN, runTimes, iterationCount, neighborPIckCount, extras);
        System.out.println("Start " + ModelFactory.getAlgorithmName(algorithm));
        model.start();
        System.out.println(String.format("The one-max result of 2^%d is %s, it's score is %d", testN, model.getResult(), model.getScore()));

    }

    private static void onTKPCompare(String[] args) throws FileNotFoundException {
        CompareModel model = ModelFactory.createTKPCompareModels(args[1]);
        if (args.length >= 3) {
            model.setSeed(Integer.parseInt(args[2]));
        }
        System.out.println("Start TKP compare");
        model.start();
    }


}
