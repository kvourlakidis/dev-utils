public class Finalizer {

	public static void main(String[] args) {

		MyObject myObject = new MyObject();
		System.out.println("Got a myObject!");
		myObject = null;
		System.out.println("Deleted the reference to myObject!");
		System.gc();
	}

}

class MyObject {
	String[] stuff = new String[10000];

	MyObject() {
		for (int i = 0; i < 10000; i++) {
			stuff[i] = "stuff";
		}
	}

	protected void finalize() {
		System.out.println("POOF!");
	}

}
