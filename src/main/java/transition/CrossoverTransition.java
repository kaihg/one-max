package transition;

import java.util.Random;

public class CrossoverTransition implements LongTransition<int[][]> {

    private double rate;

    CrossoverTransition(double rate) {
        this.rate = rate;
    }

    @Override
    public void update(int[][] obj) {

    }

    @Override
    public void setDefaultValue(String value) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void neighbor(int[][] current, int[][] empty) {
        Random random = new Random();

        int half = current.length / 2;
        for (int i = 0; i < half; i++) {
            int[] item1 = current[i];
            int[] item2 = current[i + half];
            int[] child1 = empty[i];
            int[] child2 = empty[i + half];
            int length = item1.length;

            if (random.nextDouble() < rate) {
                int crossIndex = random.nextInt(item1.length - 2) + 1;

                System.arraycopy(item1, 0, child1, 0, crossIndex);
                System.arraycopy(item2, crossIndex, child1, crossIndex, length - crossIndex);

                System.arraycopy(item2, 0, child2, 0, crossIndex);
                System.arraycopy(item1, crossIndex, child2, crossIndex, length - crossIndex);
            } else {
                System.arraycopy(item1, 0, child1, 0, length);
                System.arraycopy(item2, 0, child2, 0, length);
            }
        }
    }

    @Override
    public int[][] next() {
        return new int[0][];
    }
}
