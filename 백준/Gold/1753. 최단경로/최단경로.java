import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 시작 시간: 오후8시 38분
 * 
 * 문제 해석)
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.
 * 
 * 입력)
 * 첫째줄에는 정점의 개수 V와 간선의 개수 E가 주어진다.
 * 모든 정점에는 1부터 V까지 번호가 매겨져있고
 * 
 * 둘째줄에는 시작 정점의 번호 K가 주어진다.
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	private static class Edge implements Comparable<Edge>{
		
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	private static final int INF = 999999999;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		List<Edge>[] list = new List[V+1];
		for(int i=1; i<=V; i++) list[i] = new ArrayList<Edge>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, weight));
		}
		
		int[] dp = new int[V+1];
		boolean[] visited = new boolean[V+1];
		Arrays.fill(dp, INF);
		
		
		dp[K] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

		pq.add(new Edge(K, 0));
		
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			
			if(visited[temp.to]) continue;
			visited[temp.to] = true;
			
			for(Edge edge : list[temp.to]) {
				if(dp[temp.to] + edge.weight < dp[edge.to]) {
					dp[edge.to] = dp[temp.to] + edge.weight;
					pq.add(new Edge(edge.to, dp[edge.to]));
				}
			}
			
//			if(dp[temp.from] + temp.weight < dp[temp.to]) {
//				dp[temp.to] = dp[temp.from] + temp.weight;
//				for(Edge edge : list[temp.to]) {
//					if(dp[edge.from] + edge.weight < dp[edge.to]) {
//						pq.offer(edge);
//					}
//				}
//			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=V; i++) {
			if(dp[i] == INF) sb.append("INF").append('\n');
			else sb.append(dp[i]).append('\n');
		}
		
		System.out.println(sb);
		
		
		
	}
	
}