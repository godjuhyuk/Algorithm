import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int ans;
	private static int paperTotalCnt[] = {0, 5, 5, 5, 5, 5};
	private static int[][] map;
	
	private static void attach(int r, int c, int length, int val) {
		
		for(int i=r; i<r+length; i++) {
			for(int j=c; j<c+length; j++) {
				map[i][j] = val;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10][10];
		ans = Integer.MAX_VALUE;
		
		// 맵 입력
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(0, 0, 0);
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
			
	}
	
	private static void backtracking(int r, int c, int cnt) {
		
		if(r >= 9 && c > 9) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(ans <= cnt) {
			return;
		}
		
		if(c > 9) {
			backtracking(r+1, 0, cnt);
			return;
		}
		
		if(map[r][c] == 1) {
			
			for(int x=5; x>=1; x--) {
				
				if(paperTotalCnt[x] > 0 && isAbleToAttach(r, c, x)) {
					
					attach(r, c, x, 0);
					paperTotalCnt[x]--;
					backtracking(r, c+1, cnt+1);
					paperTotalCnt[x]++;
					attach(r, c, x, 1);
					
				}
				
			}
			
		} else {
			backtracking(r, c+1, cnt);
		}
		
	}
	
	private static boolean isAbleToAttach(int r, int c, int length) {
		
		if(r+length > 10 || c + length > 10) return false;
		
		for(int i=r; i<r+length; i++) {
			for(int j=c; j<c+length; j++) {
				if(map[i][j] != 1) return false;
			}
		}
		
		return true;
	}
	
	
}