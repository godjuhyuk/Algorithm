import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
	private static int N, M, K, ans, flag = -1;
	private static int[][] visited, deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new int[N][M];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			visited[r-1][c-1] = 1;
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++){
				if(visited[r][c] == 1) {
					bfs(r, c);
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	private static void bfs(int row, int col) {
		
		int sum = 1;
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {row, col});
		visited[row][col] = flag;
		
		while(!q.isEmpty()){
			int[] temp = q.poll();
			int tr = temp[0];
			int tc = temp[1];
			
			for(int[] d : deltas) {
				int nr = tr + d[0];
				int nc = tc + d[1];
				
				if(isOutOfRange(nr, nc) || visited[nr][nc] != 1){
					continue;
				}
				
				visited[nr][nc] = flag;
				q.offer(new int[] {nr, nc});
				sum++;
				
			}
			
		}
		
		if(ans < sum) ans = sum;
		
		flag--;
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c>=M;
	}
}