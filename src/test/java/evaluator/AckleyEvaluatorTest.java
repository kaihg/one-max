package evaluator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AckleyEvaluatorTest {

    EvaluateFunction<double[]> evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new AckleyEvaluator();
    }

    @Test
    public void testAnswer() {
        double score = evaluator.evaluate(new double[]{0, 0});
        Assert.assertEquals(0, score, 0.0001);


        score = evaluator.evaluate(new double[]{1, 1});
        Assert.assertEquals(3.62538493844, score, 0.0001);
    }
}
