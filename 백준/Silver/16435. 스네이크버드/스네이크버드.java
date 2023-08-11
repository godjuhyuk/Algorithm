import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 정렬 후 for문 돌면서 자기 높이보다 낮으면 먹고 높이 늘려주면 되지 않나?
 * 
 * @author SSAFY
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int L = Integer.parseInt(input[1]);
		
		int[] apples = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		
		for(int appleHeight: apples) {
			if(appleHeight <= L) L++;
		}
		
		System.out.println(L); 
		
		
	}
}