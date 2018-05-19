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
Keep the better neighbor for next iteration.

### Simulation Annealing
Like Hill Climbing, but has the probability to choose the worse neighbor.
The probability is base on T, temperature. The temperature will decrease every iteration. The Simulation Annealing will stop while the temperature reach the lowest setting.
However, the annealing speed is the essential. The result will not be good while speed is too fast. But take too mach time on low speed.

In the implement, I set the scale is (iteration - 1)/iteration. And the probability is e^((s1 - s2)/T).
T = T * scale after one iteration finished. The model will stop if T < 0.0001.

### Tabu Search
The Tabu memory queue size is 7. It is a magic number which defined by the inventor.
It will not choose the neighbor who is in the memory queue.

### Genetic Algorithm
With population, crossover rate, mutation rate, it will find the fitness one.
However, it is not fast as Hill Climbing.

### Genetic Algorithm for TKP
解背包問題，參數意義如下
長度為10的array。
重覆30次找最佳解，每個model跑1000次iteration，只找一個鄰居(無用)
population 大小為 20，交配機率=0.6, 突變機率=0.1
限重 20，石頭為 1 2,3 4,5 6,7 8 ,9 10 ,1 1, 2 2, 3 3, 4 4, 5 5，兩兩一組，為 weight, value

參數最後如下
ga_tkp 10 30 1000 1 20 0.6 0.1 20 1 2 3 4 5 6 7 8 9 10 1 1 2 2 3 3 4 4 5 5

## Extra Problem Solve
### Travel Knapsack Problem
假設有一個背包的負重最多可達8公斤，而希望在背包中裝入負重範圍內可得之總價物品，假設是水果好了，水果的編號、單價與重量如下所示：

0.	李子	4KG	NT$4500
1.	蘋果	5KG	NT$5700
2.	橘子	2KG	NT$2250
3.	草莓	1KG	NT$1100
4.	甜瓜	6KG	NT$6700

最佳解為放入草莓、橘子與蘋果，而總價為9050元。

The parameters of algorithms is written in param.json.
The best one is Genetic, and others is sa, tabu, hc.


## Versions
### ExhaustionSearch (2018/3/16)
1. Implement the exhaustion search.
2. You can control the size of N with "Main.class es ${N}"
3. Build the prototype of a framework.

### Hill Climbing and Simulation Annealing (2018/3/17)
1. Implement the hill climbing and simulation annealing model.
2. Add repeat property. It will run model times, and return the best one.
3. Add test.

### Tabu Search (2018/3/29)
1. Implement the Tabu search and test

### Genetic Algorithm (2018/3/31)
1. Implement the Genetic algorithm and test.

### TKP (2018/4/20)
1. Add CompareModel to compare algorithms
2. It is only for TKP now.
3. Add Factory function to read setting from json file

### Genetic K-means Algorithm (2018/5/20)
1. Add a class extend Genetic, but find clusters
2. Start with 'ga_kmean param.json' will start it with parameter from file.
3. Support less clusters setting (K) if it is better than default.