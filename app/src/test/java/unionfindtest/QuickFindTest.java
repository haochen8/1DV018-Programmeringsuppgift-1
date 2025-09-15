package unionfindtest;

import org.junit.jupiter.api.Test;

import unionfind.QuickFind;

import static org.junit.jupiter.api.Assertions.*;

public class QuickFindTest {

  @Test
  public void testConstructorValid() {
    QuickFind uf = new QuickFind(3);
    assertEquals(3, uf.count());
    assertEquals(0, uf.find(0));
    assertEquals(1, uf.find(1));
    assertEquals(2, uf.find(2));
  }

  @Test
  public void testConstructorInvalid() {
    assertThrows(IllegalArgumentException.class, () -> new QuickFind(0));
    assertThrows(IllegalArgumentException.class, () -> new QuickFind(-5));
  }

  @Test
  public void testUnionAndFind() {
    QuickFind uf = new QuickFind(4);
    uf.union(0, 1);
    assertEquals(1, uf.find(0));
    assertEquals(1, uf.find(1));
    uf.union(2, 3);
    assertEquals(3, uf.find(2));
    assertEquals(3, uf.find(3));
    uf.union(1, 3);
    assertEquals(3, uf.find(0));
    assertEquals(3, uf.find(1));
    assertEquals(3, uf.find(2));
    assertEquals(3, uf.find(3));
  }

  @Test
  public void testUnionAlreadyConnected() {
    QuickFind uf = new QuickFind(2);
    uf.union(0, 1);
    int before = uf.count();
    uf.union(0, 1);
    assertEquals(before, uf.count());
  }

  @Test
  public void testFindOutOfBounds() {
    QuickFind uf = new QuickFind(2);
    assertThrows(IndexOutOfBoundsException.class, () -> uf.find(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> uf.find(2));
  }

  @Test
  public void testSizeSingleComponent() {
    QuickFind uf = new QuickFind(3);
    assertEquals(1, uf.size(0));
    assertEquals(1, uf.size(1));
    assertEquals(1, uf.size(2));
  }

  @Test
  public void testSizeAfterUnion() {
    QuickFind uf = new QuickFind(4);
    uf.union(0, 1);
    assertEquals(2, uf.size(0));
    assertEquals(2, uf.size(1));
    uf.union(2, 3);
    uf.union(1, 2);
    assertEquals(4, uf.size(0));
    assertEquals(4, uf.size(3));
  }

  @Test
  public void testSizeOutOfBounds() {
    QuickFind uf = new QuickFind(2);
    assertThrows(IndexOutOfBoundsException.class, () -> uf.size(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> uf.size(2));
  }

  @Test
  public void testToString() {
    QuickFind uf = new QuickFind(3);
    String s = uf.toString();
    assertTrue(s.contains("components=3"));
    assertTrue(s.contains("[0, 1, 2]"));
    uf.union(0, 1);
    s = uf.toString();
    assertTrue(s.contains("components=2"));
    assertTrue(s.contains("[1, 1, 2]"));
  }
}