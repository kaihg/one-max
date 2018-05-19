package model;

import java.util.Random;

public interface KMeanModel {

    void updateGroupCenter();

    void setClusters(int[] initialGroupFlags, int groupK);

    void updateClusters(int[] groupFlags);

    void initCenter(Random random);
}
