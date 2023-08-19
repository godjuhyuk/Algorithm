import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
	private static int N, M;
	private static boolean[] knowTrueArr;
	private static boolean[][] adjMatrix;
	
	private static void dfs(int knowNum) {
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(knowNum);
		
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			for(int i=1; i<=N; i++) {
				if(adjMatrix[i][temp] && !knowTrueArr[i]) {
					knowTrueArr[i] = true;
					stack.add(i);
				}
			}
		}
		
		return;

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		// 진실 array
		knowTrueArr = new boolean[N+1];
		
		adjMatrix = new boolean[N+1][N+1];
		
		// 진실 num index 갱신
		input = br.readLine().split(" ");
		
		if(input[0].equals("0")) {
			System.out.println(M);
			return;
		}
		
		for(int i=1; i<input.length; i++) {
			knowTrueArr[Integer.parseInt(input[i])] = true;
		}
		
		List<String[]> ansCandidate = new ArrayList<String[]>();
		
		for(int i=0; i<M; i++) {
			boolean flag = false;
			input = br.readLine().split(" ");
			for(int j=1; j<input.length-1; j++) {
				for(int k=2; k<input.length; k++) {
					int num1 = Integer.parseInt(input[j]);
					int num2 = Integer.parseInt(input[k]);
					
					adjMatrix[num1][num2] = true;
					adjMatrix[num2][num1] = true;
					
					if(!flag && (knowTrueArr[num1] || knowTrueArr[num2])) {
						flag = true;
					}
					
				}
			}
			
			if(!flag) {
				// 거짓말해도 되는 파티 후보 추가
				ansCandidate.add(input);
			}
			
			for(int k=1; k<=N; k++) {
				if(knowTrueArr[k]) {
					dfs(k);
				}
			}
		}
		
		int ans = 0;
		
		loop:
		for(String[] candidate : ansCandidate) {
			for(int j=1; j<candidate.length; j++) {
				int num = Integer.parseInt(candidate[j]);
				if(knowTrueArr[num] == true) {
					continue loop;
				}
			}
			ans++;
		}
		System.out.println(ans);
	}
}