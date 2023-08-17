import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int R, C, ans;
	private static boolean[] visited = new boolean[26];
	private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static char[][] map;
	
	private static boolean isInRange(int r, int c) {
		return 0<=r && r< R && 0<=c && c<C;
	}
	
	private static void dfs(int cnt, int r, int c) {
		
		visited[map[r][c] -'A'] = true;
		
		// 유도파트
		boolean isExit = true;
		for(int[] d : deltas) {
			int nr = r + d[0];
			int nc = c + d[1];
			
			
			if(isInRange(nr, nc) && !visited[map[nr][nc] - 'A']) {
				isExit = false;
				int alphabetIdx = map[nr][nc] - 'A';
				visited[alphabetIdx] = true;
				dfs(cnt+1, nr, nc);
				visited[alphabetIdx] = false;
			}
		}
		
		if(isExit) {
			ans = Math.max(ans, cnt);
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		
		dfs(1, 0, 0);
		
		System.out.println(ans);
		
		
	}

}