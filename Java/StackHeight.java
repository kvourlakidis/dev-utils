public class StackHeight {
	static int stack = 0;

	public static void main(String[] args){
		System.out.println("Here we go:");
		try {
			go();
		}
		catch (Error e) {
			System.out.println("The stack height was: " + stack);
		}
	}

	static void go() {
		stack += 1;
		go();
	}
}
