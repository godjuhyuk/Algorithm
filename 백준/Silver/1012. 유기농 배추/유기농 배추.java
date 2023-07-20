import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class Main {
	static int N, M, K;
	static int cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = {{1,0},{-1, 0},{0, 1},{0, -1}};
	public static boolean isInRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	public static void dfs(int x, int y) {
		for(int i=0; i<delta.length; i++) {
			int r = x + delta[i][0];
			int c = y + delta[i][1];
			if(!isInRange(r,c) || visited[r][c]) continue;
			if(map[r][c] == 1){
				visited[r][c] = true;
				dfs(r, c);	
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++){
			cnt = 0;
			String[] input = br.readLine().split(" ");
			M = Integer.parseInt(input[0]);
			N = Integer.parseInt(input[1]);
			K = Integer.parseInt(input[2]);
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<K; i++) 
			{
				input = br.readLine().split(" ");
				map[Integer.parseInt(input[1])][Integer.parseInt(input[0])] = 1;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
        System.out.println(sb.toString());
	}

}

