import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
static int[] parents;
	
	static class Edge {
		
		int from;
		int to;
		
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
		
	}
	
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
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			parents = new int[V+1];
			for(int i=1; i<=V; i++) parents[i] = i;
			
			Edge[] edgeArr = new Edge[E];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				edgeArr[i] = new Edge(from, to);
				
			}
			
			int cnt = 0;
			
			for(int i=0; i<E; i++) {
				
				Edge temp = edgeArr[i];
				
				if(union(temp.from, temp.to)) {
					if(++cnt == V-1) break;
				}
				
			}
			
			System.out.println(cnt);
		}
		
		
	}
	
}