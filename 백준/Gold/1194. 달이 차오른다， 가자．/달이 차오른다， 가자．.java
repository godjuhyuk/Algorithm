import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 
 * 직사각형 모양의 미로 탈출
 * 
 * 미로 구성 요소)
 * 빈 칸 : 언제나 이동 가능, '.'
 * 벽 : 절대 이동 X, '#'
 * 열쇠 : 언제나 이동 가능, 이 곳에 처음 들어가면 열쇠를 집음 ('a', 'b', 'c', 'd', 'e', 'f')
 * 문 : 대응하는 열쇠를 소지하고 있으면 이동 가능 ('A', 'B', 'C', 'D', 'E', 'F')
 * 민식이 위치 : 빈곳, 현재 서있는 곳, '0'
 * 출구 : 민식이가 갸아하는 곳. 이곳에 오면 탈출, '1'
 * 
 * 입출력)
 * N, M <= 50
 * 
 * 문제 해결을 위한 고민)
 * 
 * bfs로 하다가 키를 주우면 visited 초기화?
 * 1. 키를 주웠을때의 최초 턴 수를 저장
 * 2. 키에 맞는 문 앞에 섰을때 최초턴수
 * 
 * user객체를 넣자
 * 키 소유 정보를 가지고 있는 비트 0b000000
 * 
 */
public class Main {
	
	private static int N, M, ans;
	private static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static char[][] map;
	private static boolean[][][] visited;
	
	public static class User {
		
		int r;
		int c;
		int keyBit;
		
		public User(int r, int c, int keyBit) {
			this.r = r;
			this.c = c;
			this.keyBit = keyBit;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][0b1000000];
		
		User user = null;
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') {
					user = new User(i, j, 0);
					map[i][j] = '.';
				}
			}
		}
		
		if(bfs(user)) {
			System.out.println(-1);
		}
	}
	
	private static boolean bfs(User user) {
		
		Queue<User> userQ = new ArrayDeque<User>();
		userQ.offer(user);
		
		while(!userQ.isEmpty()) {
			
			ans++;
			int qSize = userQ.size();
			for(int i=0; i<qSize; i++) {
				User temp = userQ.poll();
				visited[temp.r][temp.c][temp.keyBit] = true;
				
				for(int[] d : deltas) {
					
					int nr = temp.r + d[0];
					int nc = temp.c + d[1];
					if(isOutOfRange(nr, nc) || map[nr][nc] == '#') continue;
					
					if(map[nr][nc] == '1') {
						System.out.println(ans);
						return false;
					}
					
					if(map[nr][nc] == '.' && !visited[nr][nc][temp.keyBit]) {
						visited[nr][nc][temp.keyBit] = true;
						userQ.offer(new User(nr, nc, temp.keyBit));
					}
					
					else if(map[nr][nc] <= 'F') {
						// 키 소유 시
						if( (temp.keyBit & 1 << (map[nr][nc] - 'A')) > 0 && !visited[nr][nc][temp.keyBit]) {
							visited[nr][nc][temp.keyBit] = true;
							userQ.offer(new User(nr, nc, temp.keyBit));
						}
						
					}
					else {
						// 키 주웠을 때
						if(!visited[nr][nc][temp.keyBit]) {
							visited[nr][nc][temp.keyBit] = true;
							if((temp.keyBit & 1 << (map[nr][nc] - 'a')) == 0) userQ.offer(new User(nr, nc, temp.keyBit | 1 << (map[nr][nc] - 'a')));
							else userQ.offer(new User(nr, nc, temp.keyBit));
						}
					}
					
				}
			}
			
		}
		
		return true;

	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
	
}