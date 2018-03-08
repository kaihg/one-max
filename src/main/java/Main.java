public class Main {
    public static void main(String[] args) {
        int testN = 5;
        if (args.length > 0) {
            try {
                testN = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Main main = new Main();
        main.findAllObj(testN);
        System.out.println(String.format("The one-max result of 2^%d is %s, it's score is %d", testN, main.getBestObj(), main.getBestScore()));
    }


    private long bestScore = -1;
    private long bestObj;


    void findAllObj(int n) {
        long maxValue = (long) Math.pow(2, n);

//        LongStream one = LongStream.iterate(0, x -> x + 1).limit(maxValue);
//        one.forEach(this::checkBestObj);

        for (long i = 0; i < maxValue; i++) {
            checkBestObj(i);
        }

    }


    private void checkBestObj(long value) {
        long score = computerScore(value);
        if (score > bestScore) {
            bestScore = score;
            bestObj = value;
        }
    }

    int computerScore(long value) {
        return Long.bitCount(value);
    }

    public long getBestScore() {
        return bestScore;
    }

    public String getBestObj() {
        return Long.toBinaryString(bestObj);
    }
}
