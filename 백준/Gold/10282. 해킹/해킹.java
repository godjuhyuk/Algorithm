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
 * a가 b의 의존
 * 1) a 감염 => b 멀쩡 (b도 a에 의존이라면 감염)
 * 2) b 감염 => a 감염 
 * 
 * a가 b를 의존하며 b가 감염되면 s초후 컴퓨터 a도 감염된다.
 * => b가 감염됐을 때 s 초 후 a도 감염된다.
 * => b에서 a로 가는 가중치는 s이다.
 * 
 * 테케 100개
 * 
 * 테케당입력
 * n 1만개(정점), d 10만개(간선), 해킹당한 컴퓨터 번호 c
 * d개의 줄에 의존성을 나타내는 a, b, s가 주어짐 (0<=s<=1000)
 * 
 * 문제 해결을 위한 고민)
 * 
 * 테케 100개가 전부 n 1만개, d가 10만개 들어왔다면?
 * => 인접리스트 / 간선리스트
 * 
 * 왜 다익스트라를 사용해야하는가?
 * =>  총 컴퓨터가 감염되는 시간의 총합은 어차피 정해져있으므로, 중복계산을 피해야하기때문에
 * 
 * 문제 풀이)
 * 1. 간선리스트를 이용한 다익스트라가 되나? => 일단 인접리스트
 * 2. 레벨별 bfs가 낫지않나?
 * 
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	static class Computer implements Comparable<Computer>{
		
		int no;
		int dist;
		
		public Computer(int no, int dist) {
			this.no = no;
			this.dist = dist;
		}
		
		public int compareTo(Computer o) {
			return this.dist - o.dist;
		};
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			List<Computer>[] adjList = new ArrayList[V+1];
			
			for(int i=1; i<=V; i++) adjList[i] = new ArrayList<Computer>();
			
			// 간선 정보를 인접리스트에 입력
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adjList[from].add(new Computer(to, s));
			}
			
			int[] distance = new int[V+1];
			Arrays.fill(distance, 99999999);
			
			PriorityQueue<Computer> pq = new PriorityQueue<Computer>();
			
			pq.offer(new Computer(start, 0));
			
			
			distance[start] = 0;
			int cnt = 0;
			while(!pq.isEmpty()) {
				
				Computer temp = pq.poll();
				
				if(distance[temp.no] < temp.dist) continue;
				
				cnt++;
				
				for(Computer next : adjList[temp.no]) {
					if(distance[next.no] > temp.dist + next.dist) {
						distance[next.no] = temp.dist + next.dist;
						pq.offer(new Computer(next.no, distance[next.no]));
					}
				}
				
			}
			int max = 0;
			for(int i=1; i<=V;i++) {
				if(distance[i] != 99999999 && max < distance[i]) {
					max = distance[i];
				}
			}
			sb.append(cnt).append(' ').append(max).append('\n');
		}
		
		System.out.println(sb);
	}
	
}