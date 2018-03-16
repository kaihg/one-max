package model;

import org.junit.Assert;
import org.junit.Test;

public class HillClimbingModelTest {

    @Test
    public void when_bitIs8_then_ansIs11111111() throws Exception {

        AlgorithmModel model = ModelFactory.createModel(ModelFactory.HILL_CLIMBING,8);

        model.start();

        Assert.assertEquals("11111111",model.getResult());
        Assert.assertEquals(8,model.getScore());


    }

    @Test
    public void when_bitIs30_then_ansIs2Of30() throws Exception {

        AlgorithmModel model = ModelFactory.createModel(ModelFactory.HILL_CLIMBING,30);

        model.start();

        Assert.assertEquals("111111111111111111111111111111",model.getResult());
        Assert.assertEquals(30,model.getScore());


    }
}
