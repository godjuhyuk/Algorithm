import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adjList = new ArrayList[N+1];
		int[] degrees = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			degrees[to]++;
			
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++) {
			if(degrees[i] == 0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			
			int from = q.poll();
			sb.append(from).append(' ');
			
			for(int to : adjList[from]) {
				if(--degrees[to] == 0) q.add(to);
			}
			
		}
		
		System.out.println(sb);
		
	}
	
}
