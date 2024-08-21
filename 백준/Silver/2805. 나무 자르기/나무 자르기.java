import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 * T_1, T_2, ...., T_N 의 나무가 주어진다.
 * 높이 H가 주어질 때,
 * 
 * SUM = sum(T_i - H) >= M을 만족하는 최대 H를 구해야 한다.
 * 
 * 즉,
 * 
 * SUM < M : H를 줄인다
 * SUM >= M : H를 높인다
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	private static int N, M, H;
	private static int[] trees;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(trees[i], max);
		}
		
		long left = 0;
		long right = max + 1;
		
		while(left + 1 < right) {
			
			long mid = (right + left) / 2;
			long result = getLengthOfTrees(mid);
			
			if(result < M) right = mid;
			else left = mid;
				
		}
		
		System.out.println(left);
		
		
	}

	private static long getLengthOfTrees(long mid) {
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += Math.max(trees[i] - mid, 0);
		}
		return sum;
	}
	
}
