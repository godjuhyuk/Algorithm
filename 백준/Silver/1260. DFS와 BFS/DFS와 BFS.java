import java.io.*;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
/* Name of the class has to be "Main" only if the class is public. */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int S = Integer.parseInt(input[2]);

        boolean[][] pointArr = new boolean[N+1][N+1];
        for(int i = 0; i< M; i++){
            input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            pointArr[A][B] = true;
            pointArr[B][A] = true;
        }

        StringBuilder dfs = new StringBuilder();
        StringBuilder bfs = new StringBuilder();
        boolean[] visitedDfs = new boolean[N+1];
        boolean[] visitedBfs = new boolean[N+1];
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        dfs.append(S);
        bfs.append(S);
        visitedDfs[S] = true;
        visitedBfs[S] = true;
        
        // Stacking && Queuing
        for(int i=N; i>=1; i--){
        	if(pointArr[S][i]){
        		stack.push(i);
        	}
        	if(pointArr[S][N-i+1]){
        		queue.offer(N-i+1);
        	}
        }
        
        // Stack과 Queue를 이용한 DFS & BFS
		while(!stack.isEmpty()){
			S = stack.pop();
			if(!visitedDfs[S]) {
				dfs.append(" ").append(S);
				visitedDfs[S] = true;
			}
			for(int i=N; i>=1; i--){
				if(pointArr[S][i] && !visitedDfs[i]){
					stack.push(i);
				}
			}
		}
		
		while(!queue.isEmpty()){
			S = queue.poll();
			if(!visitedBfs[S]){
				bfs.append(" ").append(S);
				visitedBfs[S] = true;
			}
			for(int i=1; i<N+1; i++){
				if(pointArr[S][i] && !visitedBfs[i]){
					queue.offer(i);
				}
			}
		}
        System.out.println(dfs.toString());
        System.out.println(bfs.toString());
    }
}