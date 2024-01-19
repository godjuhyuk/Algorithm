import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤
 * 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로
 * 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다. 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을
 * 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
 * 
 * 문제 해결을 위한 고민) 1. 10만 배열 2개써서 DP? 2. BFS 하면서 중간에 최소값보다 많아지면 stop하는 방식?
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	private static final int END = 100_001;
	private static int N, K, visited[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N==K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		
		
		visited = new int[2][END];

		Arrays.fill(visited[0], END);

		Queue<Integer> q = new ArrayDeque<Integer>();
		
		visited[0][N] = 0;
		q.offer(N);

		int turn = 1;
		while (!q.isEmpty()) {

			if(visited[0][K] < turn) break;
			
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int temp = q.poll();
				if (visited[0][temp] < turn-1)
					continue;

				if (temp + 1 < END && visited[0][temp + 1] >= turn) {
					q.offer(temp+1);
					if(visited[0][temp+1] == turn) {
						visited[1][temp+1]++;
					} else {
						visited[0][temp+1] = turn;
						visited[1][temp+1] = 1;
					}
				}

				if (temp - 1 >= 0 && visited[0][temp - 1] >= turn) {
					q.offer(temp-1);
					if(visited[0][temp-1] == turn) {
						visited[1][temp-1]++;
					} else {
						visited[0][temp-1] = turn;
						visited[1][temp-1] = 1;
					}
				}

				if (temp * 2 < END && visited[0][temp * 2] >= turn) {
					q.offer(temp*2);
					if(visited[0][temp*2] == turn) {
						visited[1][temp*2]++;
					} else {
						visited[0][temp*2] = turn;
						visited[1][temp*2] = 1;
					}
				}
			}

			turn++;

		}
		
		System.out.println(visited[0][K]);
		System.out.println(visited[1][K]);

	}

}
