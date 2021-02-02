package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringLinkedList implements Iterable<String> {
  private Element first;

  StringLinkedList() {}

  public void append(String s) {
    final Element el = new Element(s);
    if (isEmpty()) {
      first = el;
    } else {
      Element last = first;
      while (last.next != null) {
        last = last.next;
      }
      last.next = el;
    }
  }

  public void prepend(String s) {
    final Element el = new Element(s);
    el.next = first;
    first = el;
  }

  boolean isEmpty() {
    return first == null;
  }

  public void removeFirst() {
    if (!isEmpty()) {
      first = first.next;
    }
  }

  public void removeLast() {
    if (!isEmpty()) {
      Element el = first;
      while (el.next != null && el.next.next != null) {
        el = el.next;
      }
      el.next = null;
    }
  }

  @Override
  public String toString() {
    Element el = first;
    final StringBuilder sb = new StringBuilder("[");
    while (el != null) {
      sb.append(el.s);
      if (el.next != null) {
        sb.append(", ");
      }
      el = el.next;
    }
    return sb.append("]").toString();
  }

  @Override
  public Iterator<String> iterator() {
    return new Iterator<String>() {
      Element el = first;

      @Override
      public boolean hasNext() {
        return el != null;
      }

      @Override
      public String next() {
        if (hasNext()) {
          final String s = el.s;
          el = el.next;
          return s;
        }
        throw new NoSuchElementException();
      }
    };
  }

  private static class Element {
    String s;
    Element next;

    Element(String s) {
      this.s = s;
    }
  }
}
