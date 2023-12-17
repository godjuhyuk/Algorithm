import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, ans, min;
	private static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		min = Integer.MAX_VALUE;
		
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<Integer>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}

		for (int i = 1; i <= N; i++) {

			// i번째 사람의 케빈 베이컨 수를 BFS로 구하기
			int tempBaconNum = getBaconNum(i);
			if(tempBaconNum < min) {
				ans = i;
				min = tempBaconNum;
			}
			else if(tempBaconNum == min && i < ans) {
				ans = i;
			}
		}
		
		System.out.println(ans);
		
	}

	private static int getBaconNum(int myNum) {

		int baconNum = 0;

		for (int friendNum = 1; friendNum <= N; friendNum++) {
			if (friendNum == myNum)
				continue;
			baconNum += baconBFS(myNum, friendNum);

		}

		return baconNum;
	}

	private static int baconBFS(int myNum, int friendNum) {

		int cnt = 0;
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(myNum);
		visited[myNum] = true;


		while (!q.isEmpty()) {
			
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int temp = q.poll();
				if(temp == friendNum) {
					return cnt;
				}
				List<Integer> tempList = adjList[temp];
				for (Integer person : tempList) {
					if (!visited[person]) {
						visited[person] = true;
						q.add(person);
					}
				}
			}
			cnt++;
		}

		return cnt;
	}

}
