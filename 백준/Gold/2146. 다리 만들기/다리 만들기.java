import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 
 * 다리를 단 하나 놔야한다. 제일 짧은 다리여야 한다.
 * N * N 크기 정사각형에 육지와 바다가 주어진다.
 * 
 * 다리를 놓을때는 육지에서 출발하여 4방탐색해가며 다리를 놓는다.
 * 육지를 만나면 다리가 완공됨 => 최소 길이 다리를 구해야하므로 BFS
 * 
 * 입출력 크기)
 * 1 <= N <= 100
 * 
 * 최악 시간복잡도는 러프하게 잡아서 100^3
 * 
 * 문제 해결을 위한 고민)
 * 해안가 : 섬에서 바다와 인접한 지역
 * 1. 한 섬의 해안가를 전부 돌면서 bfs를 해봐야하나?
 * => YES
 * 
 * 2. 다리를 쭉쭉 잇다가 새로운 육지를 발견한 경우,
 * 다리를 어떻게 철수시킬 것인가?
 * => 다리를 철수해야하는가?
 * 아니다. 마이너스 값으로 턴을 늘려나가면 된다.
 * visited를 int배열로 만들어 턴에 해당하는 값을 부여하자.
 * 
 * 2-1) BFS면 Queue를 사용하는데, 육지와 연결된 경우 남아있는 값들?
 * => 어차피 bfs 메소드 리턴해서 상관없음
 * 
 * 구현 로직)
 * 1. 처음 입력을 다 받고, 섬을 돌면서 섬마다 값을 다르게 하자.
 * ex) 섬1 = 1, 섬2 = 2, ...
 * 
 * 1-1) 해안가를 List로 모아놓는게 중복되는 방문을 피할 수 있다.
 * => 메모리를 늘리고 효율을 챙겨보자
 * 
 * 2. 해안가List를 순회하며 visited를 -turn으로 채워나간다.
 * 
 * 3. 다리 완공되면 ans 값 Math.min으로 갱신 && 중간에 ans보다 커지면 바로 return
 * 
 */
public class Main {
	
	private static int N, turn = -1, ans = 10000;
	private static int[][] map, visited, deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	private static List<int[]> coastList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map=  new int[N][N];
		visited = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 별 마킹 && 해안가 모아놓기
		coastList = new ArrayList<int[]>();
		for(int i=0, mark = 2; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 0 && visited[i][j] == 0) {
					visited[i][j] = 1;
					markBFS(i, j, mark++);
				}
			}
		}
		
		// 해안가부터 출발하기
		for(int[] locInfo : coastList) {
			buildBridgeBFS(locInfo);
			turn--;
		}
		System.out.println(ans);
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				if(visited[i][j] < 0) System.out.print("X");
//				else System.out.print("-");
//			}
//			System.out.println();
//		}
		
	}

	private static void buildBridgeBFS(int[] locInfo) {
		
		int r = locInfo[0];
		int c = locInfo[1];
		int mark = map[r][c];
		visited[r][c] = turn;
		
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] {r, c});
		
		int bridgeLength = 0;
		while(!q.isEmpty()) {
			if(bridgeLength >= ans) return;
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				int[] temp = q.poll();
				for(int[] d : deltas) {
					int nr = temp[0] + d[0];
					int nc = temp[1] + d[1];
					if(isOutOfRange(nr, nc)) continue;
					
					// 새로운 섬 발견
					if(map[nr][nc] != 0 && map[nr][nc] != mark) {
						ans = Math.min(ans, bridgeLength);
						return;
					}
					
					if(map[nr][nc] == mark || visited[nr][nc] == turn) continue;
					
					visited[nr][nc] = turn;
					q.offer(new int[] {nr, nc});
				}
			}
			bridgeLength++;
		}
		
		
		
		
	}

	private static void markBFS(int r, int c, int mark) {
		
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] {r, c});
		visited[r][c] = 1;
		map[r][c] = mark;
		
		while(!q.isEmpty()) {
			
			int[] temp = q.poll();
			
			for(int[] d : deltas) {
				int nr = temp[0] + d[0];
				int nc = temp[1] + d[1];
				if(isOutOfRange(nr, nc) || visited[nr][nc] != 0) continue;
				
				// 바닷가라면 해안가 정보 갱신 후 continue
				if(map[nr][nc] == 0) {
					coastList.add(new int[] {temp[0], temp[1]});
					visited[nr][nc] = 1;
					continue;
				}
				
				map[nr][nc] = mark;
				visited[nr][nc] = 1;
				q.offer(new int[] {nr, nc});
			}
			
		}
		
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c>=N;
	}

}