package algorithms;

public class Levenshtein {
	public static void main(String[] args) {
		print("hello","hello");
		print("hello","hallo");
		print("hello","helloo");
		print("hello","hell");
		print("hello","ello");
		print("hello","xhellox");
	}
	static int distance(String a, String b) {
		final int n = a.length(); // hello -> 5
		final int m = b.length(); // xhellox -> 7
		if (n == 0) return m;
		if (m == 0) return n;
		final int[] d = new int[m+1];// [0,1,2..8]
		for (int i = 0; i < m; d[i] = i++);
		int x = 0;
		for (int i = 0; i < n; i++) {
			final char c1 = a.charAt(i);
			int e = i + 1;
			for (int j = 0; j < m; j++) {
				char c2 = b.charAt(j);
				int cost = (c1 == c2) ? 0 : 1;
				int insertion = d[j+1] + 1;
				int deletion = e + 1;		
				int substitution = d[j] + cost;
				x = min(insertion,deletion,substitution);
				d[j] = e;
				e = x;
			}
			d[m] = x;
		}
		return x;
	}
	static int min(int a, int b) {
		return (a < b) ? a : b;
	}
	static int min(int... ia) {
		int min = ia[0];
		for (int i : ia)
			min = min(i, min);
		return min;
	}
	static void print(String a, String b) {
		System.out.println("algorithms.Levenshtein distance between:");
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("Distance: " + distance(a,b));
	}
}
