import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k, sec, qSize;
	static int[][] deltaBomb = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] boomTimeTable;
	static Queue<int[]> bfsQueue;
	public static void setBomb() {
		
		sec++;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(boomTimeTable[i][j] == 2) {
					boomTimeTable[i][j] -= 1;
					bfsQueue.add(new int[] {i, j});
				}
				else if(boomTimeTable[i][j] == 0) {
					boomTimeTable[i][j] += 2;
				}
				
			}
		}
	}
	
	
	public static void bfsBoom() {
		sec++;
		for(int i=0; i<qSize; i++) {
			int[] temp = bfsQueue.poll(); //1초 남은 애들이 0 되면서 터질 준비
			int r = temp[0];
			int c = temp[1];
			boomTimeTable[r][c] = 0;
			for(int[] delta: deltaBomb) {
				int nr = r + delta[0];
				int nc = c + delta[1];
				// 이번 큐에 터질 애들이면 continue
                if(boomTimeTable[nr][nc]==1) {
					continue;
				}
				boomTimeTable[nr][nc] = 0;
			}	
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		char[][] inputMap = new char[n][m];
		boomTimeTable = new int[n+2][m+2];
		bfsQueue = new LinkedList();
		
		sec++; // 초기 폭탄 설치 
		for(int i=1; i<=n; i++) {
			inputMap[i-1] = br.readLine().toCharArray();
			for(int j=1; j<=m; j++) {
				if(inputMap[i-1][j-1] == 'O') {
					boomTimeTable[i][j] = 2;
				}
			}
		}
		while(sec < k) {
			setBomb();
			if(sec >= k) {
				break;
			}
			qSize = bfsQueue.size();
			bfsBoom();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(boomTimeTable[i][j] > 0) {
					sb.append("O");
				} else {
					sb.append(".");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
