import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 테케 1000개
 * 수열크기 9999개
 * 
 * 입력받을때마다 O(1) 혹은 O(logN)으로 중앙값을 뽑아내야한다.
 * 원소가 추가 될 때마다 중앙값이 어떻게 되는지 봐야할듯?
 * 일단, 정렬된 상황에서의 중앙값은 늘 arr[N/2]에 위치
 * 
 * 
 * 아이디어)
 * 출력할때마다 3개의 케이스로 나뉘는거같다.
 * 
 * 1) 현재 중앙값보다 큰 값이 2개 들어오는 case
 * => 이 경우, 중앙 값을 다시 구해야한다.
 * 
 * 2) 작은값 2개 들어오는 case => 1과 유사
 * 
 * 3) 큰값1, 작은값 1개 들어오는 case
 * => 이 경우, 중앙값은 그대로
 * 
 * 결국 1, 2의 case에서 중앙값을 다시구해야한다.
 * 
 * 어떻게하면 효율적으로 다시 구할까?
 * 결국 우선순위큐를 써야할거같다
 * 우선순위큐도 중앙값을 구할순 없으니
 * 중앙값보다 큰놈들 관리하는 queue(작은게 우선) => minHeap
 * 작은놈 관리하는 queue(큰게 우선) => maxHeap
 * 
 * 만약 중앙값이 교체되어야한다면? (1, 2 case)
 * 
 * case1)
 * 	 이전 중앙값을 maxHeap로 보내고, 새로운 큰 수 2개 minHeap 추가
 * case2)
 * 	 이전 중앙값을 minHeap로 보내고(poll & offer), 새로운 작은수 2개 maxHeap 추가
 * case3)
 * 	 이전 중앙값이 그대로 유지, 작은놈 maxHeap으로 큰놈 minHeap으로
 * 
 * 
 * => 포기 신경써야할게 넘 많음ㅠ
 * 
 */
public class Main {
	private static int middleVal;
	private static final int MAX_SIZE = 9999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 힙 초기화
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(MAX_SIZE, Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(MAX_SIZE);
		for(int t=0; t < T; t++) {
			
			// 현재 테케에서의 Input 개수
			int n = Integer.parseInt(br.readLine());
			
			// 몇개 출력할지 print
			bw.write((n+1)/2 + "\n"); 
			
			// 10줄씩 입력
			for(int i=0; i<= n/10; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				// 마지막 for문일 경우 mod 10, 아니면 10
				int inputSize = i == n/10 ? n%10 : 10; 
				
				for(int j=1; j<=inputSize; j++) {
					
					int a = Integer.parseInt(st.nextToken());
					
					// 항상 양쪽 힙의 사이즈는 1 차이 이내여야한다.
					if(minHeap.size() != maxHeap.size()) maxHeap.offer(a);
					else minHeap.offer(a);
					
					if(maxHeap.size() > 0 && maxHeap.peek() > minHeap.peek()) {
						
						int temp = maxHeap.poll();
						maxHeap.offer(minHeap.poll());
						minHeap.offer(temp);
						
					}
					
					
					middleVal = minHeap.peek();
					if(j%2 == 1) bw.write(middleVal + " ");
				}
				
				// 줄바꿈 
				if( (inputSize==10 && i%2 == 1) || (inputSize + 10 * i) == n) bw.write('\n');
				
				
			}
			maxHeap.clear();
			minHeap.clear();
			}
		
		bw.flush();
		bw.close();
		br.close();
	}
}