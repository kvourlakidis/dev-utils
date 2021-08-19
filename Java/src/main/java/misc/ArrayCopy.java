package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ArrayCopy {
  public static void main(String[] args) {
    // performance test System.arraycopy method
    // create 100k string arrays 1k elements each
    final Random random = new Random();
    final String[][] sourceArrays = new String[10_000][];
    for (int i = 0; i < sourceArrays.length; i++) {
      sourceArrays[i] = new String[1000];
      for (int j = 0; j < 1000; j++) {
        sourceArrays[i][j] = Integer.toString(Math.abs(random.nextInt()));
      }
    }
    final long start = System.currentTimeMillis();

    final String[][] destArrays = new String[sourceArrays.length][];
    for (int i = 0; i < sourceArrays.length; i++) {
      destArrays[i] = prependUsingArraycopy(sourceArrays[i], "this", "is", "sparta");
    }

    final long end = System.currentTimeMillis();

    System.out.println(String.format("Time taken to convert %d "));
  }

  // TODO check how performant this is vs System.arraycopy
  static String[] prependUsingJavaCollection(String[] array, String... args) {
    final ArrayList<String> newList = new ArrayList<>(Arrays.asList(args));
    newList.addAll(Arrays.asList(array));
    return newList.toArray(new String[0]);
  }

  static String[] prependUsingArraycopy(String[] source, String... args) {
    final String[] destination = new String[source.length + args.length];
    // add the args -> destination
    System.arraycopy(args, 0, destination, 0, args.length);
    // add the source -> destination
    System.arraycopy(source, 0, destination, args.length, source.length);
    return destination;
  }
}
