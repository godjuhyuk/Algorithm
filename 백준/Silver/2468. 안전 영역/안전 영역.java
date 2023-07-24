import java.io.*;
import java.util.*;
public class Main {
	static int n, cnt, maxCnt, maxHeight;
	static int[][] delta = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] island;
	static boolean[][] landAfterRain;
	
	public static boolean[][] raining(int height) {
		boolean[][] land = new boolean[n+2][n+2];
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(island[i][j] > height) {
					land[i][j] = true;
				}
			}
		}
		return land;
	}
	
	public static void dfs(int r, int c) {
		
		for(int[] vector: delta) {
			int nr = r + vector[0];
			int nc = c + vector[1];
			if(landAfterRain[nr][nc]) {
				landAfterRain[nr][nc] = false;
				dfs(nr, nc);
			}
		}
		return;
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		island = new int[n+2][n+2];
		
		for(int i=1; i<n+1; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=1; j<n+1; j++) {
				island[i][j] = Integer.parseInt(input[j-1]);
				maxHeight = Math.max(maxHeight, island[i][j]);
			}
		}
		
		if(maxHeight == 1) {
			System.out.println(1);
			return;
		}
		
		for(int i=1; i<=maxHeight; i++) {
			landAfterRain = raining(i);
			for(int j=1; j<n+1; j++) {
				for(int k=1; k<n+1; k++) {
					if(landAfterRain[j][k]) {
						cnt++;
						dfs(j, k);
					}
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
			cnt = 0;
		}
		System.out.println(maxCnt);
	}
}
