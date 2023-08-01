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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
   static int test_case, startPoint;
	static boolean gameOver;
	static int[][] map = new int[100][100];
	static int[] dy = {1, -1};

	private static boolean isOutOfRange(int r, int c) {
		
		return r<0 || r>=100 || c<0 || c>= 100;
	}
	
	private static int checkTurn(int r, int c) {
		
		for(int i = 0; i< 2; i++) {
			
			int nc = c + dy[i];
			if(isOutOfRange(r, nc) || map[r][nc] == 0) {
				continue;
			}
			
			return i; 
		}
		
		return -1;
		
	}
	
	private static void findToGoal(int r, int c) {
		
		if(r==99) {
			if(map[r][c] == 2) {
				gameOver = true;
				System.out.printf("#%d %d%n", test_case, startPoint);
			}
			return;
		}
		
		int deltaIdx = -1;
		
		while(r<99) {
			r++;
			deltaIdx = checkTurn(r, c);
			if(deltaIdx != -1) {
				break;
				
			}
		}
		
		
		if(deltaIdx != -1) {
			int nc = c + dy[deltaIdx];
			while(nc < 100 && nc>=0 && map[r][nc] == 1) {
				c = nc;
				nc+= dy[deltaIdx];
			}
		} 
		findToGoal(r, c);
	}
	
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(test_case = 1; test_case <= 10; test_case++)
		{	
			List<Integer> startPoints = new ArrayList<Integer>();
			br.readLine();
			gameOver = false;
			for(int i=0; i<100; i++) {
				String[] input = br.readLine().split(" ");
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(input[j]);
					if(i==0 && map[i][j] == 1) {
						startPoints.add(j);
					}
				}
			}
			
			for(int i=0; i<startPoints.size(); i++) {
				if(!gameOver) {
					startPoint = startPoints.get(i);
					findToGoal(0, startPoint);
				}
			}
		}
		
	}
}