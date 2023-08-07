/**
* 풀이 시작 : 오후 4:33
* 풀이 완료 : 
* 풀이 시간 : 
*
* 문제 해석
* 
* 수열이 주어진다.
* 오른쪽에서부터 각 수에 차례로 순회를하며, 순회한 수를 시작점으로 삼고 이 수보다 높이가 크거나 같은 수를 만나면 수신하는 수로 저장한다.
*  
*
* 구해야 하는 것
* 각 index에 해당하는 수가 어떤 크거나 같은 수를 만나는지를 구해야한다.
*
* 문제 입력
* 
* 첫째 줄에 탑의 수를 나타내는 정수 N이 주어진다. 
* N은 1 이상 500,000 이하이다. 
* 둘째 줄에는 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어진다. 
* 탑들의 높이는 1 이상 100,000,000 이하의 정수이다.
*
*
* 제한 요소
* 1<= N <= 500,000
* 1<= x <= 100,000,000
*
*
*예제 
*6 9 5 7 4
* 생각나는 풀이
* 
* 정렬을 통해 얻을 수 있는 정보가 있을까? => 없는거같다
* 스택을 구현해서 끝에서부터 POP하면서 최댓값 만날때까지 반복
* => 만약 만났다면?
* => 7, 5가 9를 만났다면 인덱스 갱신을 어떻게 동시에 할까? 
* => 인덱스 3번과 2번이 1번을 만났다 => 스택을 두개 운용한다고 하면 시간복잡도가 O(N^2) 아닌가? O(2N)같으니 해보자
* 
*
* 구현해야 하는 기능
* 
* 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		Stack<Integer> idxStack = new Stack<>();
		
		int[] heightInfo = new int[N];
		for(int i=0; i<heightInfo.length; i++) {
			heightInfo[i] = Integer.parseInt(input[i]);
			idxStack.add(i);
		}
		
		Stack<Integer> tempStack = new Stack<>();
		
		int idx = N-1;
		int maxValue = heightInfo[N-1];
		while(idx >= 0) {
			int popIdx = idxStack.pop();
			int popValue = heightInfo[popIdx];
			
			if(maxValue < popValue) {
				maxValue = popValue;
				while(!tempStack.isEmpty()) {
					heightInfo[tempStack.pop()] = popIdx+1;
				}
			}
			
			while(tempStack.size() > 0 && popValue > heightInfo[tempStack.peek()]) {
				heightInfo[tempStack.pop()] = popIdx+1;
			}
			
			tempStack.add(popIdx);
			idx--;
		}
		
		while(!tempStack.isEmpty()) {
			heightInfo[tempStack.pop()] = 0;
		}
		
		for(int i=0; i<N; i++) {
			sb.append(heightInfo[i]).append(' ');
		}
		System.out.println(sb);
	}
	
}