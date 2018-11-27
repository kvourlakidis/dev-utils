class Loopy {
public static void main(String[] args){
	int[] x = {1,2,3,4,5,6,7};
	
//	for (int y : x) { // legal
//	for (x : int y) { // illegal
//	int y=0; for (y : x) { // illegal
//	for (int y = 0,     z=0; z<x.length; z++) { y= x[z]; // legal
//	for (int y = 0, int z=0; z<x.length; z++) { y= x[z]; // illegal
	int y = 0; for (int z=0; z<x.length; z++) { y= x[z]; // legal
	System.out.println(y + " ");
	}
}
}
