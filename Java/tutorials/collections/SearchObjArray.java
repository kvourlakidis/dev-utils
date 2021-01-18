package tutorials.collections;

import java.util.*;
import java.util.Arrays;

class SearchObjArray {
	public static void main(String[] args){
		String[] sa = {"one", "two", "three", "four"};
		Arrays.sort(sa);
		for (String s : sa) System.out.print(s + " ");
		System.out.println("\none = " + Arrays.binarySearch(sa, "one"));
		Arrays.sort(sa, new ReSortComparator());
		for (String s : sa) System.out.print(s + " ");
		System.out.println("\none = " + Arrays.binarySearch(sa, "one")); 
		System.out.println("\none = " + Arrays.binarySearch(sa, "bone", new ReSortComparator())); 
	}

static class ReSortComparator implements Comparator<String> {
	public int compare(String a, String b){
		return b.compareTo(a);
	}
}
}
