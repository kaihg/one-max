package transition;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MemoryTransitionTest {

    @Test
    public void testMemorySize() {
        int bitSize = 10;
        LongTransition transition = new MemoryTransition(0, bitSize);

        int[] current = new int[bitSize];
        int[] tempAry = new int[bitSize];

        for (int i = 0; i < 10; i++) {
            transition.neighbor(current, tempAry);
            Assert.assertFalse(Arrays.equals(current, tempAry));

            System.arraycopy(tempAry, 0, current, 0, bitSize);

            System.out.println(Arrays.toString(transition.next()));
        }

    }
}
