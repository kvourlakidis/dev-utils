package tutorials.collections;

import java.util.*;

class PQ {

public static void main(String[] args){
	int[] ia = {1,2,3,4,5,6,7,8};
	PriorityQueue<Integer> pq1 = new PriorityQueue<>();
	for (int x : ia) pq1.offer(x);
	while (pq1.peek() != null)
		System.out.println(pq1.poll());
}
}
