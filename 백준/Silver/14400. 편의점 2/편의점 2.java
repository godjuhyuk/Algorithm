import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 문제 설명
 * 
 * n개의 고객 좌표가 주어진다.
 * 최소 거리를 만족하는 편의점을 세워야한다.
 * 
 * 거리를 구하는 공식 : |x1-x2| + |y1-y2|
 * 
 * n = 10만
 * -1,000,000 <= x, y <= 1,000,000
 * 
 * 
 * ! 고려해야할 사항 !
 * 
 * 1. 일단 크기가 크기인 만큼 long 사용해야할듯하다
 * 2. 200만 x 200만 좌표이므로 조합으론 불가능 할 것 같다
 * => 최대한 가운데 세워야하나?
 * => 정렬 후 가장 중앙 위치 점?

 * 
 * 
 */

public class Main {
	
	private static long getDist(int[] c1, int[] c2) {
		return Math.abs(c1[0] - c2[0]) + Math.abs(c1[1] - c2[1]);
	}
	
	public static void main(String[] args) throws IOException
	{	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		int[][] customerInfo = new int[n][2];
		
		for(int i=0; i<n; i++) {
			String[] input = br.readLine().split(" ");
			customerInfo[i][0] = Integer.parseInt(input[0]);
			customerInfo[i][1] = Integer.parseInt(input[1]);
		}
		
		Arrays.sort(customerInfo, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			};
		});
		
		int x = customerInfo[n/2][0];
		
		Arrays.sort(customerInfo, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			};
		});
		
		int y = customerInfo[n/2][1];
		
		int[] bestPoint = new int[] {x, y};
		
		long ans = 0;
		for(int i=0; i<n; i++) {
			ans += getDist(customerInfo[i], bestPoint);
		}
		System.out.println(ans);
	}
}