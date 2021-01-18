import java.util.*;

public class ExceptionTest {
	
	String hello = "hello";
	public static void main(String[] args) {
		// checkFood("bacon");
		try {
			go();
		} catch (StackOverflowError e) {
			System.out.println("ho, ho, ho - you caused the stack the stack to blow up");
		}
		try {
			checkFood(args[0]);	
		} catch (BadFoodException e) {
			System.out.println("bad food, yuck!");
		} 
	}

	static void checkFood(String food) throws BadFoodException {
		List<String> foodList = new ArrayList<String>();
		foodList.add("eggs");
		foodList.add("chips");
		foodList.add("pasta");
		
		if (foodList.contains(food)) {
			System.out.println("mm, tasty!");
		} else {
			throw new BadFoodException();
		}
	}

	static void go(){ go(); }
	static class BadFoodException extends Exception {}
}
