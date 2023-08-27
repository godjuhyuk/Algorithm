import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, K;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
		
			st = new StringTokenizer(br.readLine());

			int[] degrees = new int[N+1];
			int[] timeInfo = new int[N+1];
			List<Integer>[] adjList = new ArrayList[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
				timeInfo[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				degrees[to]++;
				adjList[from].add(to);
				
			}
			
			int W = Integer.parseInt(br.readLine());
			
			Queue<Integer> q = new ArrayDeque<Integer>();
			
			for(int i=1; i<=N; i++) {
				if(degrees[i] == 0) {
					q.offer(i);
				}
			}
			
			int[] dp = new int[N+1];
			
			game : while(!q.isEmpty()) {
				
				int qSize = q.size();
				
				for(int k=0; k<qSize; k++) {
					
					int from = q.poll();
					dp[from] += timeInfo[from];
					if(from == W) {
						sb.append(dp[from]).append('\n');
						break game;
					}
					for(int to : adjList[from]) {
						if(--degrees[to] == 0) {
							q.offer(to);
						}
						dp[to] = Math.max(dp[to], dp[from]);
					}
					
				}
				
			}
			
			
		}
		System.out.println(sb);
		
	}
}