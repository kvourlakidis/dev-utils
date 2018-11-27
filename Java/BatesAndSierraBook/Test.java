class Test {
	public static void main(String[] args) {
		char[] c = {0x4e, '\u004e', 78, 'N', '\u262D'};
		System.out.println((c[0] == c[1]) + " " + (c[0] == c[2]));
		for (int i=0; i<10000; i++) System.out.print((int) 710);
		System.out.println();
	}
}
