package bench;

import unionfind.UnionFind;
import util.Stopwatch;
import java.util.*;
import java.util.function.IntFunction;

/**
 * Benchmarking utility for Union-Find implementations.
 */
public class UFBenchmark {

  private record OperationResult(boolean isUnion, int p, int q) {
  }

  // Helpers: properties & parsing
  private static String prop(String key, String def) {
    String v = System.getProperty(key);
    return (v == null || v.isBlank()) ? def : v.trim();
  }

  private static boolean propBool(String key, boolean def) {
    return Boolean.parseBoolean(prop(key, Boolean.toString(def)));
  }

  private static int propInt(String key, int def) {
    try {
      return Integer.parseInt(prop(key, Integer.toString(def)));
    } catch (NumberFormatException e) {
      return def;
    }
  }

  private static double propDouble(String key, double def) {
    try {
      return Double.parseDouble(prop(key, Double.toString(def)));
    } catch (NumberFormatException e) {
      return def;
    }
  }

  private static int[] propIntList(String key, String def) {
    String s = prop(key, def);
    String[] parts = s.split("[,;\\s]+");
    return Arrays.stream(parts).filter(p -> !p.isBlank()).mapToInt(Integer::parseInt).toArray();
  }

  private static long[] propLongList(String key, String def) {
    String s = prop(key, def);
    String[] parts = s.split("[,;\\s]+");
    return Arrays.stream(parts).filter(p -> !p.isBlank()).mapToLong(Long::parseLong).toArray();
  }

  /**
   * Runs a benchmark on the provided Union-Find implementation.
   * 
   * @param n               - number of elements
   * @param operationsCount - number of operations to perform
   * @param unionRatio      - ratio of union operations (between 0 and 1)
   * @param long            seed - seed for random number generator
   * @return a list of generated operations
   */
  private static List<OperationResult> generateOperations(int n, int operationsCount, double unionRatio, long seed) {
    Random rand = new Random(seed);
    List<OperationResult> operations = new ArrayList<>(operationsCount);
    for (int i = 0; i < operationsCount; i++) {
      int p = rand.nextInt(n);
      int q = rand.nextInt(n);
      boolean isUnion = rand.nextDouble() < unionRatio;
      operations.add(new OperationResult(isUnion, p, q));
    }
    return operations;
  }

  /**
   * Measures the time taken to perform a series of union and connected
   * operations.
   * 
   * @param factory    - a function that creates a Union-Find instance given n
   * @param n          - number of elements
   * @param operations - list of operations to perform
   * @return time taken in milliseconds
   */
  private static double measureMillis(IntFunction<UnionFind> factory, int n, List<OperationResult> operations) {
    // Warm up JVM
    for (int w = 0; w < 1; w++) {
      UnionFind warm = factory.apply(n);
      for (OperationResult op : operations) {
        if (op.isUnion()) {
          warm.union(op.p(), op.q());
        } else {
          warm.find(op.p());
        }
      }
    }
    // Measure time
    UnionFind uf = factory.apply(n);
    Stopwatch stopwatch = new Stopwatch();
    stopwatch.start();
    for (OperationResult innerOp : operations) {
      if (innerOp.isUnion()) {
        uf.union(innerOp.p(), innerOp.q());
      } else {
        uf.find(innerOp.p());
      }
    }
    return stopwatch.elapsedMillis();
  }

  /**
   * Main method to run benchmarks.
   * 
   * @param args - command line arguments
   */
  public static void main(String[] args) {

    // Parameters
    String scenario = prop("scenario", "balanced");
    int[] Ns = propIntList("Ns", "1000,10000,100000");
    double unionRatio = propDouble("unionRatio", 0.5);
    int opsPerN = propInt("opsPerN", 50_000);
    double opsFactor = propDouble("opsFactor", -1.0);
    int repetitions = propInt("reps", 5);
    long[] seeds = propLongList("seeds", "42");
    boolean printHeader = propBool("printHeader", true);
    boolean gcBetweenReps = propBool("gcBetweenReps", true);

    List<Map.Entry<String, IntFunction<UnionFind>>> impls = new ArrayList<>();
    impls.add(Map.entry("QuickFind", unionfind.QuickFind::new));
    impls.add(Map.entry("QuickUnion", unionfind.QuickUnion::new));

    if (printHeader) {
      System.out.println("scenario,variant,N,ops,union_ratio,seed,rep,ms,ms_per_op");
    }

    for (int N : Ns) {
      int ops = (opsFactor >= 0) ? (int) Math.round(opsFactor * N) : opsPerN;
      if (ops <= 0)
        throw new IllegalArgumentException("ops must be > 0 (got " + ops + ")");
      for (long seed : seeds) {
        List<OperationResult> operations = generateOperations(N, ops, unionRatio, seed);

        for (Map.Entry<String, IntFunction<UnionFind>> impl : impls) {
          String name = impl.getKey();
          IntFunction<UnionFind> factory = impl.getValue();

          double[] times = new double[repetitions];
          for (int r = 0; r < repetitions; r++) {
            times[r] = measureMillis(factory, N, operations);
            if (gcBetweenReps)
              System.gc();
          }
          Arrays.sort(times);
          double median = times[repetitions / 2];
          double msPerOp = median / ops;

          System.out.printf(
              Locale.ROOT,
              "%s,%s,%d,%d,%.2f,%d,%s,%.3f,%.9f%n",
              scenario, name, N, ops, unionRatio, seed, "median", median, msPerOp);
        }
      }
    }
  }
}
