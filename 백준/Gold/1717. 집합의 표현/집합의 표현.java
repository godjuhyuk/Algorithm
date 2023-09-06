import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb;
	private static int[] parents;
	
	private static int find(int a) {
		
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);

	}
	
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;

	}
	
	private static void func(int cmd, int a, int b) {
		
		if( cmd == 0) {
			union(a, b);
		} else {
			if(find(a) == find(b)) {
				sb.append("yes").append('\n');
			} else {
				sb.append("no").append('\n');
			}
		}

	}
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		
		for(int i=0; i<=n; i++) parents[i] = i;
		
		for(int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			func(cmd, a, b);
			
		}
		
		System.out.println(sb);
		
		
		
	}
	
}