import model.AlgorithmModel;
import model.ModelFactory;
import org.junit.Test;

public class CompositeModel {

    @Test
    public void compare_SA_and_HC_on_1_neighbor_with_100Bit_in_500_iteration() {
        int repeatTimes = 30;
        int iteration = 500;
        int bitCount = 100;
        int neighborPick = 1;

        startToTest(repeatTimes, iteration, bitCount, neighborPick);
    }


    @Test
    public void compare_SA_and_HC_on_10_neighbor_with_100Bit_in_500_iteration() {
        int repeatTimes = 30;
        int iteration = 500;
        int bitCount = 100;
        int neighborPick = 10;

        startToTest(repeatTimes, iteration, bitCount, neighborPick);
    }

    @Test
    public void compare_SA_and_HC_on_100_neighbor_with_100Bit_in_500_iteration() {
        int repeatTimes = 30;
        int iteration = 500;
        int bitCount = 100;
        int neighborPick = 100;

        startToTest(repeatTimes, iteration, bitCount, neighborPick);
    }

    @Test
    public void compare_HC_GA_on_8_neighbor_with_100Bit_in_500_iteration() {
        int repeatTimes = 30;
        int iteration = 500;
        int bitCount = 100;
        int neighborPick = 8;


        AlgorithmModel modelHC = ModelFactory.createModel(ModelFactory.HILL_CLIMBING, bitCount, repeatTimes, iteration, neighborPick);
        AlgorithmModel modelGA = ModelFactory.createModel(ModelFactory.GENETIC_ALGORITHM, bitCount, repeatTimes, iteration, 1, 8d, 0.6, 0.1);

        modelHC.init(0);
        modelGA.init(0);
        for (int i = 0; i <= iteration; i++) {

            System.out.println(String.format("%d,%f,%f", i, modelHC.getScore(), modelGA.getScore()));

            modelHC.iterateOnce();
            modelGA.iterateOnce();
        }
    }

    private void startToTest(int repeatTimes,
                             int iteration,
                             int bitCount,
                             int neighborPick) {
        AlgorithmModel modelHC = ModelFactory.createModel(ModelFactory.HILL_CLIMBING, bitCount, repeatTimes, iteration, neighborPick);
        AlgorithmModel modelSA = ModelFactory.createModel(ModelFactory.SIMULATED_ANNEALING, bitCount, repeatTimes, iteration, neighborPick, 1d, 0.99);

        modelHC.init(0);
        modelSA.init(0);
        for (int i = 0; i <= iteration; i++) {

            System.out.println(String.format("%d,%f,%f", i, modelHC.getScore(), modelSA.getScore()));

            modelHC.iterateOnce();
            modelSA.iterateOnce();
        }
    }
}
