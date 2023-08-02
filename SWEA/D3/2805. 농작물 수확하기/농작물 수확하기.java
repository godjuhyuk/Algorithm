import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	private static int n, sum, test_case;
	private static int[][] map;
	private static int[][] deltas = {{1, 0}, {0, 1},{-1, 0},{0, -1}};
	
	private static boolean isOver(int r, int c) {
		
		return (r == n/2 && c == 0) || (r == n/2 && c == n-1) || (r == 0 && c == n/2) || (r == n-1 && c == n/2);

	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r<0 || r>=n || c<0 || c>=n;
	}
	
	private static void dfs(int r, int c) {
		// 기저조건
		if(isOver(r, c)) {
			return;
		}
		
		// 유도 파트
		for(int[] d : deltas) {
			int nr = r + d[0];
			int nc = c + d[1];
			if(isOutOfRange(nr, nc) || map[nr][nc] == -1) {
				continue;
			}
			sum += map[nr][nc];
			map[nr][nc] = -1;
			dfs(nr, nc);
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(test_case = 1; test_case <= T; test_case++)
		{
			// 시작
			sum = 0;
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			int borderLineLength = 0;
			int alpha = 1;
			for(int i=0; i<n; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j=0; j<n; j++) {
					if(j <n/2 - borderLineLength || j >n/2 + borderLineLength) {
						map[i][j] = -1;
					} else {
						map[i][j] = input[j] - '0';
					}
				}
				if(borderLineLength + alpha > n/2) {
					alpha = -1;
				}
				borderLineLength+= alpha;
			}
			sum += map[n/2][n/2];
			map[n/2][n/2] = -1;
			dfs(n/2, n/2);
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
			// 끝
		}
        System.out.println(sb.toString());
	}
}