import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	private static int n;
	private static long ans;
	private static List<Integer> food1, food2;
	private static boolean[] isSelected;
	private static int[][] recipeMap;
	
	private static long cook() {
		
		for(int i=0; i<n; i++) {
			if(isSelected[i]) {
				food1.add(i);
			} else {
				food2.add(i);
			}
		}
		long sum1 = 0;
		long sum2 = 0;
		for(int i=0; i<n/2; i++) {
			for(int j=i+1; j<n/2; j++) {
				sum1 += recipeMap[food1.get(i)][food1.get(j)] + recipeMap[food1.get(j)][food1.get(i)];
				sum2 += recipeMap[food2.get(i)][food2.get(j)] + recipeMap[food2.get(j)][food2.get(i)];
			}
		}
		food1.clear();
		food2.clear();
		return Math.abs(sum1-sum2);
	}
	
	private static void makeRecipe(int cnt, int start) {
		if(cnt == n/2) {
			ans = Math.min(ans, cook());
		}

		for(int i=start; i<n; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				makeRecipe(cnt+1, i+1);
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
			String[] input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			ans = Integer.MAX_VALUE;
			recipeMap = new int[n][n];
			isSelected = new boolean[n];
			food1 = new ArrayList();
			food2 = new ArrayList();
			for(int i=0; i<n; i++) {
				input = br.readLine().split(" ");
				for(int j=0; j<n; j++) {
					recipeMap[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			makeRecipe(0, 0);
			sb.append("#").append(testCase).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
}