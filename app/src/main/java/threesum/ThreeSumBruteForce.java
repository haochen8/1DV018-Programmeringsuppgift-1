package threesum;

import java.util.ArrayList;
import java.util.List;

/**
 * A brute-force implementation to solve the 3-sum problem.
 */
public final class ThreeSumBruteForce {
  private ThreeSumBruteForce() {
  }

  public static int[] findThreeSum(int[] numbers, int target) {
    if (numbers == null) {
      throw new IllegalArgumentException("Numbers must not be null");
    }
    if (numbers.length < 3) {
      return null;
    }

    int n = numbers.length;
    for (int i = 0; i < n - 2; i++) {
      for (int j = i + 1; j < n - 1; j++) {
        for (int k = j + 1; k < n; k++) {
          if ((long) numbers[i] + numbers[j] + numbers[k] == target) {
            return new int[] { numbers[i], numbers[j], numbers[k] };
          }
        }
      }
    }
    return null;
  }

  public static List<int[]> solveAllZeroSum(int[] numbers) {
    if (numbers == null) {
      throw new IllegalArgumentException("Input array must not be null");
    }
    List<int[]> results = new ArrayList<>();
    int n = numbers.length;
    for (int i = 0; i < n - 2; i++) {
      for (int j = i + 1; j < n - 1; j++) {
        for (int k = j + 1; k < n; k++) {
          if ((long) numbers[i] + numbers[j] + numbers[k] == 0) {
            results.add(new int[] { numbers[i], numbers[j], numbers[k] });
          }
        }
      }
    }
    return results;
  }
}
