import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length(); i++) {
			char temp = input.charAt(i);
			if(temp < 97) sb.append((char)(temp + 32));
			else sb.append((char)(temp - 32));
			
		}
		System.out.println(sb);
		
	}
}
