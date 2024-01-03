import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석:
 * 
 * D킬로미터의 고속도로를 지남
 * 고속도로에 지름길이 존재, 일방통행
 * 운전해야하는 거리의 최솟값
 * 
 * 문제 풀이를 위한 고민:
 * 
 * DFS
 * 
 * 아이디어1)
 * 1. boolean[D] 배열
 * 1-1) 지름길 존재하면 true
 * 
 * 2. 0부터 시작해서 가다가ㅏ 지름길 존재하면 재귀
 * 
 * 
 * 메모)
 * 
 * 이 풀이의 안좋은 이유 : 
 * 1. 총 길이가 늘어날 경우 메모리가 터짐
 * 2. 지름길의 개수가 무수히 많을 경우 시간복잡도가 터짐 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int N, D, ans;
	private static boolean[] isFastLoad;
	private static List<Load>[] adjList;
	
	private static class Load {
		
		int from;
		int to;
		int weight;
		
		public Load(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		
	}
			
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		adjList = new ArrayList[D+1];
		isFastLoad = new boolean[D+1];
		for(int i=0; i<=D; i++) adjList[i] = new ArrayList();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(from > D) continue;
			
			isFastLoad[from] = true;
			adjList[from].add(new Load(from, to, weight));
			
		}
		
		recursive(0, 0);
		
		System.out.println(ans);
	}

	private static void recursive(int nowLoc, int allDist) {
		
		if(nowLoc == D) {
			ans = Math.min(ans, allDist);
			return;
		}
		else if(nowLoc > D) return;
		
		
		// 유도 파트
		if(isFastLoad[nowLoc]) {
			for(Load tempLoad: adjList[nowLoc]) {
				recursive(tempLoad.to, allDist + tempLoad.weight);
			}
		}
		
		recursive(nowLoc+1, allDist+1);
		
	}
	
}
