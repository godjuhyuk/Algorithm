import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int R, C, time, ansX, ansY;
	private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static int[][] map;
	private static boolean[][] visited; 
	private static List<Ice> iceList;
	private static List<Swan> swanList;
	private static Swan[] swanArr;
	
	private static class Swan {
		int r;
		int c;
		
		public Swan(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	private static class Ice {
		
		int r;
		int c;
		
		public Ice(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		private boolean isMelt() {

			for(int[] d : deltas) {
				
				int nr = r + d[0];
				int nc = c + d[1];
				
				if(isOutOfRange(nr, nc)) continue;
				
				if(map[nr][nc] == 0) {
					return true;
				}
				
			}
			
			return false;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		swanArr = new Swan[2];
		iceList = new ArrayList<Ice>();
		swanList = new ArrayList<Swan>(); 
		
		// 입출력
		for(int i=0, swanIdx = 0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				if(input.charAt(j) == 'X') {
					map[i][j] = 1;
				}
				else if(input.charAt(j) == 'L') {
					swanArr[swanIdx++] = new Swan(i, j);
				}
			}
		}
		
		

		// 녹을 예정인 얼음들만 모아놓으면 됨
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 1) {
					
					for(int[] d : deltas) {
						
						int nr = i + d[0];
						int nc = j + d[1];
						
						if(isOutOfRange(nr, nc)) continue;
						
						if(map[nr][nc] == 0) {
							iceList.add(new Ice(i, j));
							break;
						}
						
					}
					
				}
			}
		}
		
		ansX = swanArr[1].r;
		ansY = swanArr[1].c;
		
		visited = new boolean[R][C];
		
		swanList.add(swanArr[0]);
		
		while(!isAbleToMeet()) {
			List<Ice> meltList = new ArrayList<Ice>();
			
			// 녹을 예정인 얼음 다 녹이기
			for(Ice ice : iceList) {
				map[ice.r][ice.c] = 0;
			}
			
			
			boolean[][] iceVisited = new boolean[R][C];
			
			// 녹은 얼음 주변에 얼음 있으면 추가
			for(Ice ice : iceList) {
				
				for(int[] d : deltas) {
					
					int nr = ice.r + d[0];
					int nc = ice.c + d[1];
					
					if(isOutOfRange(nr, nc)) continue;
					if(map[nr][nc] == 1) {
						if(!iceVisited[nr][nc]) {
							meltList.add(new Ice(nr, nc));
							iceVisited[nr][nc] = true;
						}
					}
				}
			}
			
			iceList = meltList;
		}
		
		System.out.println(time);
		
		
		
	}
	
	private static boolean isAbleToMeet() {
		
		List<Swan> secondSwanList = new ArrayList<Swan>();
		
		boolean[][] swanVisited = new boolean[R][C];
		
		for(Swan swan : swanList) {
			Queue<int[]> q = new ArrayDeque<int[]>();
			visited[swan.r][swan.c] = true;
			q.offer(new int[] {swan.r, swan.c});
			
			while(!q.isEmpty()) {
				
				int[] temp = q.poll();
				
				int r = temp[0];
				int c = temp[1];
				for(int[] d: deltas) {
					int nr = r + d[0];
					int nc = c + d[1];
					
					if(nr == ansX && nc == ansY) {
						return true;
					}
					
					if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;
					
					if(map[nr][nc] == 1) {
						if(!swanVisited[nr][nc]) {
							secondSwanList.add(new Swan(nr, nc));
							swanVisited[nr][nc] = true;
						}
						continue;
					}
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
				
			}
		}
		
		swanList = secondSwanList;
		time++;
		return false;
	}

	private static boolean isOutOfRange(int r, int c) {
		
		return r < 0 || r >= R || c < 0 || c >= C;

	}
}