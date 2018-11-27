public class Days {

	enum Day {M, T, W, TH, F, SA, SU};

	public static void main(String[] args) {
		Day d1 = Day.TH;
		Day d2 = Day.M;

		for (Day d: Day.values() ) {
			if (d.equals(Day.F)) break;
			d2 = d;
		}
		System.out.println( "d1 == d2: " + (d1 == d2) );
	}

}
