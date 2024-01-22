import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 문제 풀이 시작 : 오후 9시 16분
 * 
 * 문제 해석)
 * 폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
 * 
 * 정사각형은 서로 겹치면 안 된다.
 * 도형은 모두 연결되어 있어야 한다.
 * 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
 * 정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있
 * 
 * 문제 해결을 위한 고민)
 * 모든 점에서 4방탐색, dfs로 4칸 가서 최대값 구하면 되는 문제인데,
 * 갔던 점을 어떻게하면 다시 안갈까?
 * => 위로 가는 delta를 빼면 될듯?
 * + 가운데에서 양쪽 + 위아래로 뻗어나가는 케이스도 구현해야함!
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	private static int R, C, ans;
	private static int[][] map, visited, deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0, turn = 1; i<R; i++) {
			for(int j=0; j<C; j++, turn++) {
				visited[i][j] = turn;
				dfs(i, j, turn, 1, map[i][j]);
				middleCheck(i, j);
			}
		}
		
		System.out.println(ans);
		
	}

	private static void middleCheck(int r, int c) {
		
		
		for(int i=0; i<4; i++) {
			int sideCnt = 0;
			int tempSum = map[r][c];
			
			for(int j=0; j<3; j++) {
				int[] d = deltas[(j + i) % 4];
				int nr = r + d[0];
				int nc = c + d[1];
				if(isOutOfRange(nr, nc)) continue;
				sideCnt++;
				tempSum += map[nr][nc];
			}
			if(sideCnt >= 3) { 
				ans = Math.max(ans, tempSum);
			}
			
		}
		
	}

	private static void dfs(int r, int c, int turn, int size, int sum) {
		
		// 기저조건
		if(size == 4) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// 유도파트
		for(int i=1; i<4; i++) {
			int[] d = deltas[i];
			int nr = r + d[0];
			int nc = c + d[1];
			if(isOutOfRange(nr, nc) || visited[nr][nc] == turn) continue;
			visited[nr][nc] = turn;
			dfs(nr, nc, turn, size+1, sum + map[nr][nc]);
			visited[nr][nc] = turn - 1;
			
		}
		
		
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C;
	}
	
}
