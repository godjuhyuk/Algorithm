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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String T = br.readLine();
		System.out.println(L - getPi(T)[L-1]);
	}
	
}
