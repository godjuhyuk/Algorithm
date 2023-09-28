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
 * 매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 
 * 벽에는 불이 붙지 않는다. 상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다. 
 * 상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다. 
 * 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
 * 
 * 불부터 움직이면서 맵에 마킹하는게 좋겠다.
 * map은 그냥 받고 visited로 미리 체크하면 될듯?
 * 
 * 
 */
public class Main {
	
	private static int R, C, time;
	private static int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	private static char[][] map;
	private static int[][] visited;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 전역변수 초기화 
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			time = 0;
			map = new char[R][C];
			visited = new int[R][C];
			
			
			Queue<int[]> fireQ = new ArrayDeque();
			Queue<int[]> playerQ = new ArrayDeque();
			
			for(int i=0; i<R; i++) {
				String input = br.readLine();
				for(int j=0; j<C; j++) {
					map[i][j] = input.charAt(j);
					
					if(map[i][j] == '*') {
						visited[i][j] = 1;
						fireQ.add(new int[] {i, j});
					}
					else if(map[i][j] == '@') {
						visited[i][j] = -1;
						playerQ.add(new int[] {i, j});
					}
					
				}
			}
			
			if(bfs(fireQ, playerQ)) {
				System.out.println("IMPOSSIBLE");
			};
			
		}
		
	}

	private static boolean bfs(Queue<int[]> fireQ, Queue<int[]> playerQ) {
		
		while(!playerQ.isEmpty()) {
			time++;
			int fireSize = fireQ.size();
			for(int i=0; i<fireSize; i++) {
				
				int[] fire = fireQ.poll();
				
				map[fire[0]][fire[1]] = '*';
				for(int[] d: deltas) {
					int nr = fire[0] + d[0];
					int nc = fire[1] + d[1];
					
					if(isOutOfRange(nr, nc) || visited[nr][nc] == 1 || map[nr][nc] == '#') continue;
					
					visited[nr][nc] = 1;
					fireQ.add(new int[] {nr, nc});
					
				}
				
			}
			
			int playerSize = playerQ.size();
			for(int i=0; i<playerSize; i++) {
				
				int[] player = playerQ.poll();
				for(int[] d: deltas) {
					int nr = player[0] + d[0];
					int nc = player[1] + d[1];
					
					if(isOutOfRange(nr, nc)) {
						System.out.println(time);
						return false;
					}
					
					if(visited[nr][nc] != 0 || map[nr][nc] != '.' ) continue;
					
					visited[nr][nc] = -1;
					playerQ.add(new int[] {nr, nc});
					
				}
				
			}
		}
		
		return true;
		
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
	
}