import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[][] sdoku;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sdoku = new int[9][9];
		
		for(int i=0; i<9; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0; j<9; j++) {
				sdoku[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		backtracking(0, 0, sdoku);
		
	}
	
	private static void backtracking(int r, int c, int[][] map) {
		// 기저조건 
		if(r == 8 && c == 9) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
			return;
		}
				
		if(c > 8) {
			backtracking(r+1, 0, map);
			return;
		}
		if(r > 8) return;
		
		if(map[r][c] != 0) {
			backtracking(r, c+1, map);
			return;
		}
		int bitMask = 0;
		
		for(int i=0; i<9; i++) {
			// 행 체크
			if(map[i][c] != 0 && (bitMask & 1 << map[i][c]) == 0) {
				bitMask |= 1 << map[i][c]; 
			}
			
			// 열 체크
			if(map[r][i] != 0 && (bitMask & 1 << map[r][i]) == 0) {
				bitMask |= 1 << map[r][i]; 
			}
			
		}

		// 3 by 3 매트릭스 체크
		int matrixStartRow = calculStartPoint(r);
		int matrixStartCol = calculStartPoint(c);
		
		for(int i = matrixStartRow; i<matrixStartRow+3; i++) {
			for(int j = matrixStartCol; j < matrixStartCol + 3; j++) {
				if(map[i][j] != 0 && (bitMask & 1 << map[i][j]) == 0) {
					bitMask |= 1 << map[i][j]; 
				}
			}
		}
		
		if(bitMask == 0b1111111110) {
			return;
		}
		for(int i=1; i<=9; i++) {
			if((bitMask & 1 << i) == 0) {
				map[r][c] = i;
				backtracking(r, c+1, map);
				map[r][c] = 0;
			}
		}
		
	}
	
	private static int calculStartPoint(int x) {
		if(x <= 2) return 0;
		else if(x<=5) return 3;
		else return 6;
	}
}
