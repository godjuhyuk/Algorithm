import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 시작 시간: 오후 9시 31분
 * 
 * 문제 해석)
 * 각 간선 가중치가 주어지는데, 
 * A에서 B로 갈때 ans[A][B]는 A에서 B까지의 최단경로 중 가장 먼저 들르는 정점 번호
 * 
 * 
 * @author GODJUHYEOK
 *
 */

public class Main {
	
	private static final int INF = 999999999;

	private static class Node implements Comparable<Node> {
		
		int to;
		int weight;
		int firstTo;
		public Node(int to, int weight, int firstTo) {
			super();
			this.to = to;
			this.weight = weight;
			this.firstTo = firstTo;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Node>[] list = new List[N+1];
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Node>();
		
		int[][] dp = new int[N+1][N+1];
		int[][] ans = new int[N+1][N+1];
		for(int i=1; i<=N; i++) Arrays.fill(dp[i], INF);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, weight, 0));
			list[to].add(new Node(from, weight, 0));
		}
		
		for(int i=1; i<=N; i++) {
			
			boolean[] visited = new boolean[N+1];
			dp[i][i] = 0;
			ans[i][i] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			pq.offer(new Node(i, 0, 0));
			
			while(!pq.isEmpty()) {
				Node temp = pq.poll();
				
				if(visited[temp.to]) continue;
				visited[temp.to] = true;
				ans[i][temp.to] = temp.firstTo;
				for(Node node : list[temp.to]) {
					if(dp[i][node.to] > dp[i][temp.to] + node.weight) {
						dp[i][node.to] = dp[i][temp.to] + node.weight;
						if(temp.firstTo == 0) pq.offer(new Node(node.to, dp[i][node.to], node.to));
						else pq.offer(new Node(node.to, dp[i][node.to], temp.firstTo));
					}
				}
				
			}
		
		}
		StringBuilder sb  = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(ans[i][j]==0) sb.append('-').append(' ');
				else sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}
	
}
