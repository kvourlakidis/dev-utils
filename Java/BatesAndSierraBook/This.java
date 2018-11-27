public class This {
	
	This th1s;

	This(int i, This th1s) {
		this.th1s = th1s;
	}

	public static void main(String[] args) {
		This that = null;
		This th1s = new This(1,that);
		System.out.println(th1s.th1s);
	}

}
