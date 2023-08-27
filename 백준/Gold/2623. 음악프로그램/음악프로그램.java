import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 * 보조PD들이 가져온 순서를 다 반영해서
 * 순서를 짜야한다.
 * 
 * 위상정렬인거같은데
 * 위상정렬은 DAG에서 사용이 가능하다.
 * 즉 싸이클이 없는 유향그래프여야한단얘기.
 * 
 * 일단 위상정렬 연습할겸 구현해보자.
 * 
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException  {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] degrees = new int[N+1];
		
		boolean[][] adjMatrix = new boolean[N+1][N+1];
		List<Integer>[] adjList = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<Integer>();
		
		// 입력받기
		for(int i=0; i<M; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j=1; j<input.length; j++) {
				int from = Integer.parseInt(input[j]);
				
				for(int k=j+1; k<input.length; k++) {
					int to = Integer.parseInt(input[k]);
					if(!adjMatrix[from][to]) {
						adjMatrix[from][to] = true;
						degrees[to]++;
						adjList[from].add(to);
					}
				}
				
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			if(degrees[i] == 0) q.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(!q.isEmpty()) {
			
			int temp = q.poll();
			sb.append(temp).append('\n');
			cnt++;
			for(int from : adjList[temp]) {
				
				if(--degrees[from] == 0) {
					q.offer(from);
				}
			}
			
		}
		
		if(cnt < N) System.out.println(0);
		else System.out.println(sb);
		
	}
}