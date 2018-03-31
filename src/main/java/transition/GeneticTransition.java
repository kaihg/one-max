package transition;

public class GeneticTransition implements LongTransition<int[][]> {

    private LongTransition<int[][]> crossover;
    private LongTransition<int[][]> mutation;

    private int[][] temp;

    GeneticTransition(double crossoverRate, double mutationRate) {
        this.crossover = new CrossoverTransition(crossoverRate);
        this.mutation = new MutationTransition(mutationRate);
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
        if (temp == null) {
            temp = new int[current.length][current[0].length];
        }

        crossover.neighbor(current, temp);
        mutation.neighbor(temp, empty);
    }

    @Override
    public int[][] next() {
        return new int[0][];
    }
}
