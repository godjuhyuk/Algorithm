import java.io.*;
import java.util.*;
public class Main {
	static int m, n, h, cnt, zeroCnt, zeroTotal;
	static int[][][] map;
	static int[][] delta = {{1, 0, 0},{-1, 0, 0},{0, 1, 0},{0, -1, 0}, {0, 0, 1}, {0,0,-1}};
	public static boolean isInRange(int r, int c, int g) {
		return r>=0 && r<n && c>=0 &&  c<m && g >= 0 && g<h;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		m = Integer.parseInt(input[0]);
		n = Integer.parseInt(input[1]);
		h = Integer.parseInt(input[2]);
		Queue<int[]> bfsQueue = new LinkedList();
		map = new int[n][m][h];
		for(int k=0; k<h; k++) {
			for(int i=0; i<n; i++) {
				input = br.readLine().split(" ");
				for(int j=0; j<m; j++) {
					map[i][j][k] = Integer.parseInt(input[j]);
					if (map[i][j][k] == 1) {
						bfsQueue.add(new int[] {i, j, k});
					}
					else if(map[i][j][k] ==0) {
						zeroTotal++;
					}
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
					int g = tempPop[2];
					for(int d=0; d< delta.length; d++) {
						int nr = r + delta[d][0];
						int nc = c + delta[d][1];
						int ng = g + delta[d][2];
						if(isInRange(nr, nc, ng) && map[nr][nc][ng] == 0) {
							bfsQueue.add(new int[] {nr, nc, ng});
							map[nr][nc][ng] = 1;
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
