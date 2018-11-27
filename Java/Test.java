import java.util.*;

public class Test
{
	public static void main (String[] args)
	{
		List<Integer> ia = Arrays.asList(1,2,3,4,5,6,7,8,9,10);	
		ia.stream().forEach(x -> timesTwo(x)).forEach(x -> System.out.println(x));


		// String s = "abcdefghijklmnopqrstuvwxyz";
		// for (int i=0;i<s.length();i++) System.out.printf( "%s = %08d \n", s.charAt(i), Integer.parseInt(Integer.toBinaryString(s.charAt(i))) );
		
	}

	static int timesTwo(int a){
		return a*2;
	}
}

