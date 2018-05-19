package transition;

public class ClusteringUnitTransition implements LongTransition<int[][]> {

    private GeneticTransition geneticTransition;

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
        this.geneticTransition.neighbor(current, empty);

    }

    @Override
    public int[][] next() {
        return new int[0][];
    }
}
