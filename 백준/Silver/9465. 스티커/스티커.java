import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 시작 시간 : 오후 3시 5분
 * 
 * 
 * 문제 해석)
 * 2 by n 직사각형에서
 * 스티커 하나를 고르면 그 주변(4방)은 못뽑는 구조
 * 최댓값 구하기
 * 
 * 해결법)
 * dp로 갱신해가면서 풀어보자
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t< T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			
			int[][] dp = new int[2][n];
			int[][] map = new int[2][n];
			
			for(int i=0; i<2; i++) {
				String[] input = br.readLine().split(" ");
				for (int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			if(n==1) {
				sb.append(Math.max(map[0][0], map[1][0])).append('\n');
				continue;
			}
			
			dp[0][0] = map[0][0];
			dp[1][0] = map[1][0];
			
			int max = Math.max(dp[0][0], dp[0][1]);
			for(int j=1; j<n; j++) {
				
				if(j==1) {
					dp[0][j] = dp[1][j-1] + map[0][j];
					dp[1][j] = dp[0][j-1] + map[1][j];
					max = Math.max(dp[0][j], dp[1][j]);
					continue;
				}
				
				dp[0][j] = Math.max(dp[1][j-2], dp[1][j-1]) + map[0][j];
				dp[1][j] = Math.max(dp[0][j-2], dp[0][j-1]) + map[1][j];
				max = Math.max(max, Math.max(dp[0][j], dp[1][j]));
			}
			sb.append(max).append('\n');
		}
		System.out.println(sb);
	}
}
