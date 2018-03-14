package transit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

public class RandomNeighborTransitTest {


    @Test
    public void when_oneBitTransitCallNext_then_onlyExist0or1() {
        Iterator<Long> iterator = new RandomNeighborTransit(0,1);

        for (int i = 0; i < 1000; i++) {
            long next = iterator.next();
            Assert.assertTrue(next == 0 || next == 1);
        }
    }

    @Test
    public void when_twoBitTransitCallNext_then_onlyExist0123() {
        Iterator<Long> iterator = new RandomNeighborTransit(0,2);

        for (int i = 0; i < 1000; i++) {
            long next = iterator.next();
            Assert.assertTrue(next == 0 || next == 1 || next == 2 || next == 3);
        }
    }

    @Test
    public void when_callNext_then_neitherEqual() {
        Iterator<Long> iterator = new RandomNeighborTransit(0,3);

        long temp = iterator.next();
        for (int i = 0; i < 1000; i++) {
            long next = iterator.next();
            Assert.assertNotEquals(temp,next);
            temp = next;
        }
    }

    @Ignore
    @Test
    public void checkListByEyes() {
        Iterator<Long> iterator = new RandomNeighborTransit(0,3);
        for (int i = 0; i < 30; i++) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void when_setStartNumber_then_firstNextNumberEqualStartNumber() {
        Long startNumber = 5L;
        for (int i = 0; i < 100; i++) {
            Iterator<Long> iterator = new RandomNeighborTransit(startNumber,4);
            Assert.assertEquals(startNumber,iterator.next());
        }
    }
}
