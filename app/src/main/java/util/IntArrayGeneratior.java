package util;

import java.util.Random;

/**
 * Utility class for generating integer arrays.
 */
public class IntArrayGeneratior {
  private Random random = new Random();
  
  public IntArrayGeneratior(long seed) {
    this.random = new Random(seed);
  }

  /**
   * Generates an array of n random integers between min (inclusive) and max (exclusive).
   * @param n - size of the array
   * @param min - minimum value (inclusive)
   * @param max - maximum value (exclusive)
   * @return an array of n random integers
   * @throws IllegalArgumentException if n <= 0 or min >= max
   */
  public int[] generate(int n, int min, int max) {
    if (n <= 0) {
      throw new IllegalArgumentException("Size must be greater than 0");
    }
    if (min >= max) {
      throw new IllegalArgumentException("Max must be greater than min");
    }
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = random.nextInt(min, max);
    }
    return array;
  }
}
