public class Inheritance {
	public static void main(String[] args) {

		A aTypeA = new A();
		A bTypeA = new B();
		B bTypeB = new B();
		Testable aTypeTestable = new A();
		
		// aTypeA.defaultMethod();
		// aTypeA.privateMethod(); // not allowed
		// aTypeA.protectedMethod();
		// aTypeA.publicMethod();
		// System.out.println(aTypeA.s);
		
		if (bTypeA instanceof B) {
			System.out.println("Got an B!");
			bTypeA.defaultMethod();	
			bTypeA.protectedMethod();	
			bTypeA.publicMethod();	
			System.out.println(bTypeA.s);
			System.out.println( ( (B) bTypeA ).s);
		}
		
		// if (bTypeB instanceof B) {
			// System.out.println("Got an B!");
			// bTypeB.defaultMethod();	
			// bTypeB.protectedMethod();	
			// bTypeB.publicMethod();
			// System.out.println(bTypeB.s);
		// }

		if (aTypeTestable instanceof Testable) {
			System.out.println("Got a Testable!");
			// aTypeTestable.defaultMethod();
			// aTypeTestable.protectedMethod();
			// aTypeTestable.publicMethod();
			// System.out.println(aTypeTestable.s);
		}		
	}
}

class A implements Testable {
	
	String s = "A";
	
	void defaultMethod() {
		System.out.println("I have default level access in A");
	}

	private void privateMethod() {
		System.out.println("I have private level access in A");
	}

	protected void protectedMethod() {
		System.out.println("I have protected level access in A");
	}

	public void publicMethod() {
		System.out.println("I have public level access in A");
	}
}

class B extends A { // B is a sub-class of A (aka Dog is an Animal)

	String s = "B";

	void defaultMethod() {
		System.out.println("B's default access level method (override)");
	}

	private void privateMethod() {
		System.out.println("B's private access level method (override)");
	}

	protected void protectedMethod() {
		System.out.println("B's protected access level method (override)");
	}
	public void publicMethod() {
		System.out.println("B's public access level method (override)");
	}
}

interface Testable {
	void publicMethod(); // public is implied
}