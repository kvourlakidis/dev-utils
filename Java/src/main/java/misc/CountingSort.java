package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CountingSort {
  static Random r = new Random();
  static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  public static void main(String[] args) {
    List<List<String>> unsorted = new ArrayList<>();
    for (int i = 0; i < 100_000; i++) {
      unsorted.add(Arrays.asList(Integer.toString(r.nextInt(10)), getRandomString(10)));
    }
    countSort(unsorted);
  }

  private static String getRandomString(int n) {
    final StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      sb.append(chars.charAt(r.nextInt(chars.length())));
    }
    return sb.toString();
  }

  public static void countSort(List<List<String>> arr) {
    // assume counting index doens't go above 100
    final List<String>[] countingArray = new List[100];
    for (int i = 0; i < arr.size(); i++) {
      List<String> entry = arr.get(i);
      final int sortingValue = Integer.parseInt(entry.get(0));
      final String s;
      if (i >= (arr.size() / 2)) {
        s = entry.get(1);
      } else {
        s = "-";
      }
      if (countingArray[sortingValue] == null) {
        countingArray[sortingValue] = new ArrayList<>();
      }
      countingArray[sortingValue].add(s);
    }
    for (List<String> sublist : countingArray) {
      if (sublist != null) {
        for (String s : sublist) {
          if (s != null && !s.isEmpty()) {
            System.out.print(s + " ");
          }
        }
      }
    }
    System.out.println();
  }
}
