import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] prefix = new int[N+1];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i > 0 ) prefix[i] += arr[i-1] + prefix[i-1];
		}
		
		prefix[N] = arr[N-1] + prefix[N-1];
		
		int left = 0;
		int right = 1;
		int ans = 100_001;
		
		while(left < N && right <= N) {
			
			if(S > prefix[right] - prefix[left]) right++;
			else {
				ans = Math.min(ans, right - left);
				left++;
			}
		}
		if(ans != 100_001) System.out.println(ans);
		else System.out.println(0);
	}
	
}