package transition;

import java.util.Random;

public class MutationTransition implements LongTransition<int[][]> {

    private double rate;

    public MutationTransition(double rate) {
        this.rate = rate;
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
        for (int i = 0; i < current.length; i++) {
            int[] item = current[i];
            int[] child = empty[i];
            int length = item.length;
            System.arraycopy(item, 0, child, 0, length);

            if (random.nextDouble() < rate) {
                int index = random.nextInt(length);
                child[index] ^= 1;
            }
        }
    }

    @Override
    public int[][] next() {
        return new int[0][];
    }
}
