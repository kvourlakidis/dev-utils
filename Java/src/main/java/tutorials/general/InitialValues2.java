package tutorials.general;

public class InitialValues2 {
  final String string1 = getString1();
  char char1 = getChar1();

  String getString1() {
    System.out.println("getString1()");
    return Character.toString(char1);
  }

  char getChar1() {
    System.out.println("getChar1()");
    return 'A';
  }

  InitialValues2() {
    System.out.println("Constructor");
  }

  public static void main(String[] args) {
    final InitialValues2 thing = new InitialValues2();
    System.out.println(thing.string1);
  }
}
