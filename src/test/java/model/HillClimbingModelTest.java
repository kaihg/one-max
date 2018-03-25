package model;

import org.junit.Assert;
import org.junit.Test;

public class HillClimbingModelTest {

    @Test
    public void when_bitIs8_then_ansIs11111111() throws Exception {

        AlgorithmModel model = ModelFactory.createModel(ModelFactory.HILL_CLIMBING, 8, 1, 1000, 1);

        model.start();

        Assert.assertEquals("11111111",model.getResult());
        Assert.assertEquals(8,model.getScore());


    }

    @Test
    public void when_bitIs30_then_ansIs2of30() throws Exception {
        int bit = 30;
        simpleTestResult(bit);
    }

    @Test
    public void when_bitIs40_then_ansIs2of40() throws Exception {
        int bit = 40;
        simpleTestResult(bit);
    }

    @Test
    public void when_bitIs50_then_ansIs2of50() throws Exception {
        simpleTestResult(50);
    }

    @Test
    public void when_bitIs100_then_ansIs2of100() throws Exception {
        int bit = 100;
        simpleTestResult(bit);
    }

    @Test
    public void when_bitIs1000_then_ansIs2of1000() throws Exception {
        int bit = 1000;
        simpleTestResult(bit);
    }

    private void simpleTestResult(int bit) {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.HILL_CLIMBING, bit, 1, 1000, 1);
        model.start();
        Assert.assertEquals(bit, model.getScore());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bit; i++) {
            builder.append("1");
        }
        Assert.assertEquals(builder.toString(), model.getResult());
    }
}
