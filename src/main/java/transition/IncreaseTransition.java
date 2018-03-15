package transition;

public class IncreaseTransition implements LongTransition {

    private long current;
    private long max;
    private int[] currentAry;

    public IncreaseTransition(long start, int bitCount) {
        this.current = start;
        this.max = 1 << bitCount;

        currentAry = new int[bitCount];
        for (int i = 0; i < bitCount; i++) {
            currentAry[i] = (int) ((start >> i) & 1);
        }
    }

    @Override
    public boolean hasNext() {
        return current < max;
    }

    @Override
    public int[] next() {
        for (int i = 0; i < currentAry.length; i++) {
//            currentAry[i] = (int) ((current >> i) & 1);
            if (currentAry[i] == 0) {
                currentAry[i] = 1;
                break;
            } else {
                currentAry[i] = 0;
            }
        }
        current++;

        return currentAry;
    }
}
