import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 설계시작: 오후 2시 11분
 * 설계종료: 오후 2시 33분
 * 
 * N* M 격자 
 * 
 * 다리 길이: 격자에서 다리가 차지하는 칸의 수
 * 			=> 길이는 2 이상, 방향 바뀌면 안됨
 * 방향이 가로인 다리 : 양 끝점이 가로방향으로 섬과 인접해야함 
 *		세로인 다리 : 			세로방향
 *
 * 다리 교차 가능 => 길이를 셀 때는 각 다리에 둘다 포함되어야함
 * 
 * 나라의 정보가 주어졌을 때 모든 섬을 연결하는 다리의 최솟값 구하기
 * 
 * 1<= N, M <= 10
 * 3<= N * M <= 100
 * 
 * 2<= 섬개수 <= 6
 * 
 * 
 * 문제 해결을 위한 고민)
 * 
 * 1. 다리를 이을 섬을 어떻게 고를것인가? (언제 최소값?)
 * => 무조건 세로면 세로로만 이어야함 ( 가로 세로 둘다되는 칸에는 다리를 이을 수 없음! )
 *=> 
 * 
 * 2. 다리를 어떻게 이을 것인가?
 * 
 * 3. 다 제대로 이었다고 가정할 때, 모든 섬이 연결되어있는지 검증 => BFS or DFS
 * 
 *
 * 무식한 방법)
 * 1번섬부터, 모든 영역을 탐색하며 가로, 세로로 다리를 지어본다.
 * visited로 해당 섬에 방문했으면 체크 & cnt + 1
 * cnt == 섬의 개수 일때 종료
 * 
 * 
 * 6! * (
 * 
 * 다리를 짓는 순서가 중요할까? 다 따져보자 .
 * 
 * 해결 프로세스)
 * 1. 다리 건설 탐색을 위한 순서를 뽑는다. => 6!
 * 2. 순서대로 섬마다 영역을 돌아가며 탐색한다. (다리건설가능여부 && 어떤 섬과 연결되는지 && 다리길이)
 * 3. 섬끼리 인접행렬로 from 섬에서 to 섬까지 다리를 연결하기 위한 최소비용 갱신 (0과 1 제외) 
 * 
 * 
 */
public class Main {
	static int N, M, islandNumber, ans;
	static int[][] map, deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static List<Island> islandList;
	
	static class Island {
		
		int num;
		List<int[]> areaInfo = new ArrayList<int[]>();
		
		public Island(int num) {
			this.num = num;
		}
		
		
	}
	
	private static void bfs(int[][] adjMatrix, boolean[] visited, int lengthSum, int startNo) {
		
		Queue<int[]> q = new ArrayDeque<>();
		visited[startNo] = true;
		q.add(new int[] {startNo, 0});
		int cnt = 0;
		while(!q.isEmpty()) {
			
			int[] temp = q.poll();
			lengthSum += temp[1];
			if(++cnt == islandList.size() && lengthSum != 0)
			{
				ans = Math.min(ans, lengthSum);
				return;
			}
			
			for(int i=2; i< islandNumber; i++) {
				
				if(!visited[i] && adjMatrix[temp[0]][i] > 0) {
					
					visited[i] = true;
					q.add(new int[] {i, adjMatrix[temp[0]][i]});
					
				}
				
			}
			
		}
		
	}
	
	
	private static void makeBridge(int[] islandOrderArr) {
		int[] visited = new int[islandNumber];
		int[][] adjMatrix = new int[islandNumber][islandNumber];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		for(int order : islandOrderArr) {
			Island nowIsland = islandList.get(order);
			for(int[] area : nowIsland.areaInfo) {
				for(int i = 0; i < 4; i++) {
					int[] d = deltas[i];
					
					int nr = area[0] + d[0];
					int nc = area[1] + d[1];
					
					// 맵 밖이거나 내 영역이라면 break
					if(isOutOfRange(nr, nc) || map[nr][nc] == nowIsland.num) continue;
					
					int tempLength = 0;
					while(!isOutOfRange(nr, nc)) {
						
						if(map[nr][nc] == 0) {
							tempLength++;
							nr += d[0];
							nc += d[1];
						} 
						else if(map[nr][nc] <= islandNumber) {
							
							int findIslandNumber = map[nr][nc];
							if(tempLength > 1) {
								
								boolean isAbleToBridge = true;
								
								if(isAbleToBridge && visited[findIslandNumber] > tempLength) {
									visited[findIslandNumber] = tempLength;
									adjMatrix[findIslandNumber][nowIsland.num] = tempLength;
									adjMatrix[nowIsland.num][findIslandNumber] = tempLength;
								}
							}


							break;
							
						}
						
						
						
					}
					
					
				}
				
			}
			
		}
		
		// 섬 다 이어졌는지 검증
		bfs(adjMatrix, new boolean[islandNumber], 0, 2);

	}
	
	
	private static boolean isOutOfRange(int r, int c) {
		
		return r < 0 || r >= N || c < 0 || c>=M;

	}
	
	private static void setIslandNum(int r, int c, int islandNumber) {
		
		
		for(int[] d : deltas) {
			int nr = r + d[0];
			int nc = c + d[1];
			
			if(isOutOfRange(nr, nc) || map[nr][nc] != 1) continue;
			
			map[nr][nc] = islandNumber;
			islandList.get(islandNumber-2).areaInfo.add(new int[] {nr, nc});
			setIslandNum(nr, nc, islandNumber);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ans = Integer.MAX_VALUE;
		islandList = new ArrayList<Island>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		islandNumber = 2;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					map[i][j] = islandNumber;
					islandList.add(new Island(islandNumber));
					islandList.get(islandNumber-2).areaInfo.add(new int[] {i, j});
					setIslandNum(i, j, islandNumber);
					islandNumber++;
				}
			}
		}
		
		int[] npArr = new int[islandNumber-2];
		for(int i=0; i<islandNumber-2; i++) npArr[i] = i;
		
		do {
			
			makeBridge(npArr);
			
		} while(np(npArr));
		
		if(ans != Integer.MAX_VALUE) System.out.println(ans);
		else System.out.println(-1);
		
	}
	
	private static boolean np(int[] arr) {
		
		int N = arr.length - 1;
		
		int i = N;
		
		while(i>0 && arr[i-1] >= arr[i]) i--;
		
		if( i == 0) return false;
		
		int j = N;
		
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j);
		
		int k = N;
		
		while(i<k) swap(arr, i++, k--);
		
		return true;

	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
}
