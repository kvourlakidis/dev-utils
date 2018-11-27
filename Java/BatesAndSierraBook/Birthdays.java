import java.util.Date;

public class Birthdays {
	static int[] year = new int[100];

	public static void main (String[] args) {
		Date date = null;
		System.out.println(date);
		for (int i=0; i<100; i+=5) {
			for (int j=i; j<i+5; j++) 	
				System.out.print("year[" + j + "] = " + year[i] + " ");
			System.out.println("");
		}
		System.out.println(date);
	}
}
