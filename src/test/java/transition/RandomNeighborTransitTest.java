package transition;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class RandomNeighborTransitTest {


    @Test
    public void when_oneBitTransitCallNext_then_onlyExist0or1() {
        LongTransition<int[]> iterator = new RandomNeighborTransition(0, 1);

        for (int i = 0; i < 1000; i++) {
            int[] next = iterator.next();
            int sum = 0;
            for (int bit :
                    next) {
                Assert.assertTrue(bit == 0 || bit == 1);
                sum += bit;
            }
            Assert.assertTrue(sum < 2);
        }
    }

    @Test
    public void when_twoBitTransitCallNext_then_onlyExist0123() {
        LongTransition<int[]> iterator = new RandomNeighborTransition(0, 2);

        for (int i = 0; i < 1000; i++) {
            int[] next = iterator.next();
            int sum = 0;
            for (int bit :
                    next) {
                Assert.assertTrue(bit == 0 || bit == 1);
                sum += bit;
            }
            Assert.assertTrue(sum < 4);
        }
    }

    @Test
    public void when_callNext_then_neitherEqual() {
        LongTransition<int[]> iterator = new RandomNeighborTransition(0, 3);

        int[] temp = Arrays.copyOf(iterator.next(), 3);
        for (int i = 0; i < 1000; i++) {
            int[] next = iterator.next();

            boolean isEqual = Arrays.equals(temp, next);
            Assert.assertFalse(isEqual);

            System.arraycopy(next, 0, temp, 0, 3);
        }
    }

    @Ignore
    @Test
    public void checkListByEyes() {
        LongTransition<int[]> iterator = new RandomNeighborTransition(0, 3);
        for (int i = 0; i < 30; i++) {
            System.out.println(Arrays.toString(iterator.next()));
        }
    }

    @Test
    public void when_setStartNumber_then_firstNextNumberEqualStartNumber() {
        long startNumber = 5;
        int[] startArray = {1, 0, 1, 0};
        for (int i = 0; i < 100; i++) {
            LongTransition<int[]> iterator = new RandomNeighborTransition(startNumber, 4);
            Assert.assertArrayEquals(startArray, iterator.next());
        }
    }

    @Test
    public void when_next_then_getOneBitChange() throws Exception {
        LongTransition<int[]> transition = new RandomNeighborTransition(0, 1);
        Assert.assertArrayEquals(new int[]{0},transition.next());
        Assert.assertArrayEquals(new int[]{1},transition.next());
        Assert.assertArrayEquals(new int[]{0},transition.next());
    }

    @Test
    public void when_setDefault_then_getTheValue() throws Exception {
        LongTransition<int[]> transition = new RandomNeighborTransition(0, 5);

        Assert.assertArrayEquals(new int[]{0,0,0,0,0},transition.next());

        transition.setDefaultValue("11001");
        Assert.assertArrayEquals(new int[]{1,0,0,1,1},transition.next());

        transition.setDefaultValue("11111");
        Assert.assertArrayEquals(new int[]{1,1,1,1,1},transition.next());
    }

    @Test
    public void only1DistanceBetween2Neighbors() {
        int length = 10;
        LongTransition<int[]> transition = new RandomNeighborTransition(4, length);

        int[] neighbor = new int[length];
        for (int i = 0; i < 50; i++) {
            int[] current = transition.next();

            for (int j = 0; j < 10; j++) {
                transition.neighbor(current, neighbor);

                int hammingDist = 0;
                for (int k = 0; k < length; k++) {
                    if (current[k] != neighbor[k]) {
                        hammingDist++;
                    }
                }
                Assert.assertEquals(1, hammingDist);
            }
        }

    }
}
