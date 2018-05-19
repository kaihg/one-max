package model;

import evaluator.EuclideanEvaluator;
import evaluator.EvaluateFunction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import transition.LongTransition;
import transition.TransitFactory;

public class GeneticKmeanModelTest {

    private GeneticKmeanModel model;
    private double[][] datas = {
            {0, 0}, {1, 1},
            {5, 5}, {6, 7},
            {-5, -4}, {-3, -4}
    };

    @Before
    public void setUp() throws Exception {
        model = new GeneticKmeanModel(500, 20, datas, 3);

        LongTransition transition = TransitFactory.createGeneticTransition(0.6, 0.1);
        EvaluateFunction function = new EuclideanEvaluator();

        model.setTransit(transition);
        model.setEvaluator(function);
    }

    @Test
    public void testKResult() {
        model.start();

        String result = model.getResult();
        System.out.println(result);
        Assert.assertEquals(result.charAt(0), result.charAt(1));
        Assert.assertEquals(result.charAt(2), result.charAt(3));
        Assert.assertEquals(result.charAt(4), result.charAt(5));

    }

    @Test
    public void testKResult2() {
        double[][] datas = {
                {0, 0}, {1, 1},
                {5, 5}, {6, 7},
                {-50, -4}, {-39, -4},
                {100, 99}, {70, 80}
        };
        model = new GeneticKmeanModel(1000, 50, datas, 4);

        LongTransition transition = TransitFactory.createGeneticTransition(0.6, 0.1);
        EvaluateFunction function = new EuclideanEvaluator();

        model.setTransit(transition);
        model.setEvaluator(function);


        model.start();

        String result = model.getResult();
        System.out.println(result);
        Assert.assertEquals(result.charAt(0), result.charAt(1));
        Assert.assertEquals(result.charAt(2), result.charAt(3));
        Assert.assertEquals(result.charAt(4), result.charAt(5));
        Assert.assertEquals(result.charAt(6), result.charAt(7));

    }
}
