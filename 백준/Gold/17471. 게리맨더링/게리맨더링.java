import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, ans;
	private static int[] peopleNum, sumArr;
	private static boolean gameOver;
	private static boolean[] isSelected, visited, boolArr;
	private static boolean[][] adjMatrix;
	
	
	// bfs로 해야함
	private static void connectCheck(int[] area, int idx, int depths) {
		
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(area[0]);
		visited[area[0]] = true;
		while(!q.isEmpty()) {
			
			int from = q.poll();
			
			if(++cnt == area.length) {
				boolArr[idx] = true;
				return;
			}
			
			for(int to : area) {
				if(!visited[to] && adjMatrix[from][to]) {
					visited[to] = true;
					q.add(to);
				}
			}
				
		}
		
		
	}
	
	private static void solve(int firstAreaCnt) {
		int[] firstArea = new int[firstAreaCnt];
		int[] secondArea = new int[N-firstAreaCnt];
		sumArr[0] = 0;
		sumArr[1] = 0;
		boolArr[0] = false;
		boolArr[1] = false;
		visited = new boolean[N+1];
		
		// 선택된 element 갱신
		for(int i=1, a = 0, b = 0; i<=N; i++) {
			if(isSelected[i]) {
				firstArea[a++] = i;
				sumArr[0] += peopleNum[i];
			} else {
				secondArea[b++] = i;
				sumArr[1] += peopleNum[i];
			}
		}
		connectCheck(firstArea, 0, 1);
			
		// boolArr[1] 갱신
		connectCheck(secondArea, 1, 1);

		if(boolArr[0] && boolArr[1]) {
			ans = Math.min(ans, Math.abs(sumArr[0] - sumArr[1]));
			if(ans == 0) {
				gameOver = true;
			}
		}
		
		

	}
	
	private static void backtracking(int from, int cnt, int firstAreaCnt) {
		
		if(gameOver) return;

		if(cnt == N) {
			if(firstAreaCnt != 0 && firstAreaCnt != N) {
				solve(firstAreaCnt);
			}
			return;
		} 
		
		
		isSelected[from+1] = true;
		backtracking(from+1, cnt+1, firstAreaCnt+1);
		isSelected[from+1] = false;
		backtracking(from+1, cnt+1, firstAreaCnt);

	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		sumArr = new int[2];
		boolArr = new boolean[2];
		peopleNum = new int[N+1];
		isSelected = new boolean[N+1];
		adjMatrix = new boolean[N+1][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 인구수 입력 
		for(int i=1; i<=N; i++) peopleNum[i] = Integer.parseInt(st.nextToken());
		
		// 인접 정보 입력
		for(int from=1; from<=N; from++)  {
			
			st = new StringTokenizer(br.readLine());
			
			int adjNum = Integer.parseInt(st.nextToken());
			for(int i=0; i<adjNum; i++) {
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[from][to] = true;
				adjMatrix[to][from] = true;
				
			}
			
		}
		
		isSelected[1] = true;
		backtracking(1, 1, 1);
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
}