import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int[] ans = new int[10];
		
		String result = String.valueOf(n*m*k);
		for(int i=0; i<result.length(); i++) {
			ans[Integer.valueOf(Integer.parseInt(String.valueOf(result.charAt(i))))]++;
		}
		for(int i=0; i<ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
}
