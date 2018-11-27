class Bitwise {
	public static void main(String[] args) {
		int a = 0b11001;
		int b = 0b01100;
		System.out.println("a   = " + Integer.toString(a,2));
		System.out.println("b   =  " + Integer.toString(b,2));
		System.out.println("a&b =  " + Integer.toString(a&b,2));
		System.out.println("a|b = " + Integer.toString(a|b,2));
		System.out.println("a^b = " + Integer.toString(a^b,2));
		int c = 0b11001100110011001100110011001100;
		System.out.println("c  = " + Integer.toBinaryString(c));
		System.out.println("~c =   " + Integer.toBinaryString(~c)); 
		int d = 0b11110000;
		System.out.println("d    =     " + Integer.toBinaryString(d));
		System.out.println("d<<4 = " + Integer.toBinaryString(d<<4));
		System.out.println("d>>4 =         " + Integer.toBinaryString(d>>4));
		System.out.println("c      = " + Integer.toBinaryString(c));
		System.out.println("c>>16  = " + Integer.toBinaryString(c>>16));
		System.out.println("c>>>16 =                 " + Integer.toBinaryString(c>>>16));	
	}
}
