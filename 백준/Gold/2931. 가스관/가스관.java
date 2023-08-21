import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static boolean gameOver;
	static StringBuilder sb;
	static char[][] map;
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] visited;
	
	
	private static boolean isOutOfRange(int r, int c) {
		return 0>r || r>=R || 0>c || c>=C;
	}
	
	public static class Kid {
		
		int row;
		int col;
		int dr;
		int dc;
		
		public Kid(int row, int col) {
			this.row = row;
			this.col = col;
			this.dr = 0;
			this.dc = 0;
			// 집 갱신
			visited[row][col] = true;
		}
		
		private void makeNewDirect(char blockType, int findDr, int findDc) {
			
			row += findDr;
			col += findDc;
			
			switch (blockType) {
			
				// 방향을 바꾸지 않는 블록들
				case '|':
				case '-':
				case '+':
					dr = findDr;
					dc = findDc;
					break;
					
				// 방향이 바뀌는 블록들
				case '1':
					if(findDr == -1) {
						dc = 1;
					} else {
						dr = 1;
					}
					break;
				case '2':
					
					if(findDr == 1) {
						dc = 1;
					} else {
						dr = -1;
					}
					
					break;
				case '3':
					
					if(dr == 1) {
						dc = -1;
					} else {
						dr = -1;
					}
					break;
					
				case '4':
					if(findDr == -1) {
						dc = -1;
					} else {
						dr = 1;
					}
					break;
			}
			
		}
		private boolean needChangeDirect() {
			
			if(!isOutOfRange(row, col) && !noChangeDirection(row, col) ) {
				return true;
			}
			
			return false;
		}
		
		private void changeDirect(char blockType, int findDr, int findDc) {
			
			switch (blockType) {
				
				case '1':
					if(dr == -1) {
						dr = 0;
						dc = 1;
					} else {
						dr = 1;
						dc = 0;
					}
					break;
				case '2':
					if(dr == 1) {
						dr = 0;
						dc = 1;
					} else {
						dr = -1;
						dc = 0;
					}
					break;
				case '3':
					if(dr == 1) {
						dr = 0;
						dc = -1;
					} else {
						dr = -1;
						dc = 0;
					}
					break;
				case '4':
					if(dr == -1) {
						dr = 0;
						dc = -1;
					} else {
						dr = 1;
						dc = 0;
					}
					break;
			}
		}
		
		private boolean noChangeDirection(int r, int c) {
			
			return map[r][c] == '-' || map[r][c] == '|' || map[r][c] == '+';
		}
		
		private void findBlock() {
			changeDirect(map[row][col], dr, dc);
		}
		
		private void move() {
			
			this.row += dr;
			this.col += dc;
			
			if(map[row][col] != '+') visited[row][col] = true;
			
			// 지워진 블록 발견
			if(map[row][col] == '.') {
				recoverBlock();
				gameOver = true;
			}
		}
		
		
		private void recoverBlock() {
			
			
			int homeCount = 0;
			boolean[] roadCheck = new boolean[4];
			for(int i=0; i<4; i++) {
				int[] d = deltas[i];
				
				int nr = row + d[0];
				int nc = col + d[1];
				
				if(isOutOfRange(nr, nc) || map[nr][nc] == '.') continue;
				
				if(map[nr][nc] == 'Z' || map[nr][nc] == 'M') homeCount++;
				
				if(i == 0 && map[nr][nc] != '|' && map[nr][nc] != '1' && map[nr][nc] != '2') roadCheck[i] = true;
				else if(i == 1 && map[nr][nc] != '|' && map[nr][nc] != '3' && map[nr][nc] != '4') roadCheck[i] = true;
				else if(i == 2 && map[nr][nc] != '-' && map[nr][nc] != '1' && map[nr][nc] != '4') roadCheck[i] = true;
				else if(i == 3 && map[nr][nc] != '-' && map[nr][nc] != '2' && map[nr][nc] != '3') roadCheck[i] = true;
				
			}
			
			char lostBlock = '.';
			if(roadCheck[0] && roadCheck[1] && roadCheck[2] && roadCheck[3]) {
				if(homeCount != 2) lostBlock = '+';
				else if(map[row + deltas[0][0]][col + deltas[0][1]] == 'M' || map[row + deltas[0][0]][col + deltas[0][1]] == 'Z') {
					lostBlock = '|';
				} else {
					lostBlock = '-';
				}
				
			}
			else if(roadCheck[0] && roadCheck[1]) lostBlock = '-';
			else if(roadCheck[0] && roadCheck[2]) lostBlock = '1';
			else if(roadCheck[0] && roadCheck[3]) lostBlock = '2';
			else if(roadCheck[1] && roadCheck[2]) lostBlock = '4';
			else if(roadCheck[1] && roadCheck[3]) lostBlock = '3';
			else if(roadCheck[2] && roadCheck[3]) lostBlock = '|';
			sb.append(row+1).append(' ').append(col+1).append(' ').append(lostBlock);
			return;
			
		}
	}
	

	public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			sb = new StringBuilder();
		
			// flag 
			gameOver = false;
			
			
			// 초기값 세팅
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			visited = new boolean[R][C];
			
			
			Kid myKid = null;
			
			for(int i=0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j<C; j++) {
					if(map[i][j] == 'M') {
						// myKid 객체 생성
						myKid = new Kid(i, j);
					}
				}
			}
			
			for(int[] d: deltas) {
				
				int nr = myKid.row + d[0];
				int nc = myKid.col + d[1];
				
				if(isOutOfRange(nr, nc) || map[nr][nc] == '.' || map[nr][nc] == 'Z') continue;
				
				myKid.makeNewDirect(map[nr][nc], d[0], d[1]);
				
				break;
			}
			
			
			// 블록 찾기 시작
			while(!gameOver) {
				
				myKid.move();
				if(myKid.needChangeDirect()) {
					myKid.findBlock();
				} 
				
			}
		
			System.out.println(sb);
			}
}