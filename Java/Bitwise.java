public class Bitwise {
	public static void main(String[] args) {
		byte b1 = 6 & 8; // 0110 AND 1000 = 0000
		byte b2 = 7 | 9; // 0111 OR 1001  = 1111
		byte b3 = 5 ^ 4; // 101 XOR 100 = 001
		System.out.println(b1 + " " + b2 + " " + b3);
	}
}
