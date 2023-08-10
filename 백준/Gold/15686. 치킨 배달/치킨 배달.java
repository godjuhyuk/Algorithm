import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 조합 문제로, 치킨집 중 M개를 뽑아서 치킨 거리를 계산한다.
 * 
 * 재귀를 이용해서 풀면 될듯?
 * 
 * 구현해야할 것:
 * 
 * 1. MAP 입력받기 & 집개수, 치킨집 개수 count
 * 2. 재귀를 이용한 재귀 구현
 * 	- 기저조건 : count == M
 * 3. 최단거리 갱신
 * 
 * 
 * 예상 시간 복잡도 : O(N^2 + 13CM * 2N) 
 * 
 * @author SSAFY
 */
public class Main {
	
	private static int N, M, ans;
	private static int[] combList;
	private static int[][] map;
	private static List<int[]> homeList, chickenList;
	
	private static int getDistance(int[] home, int[] chicken) {
		
		return Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
	}
	
	private static int getBestDistance() {
		int bestDistance = 0;
		
		for(int i=0; i<homeList.size(); i++) {
			int minDist = Integer.MAX_VALUE;
			for(int idx = 0; idx < M; idx++) {
				minDist = Math.min(minDist, getDistance(homeList.get(i), chickenList.get(combList[idx])));
			}
			bestDistance += minDist;
		}
		return bestDistance;
	}
	
	
	private static void comb(int cnt, int start) {
		
		if(cnt == M) {
			ans = Math.min(ans, getBestDistance());
			return;
		}
		
		for(int i=start; i< chickenList.size(); i++) {
			combList[cnt] = i;
			comb(cnt+1, i+1);
		}
 
	}
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		homeList = new ArrayList<>();
		chickenList = new ArrayList<>();
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		ans = Integer.MAX_VALUE;
		
		map = new int[N][N];
		
		// 치킨집 및 손님집 정보 추가 
		for(int i=0; i<N; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 1) {
					homeList.add(new int[] {i, j});
				}
				else if(map[i][j] == 2) {
					chickenList.add(new int[] {i, j});
				}
			}
		}
		
		
		combList = new int[M];
		
		comb(0, 0);
		
		System.out.println(ans);
		
	}

}