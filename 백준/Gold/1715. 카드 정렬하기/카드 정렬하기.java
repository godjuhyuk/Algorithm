import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 문제 설명
 * 
 * 정렬된 두 묶음의 숫자카드가 각각 A개 뭉치, B개 뭉치로 있을때
 * 이 두 묶음을 합치는데엔 A+B 번의 비교를 해야한다.
 * 
 * 어떻게 합치는지에 따라 전체 비교횟수가 달라진다.
 * 
 * 입력
 * 
 * N개의 카드 뭉치가 주어진다. ( 1<= N <= 100,000)
 * 이후 N개의 줄에 숫자 카드 묶음의 크기 x가 주어진다. ( 0< x <= 1000)
 * 
 * 풀이 전략
 * 
 * 스택으로 구현했는데 우선순위를 반영하지 못해서 오답이 나왔음
 * 우선순위 큐 말고 배운 것 중에서 방법이 없나? => 잘 모르겠다
 * 
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입출력 및 값 초기화
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> deckPriQueue = new PriorityQueue<Integer>();
		for(int i=0; i<n; i++) {
			deckPriQueue.add(Integer.parseInt(br.readLine()));
		}
		
		// 순회 및 정답 도출
		int sum = 0;
		while(deckPriQueue.size() >= 2) {
			int a = deckPriQueue.poll();
			int b = deckPriQueue.poll();
			sum += a+b;
			deckPriQueue.add(a+b);
		}
		System.out.println(sum);
	}
}

