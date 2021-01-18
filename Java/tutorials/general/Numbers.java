package tutorials.general;

class Numbers {
public static void main(String[] args) {
	Integer x = 16;
	System.out.println("x = " + x);
	System.out.println("x.compareTo(17) = " + x.compareTo(17));
	System.out.println("x.compareTo(15) = " + x.compareTo(15));
	System.out.println("x.compareTo(16) = " + x.compareTo(16));
	System.out.println("x.equals(16) = " + x.equals(16));
	System.out.println("Math.abs(-10) = " + Math.abs(-10));
	System.out.println("Math.ceil(3.1) = " + Math.ceil(3.1));
	System.out.println("Math.floor(3.9) = " + Math.floor(3.9));
	System.out.println("Math.rint(3.5) = " + Math.rint(3.5));
	System.out.println("Math.round(3.5) = " + Math.round(3.5));
	System.out.println("Math.max(3.14, 4) = " + Math.max(3.14,4));
	System.out.println("Math.exp(1) = " + Math.exp(1));
	System.out.println("Math.log(10) = " + Math.log(10));
	System.out.println("Math.pow(3,4) = " + Math.pow(3,4));
	System.out.println("Math.sqrt(169) = " + Math.sqrt(169));
	System.out.println("Math.PI = " + Math.PI);
	System.out.println("Math.sin(Math.PI/6) = " + Math.sin(Math.PI/6));
	System.out.println("Math.cos(Math.PI/3) = " + Math.cos(Math.PI/3));
	System.out.println("Math.tan(Math.PI/4) = " + Math.tan(Math.PI/4));
	System.out.println("Math.asin(1) = " + Math.asin(1));
	System.out.println("Math.acos(-1) = " + Math.acos(-1));
	System.out.println("Math.atan(1) = " + Math.atan(1));
	System.out.println("Math.atan2(7,1) = " + Math.atan2(7,1));
	System.out.println("Math.toDegrees(3.1416) = " + Math.toDegrees(3.1416));
	System.out.println("Math.toRadians(45) = " + Math.toRadians(45));
	for (int i=0;i<10;i++) System.out.println("Math.random() = " + Math.random());
}
}
