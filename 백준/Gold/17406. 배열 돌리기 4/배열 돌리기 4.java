import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, R;
	private static int[][] map, cmdArr, copiedMap;
	
	//행 합 계산
	private static int rowSum(int[] arr) {
		int sum = 0;
		
		for(int i = 0; i< arr.length; i++) {
			sum+= arr[i];
		}
		
		return sum;
		
	}
	
	
	// 맵 복사
	private static int[][] copyMap() {
		
		for(int i=1; i<=N; i++) {
//			copiedMap[i] = Arrays.copyOf(map[i], M+1);
			System.arraycopy(map[i], 1, copiedMap[i], 1, M);
		}

		return copiedMap;
		
	}
	
	
	// 시계방향 회전
	private static void rotateClockWise(int sr, int sc, int er, int ec, int[][] copiedMap) {
		
		int r = sr;
		int c = sc;
		
		int temp = copiedMap[r][c];
		
		while(r+1 <= er) {
			copiedMap[r][c] = copiedMap[r+1][c];
			r++;
		}
		
		while(c+1 <= ec) {
			copiedMap[r][c] = copiedMap[r][c+1];
			c++;
		}
		
		while(r-1 >= sr) {
			copiedMap[r][c] = copiedMap[r-1][c];
			r--;
		}
		
		while(c-1 >= sc) {
			copiedMap[r][c] = copiedMap[r][c-1];
			c--;
		}
		
		copiedMap[r][c+1] = temp;
		
	}
	
	
	// r, c, s 값을 계산 후 연산 시작
	private static void rcsOperate(int r, int c, int s, int[][] copiedMap) {
		
		int sr = r-s;
		int sc = c-s;
		
		int er = r+s;
		int ec = c+s;
		
		while(sr<er) rotateClockWise(sr++, sc++, er--, ec--, copiedMap);
		
	} 
	
	public static void main(String[] args) throws IOException {
		
		int ans = Integer.MAX_VALUE;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 배열 입출력
		map = new int[N+1][M+1];
		copiedMap = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j=1; j<=M; j++) { 
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cmdArr = new int[R][3];
		
		//넥퍼를 위한 순열조합  
		int[] permArr = new int[R];

		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j=0; j<3; j++) { 
				cmdArr[i][j] = Integer.parseInt(st.nextToken());
			}
			
			permArr[i] = i;
		}
		
		do {
			
			int[][] copiedMap = copyMap();
			
			for(int order : permArr) {
				int r = cmdArr[order][0];
				int c = cmdArr[order][1];
				int s = cmdArr[order][2];
				
				rcsOperate(r, c, s, copiedMap);
				
			}
			
			for(int i=1; i<=N; i++) {
				ans = Math.min(ans, rowSum(copiedMap[i]));
			}
			
			
		} while(np(permArr));
		
		System.out.println(ans);
		
	}
	
	// 넥퍼 구현
	private static boolean np(int[] arr) {
		
		int i = R - 1;
		
		while(i>0 && arr[i-1] >= arr[i]) i--;
		
		if(i==0) return false;
		
		int j = R - 1;
		
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j); 
		
		int k = R - 1;
		while(i < k) {
			swap(arr, i++, k--);
		}
		
		return true;
		
	}
	
	private static void swap(int[] arr, int a, int b) {
		
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		
	}
	
}