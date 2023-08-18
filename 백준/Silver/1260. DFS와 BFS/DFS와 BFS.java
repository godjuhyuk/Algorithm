import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, V;
	private static boolean[] visited;
	private static boolean[][] adjMatrix;
	private static StringBuilder sb;
	
	private static void dfs(int start) {
		sb.append(start).append(' ');
		visited[start] = true;
		for(int i=1; i<=N; i++) {
			if(adjMatrix[start][i] && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
		return;
	}
	
	private static void bfs(int start) {

		// Queue 생성
		Queue<Integer> bfsQueue = new ArrayDeque<>();
		bfsQueue.add(start);
		
		while(!bfsQueue.isEmpty()) {
			int head = bfsQueue.poll();
			sb.append(head).append(' ');
			visited[head] = true;
			for(int i = 1; i <= N; i++) {
				if(adjMatrix[head][i] && !visited[i]) {
					bfsQueue.add(i);
					visited[i] = true;
				}
			}
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 방문배열 생성
		visited = new boolean[N+1];
		
		// 인접행렬 생성
		adjMatrix = new boolean[N+1][N+1];
		
		// 간선 정보 갱신
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjMatrix[A][B] = true;
			adjMatrix[B][A] = true;
		}
		
		dfs(V);
		sb.append('\n');
		
		// visited 초기화
		Arrays.fill(visited, false);
		
		bfs(V);
		
		System.out.println(sb);
		
		
	}
}
