public class MyJavaReference {
	
	public static void main(String[] args) {

		System.out.println("Hello from the Java World!");

		// testPrint();
		// ternaryExample();
		// systemArgsExample(args);
		// dataTypeTest();
		isYellow(1);
		isYellow(0);
		isYellow(-1);
		
	} // end of main()

	static void ternaryExample() {
		myPrint("Writing a simple max() function:");
		int m = myMax(123, 124);
		myPrint("The highest number is: " + m);
	}
	static int myMax(int a, int b) {
		return (a > b) ? a : b;
	}

	// Overloading example with a simple print-like method
	static void myPrint( String s ) {
		System.out.println( s );
	}
	static void myPrint( int i ) {
		String s = Integer.toString(i);
		System.out.println( s + "(integer)" );
	}
	static void myPrint( double d ) {
		String s = Double.toString(d);
		System.out.println( s + "(double)" );
	}
	static void myPrint( boolean b ) {
		if ( b ) {
			System.out.println("We have a TRUE !");
		} else {
			System.out.println("We have a FALSE!");
		}
	}
	static void myPrint( char c ) {
		System.out.print( c );
	}
	// Testing that myPrint() works as intended
	static void testPrint() {
		int     i  = 123456789;
		double  d  = 3.14159265359;
		boolean t  = true;
		boolean f  = false;
		char    c1 = '/';
		char    c2 = '\\';
		String  s = "Testing out overloaded myPrint() method:";
		myPrint(s);
		myPrint(i);
		myPrint(d);
		myPrint(t);
		myPrint(f);
		for ( int ii=0;ii<25;ii++ ) { 
			myPrint(c1);
			myPrint(c2);
		}
	}
	
	// Built-in data types demo
	static void dataTypeTest() {
		int     i; // common operators: + - * / %
		long    j; //
		double  d; // common operators: + - * /
		boolean b; // common operators: %% || !
		char    c; // common operators: n/a
		String  s; // common operators: +
		i = 2;
		j = (long) Math.pow( i, 31 );
		System.out.printf("%d to the power of %d is: %,d\n", i, 31, j);
		System.out.printf("Max int is %,d\n", Integer.MAX_VALUE);
		System.out.printf("Min int is %,d\n", Integer.MIN_VALUE);
		System.out.printf("Max long is %,d\n", Long.MAX_VALUE);
		d = 3.14159265359;
		System.out.printf("The value of Pi is %.3f to 3 d.p.\n", d);
	}
	
	static void isYellow(int offence) {
		boolean yCard = (boolean) offence;
		if (yCard) { System.out.println("Is a yellow!"); }
		else { System.out.println("Is not a yellow!"); }
	}
	
	// CLASS template
	// public/private 
}
