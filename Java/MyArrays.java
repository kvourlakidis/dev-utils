public class MyArrays {
	public static void main(String[] args) {
		int[] ia = new int[10];
		for (int i=0;i<10;ia[i]=i++);
		print(ia);
	}
	static void print(int[] intArray) {
		System.out.println("int[]: " + intArray);
		System.out.println("Size: " + intArray.length);
		System.out.print("[");
		for (int i : intArray)
			System.out.print(" " + i);
		System.out.print(" ]");
	}
}
