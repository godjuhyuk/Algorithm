import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 설계 시작: 오후 4시 25분
 * 문제 해석)
 * 양방향 친구 관계가 주어진다. (싸이클x)
 * 트리를 형성했을때 얼리어답터가 몇명이어야 전부 아이디어를 받아들이는가?
 * 
 * 문제 해결을 위한 고민)
 * 
 * 어떻게 선정할 것인가?
 * 어떤 기준으로 ?
 * 
 * => leaf 바로 위 노드부터 시작해서 grandparent를 전부 얼리어답터로 만드는 경우
 * => 겹치는 경우가 없을까 ?
 *
 * 
 * 답 본 시간 - 오후 5: 24
 * 
 * 논리를 전개하는 과정이 DP스러우면서도 흥미로웠다.
 * 
 * 내가 얼리어답터면 자식(연결되어있는 노드들)은 얼리어답터가 아니어도 되지만,
 * 내가 얼리어답터가 아니라면, 자식 노드들은 무적권 얼리어답터가 되야한다는것.
 * 
 * x: 나
 * y: 자식
 * 
 * dp[x][0] : x가 얼리어답터가 아닐 경우, 내 자식들 중 얼리어답터의 명 수
 * dp[x][1] : x가 얼리어답터인 경우, 내 자식들 중 얼리어답터의 명 수
 * 
 * dp[x][0] += Math.min(dp[y][1])
 * dp[x][1] += Math.min(dp[y][0], dp[y][1])
 * 
 * 
 */
public class Main {
	static int[][] dp;
	static boolean[] visited;
	static List<Integer>[] adjList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList();
		
		for(int i=0; i<N-1; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
			
		}
		
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		
	}
	
	private static void dfs(int start) {
		visited[start] = true;
		
		if(dp[start][1] != 0) return;
		
		// 기저조건
		dp[start][0] = 0;
		dp[start][1] = 1;
		
		// 유도파트
		for(int child : adjList[start]) {
			if(!visited[child]) {
				
				// child dp 갱신
				dfs(child);
				
				// 내가 얼리어답터가 아니라면, child는 무조건 얼리어답터야함
				dp[start][0] += dp[child][1];
				
				// 내가 얼리어답터면, child는 얼리어답터거나 아니면 됨
				dp[start][1] += Math.min(dp[child][0], dp[child][1]);
			}
			
		}
		
	}
}