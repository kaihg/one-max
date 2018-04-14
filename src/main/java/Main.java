import model.AlgorithmModel;
import model.ModelFactory;

public class Main {
    public static void main(String[] args) {
        int testN = 20; // default
        String algorithm = ModelFactory.HILL_CLIMBING;
        int runTimes = 30;
        int iterationCount = 1000;
        int neighborPIckCount = 1;
        double[] extras = new double[args.length - 5];
        if (args.length > 0) {
            try {
                algorithm = args[0];
                testN = Integer.parseInt(args[1]);
                runTimes = Integer.parseInt(args[2]);
                iterationCount = Integer.parseInt(args[3]);
                neighborPIckCount = Integer.parseInt(args[4]);

                for (int i = 0; i < extras.length; i++) {
                    extras[i] = Double.parseDouble(args[i + 5]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        AlgorithmModel model = ModelFactory.createModel(algorithm, testN, runTimes, iterationCount, neighborPIckCount, extras);

        System.out.println("Start " + ModelFactory.getAlgorithmName(algorithm));
        model.start();
        System.out.println(String.format("The one-max result of 2^%d is %s, it's score is %d", testN, model.getResult(), model.getScore()));
    }
}
