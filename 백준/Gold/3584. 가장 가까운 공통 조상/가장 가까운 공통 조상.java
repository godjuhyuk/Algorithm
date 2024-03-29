import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * LCA 하는 법을 까먹었는데
 * 
 * union find
 * 
 * @author SSAFY
 *
 */
public class Main {

	private static int[] parents;
	
	private static int find(int a) {
		
		if(parents[a] == a) return a;
		
		return find(parents[a]);
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			parents = new int[N+1];
			
			for(int i=0; i<N-1; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				
				parents[child] = parent;
				
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 16
			int b = Integer.parseInt(st.nextToken()); // 7
			
			boolean[] visited = new boolean[N+1];
			
			int root = find(a);
			
			if(a == root || b == root) {
				sb.append(root).append('\n');
			}
			else {
				int parent = a;
				while(!visited[parent]) {
					visited[parent] = true;
					parent = parents[parent];
				}
				
				parent = b;
				while(!visited[parent]) {
					visited[parent] = true;
					parent = parents[parent];
				}
				
				sb.append(parent).append('\n');
				
			}
		}
		System.out.println(sb);
	}
}