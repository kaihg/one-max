package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class EuclideanClusterModelTest {

    private double[][] datas = new double[][]{
            {0, 0}, {1, 1},
            {5, 5}, {6, 7},
            {-5, -4}, {-3, -4}
    };
    private EuclideanClusterModel model;

    @Before
    public void setUp() throws Exception {
        model = new EuclideanClusterModel(datas);
    }

    @Test
    public void testUpdateCenter() {
        int[] flags = {0, 0, 1, 1, 2, 2};
        model.setClusters(flags, 3);
        model.initCenter(new Random());
        model.updateGroupCenter();

        Assert.assertArrayEquals(new double[]{0.5, 0.5}, model.getCenters()[0], 0.00001);
        Assert.assertArrayEquals(new double[]{5.5, 6}, model.getCenters()[1], 0.00001);
        Assert.assertArrayEquals(new double[]{-4, -4}, model.getCenters()[2], 0.00001);
    }

    @Test
    public void testUpdateGroup() {
        int[] flags = {0, 0, 1, 1, 2, 2};
        model.setClusters(flags, 3);
        model.initCenter(new Random());
        model.updateGroupCenter();

        flags[0] = 1;
        flags[5] = 0;
        model.updateClusters(flags);
        model.updateGroupCenter();
        Assert.assertArrayEquals(new double[]{-1, -1.5}, model.getCenters()[0], 0.00001);
        Assert.assertArrayEquals(new double[]{11d / 3d, 4}, model.getCenters()[1], 0.00001);
        Assert.assertArrayEquals(new double[]{-5, -4}, model.getCenters()[2], 0.00001);

    }

    @Test
    public void testUpdateGroup2() {
        int[] flags = {0, 0, 1, 1, 2, 2};
        model.setClusters(flags, 3);
        model.initCenter(new Random());
        model.updateGroupCenter();

        int[] flag2 = {1, 0, 1, 1, 2, 0};
        model.updateClusters(flag2);
        model.updateGroupCenter();
        Assert.assertArrayEquals(new double[]{-1, -1.5}, model.getCenters()[0], 0.00001);
        Assert.assertArrayEquals(new double[]{11d / 3d, 4}, model.getCenters()[1], 0.00001);
        Assert.assertArrayEquals(new double[]{-5, -4}, model.getCenters()[2], 0.00001);

    }
}
