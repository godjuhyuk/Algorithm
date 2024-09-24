import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 시작 시간: 오후 8시 45분
 * 
 * 분할정복문제
 * 
 * 첫 줄에 N, r, c가 주어진다.
 * r, c를 몇번째 방문하는지 구하는 문제
 * 
 * 문제 해결을 위한 고민)
 * 
 * 분할 정복 어떻게 구현하더라?
 * 	- 기억나는건 탑다운으로 들어가서 최소 단위에서 연산하고 그 결과를 합치는 방식이었던거같음
 * 
 * 이 문제의 경우엔 어떻게 합치는게 좋을까?
 * 	- 일단 4칸짜리 크기가 될 때까지 쪼개고 (size == 2^2)
 * 	- 쪼갤 때 1,2,3,4 순서로 유지시키고
 * 	- 전역변수로 카운트 들어가면 될듯?
 * 
 * Z 무빙 어떻게 구현할건지?
 * 	- xStart, yStart = cnt++
 * 	- xStart, yStart + 1 = cnt++
 * 	- xStart+1, yStart = cnt++
 * 	- xStart+1, yStart = cnt++
 * 
 * 굿
 * 
 * 헉 메모리 초과를 생각 못했음,, ㅁㅊ
 * 
 * 
 * 그냥 분할정복 구현하면 될 줄 알았는데 최대한 효율적으로 접근해야하는 문제였음
 * 
 *  전체 맵 크기가 (2^(2*N))이니까 
 *  2^(2*N-2)로 4개의 영역을 나눠서
 *  구하려는 영역 위치만 딱 구해야할듯
 *  0 1
 *  2 3 
 *  
 *  A 사분면인 경우
 *  A * 2^(2*N-2) + ans 로..
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int N, R, C, dim, ans;
	private static boolean isOver;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[][] locByDim = {{0, 0}, {0, 1<<(N-1)}, {1<<(N-1), 0}, {1<<(N-1), 1<<(N-1)}};
		
		dim = getDimension();
		ans += dim * 1 << (2*N-2);
		divideConqeur(N, locByDim[dim][0], locByDim[dim][1]);
		
	}

	private static int getDimension() {
		
		int x = 0, y = 0;
		
		if(R < 1 << (N-1)) x = -1;
		else x = 1;
		
		if(C < 1 << (N-1)) y = -1;
		else y = 1;
		
		if(x == -1 && y == -1) return 0;
		else if(x == -1 && y == 1) return 1;
		else if(x == 1 && y == -1) return 2;
		else return 3;
		
	}

	private static void divideConqeur(int size, int r, int c) {
		
		if(isOver) return;
		
		if(size == 1) {
			
			if(r == R && c == C) {
				System.out.println(ans);
				isOver = true;
			}
			ans++;
			
			if(r == R && c+1 == C) {
				System.out.println(ans);
				isOver = true;
			}
			ans++;
			
			if(r+1 == R && c == C) {
				System.out.println(ans);
				isOver = true;
			}
			ans++;
			
			if(r+1 == R && c+1 == C) {
				System.out.println(ans);
				isOver = true;
			}
			ans++;
			
		}
		else {
			size -= 1;
			int length = 1 << size;
			divideConqeur(size, r, c);
			divideConqeur(size, r, c + length);
			divideConqeur(size, r + length, c);
			divideConqeur(size, r + length, c + length);
		}
		
		
		
	}
	
	
}
