package app.src.main.java.unionfind;

/**
 * Interface for Union-Find data structure.
 */
public interface UnionFind {
  /**
   * Unions the two elements p and q.
   * @param p - first element
   * @param q - second element
   * @throws IndexOutOfBoundsException if p or q are out of bounds.
   */
  void union(int p, int q);

  /**
   * Finds the component identifier for p.
   * @param p - element to find
   * @return component identifier for p
   * @throws IndexOutOfBoundsException if p is out of bounds.
   */
  int find (int p);

  /**
   * Checks if the two elements p and q are in the same component.
   * @param p - first element
   * @param q - second element
   * @return true if p and q are in the same component, false otherwise.
   * @throws IndexOutOfBoundsException if p or q are out of bounds.
   */
  default boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  /**
   * Returns the number of components.
   * @return number of components
   */
  int count();

  /**
   * Returns the size of the component containing p.
   * @param p - element to find
   * @return size of the component containing p
   * @throws IndexOutOfBoundsException if p is out of bounds.
   */
  int size(int p);
}
