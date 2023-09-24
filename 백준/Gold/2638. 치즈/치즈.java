import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * 문제 해석)
 * N*M 모눈종이 위에 치즈가 표시되어있음.
 * 치즈 격자 4방탐색 시 2칸 이상이 빈칸(0)이면 한시간만에 사라진다.
 * 
 * 단, 치즈가 둘러싼 내부 공기는 카운트하지않는다.
 * 
 * 문제 해결을 위한 고민)
 * 
 * 치즈가 둘러싼 내부 공기를 어떻게 체크할까 ?
 * => 치즈카운트를 계속 가져감 
 * => 턴마다 0,0에서 시작하는 DFS가 외부공기값을 -1로 바꿈
 * => 그 후 치즈마다 돌면서 녹는지 확인
 * => 치즈는 동시에 녹아야한다.
 * 
 *  치즈 관리를 어떤 자료구조로?
 *  cheese 객체를 관리, 녹으면 List에 넣어둔 후 턴 마지막에 map값 갱신 
 * 
 */
public class Main {
	
	private static int N, M, cheeseCount, time;
	private static int[][] map, deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static boolean[][] visited;
	private static List<Cheese> cheeseList;
	
	public static class Air { 
		int r;
		int c;
		
		public Air(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static class Cheese { 
		int r;
		int c;
		
		public Cheese(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		cheeseList = new ArrayList<Cheese>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					cheeseCount++;
					cheeseList.add(new Cheese(i, j));
				}
				
			}
		}
		
		checkOutsideAir(0, 0);
		
		while(cheeseCount>0) {
			time++;
			meltCheese();
		}
		System.out.println(time);
	}
	
	private static void checkOutsideAir(int r, int c) {
		
		visited[r][c] = true;
		map[r][c] = -1;
		
		for(int[] delta : deltas) {
			
			int nr = r + delta[0];
			int nc = c + delta[1];
			
			if(isOutOfRange(nr, nc) || map[nr][nc] == 1 || visited[nr][nc]) {
				continue;
			}
			
			checkOutsideAir(nr, nc);
		}

	}
	
	private static void meltCheese() {
		
		List<Cheese> aliveCheese = new ArrayList<Cheese>();
		
		for(Cheese cheese : cheeseList) {
			
			int isMelting = 0;
			for(int[] delta : deltas) {
				
				int nr = cheese.r + delta[0];
				int nc = cheese.c + delta[1];
				
				if(map[nr][nc] < 0) {
					isMelting++;
				}
				
			}
			
			if(isMelting < 2) {
				aliveCheese.add(cheese);
			} else {
				map[cheese.r][cheese.c] = 2; // 녹은 치즈 마킹
			}
			
		}
		
		List<Air> airList = new ArrayList<Air>();
		for(Cheese cheese : cheeseList) {
			if(map[cheese.r][cheese.c] == 2) {
				cheeseCount--;
				map[cheese.r][cheese.c] = -1;
				airList.add(new Air(cheese.r, cheese.c));
			} 
		}
		
		for(Air air : airList) {
			checkOutsideAir(air.r, air.c);
		}
		
		cheeseList = aliveCheese;
	}
		
		

	
	private static boolean isOutOfRange(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;

	}

}