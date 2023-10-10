import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 설계 시작 시간: 오후 9시 33분
 * 설계 종료: 오후 9시 53분
 * 문제 해석)
 * - 가로 세로 길이가 2L + 1 인 격자판
 * - 가운데 좌표 : (0, 0)
 * - x는 왼쪽에서 오른쪽, y는 아래에서 위로 갈수록 증가 => 우리가 아는 좌표평면
 * - (0, 0)칸에 뱀 머리가 있으며, 오른쪾을 바라보고 있음(delta)
 * - 1초가 지나면 (0,0)과 (1,0) 두 칸을 차지
 * 		- 이때 뱀의 머리는 (1,0)에 
 * 		- 또 1초후엔 (2,0)에 
 *
 * - 뱀은 머리가 향하고 있는 방향을 일정한 규칙에 따라 시계방향, 혹은 반시계 방향으로 90도 회전
 * - 1번째 회전은 뱀이 출발한 지 t1초 후에 일어남
 * - i번째 회전은 i-1번째 회전이 끝난지 ti 초 후 일어남
 * - 단, 뱀은 ti 칸만큼 몸을 늘린 후 머리를 회전.
 * 		=> 머리를 회전하는데에는 시간이 소요되지 않음
 * 
 * - 뱀의 머리가 격자판 밖으로 나가거나, 머리가 자신으 ㅣ몸에 부딪히게 되면 숙짐
 * - 숨지기 직전까지 몸을 계속 늘림
 * 
 * 입력)
 * 첫째 줄에 정수 L (1<=L <= 1억)
 * 둘째 줄에 정수 N (0<= N <= 1000) => 머리 몇번 회전하는지
 * 다음 N개줄에 머리를 어떻게 회전하는지 주어짐
 * i for 1<= i<= N, 
 * 정수 ti와 dir_i가 차례로 주어짐
 * 1<= ti <= 2억, dir_i : L or R
 * 뱀은 i=1인 경우 출발, 그외엔 i-1번째 회전 후 ti 초 후에 dir i 방향으로 회전
 * 
 * 문제 해결을 위한 고민)
 * 
 * 1. 메모리?
 * => 맵이나 뱀의 위치를 "전부" 담기는 불가능이다.
 * 
 * 2. 시간복잡도?
 * => ti가 최대 2억씩 1000번 주어진다.
 * => ti를 1초마다 추적하기가 불가능 => 수학적 계산 필요
 * 
 * 3. 어떻게 충돌을 감지할 것인가? (Main point)
 * - 밖으로 나가는 경우는 쉽다. 
 * - 다음 회전까지의 ti를 현재 위치에서 더했을때 나가게 된다면 그때 추적하면 됨.
 * 
 * 3-1) 내 몸과 충돌하는 경우를 어떻게 감지?
 * - 턴마다 이동한다고 할때, 턴이 끝나면 회전한다고 하자.
 * - 이 때 이동한 시작점과 끝점을 기록하자.
 * - 방금 이동이 끝났다면 새로운 이동 경로 직선 구간이 생김.
 * - 이 구간을 이전까지의 구간을 완탐하며 충돌 여부를 감지하자.
 * => 1000^2 으로 끝남
 * 
 * 구현 로직)
 * 1. 명령어 정보를 int[][] array에 전부 저장하고, 턴마다 꺼내쓴다.
 * 2. 턴마다 경로를 저장해가며 게임이 끝나는지 확인한다.
 * 2-1) 만약 해당 턴에 끝난다면 디테일한 게임오버 시간 도출
 * 
 */
public class Main {
	
	private static int N, L;
	
	static class Snake {
		
		long aliveTime = 0;
		int x = 0;
		int y = 0;
		int dx = 1;
		int dy = 0;
		List<int[][]> moveInfos = new ArrayList<int[][]>();
		
		public Snake() {}
		
