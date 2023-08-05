import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
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

		String[] input = br.readLine().split(" ");
		
		
		N = Integer.parseInt(input[0]);
		limit = Integer.parseInt(input[1]);
		
		numArr = new int[N];
		permArr = new int[limit];
		isSelected = new boolean[N];
		
		input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(input[i]);
		}
		
		
		Arrays.sort(numArr);
		
		perm(0);		
		
		System.out.println(sb);
	}
}