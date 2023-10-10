import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 설계 시작 시간 : 오후 12시 55분
 * 설계 종료 시간 : 오후 1시 7분
 * 
 * 문제 해석)
 * R*C 격자판
 * 각 칸에있는 미세먼지 양을 모니터링하는 시스템
 * 
 * 공기청정기는 항상 1번열에 설치 & 크기는 "두 행 차지"
 * 
 * 1초 동안 아래 순서로 일이 일어난다.
 * 
 * 1. 미세먼지 확산 & 미세먼지가 있는 모든칸에서 동시에 일어남
 * - 인접한 4방향으로 확산
 * - 인접한 방향에 공기청정기가 있거나 칸이 없으면 그 방향으로는 확산X
 * - 확산되는 양은 A / 5 이고 소수점은 버린다.
 * - r, c에 남은 미세먼지 양은 A - (A/5) * 확산된 방향 개수이다.
 * 
 * 2. 공기청정기 작동
 * - 공기청정기에서 바람이 나온다.
 * - 위쪽 공기청정기의 바람은 반시계방향으로 순환
 * - 아래쪽 공기청정기의 바람은 시계방향으로 순환
 * - 바람이 불면 미세먼지가 바람의 방향대로 모두 한칸씩 이동한다.
 * - 공기청정기에서 부는 바람은 미세먼지가 없는 바람, 공기 청정기로 들어간
 * 미세먼지는 모두 정화
 * 
 * 문제 해결을 위한 고민)
 * 1. 미세먼지 확산 시 어떤 방식으로 연산?
 * 1-1) Queue에 미세먼지들을 전부 넣고, 4방으로 확산
 * 1-2) map을 3차원 배열로 생성하고, 0번 인덱스에는 확산 전 먼지, 1번에는 이 위치로 날아온 먼지 담기
 * 1-3) 먼지확산이 종료되면 map을 완탐하면서 1번인덱스 값을 0번인덱스에 더하기
 * 
 * 2. 공기청정기 바람 어떻게 순환?
 * - 배열 회전을 시키는 반복문 2개 만들기
 * - 시계방향 회전
 * - 반시계방향 회전
 *
 */
public class Main {
	
	private static int R, C, T, sum;
	private static int[] machineRows = new int[2];
	private static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static int[][][] map;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C][2];
		
		for(int i=0, idx = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if(map[i][j][0] == -1) {
					machineRows[idx++] = i;
				}
			}
		}
		
		while(--T >= 0) {
			// 1. 먼지 확산
			sum = 2;
			dustSpread();
			
			// 2. 청정기 바람 회전
			rotateCounterClockwise(machineRows[0]);
			rotateClockwise(machineRows[1]);
			
		}
		System.out.println(sum);
	}

	// r행 0열을 기점으로 반시계 방향으로 한칸씩 이동하는 함수
	private static void rotateCounterClockwise(int r) {
		
		// r행 0열은 청정기이므로 r-1행 0열을 0으로 만든 후 (먼지 청정) r-1행부터 0행까지 한칸씩 당기기
		sum -= map[r-1][0][0];
		map[r-1][0][0] = 0;
		
		int nowRow = r-1;
		int nowCol = 0;
		
		while(nowRow - 1 >= 0) {
			map[nowRow][nowCol][0] = map[nowRow-1][nowCol][0];
			nowRow--;
		}
		
		while(nowCol + 1 < C) {
			map[nowRow][nowCol][0] = map[nowRow][nowCol+1][0];
			nowCol++;
		}
		
		while(nowRow + 1 <= r) {
			map[nowRow][nowCol][0] = map[nowRow+1][nowCol][0];
			nowRow++;
		}
		
		while(nowCol - 1 > 0) {
			map[nowRow][nowCol][0] = map[nowRow][nowCol-1][0];
			nowCol--;
		}
		
		// 청정기 바로 옆
		map[r][nowCol][0] = 0;
		
		
	}
	
	// r행 0열을 기점으로 시계 방향으로 한칸씩 이동하는 함수
	private static void rotateClockwise(int r) {
		
		// r행 0열은 청정기이므로 r+1행 0열을 0으로 만든 후 (먼지 청정) r+1행부터 R행까지 한칸씩 당기기
		sum -= map[r+1][0][0];
		map[r+1][0][0] = 0;
		
		int nowRow = r+1;
		int nowCol = 0;
		
		while(nowRow + 1 < R) {
			map[nowRow][nowCol][0] = map[nowRow+1][nowCol][0];
			nowRow++;
		}
		
		while(nowCol + 1 < C) {
			map[nowRow][nowCol][0] = map[nowRow][nowCol+1][0];
			nowCol++;
		}
		
		while(nowRow - 1 >= r) {
			map[nowRow][nowCol][0] = map[nowRow-1][nowCol][0];
			nowRow--;
		}
		
		while(nowCol - 1 > 0) {
			map[nowRow][nowCol][0] = map[nowRow][nowCol-1][0];
			nowCol--;
		}
		
		// 청정기 바로 옆
		map[r][nowCol][0] = 0;
		
	}

	private static void dustSpread() {
		
		 Queue<int[]> dustQueue = new ArrayDeque<int[]>();
		 
		 // 먼지 Queueing
		 for(int r=0; r<R; r++) {
			 for(int c=0; c<C; c++) {
				 if(map[r][c][0] > 0) {
					 int spreadAmount = map[r][c][0] / 5;
					 for(int d=0; d<4; d++) {
						 int nr = r + deltas[d][0];
						 int nc = c + deltas[d][1];
						 
						 if(isOutOfRange(nr, nc) || map[nr][nc][0] == -1) continue;
						 
						 map[nr][nc][1] += spreadAmount;
						 map[r][c][0] -= spreadAmount;
					 }
				 }
			 }
		 }
		 
		 
		 // 확산된 먼지 합치기
		 for(int i=0; i<R; i++) {
			 for(int j=0; j<C; j++) {
				 if(map[i][j][1] > 0) {
					 map[i][j][0] += map[i][j][1];
					 map[i][j][1] = 0;
				 }
				 sum += map[i][j][0];
			 }
		 }
		 
		
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C;
	}
	
}