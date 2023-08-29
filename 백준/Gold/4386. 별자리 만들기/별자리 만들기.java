import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * x, y 좌표가 실수 형태로 주어진다. (1000을 넘지 않는 양의 실수)
 * 
 * 최대 소수점 둘째자리까지 주어진다.
 * 
 * 별자리를 잇는 비용을 최소로 해야한다.
 * 비용은 두 별 사이 만큼의 거리다.
 * 
 * 문제 해결을 위한 고민)
 * 
 * 어떻게 이어야할까?
 * => 모든 별들 간의 거리를 구하고 정렬 후 크루스칼
 * => 실수는 인접행렬을 어떻게 구현하지? 인접리스트는?
 * => 클래스로 만들어보자.
 * 
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	static int parents[];
	
	private static int find(int a) {

		if(parents[a] == a) return a;
		
		return find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		
		return true;

	}
	
	static class Edge implements Comparable<Edge>{
		
		int from;
		int to;
		double weight;
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight); 
		}
		
	}
	
	static class Star { 
		
		double x;
		double y;
		
		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static Double getDistance(Star s1, Star s2) {
		
		return Math.sqrt((s1.x - s2.x) * (s1.x - s2.x) + (s1.y - s2.y) * (s1.y - s2.y));
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Star[] starArr = new Star[N];
		
		for(int i=0; i<N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			starArr[i] = new Star(x, y);
			
		}
		
		List<Edge> edgeList = new ArrayList<Edge>();
		
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				edgeList.add(new Edge(i, j, getDistance(starArr[i], starArr[j])));
			}
		}
		
		// 간선 리스트를 만들고 정렬하자
		Collections.sort(edgeList);
		
		parents = new int[N];
		
		for(int i=0; i<N; i++) parents[i] = i;
		
		int cnt = 0;
		double sum = 0;
		for(Edge edge : edgeList) {
			
			if(union(edge.from, edge.to)) {
				sum += edge.weight;
				if(++cnt == N-1) break;
				
			}
			
		}
		
		System.out.printf("%.2f", sum);
		
	}

}