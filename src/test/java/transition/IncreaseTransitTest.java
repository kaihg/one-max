package transition;

import org.junit.Assert;
import org.junit.Test;

public class IncreaseTransitTest {

    @Test
    public void firstValue_Is_equalToStart() {
        long start = 4;
        int bitCount = 8;
        int[] temp = {0, 0, 1, 0, 0, 0, 0, 0};


        for (int i = 0; i < 100; i++) {
            LongTransition transit = new IncreaseTransition(start, bitCount);
            Assert.assertArrayEquals(temp, transit.next());
        }

    }

    @Test
    public void when_reachMax_then_stop() {
        long start = 0;
        int bitCount = 10;
        long count = 0;

        LongTransition transit = new IncreaseTransition(start, bitCount);
        while (transit.hasNext()) {
            count += 1;
            transit.next();
        }

        Assert.assertEquals(1 << bitCount, count);
    }

    @Test
    public void speedTest() {
        int bitCount = 20;

        LongTransition transit = new IncreaseTransition(0, bitCount);
        long start = System.currentTimeMillis();
        while (transit.hasNext()) {
            transit.next();
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println(cost);
        Assert.assertTrue(cost < 50);

    }
}
