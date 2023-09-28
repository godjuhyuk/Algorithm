import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		Line[] trace = new Line[N];
		int[] LIS = new int[N];
		boolean[] visited = new boolean[500001];
		
		for(int i=0; i<N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[i] = new Line(start, end);
			visited[start] = true;
		}
		
		Arrays.sort(arr);
		
		int idx = 0;
		LIS[idx] = arr[0].end;
		trace[idx] = new Line(idx, arr[0].start);
		for(int i=1; i<N; i++) {
			if(arr[i].end > LIS[idx]) {
				LIS[++idx] = arr[i].end;
				trace[i] = new Line(idx, arr[i].start);
			}
			else if(arr[i].end < LIS[idx]) {
				int insertIdx = ChangeIndexWithlowerBound(idx, arr[i].start, arr[i].end, LIS);
				trace[i] = new Line(insertIdx, arr[i].start);
			}
				
		}
		
		System.out.println(N - idx - 1);
		
		List<Integer> list = new ArrayList();
		
		for(int i=N-1; i>=0; i--) {
			if(trace[i].start == idx) {
				list.add(trace[i].end);
				idx--;
			}
		}
		
		
		for(int start : list) visited[start] = false;
		
		for(int i=0; i<500001; i++) {
			if(visited[i]) {
				System.out.println(i);
			}
		}
		
	}

	private static int ChangeIndexWithlowerBound(int right, int idx, int insertVal, int[] LIS) {
		
		int left = 0;
		
		while(left < right) {
			int mid = (left + right) / 2;
			if(LIS[mid] < insertVal) {
				left = mid + 1;
			}
			else if(LIS[mid] >= insertVal) {
				right = mid;
			} 
			
		}
		LIS[right] = insertVal;
		return right;
	}
	
	
}