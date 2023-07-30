import java.util.*;
import java.io.*;
public class Main {
	static int n, cnt, bishopCnt;
	static int[] result;
	static int[][] deltas = {{-1, -1}, {-1, 1}};
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] isBlacked;
	
	
	private static boolean isOutOfRange(int r, int c ) {
		return r<0 || r>=n || c<0 || c>=n;
	}
	
	private static boolean isSafe(int r, int c) {
		
		for(int[] delta: deltas) {
			int nr = r;
			int nc = c;
			while(true) {
				
				if(isOutOfRange(nr, nc)) {
					break;
				}
				
				if(visited[nr][nc]) {
					return false;
				}
				nr += delta[0];
				nc += delta[1];
			}
		}
		return true;
	}
	
	
	private static void dfs(int idx, int cnt, boolean isSearchBlack) {
		
		for(int i=idx+1; i<n*n; i++) {
			
			int r = i / n;
			int c = i % n;
			
			if( map[r][c] == 0 || isBlacked[r][c] != isSearchBlack || !isSafe(r, c) ) {
				continue;
			}
			
			visited[r][c] = true;
			dfs(i, cnt+1, isSearchBlack);
			visited[r][c] = false;
			
		}
		
		int resultIdx = isSearchBlack ? 1 : 0;
		result[resultIdx] = Math.max(result[resultIdx], cnt);

	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		isBlacked = new boolean[n][n];
		for(int i=0; i<n; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if((i%2 == 0 && j%2 == 1) || (i%2 == 1 && j%2 == 0)) {
					isBlacked[i][j] = true;
				}
			}
		}
		
		result = new int[2];
		dfs(-1, 0, true);
		dfs(-1, 0, false);
		System.out.println(result[0] + result[1]);
		br.close();
 	}
}