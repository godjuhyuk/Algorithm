import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 문제 설명
 * 
 * 식목일에 꽃을 심으면 정확히 1년후 식목일에 핌
 * 씨앗이 3개밖에 없음.
 * (1, 1) ~ (N, N) 에 꽃을 심는데 십자 모양으로 핌.
 * 꽃잎들이 서로 닿으면 (혹은 꽃술과 닿으면) 두 꽃다 죽어버림.
 * 
 * 꽃 하나당 5평의 땅(십자)을 대여해야하며, 평당 가격은 전부 다름
 * 모든 꽃이 피게하면서 제일 싸게 대여하고 싶음.
 * 
 * 입력값 범위
 * 6 <= N <= 10
 * 0 <= G <= 200
 *
 * 해결 방법
 * 1. N-1 * N-1 범위 내에서 조합을 통해 3개를 뽑은 뒤
 *    서로 죽는 위치인지, 아니라면 가격을 구해서 최소값 갱신
 *    
 * 1의 경우, 시간복잡도는 (N-1)^2 C 3 이고
 * 			최악의 경우 81C3 => 81*80*79 => 약 50만
 * 
 * 하지만 뻔히 죽는 위치도 전부 고려하므로 좀 비효율적일듯?
 * 
 * 2.
 *  N-1^2 을 사방탐색으로 돌아가며 땅 대여료 계산
 *  그 후 위치정보를 보존하면서 가격 오름차순 정렬 할 수 없을까?
 *  => 각 위치와 그에 대한 가격 정보를 객체로 저장하고, comparator를 통해 저장해보자.
 *  => 그리고 객체배열을 순회하며 답이 나올떄까지 돌아보자.
 *  => 시간복잡도 : (N-1)^2 * 4 (4방탐색) + (N-1)^3
 *  	=> 최악의 경우 : 81*4 + 9^3 = 1000도 안됨? 해보자
 * 		=> 알고보니 isSafe의 계산횟수 고려안함
 * 		=> 81*4 + 9^3 * 4 = 암튼 1000쯤임
 *		=> 이것도 결국 조합으로 풀어야했음ㅠ
 */

public class Main {
	static int n;
	static int[][] deltas = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
	
	public static class Flower{
		int row;
		int col;
		int totalPrice;
		
		public Flower(int r, int c, int totalPrice) {
			this.row = r;
			this.col = c;
			this.totalPrice = totalPrice;
		}
		
		public boolean isSafe(Flower f) {
			int[][] tempMap = new int[n+1][n+1];
		
			// 내 꽃술 & 꽃잎 위치를 전부 -1로 초기화
			tempMap[this.row][this.col] = -1;
			for(int[] d : deltas ) {
				tempMap[this.row+d[0]][this.col+d[1]] = -1;
			}
			
			
			// 비교 대상의 꽃술 & 꽃잎 위치가 -1이라면 false return
			
			// 꽃술
			if(tempMap[f.row][f.col] == -1) {
				return false;
			}
			
			// 꽃잎
			for(int[] d : deltas ) {
				if(tempMap[f.row + d[0]][f.col + d[1]] == -1) {
					return false;
				}
			}
			
			
			return true;
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n+1][n+1];
		Flower[] flowers = new Flower[ (n-2) * (n-2) ];
		
		for(int i=1; i<n+1; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(input[j-1]);
			}
		}
		
		int size = 0;
		for(int i=2; i<n; i++) {
			for(int j=2; j<n; j++) {
				int priceSum = map[i][j];
				for(int[] d : deltas) {
					priceSum += map[i+d[0]][j+d[1]];
				}
				flowers[size++] = new Flower(i, j, priceSum);
			}
		}
		
		// 가격 오름차순 정렬
		Arrays.sort(flowers, new Comparator<Flower>() {
			@Override
			public int compare(Flower f1, Flower f2) {
				return f1.totalPrice - f2.totalPrice;
			}
		});
		
		int ans = Integer.MAX_VALUE;
		
		// 조합 순회하며 최소값 
		for(int i=0; i<flowers.length-2; i++) {
			for(int j=i+1; j<flowers.length-1; j++) {
				for(int k=j+1; k<flowers.length; k++) {
					Flower f1 = flowers[i];
					Flower f2 = flowers[j];
					Flower f3 = flowers[k];
					
					if(f1.isSafe(f2) && f2.isSafe(f3) && f3.isSafe(f1)) {
						ans = Math.min(ans, f1.totalPrice + f2.totalPrice + f3.totalPrice) ;
					}
					
				}
			}
		}
		System.out.println(ans);
	}
}