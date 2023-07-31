import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int H, R, C;
	static String escapeMsg = "Escaped in %d minute(s).";
	static String trapped = "Trapped!";
	static char[][][] building;
	static boolean [][][] visited;
	
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	
	static class Node {
		int z, x, y, time;

		public Node(int x, int y, int z, int time) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = " ";
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
        
		while (!(input = br.readLine()).equals("0 0 0")) {
			st = new StringTokenizer(input);
			H = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			building = new char[H][R][C];
			visited = new boolean[H][R][C];
			Node start = null, end = null;
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < R; j++) {
					building[i][j] = br.readLine().toCharArray();
					for (int k = 0; k < C; k++) {
						char nowValue = building[i][j][k];
						
						if (nowValue == 'S') {
							start = new Node(j, k, i, 0);
						} else if (nowValue == 'E') {
							end = new Node(j, k, i, 0);
						} else if (nowValue == '#') {
							visited[i][j][k] = true;
						}
					}
				}
				br.readLine();
			}
			sb.append(bfs(start, end)).append('\n');
		}
		
		System.out.println(sb);
	}

	private static String bfs(Node start, Node end) {
		Queue<Node> q = new LinkedList<>();
		visited[start.z][start.x][start.y] = true;
		q.offer(start);
		
		while (!q.isEmpty()) {
			Node now = q.poll();
			if (now.x == end.x && now.y == end.y && now.z == end.z) return String.format(escapeMsg, now.time);
			for (int i = 0; i < 6; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				int nextZ = now.z + dz[i];
				
				if (!isInRange(nextX, nextY, nextZ) || visited[nextZ][nextX][nextY]) continue;
				visited[nextZ][nextX][nextY] = true;
				q.offer(new Node(nextX, nextY, nextZ, now.time + 1));
			}
		}
		
		return trapped;
	}

	private static boolean isInRange(int x, int y, int z) {
		return x >= 0 && x < R && y >= 0 && y < C & z >= 0 && z < H;
	}
}