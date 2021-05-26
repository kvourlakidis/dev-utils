package misc;

public class Quine {
  public static void main(String[] args) {
    String s = "package misc;\n\npublic class Quine {\n  public static void main(String [] args) {\n    String s = \"%s\";\n    System.out.printf(s, s.replace(\"\\n\", \"\\\\n\").replace(\"\\\\\", \"\\\\\\\\\").replace(\"\\\"\", \"\\\\\\\"\"));\n  }\n}";
    System.out.printf(s, s.replace("\n", "\\n").replace("\\", "\\\\").replace("\"", "\\\""));
  }
}
