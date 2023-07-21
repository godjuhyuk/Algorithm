import java.io.*;
import java.util.*;
public class Main {
	static int n, m, ansCnt;
	static boolean isItConstant;
	static int[][] delta = {{1, 0},{-1, 0},{0, 1},{0, -1},{1, 1},{1, -1},{-1, 1},{-1, -1}};
	static boolean[][] visited;
	static int[][] map;
	

	public static boolean isInRange(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
	public static void dfs(Stack<int[]> stack) {
		ansCnt = 0;
		while(!stack.isEmpty()) {
			Stack<int[]> tempStack = new Stack<>();
			int[] checkList = stack.pop();
			if(visited[checkList[0]][checkList[1]]) {
				continue;
			}
			tempStack.add(checkList);
			ansCnt++;
			while(!tempStack.isEmpty()) {
				int[] temp = tempStack.pop();
				int r = temp[0];
				int c = temp[1];
				for(int d=0; d<delta.length; d++) {
					int nr = r + delta[d][0];
					int nc = c + delta[d][1];
					if(isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
						visited[nr][nc] = true;
						tempStack.add(new int[] {nr, nc});
					}
				}
			}
		}
		System.out.println(ansCnt);

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		while(! ( input[0].equals("0") && input[1].equals("0") ) ) {
			if(input[0].equals("1") && input[1].equals("1")) {
				String temp = br.readLine();
				if(temp.equals("0")) {
					System.out.println(0);
				} else {
					System.out.println(1);
				}
				input = br.readLine().split(" ");
				continue;
			}
			m = Integer.parseInt(input[0]);
			n = Integer.parseInt(input[1]);
			Stack<int[]> dfsStack = new Stack<>();
			map = new int[n][m];
			visited = new boolean[n][m];
			for(int i=0; i<n; i++) {
				input = br.readLine().split(" ");
				for(int j=0; j<m; j++) {
					map[i][j] = Integer.parseInt(input[j]);
					if(map[i][j] == 1) {
						dfsStack.add(new int[] {i, j});
					}
				}
			}
			dfs(dfsStack);
			input = br.readLine().split(" ");
		}
	}
}

