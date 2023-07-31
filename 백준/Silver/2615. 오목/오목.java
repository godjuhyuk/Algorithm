import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	static boolean gameOver = false;
	static int[][] map = new int[20][20];
	static boolean[][] visited = new boolean[20][20];
	
	// 0,0 에서 18, 18까지 가므로 위를 탐색할 필요 없이 아래부분과 오른쪽 방향만 보면 된다
	static int[][] deltas = {{1, 0}, {1, 1}, {1, -1}, {0, 1}};
	public static boolean isOutOfRange(int r, int c) {
		return r<=0 || r>19 || c<=0 || c>19;
	}
	public static void dfs(int r, int c, int cnt, int searchColor, int[] delta) {
			
			if(cnt > 5) {
				return;
			}
		
			// cnt 만큼 이동한 돌이 찾는 색깔인지 체크
			int nextRow = r + cnt * delta[0];
			int nextCol = c + cnt * delta[1];
			
			if(isOutOfRange(nextRow, nextCol) || map[nextRow][nextCol] != searchColor) {
				// 범위 밖이거나 찾는 색이 아니면 return
				if(cnt == 5) {
					
					// 반대 방향 확인 - delta의 반대 방향이 만약 찾던 돌이라면 6목이므로 게임 종료 X
					int testRow = r - delta[0];
					int testCol = c - delta[1];
					if(isOutOfRange(testRow, testCol) || map[testRow][testCol] != searchColor) {
						// 게임이 끝났으므로 true
						gameOver = true;
						System.out.println(searchColor);
						if(delta[0] == 1 && delta[1] == -1) {
							System.out.println(r+4*delta[0] + " " + (int)(c + 4*delta[1]));
						} else {
							System.out.println(r + " " + c);
						}
					}
				}
				return;
			}
			dfs(r, c, cnt+1, searchColor, delta);
		}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1; i<=19; i++) {
			String[] input = br.readLine().split(" ");
			for (int j=1; j<=19; j++) {
				map[i][j] = Integer.parseInt(input[j-1]);
			}
		}
		for(int i=1; i<=19; i++) {
			for(int j=1; j<=19; j++) {
				// 한번 정한 방향으로 쭉 가야하므로 delta마다 dfs
				for(int[] delta: deltas) {
					if(map[i][j] == 1 && !gameOver) {
						dfs(i, j, 1, 1, delta);
					}
					else if(map[i][j] == 2 && !gameOver) {
						dfs(i, j, 1, 2, delta);
					}
				}
				if(gameOver) {
					return;
				}
			}
		}
		
		// 게임이 끝나지 않았다면
		if(!gameOver) {
			System.out.println(0);
		}
	}
}
