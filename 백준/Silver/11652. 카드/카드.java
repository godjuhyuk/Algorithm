import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long ans = Long.MAX_VALUE;
		int cnt = 0;
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		
		for(int i=0; i<N; i++) {
			
			long a = Long.parseLong(br.readLine());
			int now = map.getOrDefault(a, 0);
			map.put(a, ++now);
			
			if(now > cnt) {
				cnt = now;
				ans = a;
			}
			else if(now == cnt) {
				ans = Math.min(ans, a);
			}
			
		}
		
		System.out.println(ans);
		
		
	}

}