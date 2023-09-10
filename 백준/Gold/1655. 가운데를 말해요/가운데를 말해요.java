import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int middleVal;
	private static final int MAX_SIZE = 50000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 힙 초기화
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(MAX_SIZE);
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(MAX_SIZE, Collections.reverseOrder());
		
		// Input 개수
		int n = Integer.parseInt(br.readLine());
		
		// 10줄씩 입력
		for(int i=1; i<=n; i++) {
			
			int input = Integer.parseInt(br.readLine());	
			
			// 항상 양쪽 힙의 사이즈는 1 차이 이내여야한다.
			if(minHeap.size() != maxHeap.size()) maxHeap.offer(input);
			else minHeap.offer(input);
			
			// minHeap의 head가 항상 maxHeap의 head 보다 크거나 같아야한다.
			if(maxHeap.size() > 0 && maxHeap.peek() > minHeap.peek()) {
				
				int temp = maxHeap.poll();
				maxHeap.offer(minHeap.poll());
				minHeap.offer(temp);
			}
			if(i%2 == 0) bw.write(maxHeap.peek() + "\n");
			else bw.write(minHeap.peek() + "\n");
					
		}
				
		bw.flush();
		bw.close();
		br.close();
	}
}