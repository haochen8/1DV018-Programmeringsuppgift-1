package unionfind;

public class QuickUnion implements UnionFind {
  private final int[] parent;
  private int components;

  /**
   * Initializes an empty union-find data structure with n elements
   * (0 to n-1). Initially, each element is in its own set.
   * @param n - number of elements
   * @throws IllegalArgumentException if n <= 0
   */
  public QuickUnion(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Size must be greater than 0");
    }
    this.parent = new int[n];
    this.components = n;
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  private void validate(int p) {
    int n = parent.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("Index " + p + " is not between 0 and " + (n - 1));
    }
  }

  private int root(int p) {
    validate(p);
    while (p != parent[p]) {
      parent[p] = parent[parent[p]];
      p = parent[p];
    }
    return p;
  }

  @Override
  public void union(int p, int q) {
    validate(p);
    validate(q);
    int rootP = root(p);
    int rootQ = root(q);
    if (rootP == rootQ) {
      return;
    }
    parent[rootP] = rootQ;
    components--;
  }

  @Override
  public int find(int p) {
    return root(p);
  }

  @Override
  public int count() {
    return components;
  }

  @Override
  public int size(int p) {
    validate(p);
    int rootP = root(p);
    int size = 0;
    for (int i = 0; i < parent.length; i++) {
      if (root(i) == rootP) {
        size++;
      }
    }
    return size;
  }
}
