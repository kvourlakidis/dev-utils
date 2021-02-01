package tutorials.general;

public class Enums {
	public static void main(String[] args) {
		Size size = Size.values()[0];
		System.out.println(size);
		System.out.println(Container.InsideCont.Alfa_Romeo);
		System.out.println(Container.statVar);
		System.out.println(new Container().instVar);
		System.out.println(Planet.planets);
		System.out.println();
	}
	private enum Size {	SMALL,MEDIUM,LARGE; }
}
class Container {
	InsideCont instVar;
	static InsideCont statVar = InsideCont.BMW;
	public enum InsideCont {
		Mercedes, BMW, Alfa_Romeo;
		static { statVar = Mercedes; }	
	}
	static { statVar = InsideCont.Alfa_Romeo; }
}
enum Planet {
	Mercury(0), Venus(0), Earth(1), Mars(2);
	int moons;
	static final int planets = values().length;
	Planet(int moons) { 
		this.moons = moons; 
	}
}
