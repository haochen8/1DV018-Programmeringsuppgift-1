# Report

# Experiment summary
I benchmarked two algorithms:
- Union–Find (Task 3):
Quick-Find and Quick-Union on randomized operation sequences.

Finding: Quick-Find scales poorly with N (union = O(N)), while Quick-Union shows near constant time per operation on randomized inputs. Results matched the theory.
- Three-Sum (Task 6):
Brute Force and Two-Pointer (after sorting).

Finding: Normalized timings confirm the expected growth: n3_norm = ms/N³ ~ constant for Brute Force; n2_norm = ms/N² ~ constant for Two-Pointer.

## Union-Find (Task 3)

### Observations
**Quick-Find (balanced fixed operations)**:
    - As N increases, the time taken for union operations grows significantly, confirming the O(N) complexity.
**Quick-Union (balanced fixed operations)**:
    - As N increases, the time taken for both union and find operations remains relatively constant, confirming the near-constant time complexity.
    - The tree structure helps to keep the depth small, resulting in efficient operations even for larger datasets.
**Quick-Find (Scale with N (ops = 10*N))**:
    - The performance is highly sensitive to the ratio of union to find operations, increasing significantly with more union operations.
**Quick-Union (varying operation mix)**:
    - The performance remains stable across different operation mixes, demonstrating the robustness of the Quick-Union algorithm.
    - Even with a high ratio of union operations, the time taken does not increase significantly.
**Quick-Find (Mix sensitivity)**:
    - The performance is highly sensitive to the ratio of union to find operations, increasing significantly with more union operations.
**Quick-Union (Mix sensitivity)**:
    - The performance remains stable, and  is far less sensitive to the union/find mix.


### Conclusion of the Union-Find experiment  
Overall, on random datasets, Quick-Union outperforms Quick-Find in terms of scalability and efficiency, especially for larger datasets and varying operation mixes. Quick-Find is also sensitive to the mix of operations, making it less suitable for scenarios with a high ratio of union operations. The results highlight the importance of choosing the right algorithm based on the expected workload and dataset size.

## Three-Sum (Task 6)

### Implementation details
- **Brute Force**: Triple nested loops to check all combinations of three numbers.
- **Two-Pointer**: Sort the array and use a two-pointer technique to find pairs and skip duplicates.

### Observations
- **Brute Force**:
    - The time taken grows rapidly with increasing N, confirming the O(N³) complexity.
    - Results show that n3_norm increases steeply, indicating that the algorithm becomes inefficient for larger datasets.
- **Two-Pointer**:
    - The time taken grows more slowly with increasing N, confirming the O(N²) complexity.
    - Results show that n2_norm remains relatively constant, indicating that the algorithm scales well with larger datasets.

### Conclusion of the Three-Sum experiment
The Two-Pointer algorithm is significantly more efficient than the Brute Force approach for the Three-Sum problem, especially as the size of the input array increases. The normalized timings confirm that the Two-Pointer method demonstrates better scalability and performance for larger datasets. This experiment highlights the importance of algorithm choice in solving computational problems efficiently.

## Overall Conclusion

Across both experiments, the choice of algorithm significantly impacts performance, especially as the size of the dataset increases. The Union-Find experiment demonstrated that Quick-Union is more efficient and scalable than Quick-Find, particularly for larger datasets and varying operation mixes. Similarly, the Three-Sum experiment highlighted the superiority of the Two-Pointer method over the Brute Force approach, confirming its better scalability and performance for larger input sizes. These findings underscore the importance of selecting appropriate algorithms based on the specific problem to achieve optimal performance.