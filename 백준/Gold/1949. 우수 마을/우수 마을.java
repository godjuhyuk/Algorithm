import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 최소 스패닝 트리
 * 
 * 1. 내 마을이 우수 마을로 선정됐을 경우
 * => 나와 인접한 마을들은 우수마을X
 * 
 * 2. 내 마을이 우수 마을이 아닐 경우
 * => 나와 인접한 마을들은? 우수마을이거나 아니다.
 * => 나와 인접한 마을이 전부 우수마을이 아니기만 하면 된다 (기저조건)
 * 
 * 1, 2를 근거로 점화식을 세워보자.
 * 
 * x 마을이 우수마을로 선정됐을 경우
 * dp[1][x] = num[x] + dp[0][인접마을 전부]
 * 
 * x 마을이 우수마을이 아닐 경우
 * 	dp[0][x] = Math.max(dp[1][인접마을], dp[0][인접마을])
 * 
 */
public class Main {
	private static int numInfo[], dp[][];
	private static boolean[] visited;
	private static List<Integer>[] adjList;
	
	private static int dfs(int num) {
		
		for(int to : adjList[num]) {
			// 우수 마을로 선정된 경우
			if(!visited[to]) {
				visited[to] = true;
				dp[1][num] += dfs(to);
				dp[0][num] += Math.max(dp[0][to], dp[1][to]);
			}
			
		}
		
		return dp[0][num];

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		numInfo = new int[N+1];
		visited = new boolean[N+1];
		dp = new int[2][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			numInfo[i] = Integer.parseInt(st.nextToken());
			dp[1][i] = numInfo[i];
		}
		
		adjList = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		visited[1] = true;
		dfs(1);
		
		System.out.println(Math.max(dp[1][1], dp[0][1]));
		
	}
	
}
