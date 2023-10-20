import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제 시작: 12시 2분
 * N by N의 땅이 주어짐
 * 
 * 인구이동이 끝날때까지 계속 완탐을 조져야한다.
 * 2000 * (50 * 50 + BFS)
 * 
 * visited 으로 처리해가며 인구이동 실시
 * 
 * 인구수 반영 어떻게?
 * 인구이동할 영역들을 어떻게 모아놓는담
 * 스택 or List? 스택으로 해보자 오랜만에.
 * 
 * 
 */
public class Main {

	private static int N, L, R, ans;
	private static int[][] map;
	private static int[][] visited;
	private static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean gameOver = false;
		visited = new int[N][N];
		while(!gameOver) {
			gameOver = true;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == ans && openBorderBFS(i, j, 0)) {
						gameOver = false;
					}
				}
			}
			if(!gameOver) ans++;
		}
		
		System.out.println(ans);
		
		
	}

	private static boolean openBorderBFS(int r, int c, int sum) {
		
		List<int[]> list = new ArrayList<int[]>();
		Queue<int[]> q = new ArrayDeque();
		
		q.add(new int[] {r, c});
		list.add(new int[] {r, c});
		sum += map[r][c];
		visited[r][c]= ans+1;
		
		while(!q.isEmpty()) {
			
			int[] temp = q.poll();
			for(int[] d : deltas ) {
				
				int nr = temp[0] + d[0];
				int nc = temp[1] + d[1];
				
				if(isOutOfRange(nr, nc) || visited[nr][nc] == ans+1) continue;
				
				if(isOpenBorder(map[nr][nc], map[temp[0]][temp[1]])) {
					visited[nr][nc] = ans+1;
					list.add(new int[] {nr, nc});
					q.add(new int[] {nr, nc});
					sum += map[nr][nc];
				}
				
			}
			
			
		}
		
		if(list.size() == 1) {
			visited[r][c] = ans;
			return false;
		}
		int newPopulation = sum / list.size();
		
		for(int[] temp : list) {
			map[temp[0]][temp[1]] = newPopulation;
		}
		
		return true;
	}

	private static boolean isOpenBorder(int p1, int p2) {
		
		int diff = Math.abs(p1 - p2);
		return L <= diff && diff <= R;
	
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c>=N;
	}
	
}
