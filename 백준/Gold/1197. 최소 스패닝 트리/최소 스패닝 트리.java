import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 간선 객체를 저장한 리스트를 만들고
 * 오름 차순 정렬을 한다.
 * 
 * 가중치 크기가 제일 작은 간선 하나를 뽑아서 시작한다.
 * 	=> 현재 내가 갈 수 있는 점중 가장 가중치가 적은 점을 간다.
 * @author SSAFY
 *
 */
public class Main {
	static int[] parents;
	
	static class Edge implements Comparable<Edge> {
		
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			
			return this.weight - o.weight;
		};
		
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
			int weight = Integer.parseInt(st.nextToken());
			
			edgeArr[i] = new Edge(from, to, weight);
			
		}
		
		Arrays.sort(edgeArr);

		int cnt = 0;
		int sum = 0;
		
		for(int i=0; i<E; i++) {
			
			Edge temp = edgeArr[i];
			
			if(union(temp.from, temp.to)) {
				sum+= temp.weight;
				if(++cnt == V-1) break;
			}
			
		}
		
		System.out.println(sum);
		
	}
	

}