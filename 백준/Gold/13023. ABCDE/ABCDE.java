import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static List<Integer>[] adjList;
	private static boolean gameOver;
	private static boolean[] visited;
	
	private static void dfs(int start, int depths) {
	
		// 기저조건
		if(depths >= 4) {
			gameOver = true;
			return;
		}
		

		// 유도 파트
		for(int to : adjList[start]) {
			
			if(!visited[to]) {
				visited[to] = true;
				dfs(to, depths+1);
				visited[to] = false;
			}
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		gameOver = false;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		// 방문 배열 && 인접 리스트 생성
		visited = new boolean[N];
		adjList = new ArrayList[N];
		
		for(int i=0; i<N; i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			// 인접 리스트 갱신
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
			
		}
		
		
		for(int from=0; from<N; from++) {
			// 각 스타트 포인트마다 depths가 5가 나올때 까지 확인해야함
			if(!gameOver) {
				visited[from] = true;
				dfs(from, 0);
				visited[from] = false;
			}
		}
		
		if(gameOver) System.out.println(1);
		else System.out.println(0);
		
	}
	
}