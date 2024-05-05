import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작 시간: 오후 9시 54분
 * 
 * 문제 해섞)
 * - N*M 보드와 4개의 버튼
 * - 각각의 칸은 비어있거나, 벽
 * - 두 개의 빈칸에는 동전이 하나씩 놓여있다
 * - 버튼은 위, 오, 왼, 아
 * - 버튼을 누르면 두 동전이 동시에 이동한다
 * 		- 이동하려는 칸이 벽이면, 이동하지 않는다
 * 		- 칸이 없으면, 보드 바깥으로 떨어진다
 * 		- 그 외에는 한칸 이동한다. 이동하려는 칸에 동전이 있는 경우에도 한칸 이동한다.
 * 
 * 두 동전 중 하나만 보드엣 ㅓ떨어뜨리기 위해 버튼을 쵯 ㅗ몇번 눌러야하는지 구행햐ㅏㄴ다.
 * 첫째 줄에 두 동전 중 하나만 보드에서 떨어뜨리기 위해 눌러야 하는 버튼의 최소 횟수를 출력한다. 
 * 만약, 두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 한다면, -1을 출력한다.
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	private static class Coin {
		
		int r;
		int c;
		
		public Coin(int r, int c) {	
			this.r = r;
			this.c = c;
		}
		
	}
	
	private static int N, M;
	private static int[][] deltas = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	private static boolean[][][][] visited;
	private static char[][] map;
	
	public static void main(String[] args) throws IOException { 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		Coin[] arr = {null, null};
		
		
		for(int i=0, idx=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'o') {
					arr[idx++] = new Coin(i, j);
				}
			}
		}
		
		System.out.println(gameBFS(arr));
		
	}

	private static int gameBFS(Coin[] arr) {
		
		Queue<Coin[]> q = new ArrayDeque<Coin[]>();
		q.offer(arr);
		
		int turn = 0;
		while(turn < 10 && !q.isEmpty()) {
			
			int qSize = q.size();
			turn++;
			for (int k=0; k<qSize; k++) {
				Coin[] temp = q.poll();
				for(int[] d : deltas) {	
					
					int nr1 = temp[0].r + d[0];
					int nc1 = temp[0].c + d[1];
					
					int nr2 = temp[1].r + d[0];
					int nc2 = temp[1].c + d[1];
					
					if(isOutOfRange(nr1, nc1) && isOutOfRange(nr2, nc2)) continue;
					else if(isOutOfRange(nr1, nc1) || isOutOfRange(nr2, nc2)) {
						return turn;
					}
					
					if(visited[nr1][nc1][nr2][nc2]) continue;
					
					if(map[nr1][nc1] == '#') {
						nr1 = temp[0].r;
						nc1 = temp[0].c;
					}
					
					if(map[nr2][nc2] == '#') {
						nr2 = temp[1].r;
						nc2 = temp[1].c;
					}
					
					visited[nr1][nc1][nr2][nc2] = true;
					
					q.offer(new Coin[] {new Coin(nr1, nc1), new Coin(nr2, nc2)});
					
				}
			}
		}
		
		return -1;
		
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || c < 0 || r>=N || c>=M;
	}
	
}
