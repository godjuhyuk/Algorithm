import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Arrays;

	public class Solution {
		
		private static int n, ans;
		private static int[] combList;
		private static boolean[] isSelected;
		private static Location[] customerInfo;
		private static Location homeInfo, compInfo;
		
		// 거리 계산
		private static int calculateDist(Location a, Location b) {
			return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
		}
		
		
//		public static int getDistance() {
//			// 거리 합 계산
//			int distSum = calculateDist(compInfo, customerInfo[combList[0]]);
//			for(int i=0; i<n-1; i++) {
//				if(distSum > ans) {
//					return ans+1;
//				}
//				distSum += calculateDist(customerInfo[combList[i]], customerInfo[combList[i+1]]);
//			}
//			distSum += calculateDist(customerInfo[combList[n-1]], homeInfo);
//			return distSum;
//		}
		
		
		private static  void perm(int cnt, int sum, Location lastLoc) {
			if(cnt == n) {
				sum += calculateDist(lastLoc, homeInfo);
				ans = Math.min(ans, sum);
				return;
			}
			
			if(ans != Integer.MAX_VALUE && sum >= ans) {
				return;
			}
			
			for(int i=0; i<n; i++) {
				if(!isSelected[i]) {
					// i+2인 이유 : 0, 1 인덱스에는 집과 회사가 있어서
					isSelected[i] = true;
					perm(cnt+1, sum+calculateDist(customerInfo[i+2], lastLoc), customerInfo[i+2]);
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
				
				// Location 정보 등록
				for(int i=0; i<input.length; i+=2) {
					customerInfo[i/2] = new Location(Integer.parseInt(input[i]), Integer.parseInt(input[i+1]));
				}
				
				compInfo = customerInfo[0];
				homeInfo = customerInfo[1];
				
				perm(0, 0, compInfo);
				
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
		}
	}