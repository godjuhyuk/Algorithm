import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final String YES = "yes";
	private static final String NO = "no";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input;
		
		while( !"0".equals(input = br.readLine())) {
			
			int length = input.length();
			
			if(length == 1) sb.append(YES).append('\n');
			else {
				
				boolean flag = true;
				
				int i = length / 2 - 1;
				int j = length / 2;
				
				if(length % 2 != 0) j++;
				
				while(i >= 0) {
					if(input.charAt(i--) != input.charAt(j++)){
						flag = false;
						break;
					}
				}
				
				if(flag) sb.append(YES).append('\n');
				else sb.append(NO).append('\n');
				
			}
		}
		
		System.out.println(sb);
		
	}

}