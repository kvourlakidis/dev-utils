class Plane {
	static String s = "-";
	public static void main(String[] args) {
		Plane mPlane = new Plane(); // s == "-"
		mPlane.s1();
		System.out.println(s);
	}
	void s1() {
		try { s2(); }
		catch (Exception e) { s += "c"; }
	}
	void s2() throws Exception {
		s3();
		s += "2"; // doesn't get executed
		s3();     // doesn't get executed
		s += "2b";// doesn't get executed
	}
	void s3() throws Exception {
		throw new Exception();
	}
}