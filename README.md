# one-max
The implement of different algorithms to solve the one-max problem, with Java8.
The score means how many '1' in a binary array.
For example, the score of "10101" is 3, and "11111" get 5.

## Max N
The limit of max N is on your memory.

However, the cost of time is very large on Exhaustion model.
On the meanwhile, the Hill Climbing model and Simulation Annealing model is much faster.
Both of they are less then 10 sec even when N = 1000.

## Implement Algorithms

### Exhaustion Search
It takes about 12 ~ 15 seconds on my Mac-Air when N = 30.
You can only control the size of N in this version.

### Hill Climbing

### Simulation Annealing
Like Hill Climbing, but has the probability to choose the worse neighbor.
The probability is base on T, temperature. The temperature will decrease every iteration. The Simulation Annealing will stop while the temperature reach the lowest setting.
However, the annealing speed is the essential. The result will not be good while speed is too fast. But take too mach time on low speed.

In the implement, I set the scale is (iteration - 1)/iteration. And the probability is e^((s1 - s2)/T).
T = T * scale after one iteration finished. The model will stop if T < 0.0001.

## Versions
### ExhaustionSearch (2018/3/16)
1. Implement the exhaustion search.
2. You can control the size of N with "Main.class es ${N}"
3. Build the prototype of a framework.

### Hill Climbing and Simulation Annealing (2018/3/17)
1. Implement the hill climbing and simulation annealing model.
2. Add repeat property. It will run model times, and return the best one.
3. Add test.