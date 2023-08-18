import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 설계 시작시간: 오후3시 46분
 * 설계 종료시간: 오후4시 00분
 * 
 * 문제해석)
 * N*M 격자판
 * 1x1 칸에 포함된 적의 수는 최대 하나
 * 격자판의 N번행 의 바로 아래(N+1번행)의 모든칸에는 성이 있음
 * 
 * 궁수 3명 배치
 * 성이 있는 칸에 배치할 수 있음
 * 한칸에 최대 한명
 * 각 턴마다 궁수는 적 하나를 공격&모든 궁수는 동시공격
 * 	- 거리가 D 이하인 적 중에서 가장 가까운 적 공격
 * 	- 가장 가까운 적이 여럿일 경우 가장 왼쪽에 있는 적 공격
 * 	- 같은 적ㅇ ㅣ여러 궁수에게 공격당할수도 있음
 * 
 * 궁수의 공격이 끝나면 적이 아래로 한칸 이동
 *  	- 성이 있는 칸으로 이동하면 사라짐
 * 
 * end : 모든 적이 격자판에서 제외되면 끝
 * 
 * 궁수의 위치가 중요함.
 * 격자판이 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수 계산
 * 
 * 거리 : |r1-r2| + |c1-c2|
 * 
 * 입력: 0은 빈칸, 1은 적이 있는 칸
 * 3<= N, M <= 15
 * 1<= D <= 10
 * 
 * 무식하게 봤을때 최대 시간복잡도
 * 15C3(궁수배치) * 15(턴) * 10(사거리 for문) * 3 (궁수) 
 * 
 * => 무식하게 짜도 될듯?
 * => 사거리 for문이 시간초과가 난다면 BFS로 짜보자
 * 
 * 궁수의 배치는?
 * 최적배치가 없는 이상 조합으로 찾아내야함
 * 최적배치는 적의 배치에 따라 달라지므로 불가능
 * 조합으로 찾아내보자
 * 
 * 구현해야할 기능
 * 1) 궁수 배치 (조합)
 * 	- 넥퍼 조합 or 재귀 => 재귀가 빠를거같다
 * 
 * 2) 궁수 사격
 *  - 맨왼쪽 궁수부터 사격(for문으로 안될거같다 => BFS로)
 *  	- 델타 설정 : 맨 왼쪽부터 탐색하도록 델타배열 배치
 *  	- 적을 찾았을 시 해당 적의 target 성질을 true로
 *  
 *  - 사격이 끝났다면 적 배열을 돌면서 target == true라면 
 *  	kill++ && 맵에서 제외
 * 	
 * => archer 클래스, enemy 클래스 만들기
 * 
 * 3) 적의 이동
 * 	- 한칸 아래로.
 * 	- row+1 == N+1 이라면 사라지게 만들기 (kill에 포함 x)
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	private static int N, M, D, ans;
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
		int shootRange = D;
		
		public Archer(int col) {
			row = N;
			this.col = col;
		}

		// BFS 여기서 할까? 
		private void shoot(int[][] copiedMap, boolean[][] visited) {
			
			Queue<int[]> findQueue = new ArrayDeque<int[]>();
			findQueue.add(new int[] {this.row, this.col});
			
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
		int phase = 1;
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
			for(int i=N-1; i>=0; i--) {
				for(int j=M-1; j>=0; j--) {
					if(copiedMap[i][j] == phase && i+1 < N) {
						copiedMap[i][j] = 0;
						copiedMap[i+1][j] = phase+1;
					}
					else if(copiedMap[i][j] == phase && i+1 == N) {
						copiedMap[i][j] = 0;
					}
				}
			}
			
			
			// 적 체크
			for(int i=0; i<N; i++) {
				
				for(int j=0; j<M; j++) {
					if(copiedMap[i][j] == phase+1) {
						phase++;
						
						continue game;
					}
				}
			}
			
			// 적이 아무도 없다면
			break game;
		}
		ans = Math.max(ans, killPoint);
		
		
	}
	
	private static int[][] copyMap() {
		
		int[][] copiedMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			copiedMap[i] = Arrays.copyOf(map[i], M);
//			for(int j=0; j<M; j++) {
//				System.arraycopy(map[i], j, copiedMap[i], j, M-1);
//			}
		}
		
		return copiedMap;
	}

}