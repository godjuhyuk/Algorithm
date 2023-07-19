import java.io.*;
import java.util.*;
public class Main {
    static int n, m, cnt;
    static char[][] map;
    static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static boolean[][] visited;
    
    public static boolean isOutOfRange( int r, int c) {
        return r<0 || r>=n || c<0 || c>=m;
    }
    public static void bfs(int a, int b) {
    	Queue<int[]> bfsQueue = new LinkedList();
    	bfsQueue.add(new int[] {a, b});
    	visited[a][b] = true;
    	cnt = 1;
    	while(!bfsQueue.isEmpty()) {
    		
    		int qSize = bfsQueue.size();
    		for(int i=0; i<qSize; i++) {
    	   		int[] temp = bfsQueue.poll();
        		int r = temp[0];
        		int c = temp[1];
        		if(r==n-1&& c== m-1) {
        			System.out.println(cnt);
        			return;
        		}
        		for(int d=0; d<delta.length; d++) {
        			int nr = r + delta[d][0];
        			int nc = c + delta[d][1];
        			if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == '0') {
        				continue;
        			}
        			visited[nr][nc] = true;
        			bfsQueue.add(new int[]{nr, nc});
        		}
    		}
    		cnt++;
    	}

	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new char[n][m];
        visited = new boolean[n][m];
        for ( int i = 0; i < n; i++) {
        	map[i] = br.readLine().toCharArray();
        }
        bfs(0, 0);
    }
}
