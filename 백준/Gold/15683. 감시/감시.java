import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, ans;
	private static int[][] map, deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	private static List<CCTV> cctvList;
	private static CCTV[] cctvFactorialList;
	
	private static boolean isOutOfRange(int r, int c) {
		return 0>r || r>=N || 0 > c || c >= M;
	}
	
	
	public static class CCTV {
		int row;
		int col;
		int type;
		int shootCount;
		int[] deltaIdx;
		public CCTV(int r, int c, int type) {
			this.row = r;
			this.col = c;
			this.type = type;
			makeDeltaByType();
		}
		
		private void makeDeltaByType() {
			
			switch (this.type) {
			case 1:
				deltaIdx = new int[] {0};
				this.shootCount = 4;
				break;
			case 2:
				deltaIdx = new int[] {0, 2};
				this.shootCount = 2;
				break;
			case 3:
				deltaIdx = new int[] {0, 1};
				this.shootCount = 4;
				break;
			case 4:
				deltaIdx = new int[] {0, 1, 2};
				this.shootCount = 4;
				break;
			case 5:
				deltaIdx = new int[] {0, 1, 2, 3};
				this.shootCount = 1;
				break;
	
			}
		}
		
		private void rotate() {
			for(int i=0; i<deltaIdx.length; i++) {
				deltaIdx[i] = (deltaIdx[i]+1) % 4; 
			}
		}
		
		private void shoot() {
			
			for(int d: deltaIdx) {
				int nr = this.row + deltas[d][0];
				int nc = this.col + deltas[d][1];
				while(!isOutOfRange(nr, nc) && map[nr][nc] != 6) {
					
					if(map[nr][nc] > 0) {
						nr += deltas[d][0];
						nc += deltas[d][1];
						continue;
					};
					
					map[nr][nc] = -1;
					
					nr += deltas[d][0];
					nc += deltas[d][1];
					
				}
			}

		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cctvList = new ArrayList<CCTV>();
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) ans++;
				else if(map[i][j] < 6){
					cctvList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		cctvFactorialList = new CCTV[cctvList.size()];
		
		recursive(0);
		System.out.println(ans);
		
		 
	}
	
	private static void recursive(int depths) {
		
		if(depths == cctvList.size()) {
			shootAllCCTV();
			return;
		}
		
		
		for(int i=0; i< cctvList.get(depths).shootCount; i++) {
			cctvFactorialList[depths] = cctvList.get(depths);
			recursive(depths+1);
			cctvList.get(depths).rotate();
		}
		
		
	}
	
	private static void shootAllCCTV() {
		
		for(CCTV cctv : cctvFactorialList) {
			cctv.shoot();
		}
		ans = Math.min(ans, countAns());
	}
	
	private static int countAns() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -1) {
					
					map[i][j] = 0;
				}
                else if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
	
}