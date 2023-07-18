import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		String[] input = br.readLine().split(" ");
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		int k = Integer.parseInt(br.readLine());
		int i = 0;
		int j = 1;
		int cnt = 0;
		while ( i < j && j < n ) {
			if (arr[i] + arr[j] == k) {
				cnt++;
				i++;
				j = i+1;
				continue;
			}
			if(j < n-1) {
				j++;
			} 
			else {
				i++;
				j = i+1;
			}
			
		}
		System.out.println(cnt);
		
		
	}

}
