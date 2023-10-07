import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 정렬 후 투포인터를 한다면 ?
 * 
 * -999 -500 0 1 2 3 4 5 6 1000 1001 1002
 * 
 * 
 * 
 */
public class Main {
	
	private static Long[] arr, ans;
	
	public static void main(String[] args) throws IOException 
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new Long[n];
		ans = new Long[3];
		
		for(int i=0; i<n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		
		long min = Long.MAX_VALUE;
		for(int k=0; k<n-2; k++) {
				int left = k + 1;
				int right = n - 1;
				
				//이진탐색으로 0에 가까운 특성값 찾기
				while(left < right){
					Long sum = arr[left] + arr[right] + arr[k];
					
					if(Math.abs(sum) < min){
						ans[0] = arr[left];
						ans[1] = arr[right];
						ans[2] = arr[k];
						min = Math.abs(sum);
					}
					
					if(sum < 0) {
						left++;
					}
					else {
						right--;
					}
				}
		}
		
		Arrays.sort(ans);
		
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
		
	}


}