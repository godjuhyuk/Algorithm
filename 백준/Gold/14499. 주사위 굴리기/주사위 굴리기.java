import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int BOTTOM = 3, UP = 6;
	private static int N, M, R, C, K;
	private static int[][] map, deltas = {null, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; 
	private static StringBuilder sb = new StringBuilder();
	
	static class Dice {
		
		int r;
		int c;
		int[] diceInfo = {0, 0, 0, 0, 0, 0, 0};
		
		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void roll(int deltaIdx) {
			
			int nr = r + deltas[deltaIdx][0];
			int nc = c + deltas[deltaIdx][1];
			
			if(isOutOfRange(nr, nc)) return;
			
			switch (deltaIdx) {
			case 1: {
				rollRight();
				break;
			}
			case 2: {
				rollLeft();	
				break;
						}
			case 3: {
				rollUp();
				break;
			}
			case 4: {
				rollDown();
				break;
			}
			}
			
			if(map[nr][nc] == 0) {
				map[nr][nc] = diceInfo[BOTTOM];
			} else {
				
				diceInfo[BOTTOM] = map[nr][nc];
				map[nr][nc] = 0;
				
			}
			sb.append(diceInfo[UP]).append('\n');
			r = nr;
			c = nc;
			
		}

		private boolean isOutOfRange(int nr, int nc) {
			return nr < 0 || nr >= N || nc < 0 || nc >= M;
		}

		private void rollDown() {
			int temp = diceInfo[1];
			diceInfo[1] = diceInfo[3];
			diceInfo[3] = diceInfo[5];
			diceInfo[5] = diceInfo[6];
			diceInfo[6] = temp;
		}

		private void rollUp() {
			int temp = diceInfo[6];
			diceInfo[6] = diceInfo[5];
			diceInfo[5] = diceInfo[3];
			diceInfo[3] = diceInfo[1];
			diceInfo[1] = temp;
		}

		private void rollLeft() {
			int temp = diceInfo[6];
			diceInfo[6] = diceInfo[4];
			diceInfo[4] = diceInfo[3];
			diceInfo[3] = diceInfo[2];
			diceInfo[2] = temp;
		}

		private void rollRight() {
			int temp = diceInfo[2];
			diceInfo[2] = diceInfo[3];
			diceInfo[3] = diceInfo[4];
			diceInfo[4] = diceInfo[6];
			diceInfo[6] = temp;
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		Dice dice = new Dice(R, C);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(K-- > 0) {
			int cmd = Integer.parseInt(st.nextToken());
			dice.roll(cmd);
		}
		
		System.out.println(sb);
		
	}
	
}
