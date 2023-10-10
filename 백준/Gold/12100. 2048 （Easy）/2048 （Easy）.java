import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 설계 시작 시간 : 오후 4시 24분
 * 설계 종료 시간 : 
 * 
 * 
 * 문제 해석)
 * 
 * 블록이 추가되는 겨웅는 없음 
 * 
 * 한번의 이동은 전체 블록을 상하좌우 네방향으로 이동시키는 것
 * 
 * 같은 값을 가진 블록이 충돌하면 하나로 합쳐짐
 * 한번의 이동에서 합쳐졌으면 다른 블록과 다시 합쳐질 수 없음.
 * 
 * 같은 수가 세개 있는 경우엔, 이동하려는 쪽의 칸이 먼저 합쳐짐.(우선순위 부여)
 * 
 * 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하는 프로그램 작성
 * 
 * 시간복잡도 : 4^5 * ?
 * 
 * 한턴에 시간복잡도가 얼마나될까?
 * 
 * 문제 해결을 위한 고민)
 * 
 * 움직이는 로직을 어떻게 구현해야하나 ?
 * 
 * 1. 오른쪽으로 움직이는 경우, 각 행의 오른쪽에서부터 탐색
 * 1-1) 처음 값을만나면 union 시도 => 실패하면 만난 값부터 다시 탐색..
 * => 20 * (20 + 20 ) 
 *  
 * 
 * 시간복잡도는 널널한거같다?
 * 정렬을 이용하면 어떨지?
 * 
 * 
 */
public class Main {
	
	private static int N, ans, gameGrid[][];
	
	static class Node {
		
		int val;
		int loc;
		public Node(int val, int loc) {
			this.val = val;
			this.loc = loc;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		gameGrid = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				gameGrid[i][j] = Integer.parseInt(st.nextToken());
				ans = Math.max(ans, gameGrid[i][j]);
			}
		}
		
		
		for(int i=0; i<4; i++) {
			// 4가지 방향으로 밀면서 시작
			dfs(1, 0, i, mapCopy(gameGrid));
		}
		System.out.println(ans);
		
	}
	
	private static void dfs(int turn, int max, int deltaIdx, int[][] map) {
		// 기저조건 - 5번 밀었으면 종료
		if( turn == 6) {
			return;
		}
		
        int[][] copiedMap = mapCopy(map);
        
		move(copiedMap, deltaIdx);
		
		// 유도파트
		for(int i=0; i<4; i++) {
			dfs(turn+1, max, i, copiedMap);
		}
		
	}
	
	private static void move(int[][] map, int deltaIdx) {
		// deltaIdx에 따른 행렬 move
		// 0: 위로 이동 
		// 1: 아래로 이동
		// 2: 왼쪽
		// 3: 오른쪽
		switch (deltaIdx) {
		case 0:
			moveUp(map);
			break;
		case 1:
			moveDown(map);		
			break;
		case 2:
			moveLeft(map);
			break;
		case 3:
			moveRight(map);
			break;

		default:
			break;
		}
		
	}
	
	private static void moveUp(int[][] map) {
		
		// col열에서, row를 순회하며 합친다.
		for(int col=0; col<N; col++) {
			Node upNode = new Node(map[0][col], 0);
			for(int row=1; row<N; row++) {
				
				// upNode가 null이라면 새로 만들어주기
				if(upNode == null || upNode.val == 0) {
					upNode = new Node(map[row][col], row);
				}
				
				int temp = map[row][col];
				
				// 빈칸이면 건너뛰기 
				if(temp == 0) continue;
				
				// 빈칸이 아닌데 값이 다르다면 pass
				if(temp != upNode.val) {
					upNode = new Node(temp, row);
				} 
				// 같은 값이면 
				else if(row != upNode.loc){
					map[upNode.loc][col] = 2*upNode.val;
					ans = Math.max(ans, map[upNode.loc][col]);
					map[row][col] = 0;
					upNode = null;
				}
				
			}
			
			// 이 열의 값이 같다면 합쳐진 상태 - 위에서부터 정렬해야함
			int temp = 0;
			for(int row=0; row<N; row++) {
				if(map[row][col] != 0) {
					map[temp][col] = map[row][col];
					
					// row == temp 라면 0으로 초기화해버리면 안됨
					if(row != temp) map[row][col] = 0;
					temp++;
				}
			}
			
		}
		
	}
	
	private static void moveDown(int[][] map) {
		
		for(int col=0; col<N; col++) {
			Node upNode = new Node(map[N-1][col], N-1);
			for(int i=N-1; i>=0; i--) {
				
				// upNode가 null이라면 새로 만들어주기
				if(upNode == null || upNode.val == 0) {
					upNode = new Node(map[i][col], i);
				}
				
				int temp = map[i][col];
				
				// 빈칸이면 건너뛰기 
				if(temp == 0) continue;
				
				// 빈칸이 아닌데 값이 다르다면 pass
				if(temp != upNode.val) {
					upNode = new Node(temp, i);
				} 
				// 같은 값이면 
				else if(i != upNode.loc){
					map[upNode.loc][col] = 2*upNode.val;
					ans = Math.max(ans, map[upNode.loc][col]);
					map[i][col] = 0; // 필요한가?
					upNode = null;
				}
				
			}
			
			// 이 열의 값이 같다면 합쳐진 상태 - 위에서부터 정렬해야함
			
			int temp = N-1;
			for(int row=N-1; row>=0; row--) {
				if(map[row][col] != 0) {
					map[temp][col] = map[row][col];
					if(row!=temp) map[row][col] = 0;
					temp--;
				}
			}
			
		}

	}
	
