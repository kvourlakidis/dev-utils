package misc;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTest {
  // Test how to append to an array

  public static void main(String[] args) {
    System.out.println(String.format("foo %i bar", 2));

  }

  // TODO check how performant this is vs System.arraycopy
  static String[] prepend(String[] array, String... args) {
    final ArrayList<String> newList = new ArrayList<>(Arrays.asList(args));
    newList.addAll(Arrays.asList(array));
    return newList.toArray(new String[0]);
  }
}
