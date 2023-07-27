import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k, sec, qSize;
	static int[][] deltaBomb = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] boomTimeTable;
	static List<int[]> boomList;
	
	
	public static void boom(int r, int c) {
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
	
	public static void setBomb() {
		
		sec++;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(boomTimeTable[i][j] == 2) {
					boomTimeTable[i][j] -= 1;
					boomList.add(new int[] {i, j});
				}
				else if(boomTimeTable[i][j] == 0) {
					boomTimeTable[i][j] += 2;
				}
				
			}
		}
	}
	
	
	public static void dfsBoom() {
		sec++;
		for(int[] loc: boomList) {
			int r = loc[0];
			int c = loc[1];
			boomTimeTable[r][c] = 0;
			boom(r, c);
		}
		boomList.clear();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		char[][] inputMap = new char[n][m];
		boomTimeTable = new int[n+2][m+2];
		boomList = new ArrayList<>();
		
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
			dfsBoom();
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
