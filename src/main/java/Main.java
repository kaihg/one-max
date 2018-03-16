import model.AlgorithmModel;
import model.ModelFactory;

public class Main {
    public static void main(String[] args) {
        int testN = 20; // default
        String algorithm = ModelFactory.EXHAUSTION_SEARCH;
        if (args.length > 0) {
            try {
                algorithm = args[0];
                testN = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        AlgorithmModel model = ModelFactory.createModel(algorithm, testN, 30, 1000);
        model.start();
        System.out.println(String.format("The one-max result of 2^%d is %s, it's score is %d", testN, model.getResult(), model.getScore()));
    }
}
