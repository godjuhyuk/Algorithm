import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 시작 시간: 오후 8시 19분
 * 
 * 문제 해석)
 * 
 * 보석 N개는 각각 무게 M_i, 가격 V_i를 가지고 있다.
 * K개의 가방이 있고, 최대 무게는 C_i 이다.
 * 가방에는 최대 하나의 보석만 넣을 수 있다.
 * 
 * 입력)
 * 1 <= N, K <= 300,000
 * 0 <= M, V <= 1,000,000
 * 1<= C <= 100,000,000
 * 
 * 문제 풀이를 위한 고민)
 * 일단 최대 가격합은 long을 써야겠구
 * 
 * 최대 무게가 10억의 의미가 있을까? 최대 무게가 100만인데?
 * => 없을듯?
 * 
 * 고려해야할 요소 => 보석의 무게, 가격 & 가방의 무게한도
 * 
 * 1. 그리디
 * => 같은 보석 무게라면, 높은 가격의 보석을 뽑는다.
 * => 같은 보석 가격이라면, 무게가 낮은 가방부터 소모한다.
 * => 가방을 무게 한도 순으로 오름 차순 정렬하고, 보석을 (1)가격, (2)무게 순으로 정렬한 뒤
 * => 보석을 순회하며 챙길 수 있으면 챙기기
 * => 반례가 있을까에 대한 고민:
 * 		=> 반례보다는 시간초과날듯,, (최악의 경우 30만 * 30만일듯?)
 * 		=> 시간초과를 고민하다보니 왜 무게가 10억인지 알 것 같기도
 * 
 * 2. DP
 * => 자료구조를 어떻게 구성해야할까?
 * => 30만 * 2를 일단 정렬,, 가방 무게 배열도
 * => 가방 배열 예상 [1, 2, 3, 10]
 * 
 * 아니 만약 [9, 100] , [8, 99], [7, 98], [100
 * 이렇게 있음
 * 그러면 다 우선순위큐 들어가잖아
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	private static class Gem implements Comparable<Gem> {
		
		int weight;
		int value;
		
		public Gem(int w, int v) {
			this.weight = w;
			this.value = v;
		}
		
		public int compareTo(Gem otherGem) {
			if(this.value == otherGem.value) return this.weight - otherGem.weight;
			return otherGem.value - this.value;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long ans = 0;
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Gem[] gems = new Gem[N];
		int[] bags = new int[K];
 		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			gems[i] = new Gem(w, v);
		}
 		
 		for(int j=0; j<K; j++) bags[j] = Integer.parseInt(br.readLine());
		
 		Arrays.sort(gems, new Comparator<Gem>() {
 			@Override
 			public int compare(Gem o1, Gem o2) {
 				return o1.weight - o2.weight;
 			}
 		});
 		Arrays.sort(bags);
 		
 		PriorityQueue<Gem> pq = new PriorityQueue<Gem>();
 		
 		for(int k=0, n=0; k<K; k++) {
 			
 			while(n < N && gems[n].weight <= bags[k]) {
 				pq.offer(gems[n++]);
 			}
 			
 			if(!pq.isEmpty()) {
 				ans += pq.poll().value;
 			}
 			
 		}
 		
 		System.out.println(ans);
 		
 		
	}
}
