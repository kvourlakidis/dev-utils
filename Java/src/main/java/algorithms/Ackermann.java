package algorithms;

public class Ackermann {
	public static void main(String[] args) {
		System.out.println("algorithms.Ackermann:");
		for (int m=0;m<6;m++)
			for (int n=0;n<6;n++) {
				System.out.println();
				System.out.print(m + ", " +  n + ": ");
				try {
					System.out.print(ack(m,n));
				} catch (java.lang.StackOverflowError ex) {
					System.out.print("StackOverflowError");
				}
				System.out.println();
			}
	}

	public static int ack(int m, int n) {
		int ans;
		if (m == 0)
			ans = n + 1;
		else if (n == 0) 
			ans = ack(m - 1, 1);
		else 
			ans = ack(m - 1, ack(m, n - 1));
		return ans;
	}
}
