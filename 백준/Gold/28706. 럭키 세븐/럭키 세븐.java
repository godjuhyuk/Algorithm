import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static int N;
	private static boolean gameOver;
	private static boolean[][] visited;
	private static StringBuilder sb;
	
	private static int calculate(String operator, int num, int now) {
		
		if(operator.equals("*")) {
			now *= num;
		} else {
			now += num;
		}
		
		return now;
	}
	
	private static void dfs(int depths, int K, String[][] selects) {
		
		if(depths == N) {
			
			if(K%7 ==0 && !gameOver) {
				sb.append("LUCKY").append('\n');
				gameOver = true;
			}
			
			return;
			
		} else if(gameOver == true || visited[depths][K] == true) {
			
			return;
			
		} else if(visited[depths][K] == false) {
			
			visited[depths][K] = true;
			
		}
		
		
		int nextNum = calculate(selects[depths][0], Integer.parseInt(selects[depths][1]), K);
		
		dfs(depths+1, nextNum%7, selects);
		
		nextNum = calculate(selects[depths][2], Integer.parseInt(selects[depths][3]), K);
		
		dfs(depths+1, nextNum%7, selects);
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<T; t++) {
			
			N = Integer.parseInt(br.readLine());
			gameOver = false;
			visited = new boolean[N+1][7];
			
			String[][] selects = new String[N][];
			
			for(int i=0; i<N; i++) {
				selects[i] = br.readLine().split(" ");
			}
			
			dfs(0, 1, selects);
			
			if(!gameOver) {
				sb.append("UNLUCKY").append('\n');
			}
		}
		System.out.println(sb);
	}
}