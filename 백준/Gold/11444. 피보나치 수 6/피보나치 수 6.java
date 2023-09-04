import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static long[][] fibonacciCalcul;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong();
		
		if(n == 1) {
			System.out.println(1);
			return;
		}
		else if(n == 2) {
			System.out.println(1);		
			return;
		}
		
		// (n-1)/2 번 만큼의 행렬 제곱을 해야한다.
		long k = (n-1)/2;
		fibonacciCalcul = new long[][] {{1, 1}, {1, 2}};
		
		long[][] ans = matrixMultiple(new long[][] {{1, 1},{0,0}}, divideAndConquer(k));
		
		int idx = n%2 == 0 ? 1 : 0;
		
		System.out.println(ans[0][idx]);
		
	}
	
	/**
	 * [1, 1]
	 * [1, 2] 
	 * 
	 * 위 행렬을 k번 거듭제곱하는 메서드
	 * 
	 */
	private static long[][] divideAndConquer(long k) {
		
		
		if(k == 1) {
			return fibonacciCalcul;
		}
		else if(k%2 == 0) {
			long[][] temp = divideAndConquer(k/2);
			 return matrixMultiple(temp, temp);
		} else {
			long[][] temp = divideAndConquer(k/2);
			 return matrixMultiple(matrixMultiple(temp, temp), fibonacciCalcul);
		}
		
	}
	
	private static long[][] matrixMultiple(long[][] m1, long[][] m2) {
		
		long a = (m1[0][0]%1_000_000_007 * m2[0][0]%1_000_000_007 + m1[0][1]%1_000_000_007 * m2[1][0]%1_000_000_007)%1_000_000_007;
		long b = (m1[0][0]%1_000_000_007 * m2[0][1]%1_000_000_007 + m1[0][1]%1_000_000_007 * m2[1][1]%1_000_000_007)%1_000_000_007;
		long c = (m1[1][0]%1_000_000_007 * m2[0][0]%1_000_000_007 + m1[1][1]%1_000_000_007 * m2[1][0]%1_000_000_007)%1_000_000_007;
		long d = (m1[1][0]%1_000_000_007 * m2[0][1]%1_000_000_007 + m1[1][1]%1_000_000_007 * m2[1][1]%1_000_000_007)%1_000_000_007;
		
		return new long[][] {{a, b}, {c, d}};
		
	}

}