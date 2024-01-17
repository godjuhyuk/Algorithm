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
 * 문제 해석)
 * 
 * 도시 개수, 도로 정보, 출발도시X, 최단거리 K가 주어진다.
 * 
 * X에서 시작해서 도로 정보를 통해 K만에 도착하는 도시의 개수 ?
 * 
 * 다익스트라로 구해야할듯?
 * 
 * 다익스트라는 BFS + pq로 알고 있다.
 * 가장 가까운 곳부터 인접리스트를 돌며 dp 배열을 갱신한다..
 * 그리고 가장 가까운곳에서 출발해서 다시 인접리스트를 돌며 dp배열을 갱신한다..
 * 
 * O(V+E)로 끝나지않나?
 * 
 * 문제 해결을 위한 고민)
 * 
 * dp 배열을 갱신하면서도 최소인 점을 구해놨다가 그 점에서부터 시작하면 되지않나?
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int N, M, K, X, dp[];
	private static final int MAX = 1_000_000_000;
	private static List<Integer>[] adjList;
	
	public static class Edge implements Comparable<Edge> {
		
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1];
		Arrays.fill(dp, MAX);
		boolean[] visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		for(int to : adjList[X]) {
			pq.offer(new Edge(X, to, 1));
			dp[to] = 1;
		}
		
		visited[X] = true;
		
		while(!pq.isEmpty()) {
			
			Edge temp = pq.poll();
			if(visited[temp.to]) continue;
			visited[temp.to] = true;
			
			for(int to : adjList[temp.to]) {
				if(!visited[to]) {
					if(dp[to] > dp[temp.to] + 1) {
						dp[to] = dp[temp.to] + 1;
						pq.offer(new Edge(temp.from, to, dp[to]));
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++) {
			if(dp[i] == K) sb.append(i).append('\n');
		}
		
		if(sb.length() == 0)  System.out.println(-1);
		else System.out.println(sb);
		
	}

}
