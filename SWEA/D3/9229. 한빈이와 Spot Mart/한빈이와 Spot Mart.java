/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{


/**
 *
 * 
 * N개의 과자 중 2개를 뽑아서 무게 상한선 내에서 최댓값을 구하는 문제이다.
 * 
 * 완탐 vs 조합 => 뭐가 빠를까?
 * 
 * 입력받을때 M이상은 제외
 * 
 * 다른 사람이 조합으로 풀테니 난 완탐으로 풀어보자.
 * 중간에 딱 M이 맞춰지면 중단하는 식으로 하면 되겠다. 
 * 정렬이 도움이 될까? => 도움이 될것같다.
 * 정렬 후 완탐으로 도중에 continue 하는 식으로 해보자.
 * 
 * 
 * 예상 시간 복잡도 : O(N^2)
 * @author SSAFY
 */
//	4
//	3 45
//	20 20 20
//	6 10
//	1 2 5 8 9 11
//	4 100
//	80 80 60 60
//	4 20
//	10 5 10 16	// 테스트 케이스 개수
	// 첫 번째 테스트 케이스 N = 3, M = 45
	// 3개의 과자 봉지 무게 a1 = 20, a2 = 20, a3 = 20

	private static int N, M;
	private static StringBuilder sb;
	
	private static int solve(int start, int[] snacks, int maxWeight) {
		// TODO Auto-generated method stub
		
		for(int i= start; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(snacks[i] + snacks[j] <= M) {
					maxWeight = Math.max(maxWeight, snacks[i] + snacks[j]);
				}
				
				if(maxWeight == M) {
					return M;
				}
			}
		}
		
		
		return maxWeight > 0 ? maxWeight : -1;
		
	}
	
	public static void main(String[] args) throws IOException
	{
		sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			int maxWeight = 0;
			String[] input = br.readLine().split(" ");
			int zeroCnt = 0;
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			
			int[] snacks = new int[N];
			
			input = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				
				int snackWeight = Integer.parseInt(input[i]);
				if(snackWeight >= M) {
					zeroCnt++;
					continue;
				}
				
				snacks[i] = snackWeight;
			}
			
			Arrays.sort(snacks);
			
			if(zeroCnt == 0) {
				maxWeight = solve(0, snacks, maxWeight);
			} else {
				maxWeight = solve(zeroCnt, snacks, maxWeight);
			}
			
			sb.append('#').append(testCase).append(' ').append(maxWeight).append('\n');
		}
		
		System.out.println(sb);
		
	}
	 
}