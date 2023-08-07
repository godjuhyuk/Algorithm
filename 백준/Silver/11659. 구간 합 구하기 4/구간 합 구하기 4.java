/**
* 풀이 시작 : 오후 1:07
* 풀이 완료 :
* 풀이 시간 :
*
* 문제 해석
* 
* 길이가 N인 int 배열을 입력받고,
* M회의 구간 합을 구해야하는 문제.
*
* 구해야 하는 것
* i, j 가 주어졌을때 i번째 요소부터 j번째 요소까지의 합
*
* 문제 입력
* 
* N M
* Numbers
*
*
* 제한 요소
* 
* 1<= N <= 100,000
* 1<= M <= 100,000
* 1<= i <= j <= N
*
* 생각나는 풀이
* 
* O(N^2)으로는 100억이 나오기 때문에 풀 수 없기 때문에 다른 방법을 생각해야겠다.
* 배열을 0번 인덱스부터 누적해서 합을 구해놓는다면 - O(N),
* SUM[J] - SUM[I] 의 형태로 각 입력당 O(1)로 return이 가능하다.
*
* 구현해야 하는 기능
* 
* 1. 입력 받기
* 2. 누적 합 구하기
* 3. SUM[J] - SUM[I] 의 형태로 리턴하기
* 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	private static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
	
		int[] numArr = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();					
		
		
		for(int i=1; i<N; i++) {
			numArr[i] += numArr[i-1];
		}
		
		for(int i=0; i<M; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1;
			int b = Integer.parseInt(input[1]) - 1;
			
			if(a==0) {
				sb.append(numArr[b]).append('\n');
			} else {
				sb.append(numArr[b] - numArr[a-1]).append('\n');
			}
		}
		
		System.out.println(sb);
		
	}
	
}