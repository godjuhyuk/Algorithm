import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
 * 도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다. 
 * 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
 * C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
 * 
 * 입력)
 * 첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)
 * 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다. 
 * 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] house = new int[N];
		for(int i=0; i<N; i++) house[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(house);
		
		int ans = 0, left = 0, right = (house[N-1] - house[0]) + 1;
		
		while(left < right) {
			int now = house[0], cnt = 1;
			int mid = (left + right ) / 2; // 최소 거리
			
			for(int i=1; i<N; i++) {
				if(house[i] - now >= mid) {
					cnt++;
					now = house[i];
				}
			}
			
			if(cnt >= C) {
				// 조건을 만족했다면 mid를 늘려봐야함
				ans = Math.max(ans, mid);
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		System.out.println(ans);
		
	}
}