		private boolean move(int moveTime, int turnInfo, boolean isLastMove) {
			int oldX = x;
			int oldY = y;
			x += dx * moveTime;
			y += dy * moveTime;
			// 생존 여부 확인
			if(isAlive(oldX, oldY, x, y)) {
				aliveTime += moveTime;
				moveInfos.add(new int[][] {{oldX, oldY}, {x, y}});
				turnHead(turnInfo);
				
				// 더이상 명령어가 들어오지 않는다면 게임 종료
				if(isLastMove) {
					if(dx == 1 && isAlive(x, y, L, y)) {
						aliveTime += Math.abs(L-x) + 1;
					}
					else if(dx == -1 && isAlive(x, y, -L, y)) {
						aliveTime += Math.abs(-L-x) + 1;
					}
					else if(dy == -1 && isAlive(x, y, x, -L)) {
						aliveTime += Math.abs(-L-y) + 1;
					}
					else if(dy == 1 && isAlive(x, y, x, L)) {
						aliveTime += Math.abs(L-y) + 1;
					}
				}
				return true;
			}
			
			return false;
		}
		
		
		/**
		 * oldX, oldY 좌표와 newX, newY 좌표를 이은 직선이
		 * x1, y1 좌표와 x2, y2좌표를 이은 직선과 겹치는지 확인
		 * 
		 * 이 직선들의 특징 : x1 == x2거나 y1 == y2이다.
		 * 같은 맥락으로, oldX == newX 거나 oldY ==newY이다.
		 * 즉, 어느 축과 평행한 직선인지만 체크 후 범위 확인으로 넘어가면 됨
		 * 
		 * => 마지막으로 추가된 info인 경우엔 딱 끝점 한군데에서 충돌하는거 허용
		 * 
		 */
		private boolean isAlive(int oldX, int oldY, int newX, int newY) {
			
			
			int dieTime = Integer.MAX_VALUE;
			
			// 직선을 위한 크기 비교
			int bigX = oldX > newX ? oldX : newX;
			int smallX = oldX > newX ? newX : oldX;
			
			int bigY = oldY > newY ? oldY : newY;
			int smallY = oldY > newY ? newY : oldY;
			
			// 직전 경로는 비교할 필요 없음
			for(int i=0; i<moveInfos.size()-1; i++) {
				
				int tempAliveTime = Integer.MAX_VALUE;
				
				int[][] moveInfo = moveInfos.get(i);
				
				int x1 = moveInfo[0][0];
				int y1 = moveInfo[0][1];
				
				int x2 = moveInfo[1][0];
				int y2 = moveInfo[1][1];
				
				int oldBigX = x1 > x2 ? x1 : x2;
				int oldSmallX = x1 > x2 ? x2 : x1;
				
				int oldBigY = y1 > y2 ? y1 : y2;
				int oldSmallY = y1 > y2 ? y2 : y1;
				
				// 둘다 세로직선인 경우 
				if(oldBigX == oldSmallX && bigX == smallX) {
					
					// X좌표가 같다면 y좌표를 체크해줘야한다.
					if(oldBigX == bigX) {
						
						if(dy == 1) {
							if(smallY <= oldSmallY && bigY >=oldSmallY) {
								tempAliveTime = oldSmallY - smallY;
								dieTime = Math.min(dieTime, tempAliveTime);
							}
						}
						else if(dy == -1) {
							if(bigY >= oldBigY && smallY <= oldBigY ) {
								tempAliveTime = bigY - oldBigY;
								dieTime = Math.min(dieTime, tempAliveTime);
							}
						}
						
					}
					
				}
				// 둘다 가로 직선인 경우
				else if(oldBigY == oldSmallY && bigY == smallY) {
					// Y좌표가 같다면 x좌표를 체크해줘야한다.
					if(oldBigY == bigY) {
						// 충돌하는 경우
						if(dx == 1) {
							if(smallX <= oldSmallX && bigX >= oldSmallX) {
								tempAliveTime = oldSmallX - smallX;
								dieTime = Math.min(dieTime, tempAliveTime);
							}
						} 
						else if (dx == -1){
							if(bigX >= oldBigX && smallX <= oldBigX ) {
								tempAliveTime = bigX - oldBigX;
								dieTime = Math.min(dieTime, tempAliveTime);
							}
						}
					}
				}
				// 과거 경로가 세로직선, 새 경로가 가로 직선인 경우 (bigY == smallY)
				else if(oldBigX == oldSmallX) {
					
					// 새 경로의 x범위에 과거 x가 포함되면서 과거 경로 y범위에 새 경로의 y가 포함되면 사망
					if(smallX <= oldBigX && oldBigX <= bigX) {
						if(oldSmallY <= bigY && bigY <= oldBigY) {
							// 사망 return
							if(dx == 1) {
								tempAliveTime = oldBigX - oldX;
							} else {
								tempAliveTime = oldX - oldBigX;
							}
							
							dieTime = Math.min(dieTime, tempAliveTime);
						}
					}
					
				}
				// 과거 경로가 가로직선, 새 경로가 세로 직선인 경우 (bigX == smallX)
				else if(oldBigY == oldSmallY) {
					
					// 새 경로의 y 범위에 과거 y가 포함되면서, 과거 경로 x범위에 새 경로 x가 포함되면 사망
					if(smallY <= oldBigY && oldBigY <= bigY) {
						if(oldSmallX <= bigX && bigX <= oldBigX) {
							
							// 사망 case
							if(dy == -1) {
								tempAliveTime = oldY - oldBigY;
							} else { 
								tempAliveTime = oldBigY - oldY;
							}
							dieTime = Math.min(dieTime, tempAliveTime);
						}
					}
				}
			}
			
			if(dieTime != Integer.MAX_VALUE) {
				aliveTime += dieTime;
				return false;
			}
			
			// 맵 밖으로 나간 경우
			if(newX > L || newX < -L || newY > L || newY < -L) {
					if(oldX == newX) {
						if(dy == 1) {
							aliveTime += L-oldY + 1;
						} else {
							aliveTime += oldY - (-L) + 1;
						}
					} else {
						if(dx == 1) {
							aliveTime += L - oldX + 1;
						} else {
							aliveTime += oldX - (-L) + 1;
					}
					}
					
					return false;
			}
				

			return true;

		}
		
