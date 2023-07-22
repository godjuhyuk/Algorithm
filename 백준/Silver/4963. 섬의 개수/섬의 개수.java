import java.io.*;
import java.util.*;

public class Main {
	static int n, m, ansCnt;
	static int[][] delta = {{1, 0}, {0, 1}, {1, 1}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1}, {-1, -1}};
	static boolean[][] map;
	
	public static void dfs(int r, int c) {
		for(int[] vector: delta) {
			int nr = r + vector[0];
			int nc = c + vector[1];
			
			if(map[nr][nc]) {
				map[nr][nc] = false;
				dfs(nr, nc);
			}
			
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] input = br.readLine().split(" ");
			m = Integer.parseInt(input[0]);
			n = Integer.parseInt(input[1]);
			if(n==0 && m==0) {
				return;
			}
			map = new boolean[n+2][m+2];
			for(int i=1; i<=n; i++) {
				String island = br.readLine();
				for(int j=1; j<=m; j++) {
					map[i][j] = island.charAt((j-1) * 2) > '0';
				}
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(map[i][j]) {
						ansCnt++;
						map[i][j] = false;
						dfs(i, j);
						}
					}
			}
			System.out.println(ansCnt);
			ansCnt=0;
		}
		
	}
}
