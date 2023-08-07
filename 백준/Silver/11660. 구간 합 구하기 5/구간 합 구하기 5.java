import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		int[][] map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			input = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(input[j-1]);
			}
		}
		
		// 행렬 누적합 구하기
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==1) {
					map[i][j] += map[i][j-1];
				}
				else if(j==1) {
					map[i][j] += map[i-1][j];
				}
				else {
					map[i][j] += map[i][j-1] + map[i-1][j] - map[i-1][j-1];
				}
				
			}
		}
		
		for(int i=0; i<M; i++) {
	
			input = br.readLine().split(" ");
			int x1 = Integer.parseInt(input[0]);
			int y1 = Integer.parseInt(input[1]);
			int x2 = Integer.parseInt(input[2]);
			int y2 = Integer.parseInt(input[3]);
			
			sb.append(map[x2][y2] - map[x2][y1-1] - map[x1-1][y2] + map[x1-1][y1-1]).append('\n');
			
		}
		
		System.out.println(sb);
		
		
	}
}
