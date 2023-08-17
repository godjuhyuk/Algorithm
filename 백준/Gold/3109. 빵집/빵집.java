import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int R, C, ans;
	private static char map[][];
	private static int deltas[][] = {{-1, 1}, {0, 1}, {1, 1}};
	
	private static boolean findRoot(int r, int c) {
		if(c == C) {
			ans++;
			map[r][c] = 'x';
			return true;
		}
		
		for(int[] d : deltas) {	
			int nr = r + d[0];
			int nc = c + d[1];
			if(map[nr][nc] == '.') 	{
				if(findRoot(nr, nc)) {
					map[r][c] = 'x';
					return true;
				}
			}
		}
		
		map[r][c] = 'x';
		return false;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R+2][C+2];
		
		for(int i=1; i<=R; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=1; j<=C; j++) {
				map[i][j] = temp[j-1];
			}
			
		}
		
		for(int r=1; r<=R; r++) {
			findRoot(r, 1);
		}
		
		System.out.println(ans);
	}
}