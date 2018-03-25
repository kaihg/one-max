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


    private void startToTest(int repeatTimes,
                             int iteration,
                             int bitCount,
                             int neighborPick) {
        AlgorithmModel modelHC = ModelFactory.createModel(ModelFactory.HILL_CLIMBING, bitCount, repeatTimes, iteration, neighborPick);
        AlgorithmModel modelSA = ModelFactory.createModel(ModelFactory.SIMULATED_ANNEALING, bitCount, repeatTimes, iteration, neighborPick);

        modelHC.init();
        modelSA.init();
        for (int i = 0; i <= iteration; i++) {
            if (i % 10 == 0) {
                System.out.println(String.format("%d,%d,%d", i, modelHC.getScore(), modelSA.getScore()));
            }

            modelHC.iterateOnce();
            modelSA.iterateOnce();
        }
    }
}
