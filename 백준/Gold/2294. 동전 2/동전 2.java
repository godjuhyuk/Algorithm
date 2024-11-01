import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * n가지 종류의 동전이 있다. 
 * 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 
 * 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.
 * 
 * 입력)
 * 
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 
 * 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
 * 동전의 가치는 100,000보다 작거나 같은 자연수이다.
 * 가치가 같은 동전이 여러 번 주어질 수도 있다.
 * 
 * 출력)
 * 첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
 * 
 * 문제 해결을 위한 고민)
 * 탑다운으로?
 * 11원을 1,3,5로 채워보자
 * 
 * 1 10개
 * 
 * dp[x] = x가 되기 위한 최소 개수?
 * dp[2] = 1, dp[4] = 2, dp[6] = 3, dp[8] = 4, ...
 * dp[3] = 1, dp[6] = 2, ..., 
 * dp[5] = 1, dp[10] = 2, ...
 * 
 * 
 * dp[x] = dp[x-1] + dp[1] or dp[x-2] + dp[2] + or
 * 
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[100_001];
		Arrays.fill(dp, 100_001);
		
		List<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[100_001];
		
		for(int i=0; i<n; i++) {
			int input = Integer.parseInt(br.readLine());
			list.add(input);
			visited[input] = true;
			
		}
		
		
		for(Integer val: list) {
			if(!visited[val] || val > k) continue;
			visited[val] = false;
			for(int i=1, cnt = 1; i<=k/val; i++) {
				dp[val * i] = Math.min(dp[val*i], cnt++);
			}
			
		}
		
		// * dp[x] = dp[x-1] + dp[1] or dp[x-2] + dp[2] + or
		
		for(int x=1; x<=k; x++) {
			
			for(int i=1; i<x; i++) {
				dp[x] = Math.min(dp[x], dp[x-i] + dp[i]);
			}
			
		}
		System.out.println(dp[k] == 100_001 ? -1 : dp[k]);
		
	}

}