package tutorials.general;

class Bitwise {
  public static void main(String[] args) {
    int a = 0b11001;
    int b = 0b01100;
    System.out.println("a   = "  + Integer.toString(a, 2));         // 11001
    System.out.println("b   =  " + Integer.toString(b, 2));         // 01100
    // The bitwise & operator performs a bitwise AND operation.     // |||||
    System.out.println("a&b =  " + Integer.toString(a & b, 2));     // 01000
    // The bitwise | operator performs a bitwise inclusive OR operation.
    System.out.println("a|b = "  + Integer.toString(a | b, 2));     // 11101
    // The bitwise ^ operator performs a bitwise exclusive OR operation.
    System.out.println("a^b = " +  Integer.toString(a ^ b, 2));     // 10101
    int c = 0b11001100110011001100110011001100;
    System.out.println("c  = " + Integer.toBinaryString(c));
    // The unary bitwise complement operator "~" inverts a bit pattern.
    // It can be applied to any of the integral types, making every "0" a "1"
    // and every "1" a "0". For example, a byte contains 8 bits; applying
    // this operator to a value whose bit pattern is "00000000" would change
    // its pattern to "11111111".
    System.out.println("~c =   " + Integer.toBinaryString(~c));
    int d = 0b11110000;
    System.out.println("d    =     " + Integer.toBinaryString(d));
    // The signed left shift operator "<<" shifts a bit pattern to the left.
    // The signed right shift operator ">>" shifts a bit pattern to the right.
    // The bit pattern is given by the left-hand operand, and the number of positions
    // to shift by the right-hand operand.
    System.out.println("d<<4 = " + Integer.toBinaryString(d << 4));                     // 111100000000
    System.out.println("d>>4 =         " + Integer.toBinaryString(d >> 4));             //         1111
    System.out.println("c      = " + Integer.toBinaryString(c));
    System.out.println("c>>16  = " + Integer.toBinaryString(c >> 16));
    System.out.println("c>>>16 =                 " + Integer.toBinaryString(c >>> 16));
  }
}
