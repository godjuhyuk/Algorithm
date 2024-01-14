import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 각 N x N 칸에 적힌 숫자만큼 오른쪽, 아래쪽으로 점프를 해가면서 (0, 0)에서 출발해서 (N-1, N-1)에 도착하는 경우의 수를 세는 문제
 * 1초 안에 끝나야한다.
 * 
 * 문제 해결을 위한 고민)
 * 맵이 100 X 100의 맵일 때, 만약 모든 칸이 1이라면, 러프하게 잡아도 2^100 아닌가?
 * 어떻게 경우의 수를 줄일까?
 * 
 * => DP로 줄이기
 * 
 * 100 * 100 * 10 * 10
 * 
 * 자료구조)
 * 100 * 100 * 3 의 행렬
 * 0: 점프값
 * 1: 가로로 이 곳에 올 수 있는 경우의 수
 * 2: 세로로 이 곳에 올 수 있는 경우의 수
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int N;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		long[][] dp = new long[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int jump = map[r][c];
				if(jump == 0) break;
				if(c+jump < N) {
					dp[r][c+jump] += dp[r][c];
				}
				if(r+jump < N) {
					dp[r+jump][c] += dp[r][c];
				}
			}
		}
		
		System.out.println(dp[N-1][N-1]);
	}
	
	
}
