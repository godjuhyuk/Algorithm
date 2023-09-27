import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 설계 시작 시간: 오후 9시 13분
 * 
 * 문제 해석)
 * 
 * 코스의 개수 N이 주어진다. (1<=N<=100,000)
 * => 시작점 제외, 시작점은 0.
 * 그 뒤에 현재 위치한 코스를 파악할 K가 주어진다.
 * 그리고 거리가 N개 주어진다.
 * 
 * 최대 거리는 50억이므로 long 사용해야함
 * 
 * 문제 해결을 위한 고민)
 * 
 * 
 * N개 array에 값 받기
 * K를 먼저주니깐 입력받으면서부터 확인해보는 로직
 * 입력받으면서 누적합을 더해가며 K보다 커지거나 같으면 거기가 정답.
 * 
 * => 이 풀이의 경우 K가 반환점을 지나서 돌아와야할 수로 주어지는 경우엔
 * 한번 더 역으로 순회해야한다..
 * 
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		int[] flagArr = new int[N+1];
		
		long distSum = 0;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			
			if(distSum > K) {
				System.out.println(i-1);
				return;
			} else if(distSum == K) {
				System.out.println(i);
				return;
			}
			
			int a = Integer.parseInt(st.nextToken());
			flagArr[i] = a;
			distSum += a;
		}
		
		for(int i=N; i>=0; i--) {
			
			distSum += flagArr[i];
			if(distSum > K) {
				System.out.println(i);
				return;	
			} else if(distSum == K) {
				System.out.println(i-1);
				return;
			}
		}
		
	}
	
}