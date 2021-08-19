package misc;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

public class Arithmetic {
  public static void main(String[] args) {
    final Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      final int n1 = - rand.nextInt(100);
      final int n2 = rand.nextInt(100);
      final Map.Entry<Integer, Integer> simpleFraction = simplifyFraction(n1, n2);
      System.out.println(
          String.format(
              "%d / %d = %d / %d", n1, n2, simpleFraction.getKey(), simpleFraction.getValue()));
    }
  }

  /** Given a fraction of integers, return the simplest equivalent fraction. */
  static Map.Entry<Integer, Integer> simplifyFraction(int numerator, int denominator) {
    final int gcd = gcd(numerator, denominator);
    return new AbstractMap.SimpleEntry<>(numerator / gcd, denominator / gcd);
  }

  /** Given 2 integers, return the greatest common denominator. */
  static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
