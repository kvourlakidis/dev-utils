package tutorials.collections;

import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings({"UnusedAssignment"})
public class StreamsDemo {
  public static void main(String[] args) {
    List<Integer> intList = java.util.Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<String> stringList = Arrays.asList("foo", "foo", "FOO", "", "bar", "BAR", "", "foo");

    // perform a operation on each number in a list
    intList.stream().map(x -> x + x).forEach(i -> System.out.print(i + " "));

    // print ten random numbers
    Random random = new Random();
    newline();
    IntStream.range(0, 10).forEach(x -> System.out.print(random.nextInt() + " "));
    // printn ten random numbers and sort
    newline();
    random.ints().limit(10).sorted().forEach(i -> System.out.print(i + " "));

    // count number of empty strings
    long count = stringList.stream().filter(String::isEmpty).count();

    // count number of strings of length 3
    count = stringList.stream().filter(string -> string.length() == 3).count();

    // filter out empty strings
    newline();
    final List<String> noEmptyStrings =
        stringList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    System.out.println("Filtered List: " + noEmptyStrings);

    // joining collector
    final String joinedString =
        stringList.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(":"));
    System.out.println("Merged String: " + joinedString);

    // distinct values
    final List<String> uniqueStrings =
        stringList.stream()
            .filter(s -> !s.isEmpty())
            .map(String::toLowerCase)
            .distinct()
            .collect(Collectors.toList());
    System.out.println("Unique strings: " + uniqueStrings);

    // summary statistics
    IntSummaryStatistics stats = intList.stream().mapToInt((x) -> x).summaryStatistics();
    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());

    // parallel processing example
    count = stringList.parallelStream().filter(String::isEmpty).count();
    System.out.println("Empty Strings: " + count);
  }

  static void newline() {
    System.out.println();
  }
}
