import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	private static int[] getPi(String p) {
		
		int m = p.length();
		int j = 0;
		
		int[] pi = new int[m];
		
		for(int i=1; i < m; i++) {
			
			while(j>0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(p.charAt(i) == p.charAt(j)) {
				pi[i] = ++j;
			}
			
		}

		return pi;
		
	}
	
	private static List<Integer> kmp(String T, String P) {

		List<Integer> ans = new ArrayList<Integer>();
		
		int[] pi = getPi(P);
		
		int n = T.length();
		int m = P.length();
		int j = 0;
		
		for(int i=0; i<n; i++) {
			
			while(j > 0 && T.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			
			if(T.charAt(i) == P.charAt(j)) {
				if( j == m-1) {
					ans.add(i - m + 1);
					j = pi[j];
				} else {
					j++;
				}
			}
			
			
		}
		
		return ans;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String T = br.readLine();
		String P = br.readLine();
		
		List<Integer> ans =  kmp(T, P);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(ans.size()).append('\n');
		
		for(int loc : ans) {
			sb.append(loc+1).append('\n');
		}
		
		System.out.println(sb);
		
	}
	
}