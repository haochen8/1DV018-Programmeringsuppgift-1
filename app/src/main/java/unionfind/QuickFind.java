package app.src.main.java.unionfind;

import java.util.Arrays;

/**
 * Quick-Find implementation of Union-Find data structure.
 */
public class QuickFind implements UnionFind {
  private final int [] id;
  private int components;

  /**
   * Returns the number of components.
   * @return the number of components
   */
  public int count() {
    return components;
  }

  /**
   * Initializes an empty union-find data structure with n elements
   * (0 to n-1). Initially, each element is in its own set.
   * @param n - number of elements
   * @throws IllegalArgumentException if n <= 0
   */
  public QuickFind(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Size must be greater than 0");
    }
    this.id = new int[n];
    this.components = n;
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  private void validate(int p) {
    int n = id.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("Index " + p + " is not between 0 and " + (n-1));
    }
  }

  @Override
  public void union(int p, int q) {
    validate (p);
    validate (q);
    int pId = id[p];
    int qId = id[q];
    if (pId == qId) {
      return;
    }
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pId) {
        id[i] = qId;
      }
    }
    components--;
  }
}
