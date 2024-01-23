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
	
	private static int N, M, K;
	private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static boolean[][] map, visited;
	
	public static void main(String[] args) throws IOException {
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 M = Integer.parseInt(st.nextToken());
		 N = Integer.parseInt(st.nextToken());
		 K = Integer.parseInt(st.nextToken());
		 
		 map = new boolean[N+1][M];
		 visited = new boolean[N+1][M];
		 
		 for(int i=0; i<K; i++) {
			 st = new StringTokenizer(br.readLine());
			 
			 int r1 = Integer.parseInt(st.nextToken());
			 int c1 = Integer.parseInt(st.nextToken());
			 int r2 = Integer.parseInt(st.nextToken());
			 int c2 = Integer.parseInt(st.nextToken());
			 
			 for(int r=r1; r<r2; r++) {
				 for(int c=c1; c<c2; c++) {
					 map[r][c] = true;
					 visited[r][c] = true;  
				 }
			 }
			 
		 }
		 
		 List<Integer> ans = new ArrayList<Integer>();
		 int cnt = 0;
		 for(int r=0; r<N; r++) {
			 for(int c=0; c<M; c++) {
				 if(!visited[r][c]) {
					 cnt++;
					 ans.add(bfs(r, c));
				 }
			 }
		 } 
		
		 Collections.sort(ans);
		 StringBuilder sb = new StringBuilder();
		 sb.append(cnt).append('\n');
		 
		 for(int num : ans) sb.append(num).append(' ');
		 
		 System.out.println(sb);
		 
	}

	private static int bfs(int r, int c) {
		
		visited[r][c] = true;
		int sum = 1;
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int[] d : deltas) {
				int nr = temp[0] + d[0];
				int nc = temp[1] + d[1];
				
				if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;
				
				q.add(new int[] {nr, nc});
				visited[nr][nc] = true;
				sum++;
			}
		}
		
		return sum;
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}
	
}
