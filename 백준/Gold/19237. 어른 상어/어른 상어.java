import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 
 * 상어에는 1 ~ M의 서로 다른 번호가 붙어있음.
 * 1의 번호를 가진 상어는 나머지 상어를 다 쫓아낼 수 있음
 * 
 * N * N 격자 중 M개의 칸에 상어가 한마리씩있음.
 * 모든 상어가 자신의 위치에 냄새를 뿌림
 * 그 후 1초마다 모든 상어가 "동시에" 상하좌우로 인접한 칸 중 하나로 이동하고 냄새를 또 뿌림
 * 냄새는 상어가 K번 이동하고 나면 사라짐
 * 
 * 상어 이동 로직
 * 1. 인접한 칸 중 아무 냄새 없는 칸으로 방향 잡음
 * 2. 그런 칸이 없다면 자신의 냄새가 있는 칸으로
 * 3. 가능한 칸이 여러개라면 특정한 우선순위를 따르는데 이는 상어마다 다름.
 * 4. 같은 상어라도 현재 상어가 보고 있는 방향에 따라 다를 수 있음.
 * 5. 상어가 맨 처음 보고 있는 방향은 입력으로 주어지고, 그 후 이동한 방향이 보고 있는 방향이 됨.
 * 
 * 모든 상어가 이동한 후 한 칸에 여러 마리 상어가 남아있으면
 * 가장 자 ㄱ은 번호를 가진 상어를 제외하고 모두 쫓겨남
 * 이 때 1번 상어만 격자에 남게 되기 까지 몇초가 걸리는지 구하시오
 * 
 * 입력)
 * 2 <= N <= 20, 2<= M <= N^2, 1<=k<=1000
 * 
 * 1: 위 (-1, 0)
 * 2: 아래 (1, 0)
 * 3: 왼쪽(0, -1)
 * 4: 오른쪽(0, 1)
 * 
 * 천초가 넘어도 다른 상어가 격자에 남아있으면 -1 출력
 * 
 * 문제 해결을 위한 고민)
 * 
 * 1. 격자의 자료 구조를 어떻게 구성할까?
 * - 고려해야할 점: 냄새(남은 턴 까지), 상어 위치
 * - N*N*2 [냄새주인, 냄새턴] 
 * - 상어 위치는 상어 객체가 알아서
 * 
 * 2. 상어가 만난 것 감지를 어떻게?
 * - 작은 번호부터 이동시키고 누군가가 이동했을때 해당 지역이 그 턴 visited true라면
 * - 그 위치는 List에 따로 저장?
 * - 그리고 싸운다음 작은 번호의 냄새 남기기
 * 3. 우선순위 저장 어떻게?
 * - shark의 [방향][x][y] 만들어놓기
 * 
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	private static final int UP=1, DOWN=2, LEFT=3, RIGHT=4, ID=0, SMELL=1;
	private static final int[] UP_DELTA = {-1, 0}, DOWN_DELTA = {1, 0}, LEFT_DELTA = {0, -1}, RIGHT_DELTA = {0, 1};
	private static int N, M, K, sharkCount;
	private static int[][] deltas = {UP_DELTA, DOWN_DELTA, LEFT_DELTA, RIGHT_DELTA};
	private static List<int[]> battleLocs;
	private static int[][][] map;
	private static boolean[][] visited;
	private static Shark[] sharks;
	
	private static class Shark {
		
		int id;
		int r;
		int c;
		int delta;
		boolean alive = true;
		int[][][] deltas = new int[5][5][2]; 
		
		public Shark(int id, int r, int c) {
			this.id = id;
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N][2];
		sharks = new Shark[M+1];
		sharkCount = M;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int id = Integer.parseInt(st.nextToken());
				if(id > 0) {
					map[i][j][ID] = id;
					map[i][j][SMELL] = K;
					
					sharks[id] = new Shark(id, i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) {
			sharks[i].delta = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=M; i++) {
			Shark shark = sharks[i];
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<=4; k++) {
					int delta = Integer.parseInt(st.nextToken());
					switch (delta) {
					case UP:
						shark.deltas[j][k] = UP_DELTA;
						break;
					case DOWN:
						shark.deltas[j][k] = DOWN_DELTA;
						break;
					case LEFT:
						shark.deltas[j][k] = LEFT_DELTA;
						break;
					case RIGHT:
						shark.deltas[j][k] = RIGHT_DELTA;
						break;
					}
				}
			}
		}
		
		int turn = 0;
		for(int t=0; t<1000; t++) {
			
			// 냄새 빠지는 로직 발동
			if(sharkCount == 1) break;
			
//			 * 상어 이동 로직
//			 * 1. 인접한 칸 중 아무 냄새 없는 칸으로 방향 잡음
//			 * 2. 그런 칸이 없다면 자신의 냄새가 있는 칸으로
//			 * 3. 가능한 칸이 여러개라면 특정한 우선순위를 따르는데 이는 상어마다 다름.
//			 * 4. 같은 상어라도 현재 상어가 보고 있는 방향에 따라 다를 수 있음.
//			 * 5. 상어가 맨 처음 보고 있는 방향은 입력으로 주어지고, 그 후 이동한 방향이 보고 있는 방향이 됨.
			visited = new boolean[N][N];
			battleLocs = new ArrayList<int[]>();

			for(Shark shark: sharks) {
				if(shark == null || !shark.alive) continue;
				
				int canMoveCount = 0;
				int canMoveRow = -1;
				int canMoveCol = -1;
				
				for(int[] d : deltas) {
				
					int nr = shark.r + d[0];
					int nc = shark.c + d[1];
					
					if(isOutOfRange(nr, nc) || map[nr][nc][SMELL] > 0) continue;
					
					canMoveCount++;
					canMoveRow = nr;
					canMoveCol = nc;
					
				}
				
				// 냄새 없는 칸이 없다면 
				if(canMoveCount == 0) {
					// 자신 냄새 찾아가기 
					int canMoveToMySmell = 0;
					int canMoveToMySmellRow = -1;
					int canMoveToMySmellCol = -1;
					
					for(int[] d : deltas) {
						
						int nr = shark.r + d[0];
						int nc = shark.c + d[1];
						
						if(isOutOfRange(nr, nc) || map[nr][nc][ID] != shark.id) continue;

						canMoveToMySmell++;
						canMoveToMySmellRow = nr;
						canMoveToMySmellCol = nc;
						
					}
					
					if(canMoveToMySmell > 1) {
						// 내 냄새 칸이 여러개라면 우선순위 따라 이동

						for(int z=1; z<=4; z++) {
							int[] d = shark.deltas[shark.delta][z];
							int nr = shark.r + d[0];
							int nc = shark.c + d[1];

							if(isOutOfRange(nr, nc) ||  map[nr][nc][ID] != shark.id) continue;

							shark.delta = findDelta(d[0], d[1]);
							shark.r = nr;
							shark.c = nc;
							if(visited[nr][nc]) {
								battleLocs.add(new int[] {nr, nc});
							} else {
								visited[nr][nc] = true;
							}
							
							break;
						}
					}
					else {
						// 내 냄새 칸이 단 하나라면
	
						shark.delta = findDelta(canMoveToMySmellRow - shark.r, canMoveToMySmellCol - shark.c);
						shark.r = canMoveToMySmellRow;
						shark.c = canMoveToMySmellCol;
						if(visited[canMoveToMySmellRow][canMoveToMySmellCol]) {
							battleLocs.add(new int[] {canMoveToMySmellRow, canMoveToMySmellCol});
						} else {	
							visited[canMoveToMySmellRow][canMoveToMySmellCol] = true;
						}
					}
					
				}
				
				else if (canMoveCount > 1){
					// 냄새 없는 칸이 여러개라면 우선순위 따라 이동
					for(int z=1; z<=4; z++) {
						int[] d = shark.deltas[shark.delta][z];
						
						int nr = shark.r + d[0];
						int nc = shark.c + d[1];
						if(isOutOfRange(nr, nc) ||  map[nr][nc][SMELL] != 0) continue;
						shark.delta = findDelta(d[0], d[1]);
						shark.r = nr;
						shark.c = nc;
						if(visited[nr][nc]) {
							battleLocs.add(new int[] {nr, nc});
						} else {
							visited[nr][nc] = true;
						}
						
						break;
					}
					
				}
				else {
					// 냄새 없는 칸이 단 하나라면
					shark.delta = findDelta(canMoveRow - shark.r, canMoveCol - shark.c);
					shark.r = canMoveRow;
					shark.c = canMoveCol;
					if(visited[canMoveRow][canMoveCol]) {
						battleLocs.add(new int[] {canMoveRow, canMoveCol});
					} else {
						visited[canMoveRow][canMoveCol] = true;
					}
				}
				
				
			}
			for(Shark shark : sharks) {
				if(shark == null || !shark.alive ) continue;
				
				if(map[shark.r][shark.c][ID] == 0) {
					map[shark.r][shark.c][ID] = shark.id;
					map[shark.r][shark.c][SMELL] = K+1;
				}
				else if(map[shark.r][shark.c][ID] == shark.id) {
					map[shark.r][shark.c][SMELL] = K+1;
				}
			}
			
			// fight
			for(int[] battleLoc : battleLocs) {
				
				int battleRow = battleLoc[0];
				int battleCol = battleLoc[1];
				if(!visited[battleRow][battleCol]) continue;
				visited[battleRow][battleCol] = false;
				
				int surviveSharkId = map[battleRow][battleCol][ID];
			
				for(Shark shark: sharks) {
					if(shark == null || !shark.alive ) continue;
					else if(shark.r == battleRow && shark.c == battleCol) {
						if(surviveSharkId != shark.id) {
							shark.alive = false;
							sharkCount--;							
						}
					}
				}
			}
	
			turn++;
			decreaseSmell();
//			print();
		}
		
		if(sharkCount > 1) {
			System.out.println(-1);
		} else {
			System.out.println(turn);
		}
		
		
	}

	private static void print() {
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				System.out.print(map[r][c][ID] + "(" + map[r][c][SMELL] + ") " );
			}
			System.out.println();
		}
		
	}

	private static int findDelta(int dr, int dc) {
		int resultDelta = -999;
		if(dr == 0 && dc == 1) {
			resultDelta = RIGHT;
		}
		else if(dr == 0 && dc == -1) {
			resultDelta = LEFT;
		}
		else if(dr == -1 && dc == 0) {
			resultDelta = UP;
		}
		else if(dr == 1 && dc == 0) {
			resultDelta = DOWN;
		} 
		return resultDelta;
	}

	private static void decreaseSmell() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c][SMELL] > 0) map[r][c][SMELL]--;
				
				if(map[r][c][SMELL] == 0) map[r][c][ID] = 0;
				
			}
		}
		
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nc < 0 || nr >= N || nc >= N;
	}
	
	
}
