package evaluator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vo.Clustering;

import java.util.ArrayList;
import java.util.Arrays;

public class EulcideanDistanceEvaluatorTest {

    private SingleClusterDistanceEvaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new SingleClusterDistanceEvaluator();
    }

    @Test
    public void testScore() {
        Clustering<double[]> clustering = new Clustering<>();

        clustering.center = new double[]{0, 0};

        double[][] points = {{4, 3}, {0, 0}};
        clustering.unitArray = new ArrayList<>(Arrays.asList(points));

        Assert.assertEquals(5, evaluator.evaluate(clustering), 0);

        clustering.unitArray.add(new double[]{30, 40});
        Assert.assertEquals(55, evaluator.evaluate(clustering), 0);
    }
}
