import java.util.*;
import java.io.*;
public class Main {
	static int n, m, cnt;
	static boolean[] visited;
	static boolean[][] pointArr;
	static Queue<Integer> bfsQueue;
	public static void bfs() {
			bfsQueue = new LinkedList();
			bfsQueue.offer(1);
			cnt = 0;
			while(!bfsQueue.isEmpty()) {
				int now = bfsQueue.poll();
				for(int i=2; i<=n; i++) {
					if(pointArr[i][now] && !visited[i]) {
						bfsQueue.add(i);
						visited[i] = true;
						cnt++;
					}
				}
			}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		pointArr = new boolean[n+1][n+1];
		for(int i=0; i<m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			pointArr[a][b] = true;
			pointArr[b][a] = true;
		}
		bfs();
		System.out.println(cnt);
	}
}
