package model;

import org.junit.Assert;
import org.junit.Test;

public class TabuModelTest {

    @Test
    public void when_bitIs8_then_resultIs11111111() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.TABU_SEARCH, 8, 1, 100, 1);

        model.start();

        Assert.assertEquals(8, model.getScore());
        Assert.assertEquals("11111111", model.getResult());
    }

    @Test
    public void when_bitIs10_then_resultIs10bit() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.TABU_SEARCH, 10, 1, 50, 1);

        model.start();

        Assert.assertEquals(10, model.getScore());
    }

    @Test
    public void when_bitIs100_then_resultIs100bit() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.TABU_SEARCH, 100, 1, 500, 1);

        model.start();

        Assert.assertEquals(100, model.getScore());
    }
}
