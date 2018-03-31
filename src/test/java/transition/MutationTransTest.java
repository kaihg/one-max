package transition;

import org.junit.Assert;
import org.junit.Test;

public class MutationTransTest {

    @Test
    public void when_rateIs1_then_allChange1bit() {
        MutationTransition transition = new MutationTransition(1);

        int[][] src = {
                {0}, {1}, {0}, {1}
        };
        int[][] temp = {
                {0}, {0}, {0}, {0}
        };

        transition.neighbor(src, temp);

        Assert.assertArrayEquals(new int[]{1}, temp[0]);
        Assert.assertArrayEquals(new int[]{0}, temp[1]);
        Assert.assertArrayEquals(new int[]{1}, temp[2]);
        Assert.assertArrayEquals(new int[]{0}, temp[3]);
    }

    @Test
    public void when_rateIs0_then_noOneChange() {
        MutationTransition transition = new MutationTransition(0);

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
}
