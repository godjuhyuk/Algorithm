import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String A = st.nextToken();
		String B = st.nextToken();
		
		N = A.length();
		
		int[] temp = new int[N];
		for(int i=0; i<N; i++) {
			temp[i] = A.charAt(i) - '0';
		}
		
		Arrays.sort(temp);
		
		int a = Integer.parseInt(A);
		int b = Integer.parseInt(B);
		
		int max = -1;
		int candidate;
		
		do {
			if(temp[0] == 0) continue;
			
			candidate = getVal(temp);
			if(candidate < b && candidate != a) {
				max = Math.max(candidate, max);
			}
			
		} while(nextPermutation(temp));
		
		System.out.println(max);
	}
	
	private static boolean nextPermutation(int[] arr) {
		int i = N - 1;
		
		while(i>0 && arr[i-1] >= arr[i]) i--;;
		
		if(i==0) return false;
		
		int j = N - 1;
		
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j);
		
		int k = N - 1;
		
		while(i<k) {
			swap(arr, i++, k--);
		}
		
		return true;
		
	}
	
	private static void swap(int[] arr, int x, int y) {
		int tempNum = arr[x];
		arr[x] = arr[y];
		arr[y] = tempNum;
	}
	
	private static int getVal(int[] arr) {
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum += arr[i] * Math.pow(10, N-i-1);
		}
		return sum;
	}
}
