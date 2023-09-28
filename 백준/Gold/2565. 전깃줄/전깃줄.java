import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * LIS 구현
 */
public class Main {
	
	private static class Line implements Comparable<Line> {
		
		int start;
		int end;
		
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Line o) {
			return this.start - o.start; 
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Line[] arr = new Line[N];
		int[] LIS = new int[N];
		
		for(int i=0; i<N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[i] = new Line(start, end);
			
		}
		
		Arrays.sort(arr);
		
		int max = 0;
		for(int i=0; i<N; i++) {
			
			LIS[i] = 1;
			
			for(int j=0; j<i; j++) {
				if(arr[j].end < arr[i].end && LIS[i] < LIS[j] + 1 ) {
					LIS[i] = LIS[j] +1;
					max = Math.max(LIS[i], max);
				}
			}
		}
		
		System.out.println(N - max);
		
		
	}
	
}