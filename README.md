# one-max
The implement of different algorithms to solve the one-max problem, with Java8.
The score means how many '1' in a binary array.
For example, the score of "10101" is 3, and "11111" get 5.

## Max N
The max N is 62 on my Mac Air. In fact, it is the binary digital of Long.MAX_VALUE.

However, the cost time is very large on Exhaustion model.

## Implement Algorithms

### Exhaustion Search
It takes about 12 ~ 15 seconds on my Mac-Air when N = 30.
You can only control the size of N in this version.



## Versions
### ExhaustionSearch (2018/3/16)
1. Implement the exhaustion search.
2. You can control the size of N with "Main.class es ${N}"
3. Build the prototype of a framework.
