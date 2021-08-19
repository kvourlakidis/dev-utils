package misc;

import java.util.UUID;

public class Main {
  public static void main(String[] args) {
    String[] stringArray = {"foo", "bar", "baz"};
    System.out.println("toString(): " + stringArray.toString());
    System.out.println("hashCode(): " + stringArray.hashCode());
    System.out.println("Integer.toHexString(hashCode()): " + Integer.toHexString(stringArray.hashCode()));
    System.out.println(stringArray.getClass().getName());
    System.out.println("System.identityHashCode(): " + System.identityHashCode(stringArray));
  }
}
