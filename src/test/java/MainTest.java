import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.LongStream;

public class MainTest {

    Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }


    @Test
    public void testComputerScore() throws Exception {
        Assert.assertEquals(0, main.computerScore(0b0000));
        Assert.assertEquals(1, main.computerScore(0b0001));
        Assert.assertEquals(1, main.computerScore(0b0010));
        Assert.assertEquals(2, main.computerScore(0b0011));
        Assert.assertEquals(3, main.computerScore(0b0111));
        Assert.assertEquals(2, main.computerScore(0b1001));

        Assert.assertEquals(1, main.computerScore(0b100));

    }

    @Test
    public void testFindAllObj() throws Exception {
        main.findAllObj(4);
        Assert.assertEquals(4, main.getBestScore());
        Assert.assertEquals("1111", main.getBestObj());
    }

    @Test
    public void testFindAllObj2() throws Exception {
        main.findAllObj(3);
        Assert.assertEquals(3, main.getBestScore());
        Assert.assertEquals("111", main.getBestObj());
    }

    @Test
    public void testFind10BigObj() throws Exception {
        main.findAllObj(10);
        Assert.assertEquals(10, main.getBestScore());
        Assert.assertEquals("1111111111", main.getBestObj());
    }

    @Test
    public void testFind20BigObj() throws Exception {
        main.findAllObj(20);
        Assert.assertEquals(20, main.getBestScore());
        Assert.assertEquals("11111111111111111111", main.getBestObj());
    }

    @Test
    public void testFind30BigObj() throws Exception {
        // 本機跑 35s
        main.findAllObj(30);
        Assert.assertEquals(30, main.getBestScore());
        Assert.assertEquals("111111111111111111111111111111", main.getBestObj());
    }

    @Ignore
    @Test
    public void testFind40SuperBigObj() throws Exception {
        // 預估會跑 10 小時左右
        main.findAllObj(40);
        Assert.assertEquals(40, main.getBestScore());
    }

    @Ignore
    @Test
    public void testFind50SuperBigObj() throws Exception {
        // 預估會跑 10000 小時左右
        main.findAllObj(50);
        Assert.assertEquals(50, main.getBestScore());
    }

    @Test
    public void testStreamList() throws Exception {
        long maxValue = (long) Math.pow(2, 4);
        LongStream one = LongStream.iterate(0, x -> x + 1).limit(maxValue);

        long[] longs = {0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L};
        Assert.assertArrayEquals(longs, one.toArray());
    }
}
