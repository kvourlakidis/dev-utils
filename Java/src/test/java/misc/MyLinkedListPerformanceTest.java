package misc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MyLinkedListPerformanceTest {
  private static final int SIZE = 100;
  static List<String> TEST_DATA;
  LinkedList<String> javaLinkedList;
  MyLinkedList<String> myLinkedList;

  @BeforeAll
  public static void setupAll() {
    TEST_DATA = new ArrayList<>();
    final StringGenerator generator = new StringGenerator();
    for (int i = 0; i < SIZE; i++) {
      TEST_DATA.add(generator.generate(5, 10));
    }
  }

  @BeforeEach
  public void setupEach() {
    javaLinkedList = new LinkedList<>();
    myLinkedList = new MyLinkedList<>();
  }

  @Test
  public void foo() {
  }

  static class StringGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMS = "0123456789";
    private final String pool;
    private final Random random;
    StringGenerator() {
      pool = UPPER + LOWER + NUMS;
      random = new Random(4544418950904088099L);
    }
    String generate(int min, int max) {
      final int size = random.nextInt(max - min + 1) + min;
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < size; i++) {
        final char c = pool.charAt(random.nextInt(pool.length()));
        sb.append(c);
      }
      return sb.toString();
    }
  }
}
