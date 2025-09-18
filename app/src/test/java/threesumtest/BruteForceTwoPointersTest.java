package threesumtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import threesum.ThreeSumBruteForce;
import threesum.ThreeSumTwoPointer;
import java.util.*;

public class BruteForceTwoPointersTest {

  @Test
  void testEmptyArray() {
    int[] nums = {};
    assertTrue(ThreeSumBruteForce.solveAllZero(nums).isEmpty());
    assertTrue(ThreeSumTwoPointer.solveAllZero(nums).isEmpty());
  }

  @Test
  void testBasicCase() {
    int[] nums = { -1, 0, 1, 2, -1, -4 };
    List<int[]> expectedTriples = Arrays.asList(
        new int[] { -1, -1, 2 },
        new int[] { -1, 0, 1 });

    assertTripletsEqual(expectedTriples, ThreeSumBruteForce.solveAllZero(nums));
    assertTripletsEqual(expectedTriples, ThreeSumTwoPointer.solveAllZero(nums));
  }

  @Test
  void testAllZeros() {
    int[] nums = { 0, 0, 0, 0 };
    List<int[]> expectedTriples = Arrays.asList(new int[] { 0, 0, 0 });

    assertTripletsEqual(expectedTriples, ThreeSumBruteForce.solveAllZero(nums));
    assertTripletsEqual(expectedTriples, ThreeSumTwoPointer.solveAllZero(nums));
  }

  @Test
  void testNoPossibleSum() {
    int[] nums = { 1, 2, 3, 4, 5 };
    assertTrue(ThreeSumBruteForce.solveAllZero(nums).isEmpty());
    assertTrue(ThreeSumTwoPointer.solveAllZero(nums).isEmpty());
  }

  // Helper method to compare triplets
  private void assertTripletsEqual(List<int[]> expected, List<int[]> actual) {
    assertEquals(expected.size(), actual.size(), "Different number of triplets");

    for (int i = 0; i < expected.size(); i++) {
      Arrays.sort(expected.get(i));
      Arrays.sort(actual.get(i));
      assertArrayEquals(expected.get(i), actual.get(i),
          "Triplet mismatch at index " + i);
    }
  }
}