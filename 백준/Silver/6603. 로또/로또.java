import java.util.*;
import java.io.*;
public class Main {
	
	static int[] temp;
	static boolean[] visited;
	static Stack<Integer> stack;
	static StringBuilder sb;
	public static void dfs(int start, int depths) {
		if(depths == 6) {
			for(int i=0; i<6; i++) {
				sb.append(stack.get(i)).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<temp.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				stack.add(temp[i]);
				dfs(i, depths+1);
				stack.pop();
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true) {
			String[] input = br.readLine().split(" ");
			if(input[0].equals("0")) {
				break;
			}
			temp = new int[input.length-1];
			stack = new Stack<>();
			visited = new boolean[input.length-1];
			for(int i=1; i<input.length; i++) {
				temp[i-1] = Integer.parseInt(input[i]);
			}
			
			dfs(0, 0);
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
        br.close();
	}
}