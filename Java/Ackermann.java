public class Ackermann {
	public static void main(String[] args) {
		System.out.println("Ackermann:");
		for (int i=0;i<6;i++)
			for (int j=0;j<6;j++) {
				System.out.println();
				System.out.print(i + ", " +  j + ": ");
				try {
					System.out.print(ack(i,j));
				} catch (java.lang.StackOverflowError ex) {
					System.out.print("StackOverflowError");
				}
				System.out.println();
			}
	}

	public static int ack(int m, int n) {
		int ans;
		if (m == 0) ans = n + 1;
		else if (n == 0) ans = ack(m - 1, 1);
		else ans = ack(m - 1, ack(m, n - 1));
		return ans;
	}
}
