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
 * 
 * 설꼐 시작 시간 : 오후 7시 36분
 * 
 * 문제 해석)
 * 2차원ㅂ ㅐ열에 빙산 표시
 * 배열에 양의 정수로 각 부분 별 높이가 저장
 * 빙산ㅇ ㅣ외 바다에는 0임 
 * 
 * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어든다.
 * 배열에서 빙산의 각 부분에 해당되는 칸의 높이는 
 * 일년마다 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
 * 0보다 더 줄아들지는 않음.
 * 
 * 한 덩어리의 빙산이 주어질때,
 * 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간을 구하는 프로그램 작성
 * 
 * 입력)
 * 행과 열이 한 개의 빈칸을 사이에 두고 주어짐.
 * 3<=N, M<=300
 * N개의 줄에는 M개씩 값이 들어옴 (0<=x <= 10)
 * 배열에서 빙산이 들어가는 칸의 개수는 10000개 이하
 * 
 * 첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 
 * 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.
 * 
 * 문제 해결을 위한 고민)
 * 0. 산과 바다중 무엇을 추적해야하는가?
 * => 10000개밖에 안되므로 산을 추적
 * 
 * 1. 1년이 지날때마다 분리되었는지 확인해야하는가?
 * => yes, 어차피 max가 10초밖에 안됨
 * 
 * 2. level별 bfs로 풀어볼까?
 * => Mountain 객체 생성
 * Mountain {
 * 	
 * 	row : r,
 * col : c
 * 	 height : x,
 * }
 * 
 * 3. 분리되었는지 확인하는 로직은?
 * 한 곳에서 dfs를 통해 육지 전부 확인하면서 count 후,
 * 남아있는 산의 개수와 다르면 분리 완료.
 *  
 * 
 */
public class Main {

	private static int MELTED = -9999999;
	private static int N, M, year, mountainCnt, tempCount;
	private static int map[][], deltas[][] = new int[][] {{0, 1}, {1, 0},{0,-1},{-1, 0}};
	private static boolean[][] visited;
	
	public static class Mountain {
		int r;
		int c;
		int height;
		public Mountain(int r, int c, int height) {
			this.r = r;
			this.c = c;
			this.height = height;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; 
		Queue<Mountain> mq = new ArrayDeque();
		
		// 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 산 정보 입력
				if(map[i][j] != 0) {
					mq.offer(new Mountain(i, j, map[i][j]));
					mountainCnt++;
				}
			}
		}
		
		// 시간 지날때마다 녹는 bfs
		bfs(mq);
		
		
	}
	
	 private static void bfs(Queue<Mountain> mq) {
		 while(!mq.isEmpty()) {
			 year++;
			 MELTED++;
			 int qSize = mq.size();
			 int[][] mapInfo = new int[qSize][3];
			 for(int i=0; i<qSize; i++) {
				 
				 Mountain mt = mq.poll();
				 
				 
				 // 빙산이 다 녹았다면 주변 순회
				 for(int[] d: deltas) {
					 
					 int nr = mt.r + d[0];
					 int nc = mt.c + d[1];
					 
					 if(map[nr][nc] > 0 || map[nr][nc] == MELTED) continue;
					 mt.height--;
				 }
				 map[mt.r][mt.c] = mt.height > 0 ? mt.height : MELTED;
				 // 산 높이 갱신
				 if(mt.height <= 0) mountainCnt--;
				 else mq.offer(mt);
				 
			 }
			 
			 // 다 녹았으면 dfs로 분리 여부 확인
			 if(mq.size() == 0) {
				 System.out.println(0);
				 return;
			 } else {
				 
				 
				 tempCount = 0;
				 Mountain tempMountain = mq.peek();
				 visited = new boolean[N][M];
				 visited[tempMountain.r][tempMountain.c] = true;
				 dfs(tempMountain.r, tempMountain.c);
				 if(tempCount < mountainCnt) {
					 System.out.println(year);
					 return;
				 }
			 }
			 
			 
		 }
		 
	}
	 
	 private static void dfs(int r, int c) {
		 tempCount++;
		 for(int[] d: deltas) {
			 
			 int nr = r + d[0];
			 int nc = c + d[1];
			 
			 if(visited[nr][nc] || map[nr][nc] <= 0) continue;
			 visited[nr][nc] = true;
			 dfs(nr, nc);
		 }

	}
	 
}