package threesum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A brute-force implementation to solve the 3-sum problem.
 */
public final class ThreeSumBruteForce {
  private ThreeSumBruteForce() {
  }

  /**
   * Finds three numbers in the array that sum up to the target value using a
   * brute-force approach.
   * 
   * @param numbers the input array of integers
   * @param target  the target sum to find
   * @return array of three integers that sum to target, or null if no solution
   *         exists
   * @throws IllegalArgumentException if numbers is null
   */
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
            // Sort the three numbers before returning
            int a = numbers[i], b = numbers[j], c = numbers[k];
            if (a > b) {
              int temp = a;
              a = b;
              b = temp;
            }
            if (b > c) {
              int temp = b;
              b = c;
              c = temp;
            }
            if (a > b) {
              int temp = a;
              a = b;
              b = temp;
            }
            return new int[] { a, b, c };
          }
        }
      }
    }
    return null;
  }

  public static List<int[]> solveAllZeroSum(int[] numbers) {
    if (numbers == null) {
      throw new IllegalArgumentException("Numbers must not be null");
    }
    Set<String> seen = new LinkedHashSet<>();
    List<int[]> results = new ArrayList<>();
    int n = numbers.length;
    for (int i = 0; i < n - 2; i++) {
      for (int j = i + 1; j < n - 1; j++) {
        for (int k = j + 1; k < n; k++) {
          if ((long) numbers[i] + numbers[j] + numbers[k] == 0) {
            // Sort the triplet before adding to results
            int a = numbers[i], b = numbers[j], c = numbers[k];
            if (a > b) {
              int temp = a;
              a = b;
              b = temp;
            }
            if (b > c) {
              int temp = b;
              b = c;
              c = temp;
            }
            if (a > b) {
              int temp = a;
              a = b;
              b = temp;
            }
            String key = a + "," + b + "," + c;
            if (!seen.contains(key)) {
              seen.add(key);
              results.add(new int[] { a, b, c });
            }
          }
        }
      }
    }
    // Sort the results based on the first element of each triplet
    results.sort(Comparator
        .comparingInt((int[] temp) -> temp[0])
        .thenComparingInt(temp -> temp[1])
        .thenComparingInt(temp -> temp[2]));
    return results;
  }
}
