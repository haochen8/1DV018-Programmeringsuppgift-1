# 1DV018-Programming Assignment 1

## Task 3 - Analyze and present results from Union-Find experiments
The results of the experiments are presented in the file `uf_results.csv`. The file contains the results of running the union-find algorithms with different parameters. Each line in the file represents a single experiment and contains the following fields:

- `scenario`: The scenario under which the experiment was run (e.g., balanced, scale_in_N, mix_sensitivity).
- `variant`: The variant of the union-find algorithm used (e.g., QuickFind, QuickUnion).
- `N`: The size of the input (number of elements).
- `ops`: The number of union/find operations performed.
- `union_ratio`: The ratio of union operations to find operations.
- `seed`: The random seed used for the experiment.
- `rep`: The repetition number of the experiment.
- `ms`: The total time taken for the experiment (in milliseconds).
- `ms_per_op`: The average time per operation (in milliseconds).
The results are organized into three main sections based on the scenarios:
1. Balanced: Experiments with a balanced ratio of union and find operations (50% union):
   - Parameters: N = 1000, 10000, 100000; ops = 50000; union_ratio = 0.50;
   - Observations: QuickFind's time increases significantly with larger N, while QuickUnion remains efficient almost flat.
   - Conclusion: QuickUnion is more scalable for larger datasets with balanced operations.  
2. Scale in N: Experiments that vary the size of the input (N) while keeping other parameters constant.
    - Parameters: N = 1k, 2k, 5k, 10k, 20k, 50k, 100k; ops = 10 * N; union_ratio = 0.50;
    - Observations: QuickFind's time increases linearly with N, while QuickUnion's time increases proportionally to ops and remains constant.
    - Conclusion: QuickUnion is more efficient for larger datasets, while QuickFind becomes impractical as N increases.
3. Mix sensitivity: Experiments that vary the union ratio to analyze the sensitivity of the algorithms to different mixes of operations.
    - Parameters: N = 100k; ops = 1M; union_ratio = 0.10, 0.50, 0.90;
    - Observations: QuickFind's time increases with higher union ratios, while QuickUnion remains relatively stable.
    - Conclusion: Quick-Find is very sensitive to the mix of operations, while Quick-Union is more robust across different mixes.

Overall, on random datasets, Quick-Union outperforms Quick-Find in terms of scalability and efficiency, especially for larger datasets and varying operation mixes. Quick-Find is also sensitive to the mix of operations, making it less suitable for scenarios with a high ratio of union operations. The results highlight the importance of choosing the right algorithm based on the expected workload and dataset size.
