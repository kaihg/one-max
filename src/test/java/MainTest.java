import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainTest {

    private Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }


    @Ignore
    @Test
    public void testComputerScore() throws Exception {
//        Assert.assertEquals(0, main.computerScore(0b0000));
//        Assert.assertEquals(1, main.computerScore(0b0001));
//        Assert.assertEquals(1, main.computerScore(0b0010));
//        Assert.assertEquals(2, main.computerScore(0b0011));
//        Assert.assertEquals(3, main.computerScore(0b0111));
//        Assert.assertEquals(2, main.computerScore(0b1001));
//
//        Assert.assertEquals(1, main.computerScore(0b100));

        Assert.assertEquals(0, main.computerScore(0b0000L));
        Assert.assertEquals(1, main.computerScore(0b0001L));
        Assert.assertEquals(1, main.computerScore(0b0010L));
        Assert.assertEquals(2, main.computerScore(0b0011L));
        Assert.assertEquals(3, main.computerScore(0b0111L));
        Assert.assertEquals(2, main.computerScore(0b1001L));

        Assert.assertEquals(1, main.computerScore(0b100L));

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
        main.findAllObj(30);
        Assert.assertEquals(30, main.getBestScore());
        Assert.assertEquals("111111111111111111111111111111", main.getBestObj());
    }

    @Ignore
    @Test
    public void testFind40SuperBigObj() throws Exception {
        // 目前一次23分
        main.findAllObj(40);
        Assert.assertEquals(40, main.getBestScore());
    }

    @Ignore
    @Test
    public void testFind50SuperBigObj() throws Exception {
        // 預估起來太久，不執行
        main.findAllObj(50);
        Assert.assertEquals(50, main.getBestScore());
    }

}
