package tutorials.general;

import java.io.File;

public class InitialValues {
  static int staticInt;
  static boolean staticBool;
  static char staticChar;
  static String staticStr;
  static Integer staticInteger;
  static Boolean staticBoolean;
  static File staticObject;

  int memberInt;
  boolean memberBool;
  char memberChar;
  String memberStr;
  Integer memberInteger;
  Boolean memberBoolean;
  File memberObject;

  public static void main(String[] args) {
    System.out.println(InitialValues.staticInt); // 0
    System.out.println(InitialValues.staticBool); // false
    System.out.println(InitialValues.staticChar); // (empty character)
    System.out.println(InitialValues.staticStr); // null
    System.out.println(InitialValues.staticInteger); // null
    System.out.println(InitialValues.staticBoolean); // null
    System.out.println(InitialValues.staticObject); // null

    final InitialValues initialValues = new InitialValues();
    System.out.println(initialValues.memberInt); // 0
    System.out.println(initialValues.memberBool); // false
    System.out.println(initialValues.memberChar); // (empty character)
    System.out.println(initialValues.memberStr); // null
    System.out.println(initialValues.memberInteger); // null
    System.out.println(initialValues.memberBoolean); // null
    System.out.println(initialValues.memberObject); // null
  }
}
