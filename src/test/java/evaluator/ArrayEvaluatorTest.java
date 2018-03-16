package evaluator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

public class ArrayEvaluatorTest {

    @Test
    public void speedTest() {
        Random random = new Random();

        EvaluateFunction<IntStream> function = new IntStreamCountEvaluator();
        IntStream stream = IntStream.generate(() -> random.nextInt() % 2).limit(25);

        long start = System.currentTimeMillis();
        function.evaluate(stream);
        long time = System.currentTimeMillis() - start;
        Assert.assertTrue(time < 100);
        System.out.println(time);
    }

    @Test
    public void speedTest2() {
        Random random = new Random();

        EvaluateFunction<int[]> function = new IntArrayCountEvaluator();
        int[] array = new int[25];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt() % 2;
        }

        long start = System.currentTimeMillis();
        function.evaluate(array);
        long time = System.currentTimeMillis() - start;
        Assert.assertTrue(time < 100);
        System.out.println(time);
    }

    @Test
    public void testSpeedForManyTimes() {
        Random random = new Random();

        EvaluateFunction<int[]> function = new IntArrayCountEvaluator();
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt() % 2;
        }

        long start = System.currentTimeMillis();
        int times = 1 << 20;
        for (int i = 0; i < times; i++) {
            function.evaluate(array);
        }
        long time = System.currentTimeMillis() - start;
        Assert.assertTrue(time / times < 2);
        System.out.println(time);
    }
}