		private void turnHead(int turnInfo) {
			// 생존 시 머리 회전 && 왼쪽 회전
			if(turnInfo == 1) {
				
				if(dx == 0 && dy == -1) {
					dx = 1;
					dy = 0;
				}
				else if(dx == 0 && dy == 1) {
					dx = -1;
					dy = 0;
				}
				else if(dx == 1 && dy == 0) {
					dx = 0;
					dy = 1;
				}
				else if(dx == -1 && dy == 0) {
					dx = 0;
					dy = -1;
				}
				
			} 
			else {
				// 오른쪽 회전 
				if(dx == 0 && dy == -1) {
					dx = -1;
					dy = 0;
				}
				else if(dx == 0 && dy == 1) {
					dx = 1;
					dy = 0;
				}
				else if(dx == 1 && dy == 0) {
					dx = 0;
					dy = -1;
				}
				else if(dx == -1 && dy == 0) {
					dx = 0;
					dy = 1;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		if(N==0) {
			System.out.println(L+1);
			return;
		}
		
		int[][] cmdList = new int[N][2]; 
		
		// 회전 정보(턴 정보) 입력
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cmdList[i][0] = Integer.parseInt(st.nextToken());
			if(st.nextToken().equals("L")) {
				cmdList[i][1] = 1; // 왼쪽으로 90도 회전
			} else {
				cmdList[i][1] = -1; // 오른쪽으로 90도 회전
			}
		}
		
		Snake snake = new Snake();
		
		// 게임 시작
		for(int i=0; i<N; i++) {

			if(!snake.move(cmdList[i][0], cmdList[i][1], i==N-1)) {
				System.out.println(snake.aliveTime);
				return;
			}
			
		}
		
		System.out.println(snake.aliveTime);
		
	}
}