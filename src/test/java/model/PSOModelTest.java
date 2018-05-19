package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PSOModelTest {

    AlgorithmModel model;

    @Before
    public void setUp() throws Exception {
        model = ModelFactory.createPSOModelFromFile("param.json", ModelFactory.PSO_ALGORITHM);
    }


    @Test
    public void testThePower() {

        double avgScore = 0;

        int count = 30;
        for (int i = 0; i < count; i++) {
            model.start();
            double score = model.getScore();
            avgScore += score;
        }
        avgScore = avgScore / count;

        Assert.assertEquals(0, avgScore, 0.00001);
    }
}
