package misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
  MyLinkedList<String> list;

  @BeforeEach
  public void setup() {
    list = new MyLinkedList<>();
  }

  @Test
  public void newListIsEmpty() {
    assertTrue(list.isEmpty());
  }

  @Test
  public void appendTest() {
    list.append("foo");
    assertFalse(list.isEmpty());
    assertEquals("[foo]", list.toString());
  }

  @Test
  public void prependTest() {
    list.prepend("bar");
    assertFalse(list.isEmpty());
    assertEquals("[bar]", list.toString());
  }

  @Test
  public void appendPrependTest() {
    list.append("foo");
    list.prepend("bar");
    list.prepend("banana");
    list.append("moo");
    assertEquals("[banana, bar, foo, moo]", list.toString());
  }

  @Test
  public void removeTest() {
    list.append("foo");
    list.prepend("bar");
    list.prepend("banana");
    list.append("moo");
    list.removeFirst();
    assertEquals("[bar, foo, moo]", list.toString());
    list.removeLast();
    assertEquals("[bar, foo]", list.toString());
    list.removeFirst();
    assertEquals("[foo]", list.toString());
    list.removeFirst();
    assertEquals("[]", list.toString());
    list.removeLast();
    assertTrue(list.isEmpty());
    list.removeFirst();
    assertTrue(list.isEmpty());
    assertEquals("[]", list.toString());
  }

  @Test
  public void appendNullThenRemoveTest() {
    list.append(null);
    list.append(null);
    list.removeLast();
    list.removeFirst();
    assertTrue(list.isEmpty());
    assertEquals("[]", list.toString());
  }

  @Test
  public void iteratorTest() {
    list.append("foo");
    list.append("bar");
    list.append("baz");
    final Iterator<String> iterator = list.iterator();
    assertEquals("foo", iterator.next());
    assertEquals("bar", iterator.next());
    assertEquals("baz", iterator.next());
    assertFalse(iterator.hasNext());
    try {
      iterator.next();
      fail();
    } catch (NoSuchElementException ex) {
      // pass
    }
  }

  @Test
  public void removeFirstTest() {
    list.append("foo");
    list.append("bar");
    list.append("baz");
    assertTrue(list.removeFirst("bar"));
    assertEquals("[foo, baz]", list.toString());
    assertTrue(list.removeFirst("baz"));
    assertEquals("[foo]", list.toString());
    assertTrue(list.removeFirst("foo"));
    assertEquals("[]", list.toString());
  }
}
