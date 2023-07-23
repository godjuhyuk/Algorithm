import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] input;
		for(int i=0; i<n; i++) {
			int sum = 1, ans = 0;
			input = br.readLine().toCharArray();
			for(int idx = 0; idx < input.length; idx++) {
				if(input[idx] == 'O') {
					ans += sum++;
				} else {
					sum = 1;
				}
			}
			System.out.println(ans);
		}
	}
}