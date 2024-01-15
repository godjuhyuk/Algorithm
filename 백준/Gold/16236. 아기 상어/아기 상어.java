import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * N x N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다.
 * 공간은 1X1 크기의 정사각형 칸으로 나누어져 있다.
 * 
 * 처음 아기 상어 크기는 2이고
 * 1초에 상하좌우로 인접한 한 칸씩 이동한다.
 * 
 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고,
 * 나머지 칸은 모두 지나갈 수 있다.
 * 또한, 아기 상어는 자신의 크기보다 "작은" 물고기만 먹을 수 있다.
 * 
 * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
	먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
	먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
	거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
	거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
	아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.
	
	아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
	
	공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
 * 
 * 문제 해결을 위한 고민)
 * 
 * 1. 거리 계산
 * 거리 d = |x1 - x2| + |y1 - y2|
 * 
 * 2. 먹을 수 있는 물고기는 완탐으로 체크? => YES
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int N, ans, turn;
	private static Shark shark;
	private static int[][] map, visited;
	private static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	public static class Shark implements Comparable<Shark>{
		
		int r;
		int c;
		int weight;
		int eats;
		
		public Shark(int r, int c, int weight, int eats) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.eats = eats;
		}
		
		private void eat() {
			eats++;
			if(weight == eats) {
				weight++;
				eats = 0;
			}
		}
		
		public int compareTo(Shark o) {
			
			if(this.r == o.r) return this.c - o.c;
			return this.r - o.r;
		};
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		
		shark = null;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Shark(i, j, 2, 0);
				}
			}
		}
		
		while(true) {
			
			// 먹을 수 있는 물고기 없는 경우 GameOver
			if(findFish()) break;
			if(sharkBFS()) break;
			turn++;
		}
		
		System.out.println(ans);
		
	}

	private static void print() {
		System.out.println("=========" + shark.r + " " + shark.c +  " " + shark.weight + " " + ans +  "==========");
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

	private static boolean findFish() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] > 0 && map[i][j] < shark.weight) return false;
			}
		}
		return true;
	}

	private static boolean sharkBFS() {
		
		int time = 0;
		Queue<int[]> q = new ArrayDeque<int[]>();
		visited[shark.r][shark.c] = turn+1;
		q.offer(new int[] {shark.r, shark.c});
		boolean bfsOver = false;
		
		List<Shark> eatCandidates = new ArrayList<Shark>();
		
		while(!q.isEmpty()) {

			if(bfsOver) break;
			
			int qSize = q.size();
			time++;
			for(int i=0; i < qSize; i++) {
				int[] temp = q.poll();
				
				for(int[] d: deltas) {
					int nr = temp[0] + d[0];
					int nc = temp[1] + d[1];
					
					if(isOutOfRange(nr, nc) || visited[nr][nc] == turn + 1) continue;
					
					visited[nr][nc] = turn+1;
					
					if(map[nr][nc] == 0 || map[nr][nc] == shark.weight) q.offer(new int[] {nr, nc});
					else if(map[nr][nc] < shark.weight) {
						eatCandidates.add(new Shark(nr, nc, -1, -1));
						bfsOver = true;
					} 
					
				}
			}
		}
		
		if(bfsOver) {
			Collections.sort(eatCandidates);
			Shark tempFish = eatCandidates.get(0);
			shark.eat();
			shark.r = tempFish.r;
			shark.c = tempFish.c;
			map[shark.r][shark.c] = 0;
			ans += time;
			return false;
		}
		
		return true;
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= N;
	}
}
