/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(pq.size() == 0) sb.append('0').append('\n');
				else sb.append(pq.poll()).append('\n');
			} else {
				pq.offer(input);
			}
		}
		
		System.out.println(sb);
		
	}
}