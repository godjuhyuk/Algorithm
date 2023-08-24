import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, map[][], ans;
	
	private static boolean isOutOfRange(int r, int c) {
		return 0>r || r>=N || 0 > c || c>=N;
	}
	
	private static boolean canDiagMove(int hr, int hc) {
		
		return !isOutOfRange(hr+1, hc+1) && map[hr+1][hc] == 0 && map[hr][hc+1] == 0 && map[hr+1][hc+1] == 0; 
		
	}
	
	private static void backtracking(int hr, int hc, int tr, int tc) {
		if(hr==N-1 && hc == N-1) {
			ans++;
			return;
		}
		
		// 가로 상태
		if(hr==tr) {
			if(!isOutOfRange(hr,hc+1) && map[hr][hc+1] == 0) backtracking(hr, hc+1, hr, hc);
			if(canDiagMove(hr, hc) && map[hr][hc+1] == 0) backtracking(hr+1, hc+1, hr, hc);
		}
		
		// 세로 상태
		else if(hc==tc) {
			if(!isOutOfRange(hr+1,hc) && map[hr+1][hc] == 0) backtracking(hr+1, hc, hr, hc);
			if(canDiagMove(hr, hc)) backtracking(hr+1, hc+1, hr, hc);
		}
		
		// 대각선 상태
		else {
			if(!isOutOfRange(hr+1,hc) && map[hr+1][hc] == 0) backtracking(hr+1, hc, hr, hc);
			if(!isOutOfRange(hr,hc+1) && map[hr][hc+1] == 0) backtracking(hr, hc+1, hr, hc);
			if(canDiagMove(hr, hc)) backtracking(hr+1, hc+1, hr, hc);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		backtracking(0, 1, 0, 0);
		
		System.out.println(ans);
		
	}
	
}