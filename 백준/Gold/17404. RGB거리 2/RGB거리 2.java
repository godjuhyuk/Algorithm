import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static final int MAX = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] cost=  new int[3][N];
		
		for(int i=0; i<N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			int blue = Integer.parseInt(st.nextToken());
			
			cost[0][i] = red;
			cost[1][i] = green;
			cost[2][i] = blue;
			
		}
		
		int[][] dp;
		
		int ans = MAX;
		
		for(int start = 0; start < 3; start++) {
			
			dp = new int[3][N];
			
			for(int i=0; i<3; i++) {
				if(start == i) dp[i][0] = cost[start][0];
				else dp[i][0] = MAX;
			}
			
			for(int i=1; i<N; i++) {
				
				dp[0][i] = Math.min(dp[1][i-1] + cost[0][i] , dp[2][i-1] + cost[0][i]);
				dp[1][i] = Math.min(dp[2][i-1] + cost[1][i] , dp[0][i-1] + cost[1][i]);
				dp[2][i] = Math.min(dp[0][i-1] + cost[2][i] , dp[1][i-1] + cost[2][i]);
			}
			
			for(int end = 0; end < 3; end++) {
				
				if(start != end) ans = Math.min(ans, dp[end][N-1]);
				
			}
			
		}
		
		System.out.println(ans);
		
	}
	
	
}
