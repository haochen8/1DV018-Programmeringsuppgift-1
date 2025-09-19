# Union-Find and Three-Sum programming experiment

## Overview
This repository contains the implementation and analysis of two classic algorithms: Union-Find and Three-Sum
- Union–Find: Quick-Find, Quick-Union (+ benchmarks)
- Three-Sum: Brute-force, Two-pointer (+ benchmarks)
Tested with JUnit 5.

## Prerequisites
- Java 21 or higher
- Gradle 7.6 or higher

## Build and test
To build the project and run tests, use the following command:
```
./gradlew clean test
```

## Run: Union-Find benchmark
By default, `./gradlew run` executes `bench.UFBenchmark`.
Common Properties (pass with ``-Pkey=value``):
- `-Pscenario`: Scenario name (default: `balanced`)
- `-PNs`: Sizes of N, comma-separated (default: `1000,10000,100000`)
- `-PopsPerN`: Number of operations (default: `50000`)
- `-PunionRatio`: Ratio of union operations (default: `0.50`)
- `-PopsFactor`: Factor of ops relative to N (default: `0`, means not used)
- `-Preps`: Number of repetitions (default: `3`)
- `-PprintHeader`: Output CSV file (default: `uf_results.csv`)

Example:
```
Balanced mix:
./gradlew -q run \
  -Pscenario=balanced \
  -PNs=1000,10000,100000 \
  -PunionRatio=0.5 \
  -PopsPerN=50000 \
  -Preps=5 \
  --console=plain > uf_results.csv
```
Scale with N (ops = 10 * N):
```
./gradlew -q run \
  -Pscenario=scale_in_N \
  -PNs=1000,2000,5000,10000,20000,50000 \
  -PunionRatio=0.5 \
  -PopsFactor=10 \
  -Preps=5 \
  -PprintHeader=false \
  --console=plain >> uf_results.csv
```
Mix sensitivity (N=100k, ops=1M):
```
./gradlew -q run \
  -Pscenario=mix_sensitivity \
  -PNs=100000 \
  -PunionRatio=0.5 \
  -PopsPerN=1000000 \
  -Preps=5 \
  -PprintHeader=false \
  --console=plain >> uf_results.csv
```
## Run: Three-Sum benchmark
Use the dedicated task `runThreeSumBench` (configured in `build.gradle.kts`) which runs `bench.ThreeSumBenchmark`.

Common: `scenario, printHeader, gcBetweenReps, reps, seeds`

Data generation: `minVal, maxVal, dupRatio (0..1; higher ⇒ more duplicates)`

Sizes:

`NsBF` (csv ints): N-values for Brute Force

`NsTP` (csv ints): N-values for Two-Pointer

Examples:
Random uniform data:
```
./gradlew -q runThreeSumBench \
  -Pscenario=random_uniform \
  -PNsBF=50,100,150,200,300 \
  -PNsTP=1000,2000,5000,10000,20000 \
  -PminVal=-1000 -PmaxVal=1000 \
  -Preps=5 -Pseeds=42 \
  --console=plain >> threesum_results.csv
```
Duplicates-heavy data:
```
./gradlew -q runThreeSumBench \
  -Pscenario=duplicate_heavy \
  -PNsBF=50,100,150,200,300 \
  -PNsTP=1000,2000,5000,10000,20000,50000 \
  -PminVal=-100 -PmaxVal=100 \
  -PdupRatio=0.8 \
  -Preps=5 -Pseeds=42 \
  -PprintHeader=false \
  --console=plain >> threesum_results.csv
```

## Output format
- UFBenchmark: `scenario,variant,N,ops,union_ratio,seed,rep,ms,ms_per_op`
- ThreeSumBenchmark: `scenario,variant,N,min,max,dupRatio,seed,rep,ms,n2_norm,n3_norm,num_triplets`
Where:
- `ms`: median wall time for the run
- `n2_norm = ms / N^2` (≈ constant for Two-Pointer),
- `n3_norm = ms / N^3` (≈ constant for Brute Force).
