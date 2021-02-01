package misc;

import java.util.LinkedList;

public class StringLinkedListTest {
  public static void main(String[] args) {
    testEmpty();
  }

  static void testEmpty() {
    System.out.println("testEmpty");
    final StringLinkedList list = new StringLinkedList();
    System.out.println("Expected: [], Actual: " + list);
    System.out.println("Expected: true, Actual: " + list.isEmpty());
  }

  static void testNotEmpty() {

  }
}
