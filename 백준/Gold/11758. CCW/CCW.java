import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] pointArr = new int[3][2];

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pointArr[i][0] = x;
			pointArr[i][1] = y;
		}


		int x1 = pointArr[0][0];
		int x2 = pointArr[1][0];
		int x3 = pointArr[2][0];

		int y1 = pointArr[0][1];
		int y2 = pointArr[1][1];
		int y3 = pointArr[2][1];
		
		int a = x1*y2 + x2*y3 + x3*y1 - (x2*y1 + x3 * y2 + x1*y3);
		if(a == 0 ) System.out.println(0);
		else if(a > 0) System.out.println(1);
		else System.out.println(-1);

	}

}
