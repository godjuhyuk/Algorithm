import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, D, ans, enemyCnt;
	private static int[] archerLocList;
	private static int[][] deltas = {{0, -1}, {-1, 0}, {0, 1}};
	private static Archer[] archers;
	private static int[][] map;
	private static List<int[]> targetList;
	
	
	private static int getDistance(int ar, int ac, int er, int ec) {
		return Math.abs(ar - er) + Math.abs(ac - ec);
	}
	private static boolean isOutOfRange(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;
	}
	
	public static class Archer {
		
		int row;
		int col;
		
		public Archer(int col) {
			row = N;
			this.col = col;
		}

		// BFS - 적 찾기
		private void shoot(int[][] copiedMap, boolean[][] visited) {
			
			Queue<int[]> findQueue = new ArrayDeque<int[]>();
            if(copiedMap[this.row-1][this.col] > 0) {
                targetList.add(new int[] {this.row-1, this.col});
                return;
            }
            
			findQueue.add(new int[] {this.row-1, this.col});
			
			// BFS 시작
			while(!findQueue.isEmpty()) {
				
				int[] rowAndCol = findQueue.poll();
				visited[rowAndCol[0]][rowAndCol[1]] = true;
				for(int[] d : deltas) {
					int nr = rowAndCol[0] + d[0];
					int nc = rowAndCol[1] + d[1];
					if(isOutOfRange(nr, nc) || visited[nr][nc] || getDistance(row, col, nr, nc) > D) continue;
					
					// 적을 찾았다면 리턴
					if(copiedMap[nr][nc] > 0 && getDistance(row, col, nr, nc) <= D) {
						targetList.add(new int[] {nr, nc});
						return;
					}
					findQueue.add(new int[] {nr, nc});
				}
				
			}

		}
		
	}
	
	
	public static void main(String[] args) throws IOException{
		
		// 입출력 관련
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		archers = new Archer[3];
		
		// 적 생성
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) enemyCnt++;
			}
		}
		
		// 궁수 3명의 위치 찾는 조합 메소드
		archerLocList = new int[3];
		archerLocSet(0, 0);
		System.out.println(ans);
	}
	
	private static void archerLocSet(int cnt, int start) {
		
		if(cnt == 3) {
			for(int i=0; i<3; i++) {
				archers[i]  = new Archer(archerLocList[i]);
			}
			gameStart();
			return;
		}
		
		for(int i = start; i < M; i++) {
			archerLocList[cnt] = i;
			archerLocSet(cnt+1, i+1);
		}

	}
	
	private static void gameStart() {
		
		int killPoint = 0;
		int[][] copiedMap = copyMap();
		
		game : while(true) {
			
			targetList = new ArrayList<int[]>();
			
			// 궁수 사격
			for(int i=0; i<3; i++) {
				archers[i].shoot(copiedMap, new boolean[N+1][M+1]);
			}
			
			// 적 사망
			for(int i=0; i<targetList.size(); i++) {
				int[] enemyLoc = targetList.get(i);
				if(copiedMap[enemyLoc[0]][enemyLoc[1]] != 0) {
					copiedMap[enemyLoc[0]][enemyLoc[1]] = 0;
					killPoint++;
				}
			}
			
			// 적 이동
			for(int i=N-1; i>=1; i--) {
				System.arraycopy(copiedMap[i-1], 0, copiedMap[i], 0, M);
			}
			
			Arrays.fill(copiedMap[0], 0);
			
			
			// 적 체크
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					// 적이 생존해있다면 game 진행
					if(copiedMap[i][j] != 0) {
						continue game;
					}
				}
			}
			
			// 적이 존재하지 않는다면 game 종료
			break game;
		}
		ans = Math.max(ans, killPoint);
		if(ans == enemyCnt) {
			System.out.println(ans);
			System.exit(0);
		}
		
	}
	
	private static int[][] copyMap() {
		
		int[][] copiedMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, copiedMap[i], 0, M);
		}
		
		return copiedMap;
	}

}
