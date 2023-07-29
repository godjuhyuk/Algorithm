import java.util.*;
import java.io.*;
public class Main {
	static int n, m;
	static List<Integer> combList;
//	static Stack<Integer> combStack;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void comb(int depths) {
		
		if(depths == m) {
			for(Integer num : combList) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<n+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combList.add(i);
				comb(depths+1);
				visited[i] = false;
				combList.remove(combList.size()-1);
			}
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		combList = new ArrayList<Integer>();
//		combStack = new Stack<>();
		visited = new boolean[n+1];
		
		comb(0);
		System.out.println(sb.toString());
	}
}