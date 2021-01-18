package misc;

import java.util.*;

class JavaLangSystem {
	public static void main(String[] args) {
		// printMillis();
		// printNanos();

		// arrayCopyDemo();

		// getSystemProps();
		// getSystemProp();
		// getLineSep();

		// getEnvironments();
		// getEnvironment();

		runGC();

		exit();
	}

	static void printMillis(){
		System.out.println("Current system time in milliseconds:");
		System.out.println(System.currentTimeMillis());
	}

	static void printNanos(){
		System.out.println("JVM nanosecond time:");
		System.out.println(System.nanoTime());
	}

	static void arrayCopyDemo(){
		String[] sa = {"a", "b", "c", "d", "e", "f"};
		for (String s:sa) System.out.print(s + " ");
		System.out.println();
		String[] sa2 = new String[6];
		System.arraycopy(sa,0,sa2,0,6);
		for (String s:sa2) System.out.print(s + " ");
		System.out.println();
	}

	static void getSystemProps(){
		Properties props = System.getProperties();
		Enumeration keys = props.keys();
		while (keys.hasMoreElements()) {
			String s = keys.nextElement().toString();
			System.out.println(s + " : " + props.get(s));
		}
	}

	static void getSystemProp(){
		System.out.println("Java runtime: " + System.getProperty("java.runtime.version"));
	}

	static void getLineSep(){
		char c1 = System.lineSeparator().charAt(0);
		char c2 = '\n';
		char c3 = 10;
		char c4 = 012; 
		System.out.println( c1 == c2 );
		System.out.println( c2 == c3 );
		System.out.println( c3 == c4 );
		System.out.println( System.lineSeparator().equals("\n") );
	}

	static void getEnvironments(){
		Map env = System.getenv();
		Set keys = env.keySet();
		for (Object key : keys) System.out.println(key + " = " + env.get(key));		
	}

	static void getEnvironment(){
		System.out.println("PWD = " + System.getenv("PWD"));
	}

	static void runGC(){
		System.gc();
	}

	static void exit(){
		System.exit(0);
	}
}
