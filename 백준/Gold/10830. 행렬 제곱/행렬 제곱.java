import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 해석)
 * N*N 행렬 A를 B제곱한 결과
 * <주의> A^B의 각 원소를 1000으로 나눈 나머지를 출력해야한다.
 * 
 * 2<=N<=5
 * B<=1000억
 * 
 * log2(1000억) = 2^30이 10억이므로 2^36 쯤 할거같다 => 각 행*열 당 36번 연산 => 5 by 5일때 25 * 36 <= 1000
 * 
 * 해결 방법)
 * 행렬의 제곱은 행과 열의 곱으로 결정되므로
 * 행렬이 주어지면 행과 열로 나눈다.
 * 
 * 이런 식으로 결과를 도출해내보자.
 * 
 * 구현해야하는 기능)
 * 1. 행과 열을 분리
 * 1-1) 2중 for문으로 처리하자 (N*N)
 * 
 * 2. 행 & 열 거듭제곱 연산 재귀함수
 * 
 * 
 * => 쉽게 생각하면,
 * 결국 행렬의 0, 0은 A에 B번 곱함
 * 
 * divide => A에 B/2번 곱한걸 두번 곱하면 됨
 * B/2 => A에 B/4번 곱한걸 두번 곱하면 됨
 * 
 * 
 */
public class Main {
	private static int N;
	private static int[][] copyMatrix, I;
	
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