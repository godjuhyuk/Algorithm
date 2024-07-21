import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 시작 시간: 오후 5시 15분
 * 
 * 문제 해석)
 * - M*N의 토마토 격자
 * - 잘 익은 토마토도 있지만 아직 안 익은 토마토도 있음
 * - 보관 후 하루가 지나면 익지 않은 토마토가 익은 토마토의 영향을 받아 익게된다.
 * - 대각선 영향x, 4방탐색
 * - 최소 며칠 안에 다 익는지
 * 
 * 입력)
 * - 2 <= M,N <= 1000
 * 
 * 문제 해결을 위한 고민)
 * - 익은 토마토를 순회하며 4방 탐색할 때 visited이면 아예 순회를 안하는 방법으로 하고싶다
 * - 새로 추가하는 토마토의 row, col 값을 모아놓은 배열 4방탐색하면 메모리는?
 * - 0의 개수를 전부 세고, 익는 녀석 나타날 때 마다 ++ 해서 다 익었는지 확인하기 => -1 출력 여부 
 * 
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static final int MATURED = 1, PREMATURED = 0;
	private static int M, N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int prematrueTomatos = 0;
		
		int[][] map = new int[M][N];
		int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		boolean[][] visited = new boolean[M][N];
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for(int r=0; r<M; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == PREMATURED) prematrueTomatos++;
				else if(map[r][c] == MATURED) {
					q.offer(new int[] {r, c});
					visited[r][c] = true;
				}
			}
		}
		
		if(prematrueTomatos == 0) {
			System.out.println(0);
			return;
		}
		
		int day = 0;
		
		while(!q.isEmpty()) {
			
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				
				int[] temp = q.poll();
				
				int r = temp[0];
				int c = temp[1];
				
				if(map[r][c] == PREMATURED) {
					map[r][c] = MATURED;
					prematrueTomatos--;				
				}
				
				for(int[] d : deltas) {
					
					int nr = r + d[0];
					int nc = c + d[1];
					
					if(isOutOfRange(nr, nc) || map[nr][nc] != PREMATURED || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					
				}
				
			}
			if(q.size() > 0) day++;
		}
		
		
		
		if(prematrueTomatos > 0) System.out.println(-1);
		else System.out.println(day);
		
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nc < 0 || nr >= M || nc >= N;
	}
}
