package misc;

public class StringLinkedList {
  Element first;

  StringLinkedList() {
    first = new Element();
    first.next = first;
  }

  public void append(String contents) {
    Element last = first.next;
  }

  public void prepend(String contents) {
  }

  boolean isEmpty() {
    return false;
  }

  public void removeFirst() {
  }

  public void removeLast() {

  }

  @Override
  public String toString() {
    return super.toString();
  }

  private static class Element {
    String contents;
    Element next;
  }
}
