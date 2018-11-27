import java.util.*;

public class ArrayLists {
	public static void main(String[] args) {
		// testStringArrayList();
		testIntArrayList();
	}
	
	static void testStringArrayList() {
		// List<String> myList = new ArrayList<String>(); // polymorphic declaration
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("a");
		myList.add("c");
		myList.add(1, "b");
		System.out.println(myList);
		myList.clear();
		myList.add("b");
		myList.add("a");
		myList.add("c");
		System.out.println(myList);
		System.out.println(myList.contains("a") + " " + myList.contains("x") );

		System.out.println("get 1: " + myList.get(1) );
		System.out.println("index of c: " + myList.indexOf("c") );
		myList.remove(1);
		System.out.println("size: " + myList.size() + " contents: " + myList);
	}
	
	static void testIntArrayList() {
		ArrayList<Integer> myList = new ArrayList<Integer>();
		myList.add(new Integer(1));
		myList.add(2);
		myList.add(3);
		for (Integer x : myList){ 
			System.out.println(x);
		}

		// int i = myList.get(2);
		// Integer j = myList.get(2);
		// i++;
		// j = j + 1;
		// System.out.println("myList[2] == j: " + (myList.get(2) == j) );
		// System.out.println(myList);
		// int i = 4;
		// int j = 5;
		// myList.add(i);
		// myList.add(j);
		// i++;
		// j++;
		// System.out.println(myList);
		// Integer[] myArray = {1,2,3,4,i,j};
		// i++;
		// j++;
		// for (int x:myArray) System.out.print(x + " ");
		// System.out.println();
	}
}
