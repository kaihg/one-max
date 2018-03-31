package transition;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CrossoverTransitionTest {

    @Test
    public void testNeighbor_crossover() {
        LongTransition<int[][]> transition = new CrossoverTransition(1);

        int[][] src = {
                {1, 1, 0, 0}, {0, 0, 1, 1},
                {1, 0, 0, 1}, {0, 1, 1, 0}
        };

        int[][] temp = {
                {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}
        };

        transition.neighbor(src, temp);

        int[][] probability1 = {
                {1, 0, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}
        };
        Assert.assertTrue(checkChild(probability1, temp[0]) && checkChild(probability1, temp[2]));

        int[][] probability2 = {
                {0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 1}, {0, 1, 1, 1}
        };
        Assert.assertTrue(checkChild(probability2, temp[1]) && checkChild(probability2, temp[3]));


    }

    @Test
    public void when_rateIs0_then_noItemChange() {
        LongTransition<int[][]> transition = new CrossoverTransition(0);

        int[][] src = {
                {1, 1, 0, 0}, {0, 0, 1, 1},
                {1, 0, 0, 1}, {0, 1, 1, 0}
        };

        int[][] temp = {
                {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}
        };

        transition.neighbor(src, temp);

        Assert.assertArrayEquals(src[0], temp[0]);
        Assert.assertArrayEquals(src[1], temp[1]);
        Assert.assertArrayEquals(src[2], temp[2]);
        Assert.assertArrayEquals(src[3], temp[3]);
    }

    private boolean checkChild(int[][] probability, int[] child) {
        for (int[] prob : probability) {
            if (Arrays.equals(child, prob)) {
                return true;
            }
        }

        return false;
    }
}
