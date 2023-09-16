import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][][] dp = new int[N][N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					dp[i][j][0] = -1;
					dp[i][j][1] = -1;
					dp[i][j][2] = -1;
				}
				
			}
		}
		if(dp[N-1][N-1][0] < 0 ) {
            System.out.println(0);
            return;
        }
        
		// 초기값 세팅
		dp[0][1][0] = 1;
		
		for(int i=0; i<N; i++) {
			for(int j=2; j<N; j++) {
				
				// 벽인 경우 
				if(dp[i][j][0] == -1) continue;
				
				// 가로 모양으로 도착한 경우
				if(dp[i][j-1][0] != -1) dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
				
				
				if(i == 0) continue;
				
				// 세로 모양으로 도착한 경우
				if(dp[i-1][j][0] != -1) dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				
				// 대각선 모양으로 도착한 경우
				if(dp[i-1][j][0] != -1 && dp[i][j-1][0] != -1 && dp[i-1][j-1][0] != -1) {
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
					
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
		
	}
	
}