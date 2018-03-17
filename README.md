# one-max
The implement of different algorithms to solve the one-max problem, with Java8.
The score means how many '1' in a binary array.
For example, the score of "10101" is 3, and "11111" get 5.

## Max N
The limit of max N is on the basis of memory.

However, the cost time is very large on Exhaustion model.
By the way, the Hill Climbing model and Simulation Annealing model is much faster. Even when N = 1000.

## Implement Algorithms

### Exhaustion Search
It takes about 12 ~ 15 seconds on my Mac-Air when N = 30.
You can only control the size of N in this version.

### Hill Climbing


## Versions
### ExhaustionSearch (2018/3/16)
1. Implement the exhaustion search.
2. You can control the size of N with "Main.class es ${N}"
3. Build the prototype of a framework.

### Hill Climbing ans Simulation Annealing (2018/3/17)
1. Implement the hill climbing and simulation annealing model.
2. Add repeat property. It will run model times, and return the best one.
3. Add test.