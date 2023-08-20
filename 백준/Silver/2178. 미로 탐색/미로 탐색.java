import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int map[][], deltas[][] = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	private static boolean visited[][];
	private static void bfs() {
		int ans = 0;
		Queue<int[]> bfsQueue = new ArrayDeque<int[]>();
		
		bfsQueue.add(new int[] {1, 1});
		
		while(!bfsQueue.isEmpty()) {
			ans++;
			int qSize = bfsQueue.size();
			for(int i=0; i<qSize; i++) {
				int[] temp = bfsQueue.poll();
				int r = temp[0];
				int c = temp[1];
				
				visited[r][c] = true;
				
				for(int[] d : deltas) {
					int nr = r + d[0];
					int nc = c + d[1];
					
					if(!visited[nr][nc] && map[nr][nc] == 1) {
						
						if(nr == N && nc == M) {
							System.out.println(ans+1);
							return;
						}
						
						bfsQueue.add(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];
		visited = new boolean[N+2][M+2];
		for(int i=1; i<=N; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1] - '0';
			}
		}
		bfs();
	}
}
