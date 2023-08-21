import java.util.*;
import java.io.*;
public class Main {
	static int n, k;
	public static void bfs() {
		Queue<Integer> bfsQueue = new LinkedList();
		bfsQueue.add(n);
		boolean[] visited = new boolean[100001];
		int ans = 1;
        visited[n] = true;
		while(!bfsQueue.isEmpty()) {
			int qSize = bfsQueue.size();
			for(int i=0; i<qSize; i++) {
				int now = bfsQueue.poll();
				if(now*2 == k || now+1 == k || now-1 == k) {
					System.out.println(ans);
					return;
				}
				if(now*2 <= 100000 && !visited[now*2]) {
					visited[now*2] = true;
					bfsQueue.add(now*2);
				}
				if(now+1 <= 100000 && !visited[now+1]) {
					visited[now+1] = true;
					bfsQueue.add(now+1);
				}
				if(now-1 >= 0 && !visited[now-1]) {
					visited[now-1] = true;
					bfsQueue.add(now-1);
				}
			}
            ans++;
			
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		if(n>=k) {
			System.out.println(n-k);
		} else {
			bfs();
		}
	}

}
