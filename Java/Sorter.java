import java.util.*;

class Sorter {
	public static void main(String[] args){
		List<String> sa = new ArrayList<>();
		
		sa.add("Zebra");
		sa.add("Mouse");
		sa.add(new String("Aardvark"));
		sa.add("Buffalo");
		sa.add("Lemur");
		sa.add("Cat");
		sa.add("Shark");
		sa.add("Whale");
		System.out.println("Unsorted: " + sa);
		Collections.sort(sa);
		System.out.println("Sorted:   " + sa);
	}
}

class Thing implements Comparable<Thing> {
	String thingName;
	Thing(String s){
		thingName = s;
	}
	public String toString(){
		return thingName;
	}
	public int compareTo(Thing t){
		return thingName.compareTo(t.thingName);
	}
}

class MySort implements Comparator<Object>{
	public int compare(Object a, Object b){
		Character lastA = ((String)a).charAt(((String)a).length() - 1);
		Character lastB = ((String)b).charAt(((String)b).length() - 1);
		return lastA.compareTo(lastB);
	}
}
