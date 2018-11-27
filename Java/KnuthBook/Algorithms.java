import java.util.Date;

public class Algorithms {

	public static void main(String[] args) {

		Long t1 = new Date().getTime();
		int k =0;
		for (int i=2;i<100_000;i++)
			for (int j=2;j<i;j++)
				if (k < GCD2(i,j)) {
					k = GCD2(i,j);
					System.out.println("a = " + i + ", b = " + j + ", k = " + k + ", GCD = " + GCD(i,j));
				}
		// for (int i=0;i<1_000_000;i++) k = GCD2(75025,46368);
		Long t2 = new Date().getTime();
		System.out.println(t2 - t1);
		
		// System.out.println("199 is Prime: " + isPrime(199));

		// for (int i=-10;i<=20;i++) System.out.println(i + "! = " + fact(i));

		// eGCD(6099, 2166);
	}

	static int GCD(int a, int b) {
		int r = a % b;
		if (r == 0) return b;
		else return GCD(b,r);	
	}

	static int GCD2(int a, int b) {
		int r = a % b;
		int i = 0;
		while (r != 0) {
			a = b;
			b = r;
			r = a % b;
			i++;
		}
		return i;
	}

	// Extended Euclid's Algorithm:
	// given two integers 'a' and 'b',
	// calculate the GCD 'd',
	// and integers m and n, such that:
	// a * m + b * n = d
	static void eGCD(int m, int n) {
		int r = m % n;
		int q = m / n;
		System.out.println();
		return;
	}

	static void eGCD(int a, int a2, int b, int b2, int c, int d) {
		int q = c / d;
		int r = c % d;
		if (r == 0)
			System.out.println(a + " * " + c + " + " + b + " * " + d + " = " + d);
		// eGCD();
	}
	
	static boolean isPrime(int a) {
		if (a<2) return false;
		int r = (int) Math.sqrt(a);
		for (int i = 2; i <= r; i++) 
			if (a % i == 0) return false;
		return true;
	}

	static int nCr(int a, int b) {
		
		return 0;
	}

	// negative values give false results
	static long fact(int a) {
		long n=1;
		for (int i=2;i<=a;i++) n *= i;
		return n; 
	}
}
