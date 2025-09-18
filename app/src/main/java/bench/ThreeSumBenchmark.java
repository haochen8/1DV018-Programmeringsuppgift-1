package bench;

import threesum.ThreeSumBruteForce;
import threesum.ThreeSumTwoPointer;
import java.util.*;
import java.util.function.Function;
import util.Stopwatch;

/**
 * A benchmarking class to compare the performance of different 3-sum
 * algorithms.
 * 
 * It supports two algorithms:
 * 1. Brute-force approach (O(N^3))
 * 2. Two-pointer technique (O(N^2))
 * 
 * The benchmark parameters can be configured via system properties:
 * - scenario: "all", "bruteforce", or "twopointer" (default: "all")
 * - printHeader: true/false (default: true)
 * - gcBetweenReps: true/false (default: true)
 * - reps: number of repetitions per configuration (default: 3)
 * - seeds: comma-separated list of random seeds (default:
 * "42,43,44,45,46,47,48,49,50,51")
 * - minVal: minimum integer value in generated arrays (default: -1000)
 * - maxVal: maximum integer value in generated arrays (default: 1000)
 * - dupRatio: ratio of duplicate values in generated arrays (default: 0.1)
 * - NsBF: comma-separated list of array sizes for brute-force (default:
 * "100,200,300,400,500")
 * - NsTP: comma-separated list of array sizes for two-pointer (default:
 * "1000,2000,3000,4000,5000")
 */
public final class ThreeSumBenchmark {

  private static String prop(String key, String def) {
    String v = System.getProperty(key);
    return (v == null || v.isBlank()) ? def : v.trim();
  }

  private static double propDouble(String key, double def) {
    try {
      return Double.parseDouble(System.getProperty(key, Double.toString(def)));
    } catch (NumberFormatException e) {
      return def;
    }
  }

  private static int propInt(String key, int def) {
    try {
      return Integer.parseInt(System.getProperty(key, Integer.toString(def)));
    } catch (NumberFormatException e) {
      return def;
    }
  }

  private static boolean propBool(String key, boolean def) {
    return Boolean.parseBoolean(System.getProperty(key, Boolean.toString(def)));
  }

  private static int[] propIntList(String key, String def) {
    String s = System.getProperty(key, def);
    String[] parts = s.split("[,;\\s]+");
    return Arrays.stream(parts).filter(p -> !p.isBlank()).mapToInt(Integer::parseInt).toArray();
  }

  private static long[] propLongList(String key, String def) {
    String s = System.getProperty(key, def);
    String[] parts = s.split("[,;\\s]+");
    return Arrays.stream(parts).filter(p -> !p.isBlank()).mapToLong(Long::parseLong).toArray();
  }

  private static int[] generateArray(int n, int minVal, int maxVal, long seed, double dupRatio) {
    Random rand = new Random(seed);
    int[] result = new int[n];
    int range = maxVal - minVal + 1;

    // Calculate pool size based on duplicate ratio and array size
    int poolSize = Math.max(1, (int) (n * (1.0 - dupRatio)));
    poolSize = Math.min(poolSize, range);

    // Create and fill pool with unique values
    int[] pool = new int[poolSize];
    for (int i = 0; i < poolSize; i++) {
      pool[i] = rand.nextInt(range) + minVal;
    }

    // Fill result array using pool values
    for (int i = 0; i < n; i++) {
      result[i] = pool[rand.nextInt(poolSize)];
    }

    return result;
  }

  private static double measureMillis(Function<int[], List<int[]>> solver, int[] array) {
    // Warm-up
    solver.apply(array);
    Stopwatch stopwatch = new Stopwatch();
    stopwatch.start();
    solver.apply(array);
    return stopwatch.elapsedMillis();
  }

  public static void main(String[] arg) {
    // Configuration
    String scenario = prop("scenario", "random_uniform");
    boolean printHeader = propBool("printHeader", true);
    boolean gcBetweenReps = propBool("gcBetweenReps", true);
    int reps = propInt("reps", 5);
    long[] seeds = propLongList("seeds", "42");

    // Array parameters
    int minVal = propInt("minVal", -1000);
    int maxVal = propInt("maxVal", 1000);
    double dupRatio = propDouble("dupRatio",
        scenario.equals("duplicate_heavy") ? 0.8 : 0.1);

    // Test sizes
    int[] NsBF = propIntList("NsBF", "100,200,300,400,500");
    int[] NsTP = propIntList("NsTP", "1000,2000,5000,10000,50000");

    // Solver map
    Map<String, Function<int[], List<int[]>>> solvers = new LinkedHashMap<>();
    solvers.put("bruteforce", ThreeSumBruteForce::solveAllZero);
    solvers.put("twopointer", ThreeSumTwoPointer::solveAllZero);

    if (printHeader) {
      System.out.println("scenario,variant,N,min,max,dupRatio,seed,rep,ms,n2_norm,n3_norm,num_triplets");
      } 

    // Run benchmarks
    for (Map.Entry<String, Function<int[], List<int[]>>> entry : solvers.entrySet()) {
      String algorithm = entry.getKey();
      Function<int[], List<int[]>> solver = entry.getValue();
      int[] sizes = algorithm.equals("bruteforce") ? NsBF : NsTP;

      for (int n : sizes) {
        for (long seed : seeds) {
          int[] array = generateArray(n, minVal, maxVal, seed, dupRatio);
          double[] times = new double[reps];
          int triplets = 0;

          for (int rep = 0; rep < reps; rep++) {
            if (gcBetweenReps)
              System.gc();
            times[rep] = measureMillis(solver, array);
            triplets = solver.apply(array).size();
          }

          Arrays.sort(times);
          double median = times[reps / 2];
          double n2Norm = median / ((double) n * n);
          double n3Norm = median / ((double) n * n * n);

          System.out.printf(Locale.ROOT,
              "%s,%d,%d,%s,%.3f,%.9e,%.9e,%d%n",
              algorithm, n, seed, "median", median, n2Norm, n3Norm, triplets);
        }
      }
    }
  }
}