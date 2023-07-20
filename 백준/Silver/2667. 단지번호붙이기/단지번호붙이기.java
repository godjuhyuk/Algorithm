import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int idx = 0;
	static int[] ansArr;
	static char[][] map;
	static boolean[][] visited;
	static int[][] delta = {{1,0},{-1, 0},{0, 1},{0, -1}};
	public static boolean isInRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	public static void dfs(int x, int y) {
		for(int i=0; i<delta.length; i++) {
			int r = x + delta[i][0];
			int c = y + delta[i][1];
			if(!isInRange(r,c) || visited[r][c]) continue;
			if(map[r][c] == '1') {
				visited[r][c] = true;
				ansArr[idx]++;
				dfs(r, c);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		ansArr = new int[N*N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int allCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == '1' && !visited[i][j]) {
					allCnt++;
					ansArr[idx]++;
					visited[i][j] = true;
					dfs(i, j);
					idx++;
				}
				
			}
		}
		sb.append(allCnt).append("\n");
		Arrays.sort(ansArr);
		for(int i=0; i<ansArr.length; i++){
			if(ansArr[i] == 0) continue;
			sb.append(ansArr[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

}
