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
			map[r][c] = false; // 파이프를 설치했으므로 false
			return false;
		}
		
		for(int[] d : deltas) {	
			int nr = r + d[0];
			int nc = c + d[1];
			if(isInRange(nr, nc) && map[nr][nc] && map[r][c]) {
				
				map[r][c] = findRoot(nr, nc); // true가 리턴됐다면 이번 delta로 정한 다음 길은 사용 불가 && false가 리턴됐다면 파이프 설치 완료
				
			}
		}
		
		if(map[r][c]) {
			// 아무 길도 가지 못했다면 이 길 폐쇄 - 다시 오지 않기 위함
			map[r][c] = false;
			return true;
		}
		
		return false;
		
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
					map[i][j] = true; // 이 길로 갈 수 있음
				} else {
					map[i][j] = false; // 이 길로 갈 수 없음
				}
			}
			
		}
		
		for(int r=0; r<R; r++) {
			findRoot(r, 0);
		}
		
		System.out.println(ans);
	}
}