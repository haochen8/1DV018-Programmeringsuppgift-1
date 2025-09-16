package util;

/**
 * A simple stopwatch utility for measuring elapsed time.
 */
public class Stopwatch {
  private long start;
  
  public void start() {
    start = System.nanoTime();
  }

  public long elapsedNanoTime() {
    return System.nanoTime() - start;
  }

  public double elapsedMillis() {
    return elapsedNanoTime() / 1_000_000.0;
  }
}
