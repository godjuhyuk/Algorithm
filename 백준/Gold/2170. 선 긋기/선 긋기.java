import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 * 
 * 문제 해석: 매우 큰 도화지에 자를 대고 직선을 긋는다.
 * 자의 한 점에서 다른 한 점까지 긋는다.
 * 선이 있는 위치에 겹쳐서 그릴 수도 있다.
 * 여러번 그은 곳과 한 번 그은 곳의 차이를 구별할 수 없다고 하자.
 * 
 * 문제 해결을 위한 고민 : 
 * 수직선을 전체 조회해야하는데, 길이가 20억이다.
 * 무식하게 풀면 만약 -20억, -1999999999가 주어지고 19999999999, 20억이 주어지면 
 * 저 점 2개떄문에 20억번 연산해야한다.
 * 
 * 입력값도 100만개이므로, 결국 입력값을 받을때마다 계산하는 법을 떠올려야한다.
 * 선의 정보 100만개가 있다고 하자.
 * 이 100만개를 모아놓은 자료구조가 있다면?
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	private static int max;
	static class Point implements Comparable<Point> {
		int a;
		int b;
		
		public Point(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Point o) {
			if(this.a == o.a) return this.b - o.b; 
			return this.a - o.a;
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		int N = Integer.parseInt(br.readLine());
		
		Point[] arr = new Point[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i] = new Point(a, b);
		}
	
		Arrays.sort(arr);
		max = Integer.MIN_VALUE;
		Point lastPoint = null;
		for(int i=0; i<N; i++) {
			Point newPoint = arr[i];
			if(i>0) ans = applyIntersection(lastPoint, newPoint, ans);
			else ans += newPoint.b - newPoint.a;
			lastPoint = newPoint;
			if(lastPoint.b > max) max = lastPoint.b;
		}
		System.out.println(ans);
	}

	private static int applyIntersection(Point lastPoint, Point newPoint, int ans) {
		
		// 겹치는 곳이 없는 경우
		if(max <= newPoint.a) return ans + newPoint.b - newPoint.a;
		else {
			// 그 전 선분에 포함될 경우
			if(newPoint.b <= max) return ans;
			
			// 일부만 포함될 경우
			return ans + newPoint.b - max;
		}
		
	}
	
}