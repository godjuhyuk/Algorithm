import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작시간: 오후 8시 6분
 * 
 * 문제 해석)
 * - 총 N개의 시험장이 있고, 각각의 시험장마다 응시자가 있다.
 * - i번 시험장에 있는 응시자의 수는 A_i명 
 * - 감독관은 총 두 종류: 총감독관, 부감독관
 * - 총감독관은 한 시험장에서 감시할 수 있는 수가 B명, 부감독관은 C명
 * - 각각의 시험장에 총 감독관은 오직 1명만 있어야하며, 부감독관은 여러명 있어도 된다.
 * - 각 시험장마다 응시생들을 모두 감시해야함.
 * - 이 때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성
 * 
 *  입력)
 *  - 1 <= N <= 1,000,000
 *  - 1 <= A_i <= 1,000,000
 *  - 1 <= B, C <= 1,000,000
 *  
 *  문제 풀이를 위한 고민)
 *  - 그리디 아닌가? 일단, 총감독관이 무조건 1명어야함.
 *  - 100만번 완탐하면서, 각 시험장마다 필요한 감독관 수를 수학적으로 찾는다.
 *  - (A - B)와 C의 관계를 어떻게 효율적으로 찾을것인지?
 *  - 3분 고민해보고 규칙을 못찾는다면 이진탐색으로 (lower bound를 찾아야함) 
 *  - 규칙 없는거같은데 이진탐색 ㄱ
 * 
 * 로직)
 * - 100만개 A_i 배열 만들고
 * - 완탐
 * - lower bound 찾는 로직
 * - 첫 범위: 1 <= x <= ((A-B) / C) + 1
 * - if mid * C < A-B:  
 *  	left = mid
 *  - else right = mid-1
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long ans = N;
		for(int i=0; i<N; i++) {
			arr[i] -= B;
			if(arr[i] <= 0) continue;
			
			if(C >= arr[i]) {
				ans++;
				continue;
			}
			else {
				
				int left = 1;
				int right = arr[i] / C + 1;
				int mid = -1;
				while(left < right) {
					mid = (left + right)/2;
//					 * - if mid * C < A-B:  
//						 *  	left = mid
//						 *  - else right = mid-1
					if(mid * C < arr[i]) {
						left = mid + 1;
					}
					else {
						right = mid;
					}
				}
				ans += left;
				
			}
			
			
		}
		
		System.out.println(ans);
		
		
	}
	
}