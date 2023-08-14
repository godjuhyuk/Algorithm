import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 * 문제 해석)
 * 
 * 정사각형을 4등분 했을 때, 해당 정사각형이 한가지 색으로 가득차있지 않으면 다시 4등분을 한다.
 * 한 섹터가 한가지 색깔로 가득찼으면 1 혹은 0을 반환
 * 섹터가 나뉠때마다 괄호로 감싸줘야함
 * 
 * 구현해야할 기능)

 * 1. 재귀 함수 
 * - 한가지 색으로 가득차있는지 check
 * - 그게 아니라면 divide (4등분)
 * - 괄호 및 섹터의 색깔 정보를 string으로 return
 * 
 * 2. 시간복잡도?
 * - N <= 64
 * - N*N : 2^12
 * 
 */
public class Main {
	
	private static StringBuilder sb;
	private static char[][] map;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		divideAndConquer(0, 0, N);
		System.out.println(sb);
	}
	
	private static void divideAndConquer(int r, int c, int size) {
		
		int sum = 0;
		for(int i = r; i< r + size; i++) {
			for (int j = c; j < c + size; j++) {
				sum += map[i][j] - '0';
			}
		}
		if(sum == 0) {
			sb.append('0');
		}
		else if(sum == size*size) {
			sb.append('1');
		}
		else {
			sb.append('(');
			int half = size/2;
			divideAndConquer(r, c, half);
			divideAndConquer(r, c+half, half);
			divideAndConquer(r+half, c, half);
			divideAndConquer(r+half, c+half, half);
			sb.append(')');
		}
	}
}