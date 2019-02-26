public class MyMinMax {
	public static void main(String[] args) {
		System.out.println(max(10,9,12,123,0,0,-123,6));
	}
	static int min(int a, int b) {
		return (a < b) ? a : b;
	}
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}
	static int min(int... ia) {
		int min = ia[0];
		for (int i : ia)
			min = min(i,min);
		return min;
	}
	static int max(int... ia) {
		int max = ia[0];
		for (int i : ia)
			max = max(i,max);
		return max;
	}
	// TODO KV - refactor using func interface
}
