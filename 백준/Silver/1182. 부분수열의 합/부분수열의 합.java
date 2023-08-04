import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 정의)
 * 
 * N개의 수열에서 크기가 1 이상인 부분 수열을 뽑아낸다.
 * 해당 부분 수열의 원소 총합이 입력값으로 주어지는 S가 되는 경우의 수를 출력한다.
 * 
 * 풀이1)
 * 재귀를 용한 부분집합 구현 
 * 
 * 문제 풀이 로직
 * 1. 입출력을 받고 전체 수열과 S를 초기화한다. (성재 코드에서 배운 MapToInt 사용)
 * 2. 부분집합을 얻어내는 재귀 함수를 구현한다.
 * 3. 원소의 총합을 얻어내는 메소드를 구현한다.
 * 
 * 2-1)
 * 기저조건: depths가 N일때 return한다.
 * 		    리턴하기 전에 원소의 총합을 구하는 메소드를 사용, S와 비교한 뒤 ans를 카운트한다.
 * 
 * 유도파트: 부분집합을 구현해야하기 때문에 visited (isSelected)로 true, false인 2가지 경우의 수를
 * 			재귀 함수에 넘긴다.
 *
 *풀이1의 최악 시간 복잡도는 다음과 같다.
 *모든 경우의 수를 고려함과 동시에 부분집합을 for문으로 돌며 총합을 구해야하므로 O(2^N * N)이다.
 *N은 최대 20이므로 최악의 경우 2^20 * 20 = 100만 * 20 = 약 2,000만번의 연산이 필요하다.  
 * 
 * 
 * 풀이2)
 * 비트마스크를 이용한 부분집합 구현
 * 
 * 문제 풀이 로직
 * 
 * 비트마스크를 이용하여 부분집합을 구현하고, 총합을 카운트한다.
 * 시간복잡도는 풀이1과 같다.
 * 
 * 
 * 풀이1은 구현로직이 익숙하지만 "부분 집합"을 구하는 재귀함수의 유도파트가 익숙하지 않고
 * 풀이2는 비트마스킹이 어느 정도 익숙해졌지만 더 연습해보아야 하므로
 * 두가지 방법 모두 구현해본다.
 * 
 * @author SSAFY
 *
 */
public class Main {
	private static int N, S, ans1, ans2;
	private static int[] input, numList;
	
	public static void main(String[] args) throws IOException
	{	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력값을 이용한 초기화 
		input = Arrays
				.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		numList = Arrays
				.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		N = input[0];
		S = input[1];
		
		
		// 재귀 함수 이용
		solve1();
		
		// 비트마스킹 이용
//		solve2();
				
		
	}
	
	// 부분집합을 구하는 재귀 함수
	private static void getPowerSet(int depths, int sum) {
		
		// 기저조건
		if(depths == N) {
			if(sum==S) {
				ans1++;
			}
			return;
		}
		
		getPowerSet(depths+1, sum+numList[depths]);
		getPowerSet(depths+1, sum);
		
	}
	
	// 재귀함수를 이용한 풀이
	private static void solve1() {
		
		getPowerSet(0, 0);
		if(S == 0) {
			System.out.println(ans1 - 1);
		} else {
			System.out.println(ans1);
		}
	}
	
	// 비트마스킹을 이용한 풀이
	private static void solve2() {

		// 원소는 양의 길이를 가지므로 flag는 1부터 고려
		for(int flag = 1; flag < Math.pow(2, N); flag++) {
			int tempSum = 0;
			for(int i=0; i<N; i++) {
				if( (flag & 1<<i) > 0 ) {
					tempSum += numList[i];
				}
			}
			if(tempSum == S) {
				ans2++;
			}
			
		}
		System.out.println(ans2);
	}
}