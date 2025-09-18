package threesum;

import java.util.*;

/**
 * A class to solve the 3-sum problem using the two-pointer technique.
 */
public final class ThreeSumTwoPointer {
  public ThreeSumTwoPointer() {
  }

  // Reuses the efficient implementation from ThreeSum class
  public static int[] findThreeSum(int[] numbers, int target) {
    return ThreeSum.findThreeSum(numbers, target);
  }

  /**
   * Finds all unique triplets in the array that sum up to zero.
   * 
   * @param numbers the input array of integers
   * @return a list of unique triplets that sum to zero
   * @throws IllegalArgumentException if numbers is null
   */ 
  public static List<int[]> solveAllZero(int[] numbers) {
    if (numbers == null) {
      throw new IllegalArgumentException("Numbers must not be null");
    }
    List<int[]> results = new ArrayList<>();
    int n = numbers.length;
    if (n < 3) {
      return results;
    }

    int[] array = Arrays.copyOf(numbers, n);
    Arrays.sort(array);

    for (int i = 0; i < n - 2; i++) {
      if (i > 0 && array[i] == array[i - 1]) {
        continue;
      }
      int left = i + 1;
      int right = n - 1;

      while (left < right) {
        long sum = (long) array[i] + array[left] + array[right];
        if (sum == 0) {
          results.add(new int[] { array[i], array[left], array[right] });
          int valueLeft = array[left], valueRight = array[right];
          while (left < right && array[left] == valueLeft) left++;
          while (left < right && array[right] == valueRight) right--;
        } else if (sum < 0) {
          left++;
        } else {
          right--;
        }
      }
    }
    return results;
  }
}
