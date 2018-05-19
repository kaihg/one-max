package evaluator;

import model.EuclideanClusterModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class EuclideanEvaluatorTest {

    private EuclideanEvaluator evaluator;
    private double[][] datas = new double[][]{
            {0, 0}, {1, 1},
            {5, 5}, {6, 7},
            {-5, -4}, {-3, -4}
    };
    private EuclideanClusterModel model;

    @Before
    public void setUp() throws Exception {
        evaluator = new EuclideanEvaluator();

        int[] flags = {0, 0, 1, 1, 2, 2};
        model = new EuclideanClusterModel(datas);
        model.setClusters(flags, 3);
        model.initCenter(new Random());
        model.updateGroupCenter();
    }

    @Test
    public void testScore() {
        double score = evaluator.evaluate(model);

        double a1 = Math.sqrt(2);
        double a2 = Math.sqrt(5);
        double a3 = Math.sqrt(4);

        Assert.assertEquals(a1 + a2 + a3, score, 0.000001);
    }
}
