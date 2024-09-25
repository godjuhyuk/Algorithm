import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		Deque<Integer> dq2 = new ArrayDeque<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			if(Integer.parseInt(st.nextToken()) == 0) {
				dq.addLast(i);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if(dq.size() > 0 && dq.peekFirst() == i) {
				dq.pollFirst();
				dq2.addLast(input);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<M; k++) {
			
			dq2.offerFirst(Integer.parseInt(st.nextToken()));
			sb.append(dq2.pollLast()).append(' ');
			
		}
		
		System.out.println(sb);
		
	}

}
