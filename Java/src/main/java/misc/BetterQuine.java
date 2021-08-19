package misc;

class BetterQuine {
  public static void main(String[] args) {
    char LF = (char)10, SP = (char)32, Q = (char)34, SEM = (char)59, EQ = (char)61, CBR = (char)125;
    String A = "    String ";
    String B = "class BetterQuine {";
    String C = "  public static void main(String[] args) {";
    String D = "    char LF = (char)10, SP = (char)32, Q = (char)34, SEM = (char)59, EQ = (char)61, CBR = (char)125;";
    String E = "    System.out.print(B+LF+C+LF+D+LF+A+(char)65+SP+EQ+SP+Q+A+Q+SEM+LF);";
    String F = "    System.out.print(A+(char)66+SP+EQ+SP+Q+B+Q+SEM+LF+A+(char)67+SP+EQ+SP+Q+C+Q+SEM+LF);";
    String G = "    System.out.print(A+(char)68+SP+EQ+SP+Q+D+Q+SEM+LF+A+(char)69+SP+EQ+SP+Q+E+Q+SEM+LF);";
    String H = "    System.out.print(A+(char)70+SP+EQ+SP+Q+F+Q+SEM+LF+A+(char)71+SP+EQ+SP+Q+G+Q+SEM+LF);";
    String I = "    System.out.print(A+(char)72+SP+EQ+SP+Q+H+Q+SEM+LF+A+(char)73+SP+EQ+SP+Q+I+Q+SEM+LF);";
    String J = "    System.out.print(A+(char)74+SP+EQ+SP+Q+J+Q+SEM+LF+A+(char)75+SP+EQ+SP+Q+K+Q+SEM+LF);";
    String K = "    System.out.print(E+LF+F+LF+G+LF+H+LF+I+LF+J+LF+K+LF+SP+SP+CBR+LF+CBR+LF);";
    System.out.print(B+LF+C+LF+D+LF+A+(char)65+SP+EQ+SP+Q+A+Q+SEM+LF);
    System.out.print(A+(char)66+SP+EQ+SP+Q+B+Q+SEM+LF+A+(char)67+SP+EQ+SP+Q+C+Q+SEM+LF);
    System.out.print(A+(char)68+SP+EQ+SP+Q+D+Q+SEM+LF+A+(char)69+SP+EQ+SP+Q+E+Q+SEM+LF);
    System.out.print(A+(char)70+SP+EQ+SP+Q+F+Q+SEM+LF+A+(char)71+SP+EQ+SP+Q+G+Q+SEM+LF);
    System.out.print(A+(char)72+SP+EQ+SP+Q+H+Q+SEM+LF+A+(char)73+SP+EQ+SP+Q+I+Q+SEM+LF);
    System.out.print(A+(char)74+SP+EQ+SP+Q+J+Q+SEM+LF+A+(char)75+SP+EQ+SP+Q+K+Q+SEM+LF);
    System.out.print(E+LF+F+LF+G+LF+H+LF+I+LF+J+LF+K+LF+SP+SP+CBR+LF+CBR+LF);
  }
}
