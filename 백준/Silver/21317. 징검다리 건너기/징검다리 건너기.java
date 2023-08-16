import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] jump = new int[N+1][2];
		int[][] dp = new int[N+1][2];
		
		for(int i=1; i<=N-1; i++) {
			st = new StringTokenizer(br.readLine());
			jump[i][0] = Integer.parseInt(st.nextToken());
			jump[i][1] = Integer.parseInt(st.nextToken());
			
			dp[i][0] = dp[i-1][0] + jump[i-1][0];
			dp[i][1] = 5000 * 20;
		}
		
		dp[N][0] = dp[N-1][0] + jump[N-1][0];
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		if(N==2) {
			System.out.println(jump[1][0]);
			return;
		} 
		if(N==3) {
			System.out.println(Math.min(jump[1][1], dp[2][0] +  jump[2][0]));
			return;
		}
		
		dp[3][0] = Math.min(jump[1][1], dp[2][0] +  jump[2][0]);
		
		for(int i=4; i<=N; i++) {
			
			dp[i][0] = Math.min(dp[i][0], Math.min(dp[i-1][0] + jump[i-1][0], dp[i-2][0] + jump[i-2][1]));
			dp[i][1] = Math.min(dp[i-3][0] + K, Math.min(dp[i-1][1] + jump[i-1][0], dp[i-2][1] + jump[i-2][1]));
			
		}
		
		System.out.println(Math.min(dp[N][0], dp[N][1]));
	}
}