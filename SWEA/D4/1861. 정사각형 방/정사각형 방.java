/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	private static int N, minNum, ansCnt;
	private static Queue<int[]> bfsQueue;
	private static int[][] map;
	private static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static boolean[][] visited;
	
	private static void bfs(int minVal) {
		
		int count = 1;
		
		while(!bfsQueue.isEmpty()) {
			int[] temp = bfsQueue.poll();
			
			int r = temp[0];
			int c = temp[1];
			
			for(int[] d: deltas) {
				int nr = r+d[0];
				int nc = c+d[1];
				
				if(Math.abs(map[nr][nc] - map[r][c]) > 1 || visited[nr][nc] || map[nr][nc] == 0) {
					continue;
				}
				
				count++;
				minVal = Math.min(minVal, map[nr][nc]);
				visited[nr][nc] = true;
				bfsQueue.add(new int[] {nr, nc});
			}
		}
		
		if(count>ansCnt) {
			ansCnt = count;
			minNum = minVal;
		}
		else if(ansCnt == count) {
			minNum = Math.min(minNum, minVal);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			ansCnt = 0;
			minNum = N*N + 1;
			
			// 초기 배열 및 큐 초기화
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][N+2];
			visited = new boolean[N+2][N+2];
			
			
			// 입력값 받기
			for(int i=1; i<=N; i++) {
				String[] input = br.readLine().split(" ");
				for(int j=1; j<=N; j++) { 
					map[i][j] = Integer.parseInt(input[j-1]); 
				}
			}
			
			
			// bfs
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) { 
					if(!visited[i][j]) {
						bfsQueue = new ArrayDeque();
						bfsQueue.add(new int[] {i, j});
						visited[i][j] = true;
						bfs(map[i][j]);
					}
				}
			}
			
			sb.append('#').append(testCase).append(' ').append(minNum)
						.append(' ').append(ansCnt).append('\n');
			
		}
		System.out.println(sb);
		
		
		
	}
}