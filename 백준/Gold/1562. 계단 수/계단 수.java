import java.util.Scanner;

/**
 * 
 * 0~9을 다 방문했을때 길이가 2남았다면 경우의 수는 ?
 * 
 *
 * 1234567898765432 같은 애들이 문제임. 
 * 12323233223232 같은 애들 ㅇㅇ
 *  
 *  어떻게 하면 빠르게 쳐낼 수 있을까 ..
 *  
 *
 */
public class Main {
    
    static int N;
    static long ans;
    static long dp[][][];
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new long[N+1][10][1<<10];
        for(int i=1; i<10; i++) {
        	dp[1][i][1 << i] = 1;
        }
        
        for(int i=2; i<=N; i++) {
        	for(int j=0; j<=9; j++) {
        		for(int k=0; k<1024; k++) {
        			if(j > 0) {
        				dp[i][j][k | (1 << j)] += dp[i-1][j-1][k] % 1_000_000_000;
        			}
        			
        			if(j < 9) {
        				dp[i][j][k | (1 << j)] += dp[i-1][j+1][k] % 1_000_000_000;
        			}
        		}
        	}
        }
        long sum = 0;
        for(int i=0; i<=9; i++) {
        	sum = (sum + dp[N][i][1023] % 1_000_000_000) % 1_000_000_000;
        }
        System.out.println(sum);
        
    }
    
}