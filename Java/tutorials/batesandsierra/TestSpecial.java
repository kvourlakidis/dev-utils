class Special {
	private StringBuilder s = new StringBuilder("bob");
	StringBuilder getName() { return s;}
	void printName() { System.out.println(s); }
}

public class TestSpecial {
	public static void main(String[] main) {
		Special sp = new Special();
		StringBuilder s2 = sp.getName();
		s2.append("fred");
		sp.printName();
	}
}
