import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * 2 ≤ N, M ≤ 300
 * 1 ≤ R ≤ 1,000
 * min(N, M) mod 2 = 0
 * 1 ≤ Aij ≤ 10^8
 * 
 * 껍데기부터 안으로 파고들면서 돌린다고 하자.
 * 
 * 각 껍데기 당
 * R % (직사각형의 변의 길이의 총합 - 4) 번을 돌려야한다.
 * 
 * 또, 순서가 바뀌지 않는다.
 * 각 껍데기의 배열을 각각 구해놓고, 시작점에서부터 R칸 가서 채워가는 식으로 해보자
 * 
 * 
 * 1. 껍데기 배열 구하는 메소드 구현
 * 2. 껍데기 시작점부터 모서리를 타고 갔을때 R칸 후 위치 구하기
 * 3. 채우기 
 * 
 * @author SSAFY
 * 
 */




public class Main {
	
	private static int N, M, R;
	private static boolean nIsSmallerThanM;
	private static int[][] map;
	
	private static void fillToMap1(int startX, int startY, int[] fillArr, int xLength, int yLength) {
		
		int nowX = startX;
		int nowY = startY;
		int lengthSum = 2*(xLength+yLength);
		int tempR = 0;
		
		while(tempR<lengthSum) {
			while(nowX < startX + xLength) {
				map[nowX][nowY] = fillArr[tempR];
				nowX++;
				tempR++;
			}
			
			startX = nowX;
			
			while(nowY < startY + yLength) {
				map[nowX][nowY] = fillArr[tempR];
				nowY++;
				tempR++;
			}
			
			startY = nowY;
			while(nowX > startX - xLength) {
				map[nowX][nowY] = fillArr[tempR];
				nowX--;
				tempR++;
			}
			startX = nowX;
			while(nowY > startY - yLength) {
				map[nowX][nowY] = fillArr[tempR];
				nowY--;
				tempR++;
			}
			startY = nowY;
		}
		

	}
	
	private static void fillToMap(int startX, int startY, int[] fillArr, int minLength, int maxLength) {
		if(nIsSmallerThanM) {
			fillToMap1(startX, startY, fillArr, minLength, maxLength);
		} 
		else {
			fillToMap1(startX, startY, fillArr, maxLength, minLength);
		}
		
	}
	
	private static int[] move(int[] arr, int moveVal) {
		int[] newArr = new int[arr.length];
		for(int i=0; i<arr.length; i++) {
			newArr[(i+moveVal)%(arr.length)] = arr[i];
		}
		return newArr;
	}
	
	private static int[] getOutSideArr1(int startX, int startY, int xLength, int yLength) {

		int lengthSum = 2*(xLength+yLength);
		int[] returnArr = new int[lengthSum];
		
		int nowX = startX;
		int nowY = startY;
		int tempR = 0;
//		
		while(tempR<lengthSum) {
			while(nowX < startX + xLength) {
				returnArr[tempR] = map[nowX][nowY];
				nowX++;
				tempR++;
			}
			
			startX = nowX;
			
			while(nowY < startY + yLength) {
				returnArr[tempR] = map[nowX][nowY];
				nowY++;
				tempR++;
			}
			
			startY = nowY;
			while(nowX > startX - xLength) {
				returnArr[tempR] = map[nowX][nowY];
				nowX--;
				tempR++;
			}
			startX = nowX;
			while(nowY > startY - yLength) {
				returnArr[tempR] = map[nowX][nowY];
				nowY--;
				tempR++;
			}
			startY = nowY;
		}
		
		return returnArr;
	}
	
	
	private static int[] getOutSideArr(int startX, int startY, int minLength, int maxLength) {
		
		if(nIsSmallerThanM) {
			return getOutSideArr1(startX, startY, minLength, maxLength);
		} 
		else {
			return getOutSideArr1(startX, startY, maxLength, minLength);
		}
	
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		R = Integer.parseInt(input[2]);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		int flagLength = 0;
		int minLength = 0;
		if(N <= M) {
			nIsSmallerThanM = true;
			minLength = N-1;
			flagLength = N/2;
		} else {
			nIsSmallerThanM = false;
			minLength = M-1;
			flagLength = M/2;
		}
		
		int maxLength = Math.abs(M-N) + minLength;
		
		
		
		for(int i=0; i<flagLength; i++) {
			int[] outSideArr = getOutSideArr(i, i, minLength, maxLength);
			
			int[] fillArr = move(outSideArr, R%(2 * (minLength + maxLength)));
			
			fillToMap(i, i, fillArr, minLength, maxLength);
			minLength -= 2;
			maxLength -= 2;
		}
		
//		input = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	

}