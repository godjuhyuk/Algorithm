import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		Queue<int[]> jQueue = new ArrayDeque<int[]>();
		Queue<int[]> fireQueue = new ArrayDeque<int[]>();
		
		boolean[][] visited = new boolean[R][C];
		boolean[][] fireVisited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'J') {
					jQueue.offer(new int[] {i, j, -1, -1});
					visited[i][j] = true;
				}
				else if(map[i][j] == 'F') {
					fireQueue.offer(new int[] {i, j});
					fireVisited[i][j] = true;
				}
			}
		}
		
		int time = 0;
		int[][] deltas = {{0, 1}, {-1, 0}, {1, 0}, {0, -1}};
		
		while(!jQueue.isEmpty()) {
			int qSize = jQueue.size();
			for(int i=0; i<qSize; i++) {
				int[] tempJ = jQueue.poll();
				
				int jr = tempJ[0];
				int jc = tempJ[1];
				
				int pr = tempJ[2];
				int pc = tempJ[3];
				
				
				if(isOutOfRange(jr,jc) && map[pr][pc] != 'F') {
					System.out.println(time);
					return;
				}
				
				if(isOutOfRange(jr, jc) || map[jr][jc] == 'F' || (pr!=-1 && pc!= -1  && map[pr][pc] == 'F')) continue;
				map[jr][jc] = 'J';
				
				
				for(int[] d: deltas) {
					int nr = jr + d[0];
					int nc = jc + d[1];
					
					if(isOutOfRange(nr, nc)) {
						jQueue.offer(new int[] {nr, nc, jr, jc});
						continue;
					}
					
					if(map[nr][nc] != '.' || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					jQueue.offer(new int[] {nr, nc, jr, jc});
					
				}
				
			}
			
 			qSize = fireQueue.size();
			for(int i=0; i<qSize; i++) {
				
				int[] tempFire = fireQueue.poll();
				
				int tr = tempFire[0];
				int tc = tempFire[1];
				
				map[tr][tc] = 'F';
				
				for(int[] d : deltas) {
					int nr = tr + d[0];
					int nc = tc + d[1];
					if(isOutOfRange(nr, nc) || map[nr][nc] == 'F' || map[nr][nc] == '#' || fireVisited[nr][nc]) {
						continue;
					}
					
					fireVisited[nr][nc] = true;
					fireQueue.offer(new int[] {nr, nc});
					
				}
				
			}
			
			time++;
			
		}
		
		System.out.println("IMPOSSIBLE");
		
	}
	private static boolean isOutOfRange(int r, int c) {
		return 0>r || r>=R || 0>c || c >=C;
	}
	
}
