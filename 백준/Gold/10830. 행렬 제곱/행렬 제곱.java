import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	private static int N;
	private static int[][] copyMatrix;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int[][] matrix = new int[N][N];
		copyMatrix = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				copyMatrix[i][j] = matrix[i][j];
			}
		}
		
		int[][] ans = divideAndConquer(matrix, B);
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static int[][] divideAndConquer(int[][] matrix, long B) {
		
		
		if(B == 1) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					matrix[i][j] %= 1000;
				}
			}
			return matrix;
		}
		else if (B == 2) {
			
			int[][] halfMatrix = divideAndConquer(matrix, B/2);
			
			return matrixMultiple(halfMatrix, halfMatrix);
		}
		else if(B%2 == 0) {
			
			int[][] halfMatrix = divideAndConquer(matrix, B/2);
			
			return matrixMultiple(halfMatrix, halfMatrix);
		} else {
			
			int[][] halfMatrix = divideAndConquer(matrix, B/2);
			
			return matrixMultiple(matrixMultiple(halfMatrix, halfMatrix), copyMatrix);
		}
	}
	
	private static int[][] matrixMultiple(int[][] matrix1, int[][] matrix2) {
		
		int[][] resultMatrix = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int k=0; k<N; k++) {
				int sum = 0;
				for(int j=0; j<N; j++) {
					sum += matrix1[i][j] * matrix2[j][k];
				}
				resultMatrix[i][k] = sum%1000;
			}
		}
		
		return resultMatrix;

	}
	
}