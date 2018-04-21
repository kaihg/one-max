import model.AlgorithmModel;
import model.CompareModel;
import model.ModelFactory;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int testN = 20; // default
        String algorithm = ModelFactory.HILL_CLIMBING;
        int runTimes = 30;
        int iterationCount = 1000;
        int neighborPIckCount = 1;
        if (args.length > 0) {
            algorithm = args[0];
        }

        if (ModelFactory.TKP_COMPARE.equals(algorithm)) {
            CompareModel model = ModelFactory.createTKPCompareModels(args[1]);
            if (args.length >= 3) {
                model.setSeed(Integer.parseInt(args[2]));
            }
            System.out.println("Start TKP compare");
            model.start();

        } else {
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
    }


}
