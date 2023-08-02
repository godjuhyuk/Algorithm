import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Solution
{
	private static int n;
	private static int[][] map;
	private static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); 

		for(int test_case = 1; test_case <= T; test_case++)
		{	
			sb.append("#").append(test_case).append("\n");
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			int r = 0;
			int c = 0;
			int d = 0;
			int num = 1;
			while(true) {
				
				if(num > n*n) {
					break;
				}
				map[r][c] = num;
				
				int nr = r+ deltas[d%4][0];
				int nc = c+ deltas[d%4][1];
				if(nr>= 0 && nr <n && nc >= 0 && nc < n && map[nr][nc] == 0) {
					r = nr;
					c = nc;
					
				} else {
					d++;
					r += deltas[d%4][0];
					c += deltas[d%4][1];
				}
				num++;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
            sb.delete(sb.length()-1, sb.length());
			System.out.println(sb.toString());
			sb.setLength(0);
		}

	}
}