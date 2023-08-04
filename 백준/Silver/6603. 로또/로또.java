import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 조합 문제
 * 
 * K개의 수 중 6개의 수를 뽑아 나열한다 (오름차순)
 * 
 * 
 * 풀이1) 재귀를 이용한 조합 구현 - 시간 복잡도 : KC6 -> k의 최댓값은 13이기 때문에 최악의 시간복잡도 : 13C6 => 약 1,700
 * 풀이2) 비트마스킹을 이용하여 길이가 6일때 출력하는 반복문 구현 - 시간복잡도 : O( K * 2^K ) -> 13 * 2^13 => 약 100,000
 *
 * 로직 설계
 * 풀이1)
 * 1. 입출력을 받는다.
 * 2. 재귀 함수 설계
 * 2-1) 재귀의 기저조건 : depths == 6 일때 출력
 * 2-2) 재귀의 유도파트 : 오름차순 조합이기 때문에 visited 혹은 start parameter를 이용하여 반복문 구현
 * 
 * 풀이2) 
 * 1. 입출력을 받는다.
 * 2. 반복문 구현 
 * 2-1) 비트마스크를 이용하여 경우의 수를 뽑아낸다.
 * 2-2) 뽑아낸 경우의 수 중 count++를 이용하여 count == 6일때 출력한다
 * 
 * 시간복잡도 상 풀이1이 우위지만 이미 풀어봤기 때문에 풀이2를 이용하여 문제를 풀어보자.
 * 
 * ==> 부분집합으로 뽑아냈을때 오름차순으로 뽑히지가 않고, 정렬하기도 번거로우므로 풀이2 포기
 * @author SSAFY
 *
 */
public class Main {
	static int[] temp;
	static int[] combList;
	static StringBuilder sb;
	public static void dfs(int start, int depths) {
		if(depths == 6) {
			for(int i=0; i<6; i++) {
				sb.append(combList[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<temp.length; i++) {
				combList[depths] = temp[i];
				dfs(i+1, depths+1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while(true) {
			String[] input = br.readLine().split(" ");

			// 종료 조건
			if(input[0].equals("0")) {
				break;
			}
			
			combList = new int[6];
			temp = new int[input.length-1];
			for(int i=1; i<input.length; i++) {
				temp[i-1] = Integer.parseInt(input[i]);
			}
			
			dfs(0, 0);
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
        br.close();
	}
}