import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 최단거리 중 가장 긴 최단거리가 몇인지를 구하는 문제
 * 
 * 모든 점에서 BFS를 때리면 되지 않나?
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int R, C, ans;
	private static char[][] map;
	private static int[][] visited, deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new int[R][C];
		
		for(int i=0; i<R; i++) map[i] = br.readLine().toCharArray();
		
		int turn = 1;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == 'L') {
					BFS(r, c, turn++);
				}
			}
		}
		
		System.out.println(ans);
	}

	private static void BFS(int r, int c, int turn) {
		
		int hour = -1;
		
		visited[r][c] = turn;
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			hour++;
			int qSize = q.size();
			for(int i=0; i<qSize; i+=1) {
				int[] temp = q.poll();
				
				for(int[] d : deltas) {
					int nr = temp[0] + d[0];
					int nc = temp[1] + d[1];
					
					if(isOutOfRange(nr, nc) || visited[nr][nc] == turn || map[nr][nc] == 'W') continue;
					
					visited[nr][nc] = turn;
					q.offer(new int[] {nr, nc});
					
				}
			}
		}
		
		ans = Math.max(ans, hour);
		
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C;
	}
	

}
