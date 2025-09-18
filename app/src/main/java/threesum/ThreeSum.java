package threesum;

import java.util.Arrays;

/**
 * A class to solve the 3-sum problem.
 */
public final class ThreeSum {
  private ThreeSum() {
  }

  /**
   * Finds three numbers in the array that sum up to the target value.
   * @param numbers the input array of integers
   * @param target  the target sum to find
   * @return array of three integers that sum to target, or null if no solution exists
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
    int[] array = Arrays.copyOf(numbers, n);
    Arrays.sort(array);

    for (int i = 0; i < n - 2; i++) {
      if (i > 0 && array[i] == array[i - 1]) {
        continue;
      }

      // Uses long to prevent integer overflow
      long twoSumTarget = (long) target - array[i];
      int left = i + 1;
      int right = n - 1;

      while (left < right) {
        long currentSum = (long) array[left] + array[right];

        if (currentSum == twoSumTarget) {
          return new int[] { array[i], array[left], array[right] };
        } else if (currentSum < twoSumTarget) {
          left++;
        } else {
          right--;
        }
      }
    }
    return null;
  }
}