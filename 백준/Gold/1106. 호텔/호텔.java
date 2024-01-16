/**
 * 
 * 문제 해섞)
 * 
 * 도시가 주어지고, 각 도시별로 홍보하는데 드는 비용과
 * 그 때 몇명의 호텔 고객이 늘어나는지에 대한 정보가 주어진다.
 * 
 * 정수배만큼 투자가 가능하다.
 * 
 * 적어도 C명 늘리기 위해 투자해야하는 돈의 최솟값은?
 * 
 * 문제 해결을 위한 고민)
 * 
 * 1. 각자의 1인당 가성비를 구한다.
 * 2. 
 * 
 * 
 * @author GODJUHYEOK
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
public class Main {
 
    private static int c, n;
    private static int [] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
 
        dp= new int [c+101];
        Arrays.fill(dp,987654321);
        dp[0]=0;
        
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int cost = Integer.parseInt(st.nextToken());
        	int people = Integer.parseInt(st.nextToken());
        	
        	for(int j=people; j<c+101; j++) {
        		dp[j] = Math.min(dp[j], cost + dp[j - people]);
        	}
        }
        
        
        int ans = Integer.MAX_VALUE;
        for(int i=c; i<c+101; i++) {
        	ans = Math.min(dp[i], ans);
        }
        System.out.println(ans);
 
    }

}