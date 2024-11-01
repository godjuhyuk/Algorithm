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
 * 방향성이 없는 그래프가 주어진다. 
 * 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 
 * 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.
 * 세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 
 * 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 
 * 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.
 * 
 * 문제 해결을 위한 고민)
 * 정점이 800개, 간선이 20만개
 * 어떻게 하면 두 정점X, Y을 반드시 통과하도록 구현할 수 있을까?
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	private static int N, E, A, B;
	private static final int INF = 555_555_555;
	private static List<Edge>[] adjList;
	
	private static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return weight - o.weight;
		};
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new List[N+1];
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<Edge>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Edge(to, weight));
			adjList[to].add(new Edge(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		int aToB = dijkstra(A, B);
		
		if(N == 2) {
			System.out.println(aToB >= INF ? -1 : aToB);
			return;
		}
		
		int ans1 = dijkstra(1, A) + dijkstra(B, N) + aToB;
		int ans2 = dijkstra(1, B) + dijkstra(A, N) + aToB;
		
		int ans = Math.min(ans1, ans2);
		
		System.out.println(ans >= INF ? -1 : ans);
	}
	
	private static int dijkstra(int start, int end) {
		
		boolean[] visited = new boolean[N+1];
		int[] dp = new int[N+1];
		Arrays.fill(dp, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(start, 0));
		dp[start] = 0;
		
		while(!pq.isEmpty()) {
			
			Edge temp = pq.poll();
			
			if(visited[temp.to]) continue;
			visited[temp.to] = true;
			
			for(Edge edge : adjList[temp.to]) {
				if(dp[edge.to] > dp[temp.to] + edge.weight) {
					dp[edge.to] = dp[temp.to] + edge.weight;
					pq.offer(new Edge(edge.to, dp[edge.to]));
				}
			}
			
		}
		
		
		return dp[end];
	}

}
