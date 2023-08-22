import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int N, ans1, ans2;
	private static int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	private static char[][] map;
	private static boolean[][] visited;
	
	private static void dfs(int r, int c, char color) {
		
		// 적록색약을 위한 mapChange
		if(color == 'G') map[r][c] = 'R';
		
		visited[r][c] = true;
		
		
		for(int[] d: deltas) {
			int nr = r + d[0];
			int nc = c + d[1];
			
			if(map[nr][nc] == color && !visited[nr][nc]) {
				dfs(nr, nc, color);
			}
		}
		

	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N+2][N+2];
		visited = new boolean[N+2][N+2];
		
		for(int i=1; i<=N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j=1; j<=N; j++) {
				map[i][j] = input[j-1];
			}
		}
		
		for(int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if(!visited[i][j]) {
					//비적록색약 dfs
					dfs(i, j, map[i][j]);
					ans1++;
				}
			}
		}
		
		visited = new boolean[N+2][N+2];
		
		
		for(int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if(!visited[i][j]) {
					//적록색약 dfs
					dfs(i, j, map[i][j]);
					ans2++;
				}
			}
		}
		
		
		System.out.println(ans1 + " " + ans2);
		
	}

}
