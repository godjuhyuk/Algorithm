import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간: 오후 9시 50분
 * 
 * 문제 해석)
 * K개의 랜선이 있다
 * 이 K개의 랜선은 길이가 제각각인데, 이 랜선을 정수 A로 잘라서 나오는 개수의 총합이 N이 되어야한다. 
 * 즉 k1, k2, k3, ..., kn 에 대하여
 * 
 * sum(k_i // A) = N이 되도록 하는 A의 최댓값을 구하는 문제이다. 
 * 
 * 항상 K<=N이기 때문에, A<=K 일 것이며
 * K<=10000 이기 때문에 
 * 14번의 이분탐색 안에 최적의 A를 찾을 수 있다?
 * 
 * SUM > N : A를 줄이자
 * SUM <= N : A를 늘리자
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	private static int K, N;
	private static int[] lines;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lines = new int[K];
		long max = Integer.MIN_VALUE;
		for(int i=0; i<K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			max = Math.max(lines[i], max);
		}
		
		
		long left = 0;
		long right = max + 1;
		while(left + 1< right) {
			long mid = (left + right) / 2;
			long result = getNumOfLine(mid);
			
			if(result < N) right = mid;
			else left = mid;
		}
		
		System.out.println(left);
		
	}

	private static long getNumOfLine(long mid) {
		long sum = 0;
		for(int i=0; i<K; i++) {
			sum += lines[i] / mid;
		}
		return sum;
	}
}
