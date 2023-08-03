import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	
//	10
//	5
//	0 0 100 100 70 40 30 10 10 5 90 70 50 20
//	6
//	88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
//	10
//	39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
//	...
	private static int n, ans;
	private static int[] combList;
	private static boolean[] isSelected;
	private static Location[] customerInfo;
	private static Location homeInfo, compInfo;
	
	private static int calculateDist(Location a, Location b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	
	public static int getDistance() {
		int distSum = calculateDist(compInfo, customerInfo[combList[0]]);
		for(int i=0; i<n-1; i++) {
			distSum += calculateDist(customerInfo[combList[i]], customerInfo[combList[i+1]]);
		}
		
		distSum += calculateDist(customerInfo[combList[n-1]], homeInfo);
		return distSum;
	}
	
	
	private static  void perm(int cnt) {
		if(cnt == n) {
			ans = Math.min(ans, getDistance());
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				combList[cnt] = i+2;
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			ans = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			combList = new int[n];
			isSelected = new boolean[n];
			customerInfo = new Location[n+2];
			String[] input = br.readLine().split(" ");
			for(int i=0; i<input.length; i+=2) {
				customerInfo[i/2] = new Location(Integer.parseInt(input[i]), Integer.parseInt(input[i+1]));
			}
			compInfo = customerInfo[0];
			homeInfo = customerInfo[1];
			
			perm(0);
			
			sb.append("#").append(testCase).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
		
		
	public static class Location {
		int x;
		int y;
		
		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
	}
}