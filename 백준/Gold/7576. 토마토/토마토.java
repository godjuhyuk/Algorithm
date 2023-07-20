import java.io.*;
import java.util.*;
public class Main {
	static int m, n, cnt, zeroCnt, zeroTotal;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = {{1, 0},{-1, 0},{0, 1},{0, -1}};
	public static boolean isInRange(int r, int c) {
		return r>=0 && r<n && c>=0 &&  c<m;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		m = Integer.parseInt(input[0]);
		n = Integer.parseInt(input[1]);
		Queue<int[]> bfsQueue = new LinkedList();
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if (map[i][j] == 1) {
					bfsQueue.add(new int[] {i, j});
				}
				else if(map[i][j] ==0) {
					zeroTotal++;
				}
			}
		}
		if(zeroCnt==zeroTotal) {
			System.out.println(0);
		}
		else {
			cnt=0;
			boolean flag = false;
			while(!bfsQueue.isEmpty()) {
				int qSize = bfsQueue.size();
				for(int i=0; i < qSize; i++) {
					int[] tempPop = bfsQueue.poll();
					int r = tempPop[0];
					int c = tempPop[1];
					for(int d=0; d< delta.length; d++) {
						int nr = r + delta[d][0];
						int nc = c + delta[d][1];
						if(isInRange(nr, nc) && map[nr][nc] == 0) {
							bfsQueue.add(new int[] {nr, nc});
							map[nr][nc] = '1';
							zeroCnt++;
						}
					}
				}
				cnt++;
				if(zeroCnt == zeroTotal) {
					flag = true;
					break;
				}
			}
	
			if(flag) {
				System.out.println(cnt);
			} else {
				System.out.println(-1);
			}
		
		}
		br.close();
		
	}
}
