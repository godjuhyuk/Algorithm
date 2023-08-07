/**
* 풀이 시작 : 오후 1:07
* 풀이 완료 : 오후 1:30
* 풀이 시간 : 23분
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
import java.util.StringTokenizer;

class Main {
	
	private static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		int[] numArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		numArr[1] = Integer.parseInt(st.nextToken());
		for(int i=2; i<N+1; i++) {
			numArr[i] = Integer.parseInt(st.nextToken()) + numArr[i-1];
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(numArr[b] - numArr[a]).append('\n');
		}
		
		System.out.println(sb);
		
	}
	
}