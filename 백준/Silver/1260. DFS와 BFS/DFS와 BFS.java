import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
public class Main {
	static int n, m, s;
	static boolean[] visitedDfs;
	static boolean[][] pointArr;
	static StringBuilder sb;
	
	public static void dfs(int s) {
		for(int i=1; i<n+1; i++) {
			if(pointArr[s][i] && !visitedDfs[i]) {
				visitedDfs[i] = true;
				sb.append(i).append(" ");
				dfs(i);
			}
		}
	}
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        s = Integer.parseInt(input[2]);

        pointArr = new boolean[n+1][n+1];
        for(int i = 0; i< m; i++){
            input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            pointArr[A][B] = true;
            pointArr[B][A] = true;
        }
        
        // DFS - recursion
        
        sb = new StringBuilder();
        visitedDfs = new boolean[n+1];
        sb.append(s).append(" ");
        visitedDfs[s] = true;
        dfs(s);
        
        
        
        // BFS - Queue
        StringBuilder bfs = new StringBuilder();
        boolean[] visitedBfs = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();
        bfs.append(s);
        visitedBfs[s] = true;
        
        // Queue add
        for(int i=1; i<=n; i++){
        	if(pointArr[s][i]){
        		queue.offer(i);
        	}
        }
        
        // BFS
		while(!queue.isEmpty()){
			int point = queue.poll();
			if(!visitedBfs[point]){
				bfs.append(" ").append(point);
				visitedBfs[point] = true;
			}
			for(int i=1; i<n+1; i++){
				if(pointArr[point][i] && !visitedBfs[i]){
					queue.offer(i);
				}
			}
		}
		
        System.out.println(sb.toString());
        System.out.println(bfs.toString());
    }
}