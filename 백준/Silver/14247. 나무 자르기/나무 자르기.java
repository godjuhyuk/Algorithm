import java.util.*;
import java.io.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long sum = 0;
		
		// treeinfo[x][0] : 초기 길이
		// treeinfo[x][1] : 1일 성장 길이
		int[][] treeInfo = new int[n][2]; 
		String[] input = br.readLine().split(" ");
		
		// 나무 길이 정보 
		for(int i=0; i<n; i++) {
			treeInfo[i][0] = Integer.parseInt(input[i]);
		}
		
		input = br.readLine().split(" ");
		
		// 나무 성장 정보
		for(int i=0; i<n; i++) {
			treeInfo[i][1] = Integer.parseInt(input[i]);
		}
		
		// 성장 길이 오름차순 정렬
		Arrays.sort(treeInfo, new Comparator<int[]>(){
			@Override
			public int compare(int[] tree1, int[] tree2) {
				if(tree1[1] == tree2[1]) {
					return tree1[0] - tree2[0];
				}
				return tree1[1] - tree2[1];
			}
		});
		
		for(int day = 0; day < n; day++) {
			sum += treeInfo[day][0] + (long)treeInfo[day][1]*day;
		}
		System.out.println(sum);
 	}
}
