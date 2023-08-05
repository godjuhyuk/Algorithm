import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	private static int N, limit;
	private static int[] numArr, permArr;
	private static boolean[] isSelected;
	private static StringBuilder sb;
	
	private static void stringBuild() {
		
		for(int i=0; i<limit; i++) {
			sb.append(permArr[i]).append(' ');
		}
		sb.append('\n');

	}
	
	private static void perm(int depths) {
		if(depths == limit) {
			stringBuild();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				permArr[depths] = numArr[i]; 
				perm(depths+1);
				isSelected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int[] input = Arrays.stream(br.readLine()
							.split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
		
		N = input[0];
		isSelected = new boolean[N];
		
		limit = input[1];
		permArr = new int[limit];
		
		
		numArr = Arrays.stream(br.readLine()
				.split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		Arrays.sort(numArr);
		
		perm(0);		
		
		System.out.println(sb);
	}
}