package model;

import java.util.Random;

public class EuclideanClusterModel implements KMeanModel {

    private double[][] datas;
    private int groupNum;
    private int attrNum;

    //    private Clustering<double[]>[] clusters;
    private int[] clusterFlags;
    private double[][] centers;

    public EuclideanClusterModel(double[][] datas) {
        this.datas = datas;
        attrNum = datas[0].length;
    }

    @Override
    public void updateGroupCenter() {
        double[][] temp = new double[groupNum][attrNum];
        int[] flagCount = new int[groupNum];
        for (int i = 0; i < clusterFlags.length; i++) {
            int flag = clusterFlags[i];
            flagCount[flag]++;
            double[] data = datas[i];

            for (int attr = 0; attr < attrNum; attr++) {
                temp[flag][attr] += data[attr];
            }
        }

        for (int i = 0; i < groupNum; i++) {
            for (int j = 0; j < attrNum; j++) {
                centers[i][j] = temp[i][j] / flagCount[i];
            }
        }

//
//        for (Clustering<double[]> clustering : clusters) {
//            int length = clustering.unitArray.size();
//            for (int i = 0; i < clustering.center.length; i++) {
//                double sum = 0;
//                for (double[] unit : clustering.unitArray) {
//                    sum += unit[i];
//                }
//                clustering.center[i] = sum / length;
//            }
//        }

    }

    public double[][] getCenters() {
        return centers;
    }

    public double[][] getDatas() {
        return datas;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public int getAttrNum() {
        return attrNum;
    }

    public int[] getClusterFlags() {
        return clusterFlags;
    }

    @Override
    public void setClusters(int[] initialGroupFlags, int groupK) {
        groupNum = groupK;
        this.centers = new double[groupK][attrNum];
        this.clusterFlags = initialGroupFlags;


//        int attributes = datas[0].length;
//
//        clusters = new Clustering[groupNum];
//
//        for (int i = 0; i < k; i++) {
//            Clustering<double[]> cluster = new Clustering<>();
//            cluster.center = new double[attributes];
//            cluster.unitArray = new ArrayList<>();
//            clusters[i] = cluster;
//        }
//
//        for (int i = 0; i < initialGroupFlags.length; i++) {
//            int groupFlag = initialGroupFlags[i];
//            Clustering<double[]> cluster = clusters[groupFlag];
//            cluster.unitArray.add(datas[i]);
//        }
//
//        Random random = new Random();
//        // 隨機設定中心點
//        for (Clustering<double[]> cluster : clusters) {
//            int index = random.nextInt(cluster.unitArray.size());
//            System.arraycopy(cluster.unitArray.get(index), 0, cluster.center, 0, attributes);
//        }
    }

    @Override
    public void updateClusters(int[] groupFlags) {
        if (this.clusterFlags != groupFlags) {
            this.clusterFlags = groupFlags;
        }

//        for (Clustering<double[]> clustering : clusters) {
//            clustering.unitArray.clear();
//        }
//
//        for (int i = 0; i < groupFlags.length; i++) {
//            int groupFlag = groupFlags[i];
//            Clustering<double[]> cluster = clusters[groupFlag];
//            cluster.unitArray.add(datas[i]);
//        }
    }

    @Override
    public void initCenter(Random random) {
        for (int i = 0; i < groupNum; i++) {
            double[] data = datas[random.nextInt(datas.length)];
            System.arraycopy(data, 0, centers[i], 0, attrNum);
        }
    }

}
