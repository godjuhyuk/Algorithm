import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static char[][] map;
	static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[] colorCnt = new int[3];
	static int[] colorCnt2 = new int[2];
	
	static boolean[][] visited;
	static boolean[][] visited2;
	
	public static boolean isRedOrGreen(char color) {
		if(color == 'R' || color == 'G') {
			return true;
		} else {
			return false;
		}
	}
	
	public static void ansCnt2(char color) {
		if(color == 'R' || color == 'G') {
			colorCnt2[0]++;
		}
		else if(color == 'B') {
			colorCnt2[1]++;
		}
		
	}
	
	public static void ansCnt(char color) {
		if(color == 'R') {
			colorCnt[0]++;
		}
		else if(color == 'G') {
			colorCnt[1]++;
		}
		else if(color == 'B') {
			colorCnt[2]++;
		}
		
	}
	
	public static void dfs2(int r, int c, char color){
		for(int[] delta: deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			char nextColor = map[nr][nc]; 
			if(!visited2[nr][nc] && isRedOrGreen(color) && isRedOrGreen(nextColor)) {
				visited2[nr][nc] = true;
				dfs2(nr, nc, nextColor);
			}
			
			// blue일떄
			else if(!visited2[nr][nc] && nextColor == color){
				visited2[nr][nc] = true;
				dfs2(nr, nc, nextColor);
			}
		}
	}
	
	public static void dfs(int r, int c, char color){
		for(int[] delta: deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];
			if(!visited[nr][nc] && map[nr][nc] == color) {
				visited[nr][nc] = true;
				dfs(nr, nc, color);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n+2][n+2];
		visited = new boolean[n+2][n+2];
		visited2 = new boolean[n+2][n+2];
		for(int i=1; i<n+1; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=1; j<n+1; j++) {
				map[i][j] = input[j-1];
			}
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				
				char color = map[i][j];

				//적록색약 X
				if(!visited[i][j]) {
					visited[i][j] = true;
					dfs(i, j, color);
					ansCnt(color);
				}
				
				//적록색약 O
				if(!visited2[i][j]) {
					visited2[i][j] = true;
					dfs2(i, j, color);
					ansCnt2(color);
				}
			}
		}
		
		System.out.print(colorCnt[0]+ colorCnt[1] + colorCnt[2] + " ");
		System.out.print(colorCnt2[0]+ colorCnt2[1]);
	}
}