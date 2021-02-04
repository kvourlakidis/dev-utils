package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
  private Element<T> first;

  MyLinkedList() {}

  public void append(T value) {
    final Element<T> el = new Element<>(value);
    if (isEmpty()) {
      first = el;
    } else {
      Element<T> last = first;
      while (last.next != null) {
        last = last.next;
      }
      last.next = el;
    }
  }

  public void prepend(T value) {
    final Element<T> el = new Element<>(value);
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

  public boolean removeFirst(T value) {
    if (!isEmpty()) {
      // edge case when first value is removed
      if (first.value.equals(value)) {
        removeFirst();
        return true;
      }
      Element<T> element = first;
      while (element.next != null) {
        if (element.next.value.equals(value)) {
          element.next = element.next.next;
          return true;
        }
        element = element.next;
      }
    }
    return false;
  }

  public void removeLast() {
    if (!isEmpty()) {
      Element<T> el = first;
      while (el.next != null && el.next.next != null) {
        el = el.next;
      }
      el.next = null;
    }
  }

  @Override
  public String toString() {
    Element<T> el = first;
    final StringBuilder sb = new StringBuilder("[");
    while (el != null) {
      sb.append(el.value);
      if (el.next != null) {
        sb.append(", ");
      }
      el = el.next;
    }
    return sb.append("]").toString();
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Element<T> el = first;

      @Override
      public boolean hasNext() {
        return el != null;
      }

      @Override
      public T next() {
        if (hasNext()) {
          final T s = el.value;
          el = el.next;
          return s;
        }
        throw new NoSuchElementException();
      }
    };
  }

  private static class Element<T> {
    T value;
    Element<T> next;
    Element(T value) {
      this.value = value;
    }
  }
}
