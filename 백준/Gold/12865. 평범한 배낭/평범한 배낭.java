import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 0-1 Knapsack
 * 
 */
public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] bagInfo = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			bagInfo[i][0] = W;
			bagInfo[i][1] = V;
			
		}
		
		int[] dp = new int[K+1];
		
		for(int i = 0; i < N; i++) {
			for(int j=K; j>=0; j--) {
				if(j-bagInfo[i][0] >= 0) dp[j] = Math.max(dp[j], dp[j-bagInfo[i][0]] + bagInfo[i][1]);
			}
		}
		
		System.out.println(dp[K]);
		
	}
}