private static void moveLeft(int[][] map) {
		
	for(int row=0; row<N; row++) {
		Node upNode = new Node(map[row][0], 0);
		for(int col=1; col<N; col++) {
			
			// upNode가 null이라면 새로 만들어주기
			if(upNode == null || upNode.val == 0) {
				upNode = new Node(map[row][col], col);
			}
			
			int temp = map[row][col];
			
			// 빈칸이면 건너뛰기 
			if(temp == 0) continue;
			
			// 빈칸이 아닌데 값이 다르다면 pass
			if(temp != upNode.val) {
				upNode = new Node(temp, col);
			} 
			// 같은 값이면 
			else if(col != upNode.loc){
				map[row][upNode.loc] = 2*upNode.val;
				ans = Math.max(ans, map[row][upNode.loc]);
				map[row][col] = 0; // 필요한가?
				upNode = null;
			}
			
		}
		
		int temp = 0;
		for(int col=0; col<N; col++) {
			if(map[row][col] != 0) {
				map[row][temp] = map[row][col];
				if(col!=temp) map[row][col] = 0;
				temp++;
			}
		}
	}

	}

private static void moveRight(int[][] map) {
	
	for(int row=0; row<N; row++) {
		Node upNode = new Node(map[row][N-1], N-1);
		
		for(int col=N-1; col>=0; col--) {
			
			// upNode가 null이라면 새로 만들어주기
			if(upNode == null || upNode.val == 0) {
				upNode = new Node(map[row][col], col);
			}
			
			int temp = map[row][col];
			
			// 빈칸이면 건너뛰기 
			if(temp == 0) continue;
			
			// 빈칸이 아닌데 값이 다르다면 pass
			if(temp != upNode.val) {
				upNode = new Node(temp, col);
			} 
			// 같은 값이면 
			else if(col != upNode.loc){
				map[row][upNode.loc] = 2*upNode.val;
				ans = Math.max(ans, map[row][upNode.loc]);
				map[row][col] = 0; // 필요한가?
				upNode = null;
			}
			
		}
		
		// 이 열의 값이 같다면 합쳐진 상태 - 위에서부터 정렬해야함
		
		int temp = N-1;
		for(int col=N-1; col>=0; col--) {
			if(map[row][col] != 0) {
				map[row][temp] = map[row][col];
				if(col!=temp) map[row][col] = 0;
				temp--;
			}
		}
		
	}

	}
	
private static int[][] mapCopy(int[][] map) {
		
		int[][] copiedMap = new int[N][N];

		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, copiedMap[i], 0, N);
		}
		
		return copiedMap;
	}

	
}