package model;

import org.junit.Assert;
import org.junit.Test;

public class SimulatedAnnealingModelTest {

    @Test
    public void when_bitIs8_then_resultIs11111111() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.SIMULATED_ANNEALING, 8, 1, 1000);

        model.start();

        Assert.assertEquals(8, model.getScore());
        Assert.assertEquals("11111111", model.getResult());
    }

    @Test
    public void when_bitIs20_then_resultIs2of20() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.SIMULATED_ANNEALING, 20, 1, 1000);

        model.start();

        Assert.assertEquals(20, model.getScore());
        Assert.assertEquals("11111111111111111111", model.getResult());
    }

    @Test
    public void when_bitIs50_then_ansIs2of50() {
        int bit = 50;
        simpleTestResult(bit);
    }

    @Test
    public void when_bitIs1000_then_ansIs2of1000() {
        int bit = 1000;
        simpleTestResult(bit);
    }

    private void simpleTestResult(int bit) {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.SIMULATED_ANNEALING, bit, 1, 1000);
        model.start();
        Assert.assertEquals(bit, model.getScore());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bit; i++) {
            builder.append("1");
        }
        Assert.assertEquals(builder.toString(), model.getResult());
    }
}
