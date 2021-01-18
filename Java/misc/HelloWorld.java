package misc;

public class HelloWorld {

	// static final double PI = 3.14159265359; // member (class) variable
	static final double PI = 3.141592653589793;
	static int helloCount; // implicitly set to zero
	int[] numbers; // array declaration (GOOD)
	int nums [];   // array declaration (BAD!)
	
	final String myString = "blah";
	
	
	public static void main(String[] args) {
		System.out.println("Start of main()");
		// doStuff1(); // only works if doStuff() is static
		// doStuff2(1,2,3,4,5,6,7.7);
		// doStuff3();
		// doStuff4(3);
		// doStuff5();
		// doStuff6();
		// doStuff7();
		// doStuff8();
		// doStuff9();
		// doStuff10();
		// doStuff11();
		doStuff12();
		System.out.println("End of main()");
	}

	// shadowing example
	static void doStuff1() {
		final double PI = 4; // local variable
		System.out.println("Pi is: " + HelloWorld.PI); // only works if PI is static
		// System.out.println("Pi is: " + this.PI); // only works if misc.HelloWorld is instantiated
		System.out.println("Pi is: " + PI); // uses the local PI ("shadowing")
	}
	
	// var-args example
	static void doStuff2(Object... x) {
		for (int i=0; i<x.length; i++) {
			int y = (int) x[i];
			System.out.println(y);
		}
	}

	// enums example
	enum CoffeeSize { 
		// construct the enums
		BIG(8),
		BIGGER(10), 
		HUGE(16) {
			String getLidCode() {
				return "A";
			}
		};
		
		int mOunces; // member variable (hence the m)
		// constructor
		CoffeeSize(int ounces) {
			mOunces = ounces; // assign ounces
		}
		
		String getLidCode() {
			return "B";
		}
	}; // semi-colon is optional
	
	static void doStuff3() {
		System.out.println("Cofee Sizes:");
		for (int i=0;i<CoffeeSize.values().length;i++) {
			System.out.print(CoffeeSize.values()[i] + " ");
		}
		System.out.println("");
		CoffeeSize csBig = CoffeeSize.BIG;
		CoffeeSize csBigger = CoffeeSize.BIGGER;
		CoffeeSize csHuge = CoffeeSize.HUGE;
		System.out.println( csHuge + " " + csHuge.mOunces );
		System.out.println( "Lid Code: " + csHuge.getLidCode() );
		
		System.out.println( csBig + " " + csBig.mOunces );
		System.out.println( "Lid Code: " + csBig.getLidCode() );
	}
	
	static void doStuff4(int num) {
		for (int i=0;i<num;i++) {
			new HelloWorld();
			System.out.println("Hello count is now: " + HelloWorld.helloCount);
		}
	}
 
	static void doStuff5() {
		final int EARTH_RADIUS        = 6_371_000;
		final int EARTH_RADIUS_BINARY = 0b0110_0001_0011_0110_1011_1000;
		final int EARTH_RADIUS_OCTAL  = 030233270;
		final int EARTH_RADIUS_HEX    = 0x6136b8;
		final long SUN_DIST = 149_597_870_700L; // has to be declared as a long
		final float PI = 3.14159265359f; // explicit float (less precise than a double)
		
		System.out.println( "Earth Radius: " + EARTH_RADIUS + " meters" );
		System.out.println( "Earth Radius: " + EARTH_RADIUS_BINARY + " meters" );
		System.out.println( "Earth Radius: " + EARTH_RADIUS_OCTAL + " meters" );
		System.out.println( "Earth Radius: " + EARTH_RADIUS_HEX + " meters" );
		System.out.println("The integer limit in Java is: " + Integer.MAX_VALUE);
		System.out.println("One Astronomical Unit (AU) is: " + SUN_DIST + " m");
		System.out.println("Pi (float) is:  " + PI);
		System.out.println("Pi (double) is: " + HelloWorld.PI);
	}
 
	static void doStuff6() {
		int i = 123; // a literal integer is always implicitly an int
		byte a = 100; // java is implicitly casting the int into a byte
		byte b = 120;
		byte c = (byte) (a + b); // will not compile without the cast
		System.out.println(a + " + " + b + " = " + c);
	
		short d = (short) 234.56F;
		System.out.println(d);
		
		byte e = (byte) 127;
		e += 1;
		e -= 1;
		e *= 2;
		e /= 2;
		System.out.println(e);
		
		int f = new Integer(10);
		int g = new Integer(2 * f);
		System.out.println(f + " " + g);
		f = 20;
		System.out.println(f + " " + g);
	}
	
	static void doStuff7() {
		String s = "Blah";
		String t = s;
		System.out.println(t);
		t.toUpperCase();
		System.out.println(t);
		System.out.println( t.toUpperCase() );
	}
	
	static void doStuff8() {
		// compound operators
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 8;
		a += 5;
		b -= 6;
		c *= 7;
		d /= 3;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		short e = 2;
		if ( e == 8/3 ) System.out.println(e==8/3);
	}
	
	static void doStuff9() {
		String a = "blah";
		String b = "blah";
		String c = a;
		c += "2";
		if (a != c) {
			System.out.println(a + " is not equal to " + c); 
		} else {
			System.out.println(a + " is equal to " + c); 
		}
	}
	
	static void doStuff10() {
		int z = 5;
		if (z++ > 5 || ++z > 6) z++; 
		System.out.println("z is: " + z);
		
		int a = 10;
		if (a != 9) System.out.println("a is not 9!");
		Object s = new Integer(10);
		System.out.println(s instanceof Long);
		
	}
	
	static void doStuff11() {
		int [] a; // declare array of ints
		// int b []; // same as above
		// int[][] c; // declare two-dimensional array of ints
		// int [] d []; // same as above
		a = new int[10];
		a[0] = 10;
		for (int i=0; i < a.length; i++) a[i] = i;
		for (int i : a) System.out.print(i + " ");
		System.out.println(a[a.length-1]);
		int[][] b = new int[10][];
		int[] c = {4,7,2};
		int[] d = new int[] {4,7,2}; // functionally equivalent
		for (int i : c) System.out.print(i + " ");
	}
	// difference between primitives and objects
	static void doStuff12() {
		int i = 123;
		int j = i;
		Integer ii = 123;
		Integer jj = ii;
		j++;
		jj++;
		System.out.println("i = " + i);
		System.out.println("ii = " + ii);
	}
	
	static void doStuff13() {
		int x = 3;
		switch (x) {
			case 1:
				System.out.println("x is 1");
				break;
			case 2:
				System.out.println("x is 2");
				break;
			case 3:
				System.out.println("x is 3");
				break;
			default:
				System.out.println("x is... something");
		}
	}
	// constructors
	HelloWorld() {
		super(); // this call is implicit
		// instance init block runs now
		System.out.println("Creating an instance of misc.HelloWorld...");
		helloCount += 1;
	}
	
	// Java primitives - live on the stack
	// char, boolean, byte, short, int, long, double, float
	
	// static init block
	static { System.out.println("I am a class!"); }
	// instance init block
	{ System.out.println("I am an instance!"); }
	
}
