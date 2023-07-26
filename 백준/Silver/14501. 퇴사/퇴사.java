import java.io.*;
import java.util.*;

public class Main {
	static int n, max;
	static int[][] info;
	public static void dfs(int idx, int sum) {
		if(idx >= n || idx + info[idx][0] > n) {
			max = Math.max(max, sum);
			return;
		}
		sum += info[idx][1];
		if(idx+info[idx][0] >= n) {
			dfs(idx+info[idx][0], sum);
		} else {
			for(int i=0; i<n-idx+info[idx][0]; i++) {
				dfs(idx+info[idx][0]+i, sum);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		info = new int[n][2];
		for(int i=0; i<n; i++) {
			input = br.readLine().split(" ");
			info[i][0] = Integer.parseInt(input[0]);
			info[i][1] = Integer.parseInt(input[1]);
		}
		for(int i=0; i<n; i++) {
			dfs(i, 0);
		}
		
		System.out.println(max);
	}

}
