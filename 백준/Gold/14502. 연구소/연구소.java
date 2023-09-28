import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 
 * N*M 직사각형이며, 빈칸과 벽으로 이루어져있다.
 * 일부 칸ㅇ은 바이러스가 존재하여, 상하좌우로 퍼져나간다.
 * 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야한다.  
 * 
 * 
 * 벽 3개를 추가로 세워서
 * 안전 영역 크기의 최댓값을 구해야한다.
 * 
 * 문제 해결을 위한 고민)
 * 
 * 벽을 어떻게 세울 것인가?
 * 
 * 64 C 3으로 벽을 세우고 안전 영역 크기를 구하며 갱신?
 * 
 * 로직)
 * 
 * 1. 벽을 세울 수 있는 후보군을 List로 모아놓는다 + 바이러스 위치 정보도 모아놓자.
 * 2. List 중 조합(재귀)으로 3개 뽑고 안전영역 카운트 후 갱신
 * 2-1) 맵을 되돌릴 때는 세웠던 벽만 되돌리면 된다.
 * 2-2) 퍼져있는 바이러스 같은 경우엔 시작점만 모아놓으면 된다. (되돌릴 필요없음)
 * 
 * 
 * @author SSAFY
 *
 */
public class Main {

	private static int ans, N, M, turn = 2;
	private static int[][] map, deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static boolean[][] visited;
	
	private static Air[] wallCombArr;
	private static List<Air> airList;
	private static List<Virus> virusList;
	
	static class Virus {
		int r;
		int c;
		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		// 확산 bfs
		private int spread() {

			int cnt = 0;
			
			Queue<Virus> q = new ArrayDeque<Virus>();
			
			q.add(new Virus(this.r, this.c));
			
			while(!q.isEmpty()) {
				
				Virus virus = q.poll();
				
				for(int[] d: deltas) {
					int nr = virus.r + d[0];
					int nc = virus.c + d[1];
					
					if(isOutOfRange(nr, nc) || map[nr][nc] == 1 || map[nr][nc] == turn) continue;
					
					map[nr][nc] = turn;
					q.offer(new Virus(nr, nc));
					cnt++;
					
				}
				
			}
			
			return cnt;
		}

		private boolean isOutOfRange(int r, int c) {
			return r < 0 || r >= N || c < 0 || c >= M;
		}
		
	}
	
	static class Air {
		int r;
		int c;
		public Air(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = N*M+1;
		map = new int[N][M];
		visited = new boolean[N][M];
		
		wallCombArr = new Air[3];
		airList = new ArrayList<Air>();
		virusList = new ArrayList<Virus>();

		
		int airCount = 0;
		
		// 입출력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				
				map[i][j] = Integer.parseInt(st.nextToken());
				switch (map[i][j]) {
				case 0:
					airList.add(new Air(i, j));
					airCount++;
					break;
				case 2:
					virusList.add(new Virus(i, j));
					break;
				}
			}
		}
		
		wallComb(0, 0);
		
		System.out.println(airCount-ans-3);
	}
	
	private static void wallComb(int start, int cnt) {
		
		// 기저 조건
		if(cnt == 3) {
			ans = Math.min(ans, countVirus());
			turn++;
			return;
		}
		
		// 유도 파트
		
		for(int i=start; i<airList.size(); i++) {
			
			Air air = airList.get(i);
			
			if(!visited[air.r][air.c]) {
				visited[air.r][air.c] = true;
				wallCombArr[cnt] = air;
				wallComb(i+1, cnt+1);
				visited[air.r][air.c] = false;
			}
			
		}
		
	}
	
	private static int countVirus() {
		
		int cnt = 0;
		
		// 벽 세우기
		for(Air air : wallCombArr) {
			map[air.r][air.c] = 1;
		}
		
		// turn 갱신
		for(Virus virus : virusList) {
			map[virus.r][virus.c] = turn;
		}
		
		for(Virus virus : virusList) {
			cnt += virus.spread();
		}
		
		// 벽 제거
		for(Air air : wallCombArr) {
			map[air.r][air.c] = 0;
		}
		
		return cnt;

	}
	
}