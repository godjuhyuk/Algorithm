import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int R, C, ans;
	private static boolean map[][];
	private static int deltas[][] = {{-1, 1}, {0, 1}, {1, 1}};
	
	private static boolean isInRange(int r, int c) {
		return 0<=r && r< R && 0<=c && c<C;
	}
	
	private static boolean findRoot(int r, int c) {
		
		if(c == C-1) {
			ans++;
			map[r][c] = false;
			return false;
		}
		
		boolean NoAvailableRoute = true;
		
		for(int[] d : deltas) {	
			int nr = r + d[0];
			int nc = c + d[1];
			if(isInRange(nr, nc) && map[nr][nc] && map[r][c]) {
				NoAvailableRoute = false;
				map[r][c] = findRoot(nr, nc);
			}
		}
		
		if(NoAvailableRoute) {
			map[r][c] = false;
			return true;
		} else {
			return map[r][c];
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(temp[j] == '.') {
					map[i][j] = true;
				} else {
					map[i][j] = false;
				}
			}
			
		}
		
		for(int r=0; r<R; r++) {
			
			findRoot(r, 0);
		}
		
		System.out.println(ans);
	}
}