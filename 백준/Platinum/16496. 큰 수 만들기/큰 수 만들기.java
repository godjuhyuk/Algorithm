import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int zeroCnt;
	public static class Value implements Comparable<Value> {
		
		String val;
		
		public Value(String val) {
			this.val = val;
			if(val.equals("0")) {
				zeroCnt++;
			}
		}
		
		@Override
		public int compareTo(Value o) {
			
			if(o.val.length() == 10 && this.val.length() == 10) {
				return 1;
			}
			else if(o.val.length() == 10) {
				return -1;
			} 
			else if(this.val.length() == 10){
				return 1;
			}
			
			if(Long.parseLong(o.val + this.val) > Long.parseLong(this.val + o.val)) {
				return 1;
			} else {
				return -1;
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Value> pq = new PriorityQueue<Value>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) pq.add(new Value(st.nextToken()));
		
		if(zeroCnt == N ) {
			System.out.println(0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			sb.append(pq.poll().val);
		}
		
		System.out.println(sb);
	}
	
}