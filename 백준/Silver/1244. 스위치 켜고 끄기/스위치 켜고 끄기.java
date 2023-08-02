import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int[] switchList;
	
	private static void change(int sex, int num) {
		
		if(sex == 1) {
			
			for(int i=0; i<switchList.length; i++) {

				if( (i+1) % num == 0) {
					
					switchList[i] = 1 - switchList[i];
				}
			}
		} else {
			
			int idx = num - 1;
			int length = 1;
			
			// 시작점 바꾸기 
			switchList[idx] = 1 - switchList[idx];
			while(true) {
				
				// 범위 초과 or 대칭이 아니면 break
				if(idx+length >= switchList.length || idx-length < 0 || switchList[idx+length] != switchList[idx-length]) {
					break;
				}
				
				switchList[idx+length] = 1 -switchList[idx+length];
				switchList[idx-length] = 1 -switchList[idx-length];
				length++;
				
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		switchList = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i=0; i<m; i++) {
			String[] input = br.readLine().split(" ");
			int sex = Integer.parseInt(input[0]);
			int num = Integer.parseInt(input[1]);
			
			change(sex, num);
		}
		
		for(int i=0; i<switchList.length; i++) {
			if(i>0 && i % 20 == 0) {
				sb.append("\n");
			}
			sb.append(switchList[i]).append(" ");
		}
		System.out.println(sb.toString());
		
	}
}