package transition;

import java.util.Random;

class RandomNeighborTransition implements LongTransition<int[]> {

    private int[] currentAry;
    private int bitNumber;
    private boolean isFirst = true;

    private Random random;

    public RandomNeighborTransition(long start, int bitNumber) {
        this.random = new Random();
        this.bitNumber = bitNumber;
        this.currentAry = new int[bitNumber];
        LongTransition.convertLongToIntAry(bitNumber, start, currentAry);
    }


    @Override
    public void setDefaultValue(String value) {
        int length = value.length();
        for (int i = 0; i < currentAry.length; i++) {
            int bit = i > length ? 0 : value.charAt(length - i - 1) - '0';
            currentAry[i] = bit;
        }

        isFirst = true;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void neighbor(int[] current, int[] empty) {
        int bit = random.nextInt(bitNumber);
        for (int i = 0; i < current.length; i++) {
            empty[i] = i == bit ? current[i] ^ 1 : current[i];
        }
    }

    @Override
    public int[] next() {
        if (isFirst) {
            isFirst = false;
            return currentAry;
        } else {
            int bit = random.nextInt(bitNumber);
            currentAry[bit] ^= 1;
            return currentAry;
        }

    }

}
