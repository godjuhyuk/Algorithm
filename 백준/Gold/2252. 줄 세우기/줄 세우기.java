import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 해석)
 * M개의 줄에 N명의 학생 키를 비교한 정보가 주어진다.
 * 두명씩밖에 주어지지 않으므로, 2명간의 키비교에 대한 정보들로 유추해야한다.
 * 
 * A B가 주어졌을 때, A가 B의 앞에 서야한다는 의미이다.
 * 
 * 문제 해결에 대한 고민)
 * 싸이클이 없는 유향 그래프이기 때문에 위상 정렬을 사용해서 풀 수 있다.
 * 만약 위상 정렬을 몰랐다면? ..
 * 
 * 암튼 인접 리스트를 생성하자.
 * A index의 리스트에 B를 추가해주자
 * 추가하는 동시에 degree[B]++를 해준다.
 * 
 * degree가 0인 녀석부터 맨 앞에 세우고, 
 * 인접리스트를 순회하면서 degree[get(i)]--;를 해준다!
 * 0이 된다면 queue에 넣도록 하자
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] degrees = new int[N+1];
		List<Integer>[] adjList = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adjList[A].add(B);
			degrees[B]++;
			
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++) {
			if(degrees[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			
			int from = q.poll();
			sb.append(from).append(' ');
			for(int to : adjList[from]) {
				
				if(--degrees[to] == 0) q.offer(to);
				
			}
			
		}
		
		System.out.println(sb);
		
		
	}
	
}