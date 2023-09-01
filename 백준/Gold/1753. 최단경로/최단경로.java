import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 시작점에서 다른 모든 정점으로의 최단 경로를 구하고 있는 프로그램 작성
 * 가중치는 10 이하
 * 
 * V, E가 주어짐
 * 	
 * 
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	static class Vertex implements Comparable<Vertex> {
		
		int no;
		int dist;
		
		public Vertex(int no, int dist) {
			this.no = no;
			this.dist = dist;
		}
		
		public int compareTo(Vertex o) {
			return this.dist - o.dist;
		};
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		List<Vertex>[] adjList = new ArrayList[V+1];
		
		for(int i=0; i<=V; i++) adjList[i] = new ArrayList<Vertex>();
		
		for(int i=0; i<E; i++) {
			
			st = new StringTokenizer(br.readLine());
		
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Vertex(to, weight));
			
		}
		
		int[] distance = new int[V+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE - 11);
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		
		distance[start] = 0;
		pq.offer(new Vertex(start, distance[start]));
		
		boolean[] visited = new boolean[V+1];
		
		while(!pq.isEmpty()) {
			
			Vertex cur = pq.poll();
			
			if(visited[cur.no]) continue;
			
			visited[cur.no] = true;
			
			for(Vertex next : adjList[cur.no]) {
				if(!visited[next.no] && distance[next.no] > distance[cur.no]+next.dist){
					distance[next.no] = distance[cur.no]+next.dist;
					pq.offer(new Vertex(next.no, distance[next.no]));
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			if(distance[i] != Integer.MAX_VALUE - 11) sb.append(distance[i]).append('\n');
			else sb.append("INF").append('\n');
		}
		
		System.out.println(sb);
		
	}

}