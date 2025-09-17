package threesumtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import threesum.ThreeSum;

public class ThreeSumTest {

  @Test
  public void testEmptyArray() {
    int[] numbers = {};
    int target = 0;
    int[] result = ThreeSum.findThreeSum(numbers, target);
    assertNull(result);
  }

  @Test
  public void testArrayTooSmall() {
    int[] numbers = { 1, 2 };
    int target = 3;
    int[] result = ThreeSum.findThreeSum(numbers, target);
    assertNull(result);
  }

  @Test
  public void testBasicThreeSum() {
    int[] numbers = { 1, 2, 3, 4, 5 };
    int target = 6;
    int[] expected = { 1, 2, 3 };
    int[] result = ThreeSum.findThreeSum(numbers, target);
    assertArrayEquals(expected, result);
  }

  @Test
  public void testNoSolution() {
    int[] numbers = { 1, 2, 3, 4, 5 };
    int target = 15;
    int[] result = ThreeSum.findThreeSum(numbers, target);
    assertNull(result);
  }

  @Test
  public void testNegativeNumbers() {
    int[] numbers = { -1, -2, 3, 4, 5 };
    int target = 0;
    int[] expected = { -2, -1, 3 };
    int[] result = ThreeSum.findThreeSum(numbers, target);
    assertArrayEquals(expected, result);
  }

  @Test
  public void testDuplicateNumbers() {
    int[] numbers = { 1, 1, 1, 2, 3 };
    int target = 3;
    int[] expected = { 1, 1, 1 };
    int[] result = ThreeSum.findThreeSum(numbers, target);
    assertArrayEquals(expected, result);
  }
}