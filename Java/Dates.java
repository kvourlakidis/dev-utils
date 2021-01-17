import java.util.*;
import java.text.*;

public class Dates {
	public static void main(String[] args){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("'Era: 'G, Year: yyyy, Month: M (MM), Day: d (E), Hour (12): h, Hour (24): H, Minute: m, Second: s, Millisecond: S, Day in year: D, Week: w, AM/PM: a, Time zone: z");
		System.out.println(d);
		System.out.println(sdf.format(d));
	}
}
