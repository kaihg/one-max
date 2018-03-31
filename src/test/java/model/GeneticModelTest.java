package model;

import org.junit.Assert;
import org.junit.Test;

public class GeneticModelTest {

    @Test
    public void test_10bit() {
        simpleTestResult(10, 100, 4, 0.6, 0.1);

    }

    @Test
    public void test_100bit() {
        simpleTestResult(100, 500, 8, 0.6, 0.1);

    }

    @Test
    public void test_1000bit() {
        simpleTestResult(1000, 5000, 16, 0.6, 0.1);

    }

    private void simpleTestResult(int bit, int iterationCount, int populationSize, double rate1, double rate2) {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.GENETIC_ALGORITHM, bit, 1, iterationCount, 1, populationSize, rate1, rate2);
        model.start();
        Assert.assertEquals(bit, model.getScore());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bit; i++) {
            builder.append("1");
        }
        Assert.assertEquals(builder.toString(), model.getResult());
    }
}
