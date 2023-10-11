import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 설계 시작 시간: 오후 9시 20분
 * 
 * 문제 해석)
 * 맵의 가운데에서 달팽이 모양을 그리며 탈출하는데,
 * 한칸 이동한 곳에 적혀있는 모래의 양이 주변으로 퍼진다.
 * 
 * - 진행방향의 오른쪽, 진행방향, 왼쪽, 진행반대방향으로 퍼지는 비율이 다르다.
 * - 내가 가지고 있는 모래를 가지고 이동해서, 합친다음 뿌린다.
 * 
 * 이 때, 격자 밖으로 튕겨져 나가는 모래의 합을 구해야한다.
 * 
 * 문제 해결을 위한 고민)
 * - 결국 제일 어려운 부분은 다음과 같다.
 * !! 진행 방향에 따라서 왼쪽,오른쪽을 추적해야한다 !!
 * !! 게다가 이동이 끝난 후, 진행방향 앞 두칸의 모래는 5%, 한칸의 모래는 a이다.
 * 
 * 로직을 다음과 같이 짠다면 헛점이 있을까?
 * 1. 이동 전 왼쪽 오른쪽에 1%씩 뿌림.
 * 2-1) 이동 후 왼쪽으로 7%, 2%씩 뿌림.
 * 2-2) 그 후 오른쪽으로도 7%, 2%씩 뿌림.
 * 3. 그리고 한번 더 진행방향 앞칸으로 이동한 위치를 메소드로 넘김.
 * 3-1) 해당 메소드에선 양옆에 10% 뿌리고, 진행방향 한칸 앞엔 5% 뿌림.
 * 3-2) 그리고 나머지를 이 위치에 저장함.
 * 4. isOutOfRange에 걸리는 모든 위치는 정답에 더해짐.
 * 
 * 주의해야할점 : 총량을 일정하게 넘겨줘야 정확한 계산이 가능하다.
 * 
 */
public class Main {
	private static int N, ans, addOutAmount, addMoveAmount;
	private static int[][] map, deltas = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		int r = N/2;
		int c = N/2;
		int move = 1;
		int deltaIdx = 0;
		loop : while(true) {
			
			// 첫번째 delta 방향으로 이동
			for(int i=0; i<move; i++) {
				
				int yr = r + deltas[deltaIdx][0];
				int yc = c + deltas[deltaIdx][1];
				
				int ar = r + deltas[deltaIdx][0]*2;
				int ac = c + deltas[deltaIdx][1]*2;
				
				// 모래값 마킹 && 초기 위치 양옆에 1% 뿌리기
				int nowSandAmount = map[yr][yc] + map[r][c]; 
				
				int left = (deltaIdx+1)%4;
				int right = (left+2)%4;
				
				sandAdd(r+deltas[left][0], c+deltas[left][1], (int)(nowSandAmount * 0.01));
				sandAdd(r+deltas[right][0], c+deltas[right][1], (int)(nowSandAmount * 0.01));
				
				// 양 사이드에 7%, 2% 뿌리기
				sandAdd(yr+deltas[left][0], yc+deltas[left][1], (int)(nowSandAmount * 0.07));
				sandAdd(yr+deltas[right][0], yc+deltas[right][1], (int)(nowSandAmount * 0.07));
				sandAdd(yr+deltas[left][0]*2, yc+deltas[left][1]*2, (int)(nowSandAmount * 0.02));
				sandAdd(yr+deltas[right][0]*2, yc+deltas[right][1]*2, (int)(nowSandAmount * 0.02));
				
				// 앞으로 한칸 이동한 곳 양옆에 10%씩 뿌리기
				sandAdd(ar + deltas[left][0], ac + deltas[left][1], (int)(nowSandAmount * 0.1));
				sandAdd(ar + deltas[right][0], ac + deltas[right][1], (int)(nowSandAmount * 0.1));
				
				// 그 앞에 5% 뿌리기
				sandAdd(ar + deltas[deltaIdx][0], ac + deltas[deltaIdx][1], (int)(nowSandAmount * 0.05));
				
				// a 위치에 나머지 넣기
				sandAdd(ar, ac, nowSandAmount - addMoveAmount - addOutAmount);
				map[r][c] = 0;
				map[yr][yc] = 0;
				
				ans += addOutAmount;
				addMoveAmount = 0;
				addOutAmount = 0;
				
				r = yr;
				c = yc;
				
				if(r == 0 && c == 0) break loop;
				
			}
			
			deltaIdx = (deltaIdx+1)%4;
			
			// 두번째 delta 방향으로 이동
			for(int i=0; i<move; i++) {
				
				int yr = r + deltas[deltaIdx][0];
				int yc = c + deltas[deltaIdx][1];
				
				int ar = r + deltas[deltaIdx][0]*2;
				int ac = c + deltas[deltaIdx][1]*2;
				
				// 모래값 마킹 && 초기 위치 양옆에 1% 뿌리기
				int nowSandAmount = map[yr][yc] + map[r][c]; 
				int left = (deltaIdx+1)%4;
				int right = (left+2)%4;
				
				sandAdd(r+deltas[left][0], c+deltas[left][1], (int)(nowSandAmount * 0.01));
				sandAdd(r+deltas[right][0], c+deltas[right][1], (int)(nowSandAmount * 0.01));
				
				// 양 사이드에 7%, 2% 뿌리기
				sandAdd(yr+deltas[left][0], yc+deltas[left][1], (int)(nowSandAmount * 0.07));
				sandAdd(yr+deltas[right][0], yc+deltas[right][1], (int)(nowSandAmount * 0.07));
				sandAdd(yr+deltas[left][0]*2, yc+deltas[left][1]*2, (int)(nowSandAmount * 0.02));
				sandAdd(yr+deltas[right][0]*2, yc+deltas[right][1]*2, (int)(nowSandAmount * 0.02));
				
				// 앞으로 한칸 이동한 곳 양옆에 10%씩 뿌리기
				sandAdd(ar + deltas[left][0], ac + deltas[left][1], (int)(nowSandAmount * 0.1));
				sandAdd(ar + deltas[right][0], ac + deltas[right][1], (int)(nowSandAmount * 0.1));
				
				// 그 앞에 5% 뿌리기
				sandAdd(ar + deltas[deltaIdx][0], ac + deltas[deltaIdx][1], (int)(nowSandAmount * 0.05));
				
				// a 위치에 나머지 넣기
				sandAdd(ar, ac, nowSandAmount - addMoveAmount - addOutAmount);
				map[r][c] = 0;
				map[yr][yc] = 0;
				ans += addOutAmount;
				addMoveAmount = 0;
				addOutAmount = 0;
				
				r = yr;
				c = yc;
				
				if(r == 0 && c == 0) break loop;
				
			}
			
			deltaIdx = (deltaIdx+1)%4;
			move++;
		}
		System.out.println(ans);
		
		
	}
	private static void sandAdd(int r, int c, int amount) {
		if(isOutOfRange(r, c)) addOutAmount += amount;
		else {
			map[r][c] += amount;
			addMoveAmount += amount;
		}
		
	}
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c>= N;
	}
}