package transition;

import vo.PsoParam;
import vo.Swarm;

import java.util.Random;

public class SwarmTransition implements LongTransition<Swarm[]> {

    private PsoParam param;

    private int dimLength;
    private double[] temp;
    private double[] globalBest;
    private Random random;

    public SwarmTransition(PsoParam param) {
        this.param = param;
        dimLength = param.dimRange.length;
        temp = new double[dimLength];
    }

    @Override
    public void update(Swarm[] swarms) {
        for (Swarm swarm : swarms) {
            double v, p;
            for (int dim = 0; dim < dimLength; dim++) {
                v = param.inertiaWeight * swarm.inertia[dim] + param.localWeight * random.nextDouble() * (swarm.localBest[dim] - swarm.position[dim]) + param.globalWeight * random.nextDouble() * (swarm.globalBest[dim] - swarm.position[dim]);
                v = Math.max(-param.maxSpeed, Math.min(v, param.maxSpeed));

                p = swarm.position[dim] + v;
                p = Math.max(param.dimRange[dim].min, Math.min(p, param.dimRange[dim].max));

                swarm.inertia[dim] = p - swarm.position[dim];
                swarm.position[dim] = p;
            }
        }
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void updateGlobalBest(double[] best) {

    }

    @Override
    public void setDefaultValue(String value) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void neighbor(Swarm[] current, Swarm[] empty) {

    }

    @Override
    public Swarm[] next() {
        return null;
    }
}
