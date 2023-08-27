import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static boolean isOutOfRange(int r, int c) {
		
		return 0>r || r >=N || 0>c || c>=N;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		
		int[][] deltas = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
		
		char[][] map = new char[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) Arrays.fill(map[i], '.');
		
		map[r][c] = 'v';
		visited[r][c] = true;
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			
			int[] carrot = q.poll();
			
			for(int[] d : deltas) {
				int nr = carrot[0] + d[0];
				int nc = carrot[1] + d[1];
				
				if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;
				
				map[nr][nc] = 'v';
				visited[nr][nc] = true;
				
				q.offer(new int[] {nr, nc});
				
			}
			
		}
		
		for(int i=0; i<N; i++) System.out.println(map[i]);
		
	}
}