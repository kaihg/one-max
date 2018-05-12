package vo;

public class Swarm {

    public double[] inertia;
    public double localScore;
    public double[] localBest;
    public double[] globalBest;
    public double[] position;

    public Swarm(int dimension) {
        this.inertia = new double[dimension];
        this.localBest = new double[dimension];
        this.position = new double[dimension];
    }

}
