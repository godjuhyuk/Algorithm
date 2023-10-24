import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input;
		while((input = br.readLine()).charAt(0) != '0') {
			StringTokenizer st = new StringTokenizer(input);
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a>b) sb.append("Yes").append('\n');
			else sb.append("No").append('\n');
		}
		
		System.out.println(sb);
		
		
		
	}
	
}