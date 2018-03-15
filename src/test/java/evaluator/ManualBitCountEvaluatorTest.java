package evaluator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManualBitCountEvaluatorTest {

    private EvaluateFunction<Long> evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new ManualBitCountEvaluator();
    }

    @Test
    public void testComputerScore() throws Exception {
        Assert.assertEquals(0, evaluator.evaluate(0b0000L));
        Assert.assertEquals(1, evaluator.evaluate(0b0001L));
        Assert.assertEquals(1, evaluator.evaluate(0b0010L));
        Assert.assertEquals(2, evaluator.evaluate(0b0011L));
        Assert.assertEquals(3, evaluator.evaluate(0b0111L));
        Assert.assertEquals(2, evaluator.evaluate(0b1001L));

        Assert.assertEquals(1, evaluator.evaluate(0b100L));

    }

    @Test
    public void testRunTime_Long() {
        long count = 0;
        long end = (long) Math.pow(2, 25);
        while (count < end) {
            evaluator.evaluate(count++);
        }
    }

    @Test
    public void testRunTime_Long2() {
        Long count = 0L;
        long end = (long) Math.pow(2, 25);
        while (count < end) {
            evaluator.evaluate(count++);
        }
    }


    @Test
    public void testRunTime_long() {
        long count = 0;
        long end = (long) Math.pow(2, 25);
        while (count < end) {
            evaluate(count++);
        }
    }

    public int evaluate(long value) {
        int count = 0;

        while (value > 0) {
            count += value & 0b1;
            value = value >> 1;
        }

        return count;

    }
}
