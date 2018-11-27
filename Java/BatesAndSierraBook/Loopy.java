class Loopy {
	public static void main(String[] args){
		int[] x = {1,2,3,4,5,6,7,8,9};
		for (int y=0, z=0; z<x.length; z++ ) {
			y = x[z];
			System.out.print(y + " ");
		}
	}
}
