import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * PRIM 알고리즘은 정점을 중심으로 간선들을 봐가면서 체크한다.
	 * 
	 * 간선의 정보들을 인접 리스트로 담는다.
	 * 
	 * 시작점을 아무거나 큐에 담고 시작
	 * 시작점부터 연결가능한 점들을 보면서 비용이 최소인 점으로 연결한다.
	 * 
	 * visited처리를 해준다.
	 * 
	 * 이제 다음 점을 꺼내고 visit 처리 및 다시 탐색
	 * 
	 * 
	 * @param args
	 */
	
	public static class Vertex implements Comparable<Vertex> {
		
		int no;
		int weight;
		
		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<Vertex>[] adjList = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) adjList[i] = new ArrayList<Vertex>();
		
		for(int i=0; i<E; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Vertex(to, weight));
			adjList[to].add(new Vertex(from, weight));
			
		}
		
		PriorityQueue<Vertex> priQueue = new PriorityQueue<Vertex>();
		
		boolean[] visited = new boolean[V+1];
		
		int cnt = 0;
		int sum = 0;
		
		Collections.sort(adjList[1]);
		for(Vertex vertex : adjList[1]) {
			visited[1] = true;
            priQueue.add(vertex);
		}
		
		while(!priQueue.isEmpty()) {
			
			Vertex vertex = priQueue.poll();
			if(visited[vertex.no]) continue;
			visited[vertex.no] = true;
			sum += vertex.weight;
			if(++cnt == V-1) break;
			
			for(Vertex temp : adjList[vertex.no]) {
				priQueue.add(temp);
			}
			
		}
		
		System.out.println(sum);
		
	}
}