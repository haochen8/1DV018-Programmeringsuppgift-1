package unionfindtest;

import org.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuickFindTest {

  @Test
  public void testUnionFind() {
    QuickFind uf = new QuickFind(5);
    assertEquals(5, uf.count());
    uf.union(0, 1);
    assertEquals(4, uf.count());
    uf.union(1, 2);
    assertEquals(3, uf.count());
    uf.union(3, 4);
    assertEquals(2, uf.count());
  }
}