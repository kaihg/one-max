package transition;

import evaluator.EvaluateFunction;
import evaluator.EvaluatorFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BestNeigTranstitionTest {

    @Test
    public void when_ten_neighbor_then_diff_1() {
        EvaluateFunction function = EvaluatorFactory.createLongEvaluator();
        LongTransition transition = new BestNeighborTransition(new RandomNeighborTransition(0, 100), 10, function);

        int[] start = transition.next();
        int[] current = Arrays.copyOf(start, 100);
        int[] temp = new int[100];

        transition.neighbor(current, temp);
        Assert.assertEquals(1, function.evaluate(temp) - function.evaluate(current));

        current = Arrays.copyOf(temp, 100);
        transition.neighbor(current, temp);
        Assert.assertEquals(1, function.evaluate(temp) - function.evaluate(current));

        Assert.assertEquals(2, function.evaluate(temp) - function.evaluate(start));
    }
}
