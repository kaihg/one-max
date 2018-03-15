package transition;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class RandomNeighborTransitTest {


    @Test
    public void when_oneBitTransitCallNext_then_onlyExist0or1() {
        LongTransition iterator = new RandomNeighborTransition(0, 1);

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
        LongTransition iterator = new RandomNeighborTransition(0, 2);

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
        LongTransition iterator = new RandomNeighborTransition(0, 3);

        int[] temp = iterator.next();
        for (int i = 0; i < 1000; i++) {
            int[] next = iterator.next();
//            Assert.assertNotEquals(temp,next);
            boolean isEqual = Arrays.equals(temp, next);
            Assert.assertFalse(isEqual);
            temp = next;
        }
    }

    @Ignore
    @Test
    public void checkListByEyes() {
        LongTransition iterator = new RandomNeighborTransition(0, 3);
        for (int i = 0; i < 30; i++) {
            System.out.println(Arrays.toString(iterator.next()));
        }
    }

    @Test
    public void when_setStartNumber_then_firstNextNumberEqualStartNumber() {
        long startNumber = 5;
        int[] startArray = {1, 0, 1, 0};
        for (int i = 0; i < 100; i++) {
            LongTransition iterator = new RandomNeighborTransition(startNumber, 4);
            Assert.assertArrayEquals(startArray, iterator.next());
        }
    }
}
