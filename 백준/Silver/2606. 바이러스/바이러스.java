/* package whatever; // don't place package name! */

import java.util.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Main
{
	public static void main (String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N+1];
		boolean[][] pointArr = new boolean[N+1][N+1];
		
		for(int i=0; i<M;i++){
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			pointArr[A][B] = true;
			pointArr[B][A] = true;
		}
		Stack<Integer> stack = new Stack<>();
		for(int i=1; i<N+1; i++){
			if(pointArr[i][1]){
				stack.push(i);
			}
		}
		int loca;
		int cnt = 0;
		visited[1] = true;
		while(!stack.isEmpty()){
			loca = stack.pop();
			if(!visited[loca]) cnt++;
			visited[loca] = true;
			for(int i=1; i<N+1; i++){
				if(!visited[i] && pointArr[loca][i]){
					stack.push(i);
				}
			}
		}
		System.out.println(cnt);
	}
}