import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, V;
	private static boolean[] visited;
	private static List<Integer>[] adjList;
	private static StringBuilder sb;
	
	private static void dfs(int start) {
		sb.append(start).append(' ');
		visited[start] = true;
		for(int i=0; i<adjList[start].size(); i++) {
			int node = adjList[start].get(i);
			if(node > 0 && !visited[node]) {
				visited[node] = true;
				dfs(node);
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
			for(int i = 0; i < adjList[head].size(); i++) {
				int node = adjList[head].get(i);
				if(node > 0 && !visited[node]) {
					bfsQueue.add(node);
					visited[node] = true;
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
		
		// 인접리스트 생성
		adjList = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

		// 간선 정보 갱신
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjList[A].add(B);
			adjList[B].add(A);
		}
		
		for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);;
        }
		
		dfs(V);
		sb.append('\n');
		
		// visited 초기화
		Arrays.fill(visited, false);
		
		bfs(V);
		
		System.out.println(sb);
		
		
	}
}
