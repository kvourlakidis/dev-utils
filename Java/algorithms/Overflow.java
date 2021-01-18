package algorithms;

public class Overflow {
  public static void main(String[] args) {
    System.out.println(willAdditionOverflow(1, Integer.MAX_VALUE));
  }

  public static int add(int a, int b) {
    return a + b;
  }

  public static boolean willAdditionOverflow(int left, int right) {
    if (right < 0 && right != Integer.MIN_VALUE) {
      return willSubtractionOverflow(left, -right);
    } else {
      return (~(left ^ right) & (left ^ (left + right))) < 0;
    }
  }

  public static boolean willSubtractionOverflow(int left, int right) {
    if (right < 0) {
      return willAdditionOverflow(left, -right);
    } else {
      return ((left ^ right) & (left ^ (left - right))) < 0;
    }
  }
}
