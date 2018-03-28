package transition;

import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class MemoryTransition implements LongTransition {

    private static final int TABU_MAGIC_MEMORY_SIZE = 7;

    private int[] currentAry;
    private Random random;
    private int bitNumber;

    private Queue<int[]> memoryQueue;

    public MemoryTransition(long start, int bitNumber) {
        this.random = new Random();
        this.bitNumber = bitNumber;
        this.currentAry = new int[bitNumber];
        this.memoryQueue = new ArrayBlockingQueue<>(TABU_MAGIC_MEMORY_SIZE);
        LongTransition.convertLongToIntAry(bitNumber, start, currentAry);
    }

    @Override
    public void setDefaultValue(String value) {
        int length = value.length();
        for (int i = 0; i < currentAry.length; i++) {
            int bit = i > length ? 0 : value.charAt(length - i - 1) - '0';
            currentAry[i] = bit;
        }
        memoryQueue.clear();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void neighbor(int[] current, int[] empty) {

        int count = 0;
        int maxTry = 7;
        System.arraycopy(current, 0, empty, 0, bitNumber);
        while (count++ < maxTry) {
            int bit = random.nextInt(bitNumber);
            empty[bit] = empty[bit] ^ 1;

            if (!isInMemory(empty)) {
                System.arraycopy(empty, 0, currentAry, 0, bitNumber);
                break;
            }
        }


    }

    private void addNewMemory(int[] tempAry) {
        if (this.memoryQueue.size() == TABU_MAGIC_MEMORY_SIZE) {
            this.memoryQueue.remove();
        }
        this.memoryQueue.add(Arrays.copyOf(tempAry, bitNumber));
    }

    private boolean isInMemory(int[] neighbor) {
        for (int[] memory : memoryQueue) {
            if (Arrays.equals(memory, neighbor)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int[] next() {
        addNewMemory(currentAry);
        return currentAry;
    }
}
