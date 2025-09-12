package unionfind;

import java.util.Arrays;

/**
 * Quick-Find implementation of Union-Find data structure.
 */
public class QuickFind implements UnionFind {
  private final int [] id;
  private int components;

  
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
  
  /**
   * Validates that p is a valid index.
   * @param p - index to validate
   */
  private void validate(int p) {
    int n = id.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("Index " + p + " is not between 0 and " + (n-1));
    }
  }
  
  /**
   * Returns the number of components.
   * @return the number of components
   */
  public int count() {
    return components;
  }

  /**
   * Unions the two elements p and q.
   * @param p - first element
   * @param q - second element
   * @throws IndexOutOfBoundsException if p or q are out of bounds.
   */
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

  /**
   * Finds the component identifier for p.
   * @param p - element to find
   * @return component identifier for p
   */
  @Override
  public int find(int p) {
    validate(p);
    return id[p];
  }

  /**
   * Returns the size of the component containing p.
   * @param p - element to find
   * @return size of the component containing p
   * @throws IndexOutOfBoundsException if p is out of bounds.
   */
  @Override
  public int size(int p) {
    validate(p);
    int pId = id[p];
    int size = 0;
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pId) {
        size++;
      }
    }
    return size;
  }

  
  /**
   * Returns a string representation of the QuickFind object.
   * @return string representation of the QuickFind object
   */
  @Override
  public String toString() {
    return "QuickFind{components=" + components + ", id=" + Arrays.toString(id) + '}';
  }
  
}
