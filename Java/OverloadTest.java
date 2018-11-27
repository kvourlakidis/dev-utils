class Thing {
	String s1, s2;
	Thing(String a, String b){
		s1 = a;
		s2 = b;
	}
	public boolean equals(Object o){
		// System.out.println("Inside the Thing's equals()");
		if ((o instanceof Thing) && ((Thing)o).s1 == this.s1) return true;
		else return false;
	}
}

class SpecialThing extends Thing {
	SpecialThing(String a, String b){
		super(a,b);
	}
	@Override
	public boolean equals(Object o){
		// System.out.println("Inside the SpecialThing's equals()");
		if ((o instanceof SpecialThing) && ((SpecialThing)o).s2 == this.s2) return true;
		else return false;
	}
}

public class OverloadTest {
	public static void main(String[] args){
		Object a = new SpecialThing("moof", "boof");
		Object b = new SpecialThing("moof", "boof");
		System.out.println( "Object a equals Object b: " + a.equals(b) );
		System.out.println( "Thing a equals Thing b: " + ((SpecialThing)a).equals((SpecialThing)b) );
		System.out.println( "SpecialThing a equals SpecialThing b: " + ( (SpecialThing)a ).equals( (SpecialThing)b ) );
		System.out.println( "a's hashCode() is: " + a.hashCode() );
		System.out.println( "b's hashCode() is: " + b.hashCode() );
	}
}
