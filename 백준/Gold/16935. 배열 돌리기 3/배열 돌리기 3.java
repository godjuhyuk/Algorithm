import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import javax.sound.sampled.ReverbType;

/**
 *
 * 2 ≤ N, M ≤ 100
 * 1 ≤ R ≤ 1,000
 * N, M은 짝수
 * 1 ≤ Aij ≤ 10^8
 * 
 * 아이디어)
 * 
 * 공간복잡도:
 * 100*100 int 행렬 => 40000바이트 => 40kb * 1000번 반복 => 40MB?
 * 메모리는 충분하니까 구현만 하면 된다.
 *
 * 시간복잡도:
 * 연산당 N^2이라 가정하면, 최악의 연산 횟수는 10000 * 1000 => 1000만:  
 * 
 * 1~4번까지는 N^2 
 * 
 * 5, 6도 무식하게 접근하면 
 * 쪼개고 각자 배열 생성하고 차례대로 배열에 넣으면 결국 N^2인듯?
 * 
 * 회전을 더 효율적으로 하는 방법이 있을까?
 * => 스택에 명령어를 쌓고, 상쇄되는 명령어는 바로바로 지우자.
 * => 상쇄되는 명령어 순서쌍 : (1,1), (2, 2), (3, 4), (5, 6) 
 * 
 * 
 * @author SSAFY
 * 
 */




public class Main {
	
	private static int N, M;
	private static int[][] map;
	private static int[][] deltas = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};
	
	private static void rotateSubCounterClock() {
		N = map.length;
		M = map[0].length;
		
		int aCnt = 0;
		int bCnt = 0;
		int[] order = {1, 2, 0, 3};
		int[][] tempMap = new int[N][M];

		for(int o: order) {
			
			int dx = deltas[o][0];
			int dy = deltas[o][1];
			
			int startX = (N/2)*dx;
			int startY = (M/2)*dy;
			
			for(int i = startX, a=aCnt*N/2; i<startX + N/2; i++, a++) {
				for(int j = startY, b=bCnt*M/2; j <startY + M/2; j++, b++) {
					tempMap[a][b] = map[i][j];
				}
			}
			bCnt = (bCnt+1)%2;
			if(bCnt==0) {
				aCnt++;
			}
		}
		map = tempMap;
		
	}
	
	private static void rotateSubClock() {
		
		N = map.length;
		M = map[0].length;
		
		int aCnt = 0;
		int bCnt = 0;
		int[] order = {3, 0, 2, 1};
		int[][] tempMap = new int[N][M];

		for(int o: order) {
			
			int dx = deltas[o][0];
			int dy = deltas[o][1];
			
			int startX = (N/2)*dx;
			int startY = (M/2)*dy;
			
			for(int i = startX, a=aCnt*N/2; i<startX + N/2; i++, a++) {
				for(int j = startY, b=bCnt*M/2; j <startY + M/2; j++, b++) {
					tempMap[a][b] = map[i][j];
				}
			}
			bCnt = (bCnt+1)%2;
			if(bCnt==0) {
				aCnt++;
			}
		}
		map = tempMap;
		
	}
	
	private static void rotateCounterClock() {
		N = map.length;
		M = map[0].length;
		int[][] tempMap = new int[M][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tempMap[j][i] = map[i][M-j-1];
			}
		}
		
		map = tempMap;
		return;
	}
	
	private static void rotateClock() {
		N = map.length;
		M = map[0].length;
		
		int[][] tempMap = new int[M][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tempMap[j][i] = map[N-i-1][j];
			}
		}
		
		map = tempMap;
		return;
	}
	
	private static void reverseLR() {
		N = map.length;
		M = map[0].length;
		int[][] tempMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tempMap[i][j] = map[i][M-j-1];
			}
		}
		map = tempMap;
		return;
	}
	
	private static void reverseUd() {
		N = map.length;
		M = map[0].length;
		
		int[][] tempMap = new int[N][M];
		
		for(int i=N-1; i>=0; i--) {
			tempMap[i] = map[N-i-1];
		}
		map = tempMap;
		return;
	}
	
	
	private static void matrixMove(int cmd) {
		
		switch(cmd) {
			case 1:
				reverseUd();
				break;
			case 2:
				reverseLR();
				break;
			case 3:
				rotateClock();
				break;
			case 4:
				rotateCounterClock();
				break;
			case 5:
				rotateSubClock();
				break;
			case 6:
				rotateSubCounterClock();
				break;
		}

	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		int R = Integer.parseInt(input[2]);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		input = br.readLine().split(" ");
		int[] cmdArr = new int[R];
		for(int i=0; i<R; i++) {
			int cmd = Integer.parseInt(input[i]);
			cmdArr[i] = cmd;
		}
		
		for(int i=0; i<cmdArr.length; i++) {
			matrixMove(cmdArr[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}