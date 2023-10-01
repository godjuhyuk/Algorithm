import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, K;
	private static int[] arr, parents;
	
	private static void union(int a, int b) {
		if(b >= M) return;
		parents[a] = b;
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);

	}
	
	private static int binarySearch(int target) {
		
		int left = 0;
		int right = M-1;
		
		while(left < right) {
			
			int mid = (left + right) / 2;
			
			if(arr[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
			
		}
		return right;

	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		parents = new int[M];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			parents[i] = i;	
		}
		
		Arrays.sort(arr);
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<K; i++) {
			
			Integer a = Integer.parseInt(st.nextToken());
			int position = find(binarySearch(a));
			bw.write(arr[position] + "\n");
			union(position, position+1);
			
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
}