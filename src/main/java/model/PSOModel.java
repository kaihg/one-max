package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;
import transition.SwarmTransition;
import vo.DimensionRange;
import vo.PsoParam;
import vo.Swarm;

import java.util.Arrays;
import java.util.Random;

public class PSOModel implements AlgorithmModel {

    private SwarmTransition transition;
    private EvaluateFunction<double[]> evaluateFunction;

    private Random random;

    private int iteration;
    private int swarmSize;

    private Swarm[] swarms;
    private double[] globalBest;
    private double globalScore;
    private DimensionRange[] dimRanges;
    private double maxSpeed;

    public PSOModel(int iteration, PsoParam param) {
        this.swarmSize = param.swarmSize;
        this.iteration = iteration;
        this.dimRanges = param.dimRange;
        this.maxSpeed = param.maxSpeed;

        int dimensions = param.dimRange.length;
        globalBest = new double[param.dimRange.length];
        swarms = initSwarms(dimensions, param);
    }

    private Swarm[] initSwarms(int dimensions, PsoParam param) {
        Swarm[] swarms = new Swarm[param.swarmSize];
        for (int i = 0; i < param.swarmSize; i++) {
            Swarm swarm = new Swarm(dimensions);
            swarms[i] = swarm;
        }
        return swarms;
    }

    @Override
    public void init(int seed) {
        this.random = new Random(seed);

        this.globalScore = Double.MAX_VALUE;

        transition.setRandom(random);
        int dimensions = globalBest.length;

        for (int i = 0; i < swarmSize; i++) {
            Swarm swarm = swarms[i];

            // init inertia and position
            for (int dim = 0; dim < dimensions; dim++) {
                swarm.position[dim] = getRandomDouble(random, dimRanges[dim].max, dimRanges[dim].min);
                swarm.inertia[dim] = getRandomDouble(random, maxSpeed, -maxSpeed);
            }
            // local best is equal to position at initial
            System.arraycopy(swarm.position, 0, swarm.localBest, 0, dimensions);
            swarm.localScore = evaluateFunction.evaluate(swarm.localBest);

            // use same global answer
            swarm.globalBest = globalBest;
            checkGlobalBest(swarm.localScore, swarm.position);
        }

    }

    private void checkSwarmLocalBest(Swarm swarm) {
        double score = evaluateFunction.evaluate(swarm.position);
        if (score < swarm.localScore) {
            System.arraycopy(swarm.position, 0, swarm.localBest, 0, swarm.localBest.length);
            swarm.localScore = score;
        }
    }

    private void checkGlobalBest(double score, double[] challenger) {
        if (score < this.globalScore) {
            System.arraycopy(challenger, 0, globalBest, 0, globalBest.length);
            this.globalScore = score;
        }
    }

    private double getRandomDouble(Random random, double max, double min) {
        return random.nextDouble() * (max - min) + min;
    }

    @Override
    public void setTransit(LongTransition transit) {
        this.transition = (SwarmTransition) transit;
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
        this.evaluateFunction = evaluator;
    }

    @Override
    public void start() {
        init((int) System.currentTimeMillis());
        double stopCap = 1 >> 10;

        for (int i = 0; i < iteration; i++) {
            iterateOnce();

            if (Math.abs(this.globalScore - evaluateFunction.maxScore(null)) < stopCap) {
                break;
            }
        }
    }

    @Override
    public void iterateOnce() {
        transition.update(this.swarms);

        for (Swarm swarm : swarms) {
            checkSwarmLocalBest(swarm);
            checkGlobalBest(swarm.localScore, swarm.position);
        }
    }


    @Override
    public String getResult() {
        return Arrays.toString(globalBest);
    }

    @Override
    public double getScore() {
        return globalScore;
    }
}
