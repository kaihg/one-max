package transition;

import java.util.Random;

public class MutationTransition implements LongTransition<int[][]> {

    private double rate;
    private int maxRange;

    public MutationTransition(double rate) {
        this(rate, 1);
    }

    public MutationTransition(double rate, int maxRange) {
        this.rate = rate;
        this.maxRange = maxRange;
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
        for (int i = 0; i < current.length; i++) {
            int[] item = current[i];
            int[] child = empty[i];
            int length = item.length;
            System.arraycopy(item, 0, child, 0, length);

            if (random.nextDouble() < rate) {
                int index = random.nextInt(length);
                int curValue = child[index];
                int value;
                do {
                    value = random.nextInt(maxRange + 1);
                } while (value == curValue);
                child[index] = value;
//                child[index] ^= 1;
            }
        }
    }

    @Override
    public int[][] next() {
        return new int[0][];
    }
}
