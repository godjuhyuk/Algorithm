import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int S;
	private static BufferedWriter bw;
	
	private static void func(String cmd, String[] input) throws IOException {
		
		switch (cmd.charAt(1)) {
		case 'd':
			int x = Integer.parseInt(input[1]);
			if((S & 1 << x) == 0) S |= 1 << x;
			break;
		case 'h':
			x = Integer.parseInt(input[1]);
			if((S & 1 << x) > 0) bw.write(1 + "\n");
			else bw.write(0 + "\n");
			break;
		case 'e':
			
			x = Integer.parseInt(input[1]);
			if((S & 1 << x) > 0) S -= 1 << x;
			break;
		case 'o':
			x = Integer.parseInt(input[1]);
			if((S & 1 << x) > 0) S -= 1 << x;
			else S |= 1 << x;
			
			break;
		case 'm':
			S = 0;
			break;
		case 'l':
			S = (int)Math.pow(2, 21) - 1;
			break;
		}

	}
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = 0;
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			
			String[] input = br.readLine().split(" ");
			func(input[0], input);
			
		}
		
		br.close();
		bw.close();
		
	}
}
