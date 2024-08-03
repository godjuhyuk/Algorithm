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
 * 문제 해석)
 * 바이러스는 활성/비활성 상태가 있음
 * 
 * 가장 처음 모든 바이러스는 비활성 상태임.
 * 활성 상태인 바이러스는 상하좌우로 인접한 모든 빈칸으로 동시 복제되며 1초가 걸림.
 * 연구소의 바이러스 M개를 활성 상태로 변경함.
 * 
 * 크기가 N x N인 정사각형으로 나타낼 수 있으며, 정사각형은 1x1 크기임.
 * 연구소는 '빈 칸', '벽', '바이러스'로 이루어져 있음.
 * 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변함.,
 * 
 * 연구소의 상태가 주어졌을 때, 모든 빈칸에 바이러스 퍼뜨리는 최소 시간을 구하는 문제
 * 
 * 문제 입력)
 * 4 <= N <= 50
 * 1 <= M <= 10
 * 
 * N개의 줄에 연구소 상태가 주어짐.
 * 0 : 빈 칸
 * 1 : 벽
 * 2 : 비활성 바이러스의 위치 ( M <= 2의 개수 <= 10)
 * 
 * 문제 해결을 위한 고민)
 * 
 * 1. 완탐?
 * 
 * 10 C 5 * 50 * 50
 * 
 * 로직)
 * 
 * 1. Virus를 다 모아놓는다.
 * Virus:
 * 		- location
 * 		- active Y/N
 * 
 * 2. 바이러스 총 개수 C M 을 뽑아서 완탐 조져본다.
 * - 중간 중간 best case보다 늦어지면 그만두기
 * 
 * 3. 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우
 * => 빈칸 개수 다 세고 마지막에 얼마나 감염시켰는지 카운트해서?
 * => ㄱㄱ

 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static final int BLANK = 0, WALL = 1 , VIRUS = 2;
	private static int[] combArr;
	private static int[][] map, deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private static boolean[] isSelected;
	private static boolean[][] visited;
	private static int ans = Integer.MAX_VALUE;
	private static int N, M, blankTotalCount;
	private static List<Virus> virusList;
	private static class Virus {
		
		int r;
		int c;
//		boolean active;
		
		public Virus(int r, int c) {
			super();
			this.r = r;
			this.c = c;
//			this.active = active;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virusList = new ArrayList<Virus>();
		
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == BLANK) blankTotalCount++;
				else if(map[r][c] == VIRUS) virusList.add(new Virus(r, c));
				
			}
		}
		
		if(blankTotalCount == 0) {
			System.out.println(0);
			return;
		}
		
		combArr = new int[M];
		isSelected = new boolean[virusList.size()];
		
		comb(0, 0);
		
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
	}

	private static void comb(int selectCount, int start) {
		
		// 기저 조건
		if(selectCount == M) {
//			System.out.println(Arrays.toString(combArr));
			spreadBFS();
			return;
		}
		
		// 유도 파트
		for(int i=start; i<virusList.size(); i++) {
			
			if(!isSelected[i]) {
				isSelected[i] = true;
				combArr[selectCount] = i;
				comb(selectCount+1, i);
				isSelected[i] = false;
			}
			
		}
		
		
		
	}

	private static void spreadBFS() {
		
		visited = new boolean[N][N];
		int time = 0, spreadCount = 0;
		
		Queue<Virus> q = new ArrayDeque<Virus>();
		for(int idx : combArr) {
			Virus tempVirus = virusList.get(idx);
			// 상태 변화 신경쓰기
//			tempVirus.active = true;
			visited[tempVirus.r][tempVirus.c] = true;
			q.offer(tempVirus);
		}
		
		while(!q.isEmpty()) {
//			print();
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				Virus tempVirus = q.poll();
				
				for(int[] d: deltas) {
					
					int nr = tempVirus.r + d[0];
					int nc = tempVirus.c + d[1];
					
					if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == WALL) continue;
					
					visited[nr][nc] = true;
					
					// 일단 그냥 해보자
					if(map[nr][nc] == BLANK) spreadCount++;
					q.offer(new Virus(nr, nc));
					
				}
				
			}
			if(spreadCount == blankTotalCount) {
				if(time < ans) ans = time + 1;
				break;
			}
			time++;
			if(ans < time) return;
		}
		
		// 끝
//		for(Virus virus : virusList) virus.active = false;
		
		
		
	}

	private static void print() {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]) {
					System.out.print('X');
				}
				else if(map[i][j] == WALL) System.out.print('W');
				else System.out.print('O');
			}
			System.out.println();
		}
		System.out.println("==============");
		
	}

	private static boolean isOutOfRange(int r, int c) {
		// TODO Auto-generated method stub
		return r < 0 || c < 0 || r >= N || c >= N;
	}
}
