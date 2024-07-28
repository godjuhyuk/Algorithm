
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 뱀이 나와서 기어다니면서
 * 사과를 먹으면 몸의 길이가 1 늘어남
 * 벽이나 자기 자신과 부딪히면 게임 끝
 * 
 * N*N 정사각 보드 위에서 진행됨
 * 뱀은 처음에 오른쪽을 향함 (0, 1)
 * 
 * 뱀은 다음 규칙으로 매초 이동함
 * 1. 먼저 뱀은 몸길이를 늘려 머리를 다음 칸에 위치시킨다.
 * 2. 만약 벽이나 자기 자신의 몸과 부딪히면 게임 끝
 * 3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움지이지 않는다(몸 길이 +1)
 * 4. 사과가 없다면, 몸의 길이를 줄여 꼬리가 위치한 칸을 비워준다. 즉 몸 길이는 변하지 않음
 * 
 * 입력)
 * - 2 <= N <= 100
 * - 0 <= K (사과 개수) <= 100
 * - 다음 K개의 줄엔 사과 위치가 주어짐
 * - 뱀의 방향 변환 횟수 1<= L <= 100
 * - 다음 L개의 줄엔 정수 X와 문자 C로 이루어져 있으며
 * - 게임 시작 X초가 "끝난" 뒤에 왼쪽(C="L") 또는 오른쪽 (C="D")로 90도 방향 회전시킨다는 뜻
 * - 0 <= X <= 10000
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	private static final boolean APPLE = true;
	private static final int[] LEFT_DELTA = {0, -1}, UP_DELTA = {-1, 0}, DOWN_DELTA = {1, 0}, RIGHT_DELTA = {0, 1};
	private static int N;
	private static class Command {
		
		int x;
		String cmd;
		
		public Command(int x, String cmd) {
			this.x = x;
			this.cmd = cmd;
		}
		
	}
	
	private static class Snake {
		
		Deque<int[]> body;
		int[] delta;
		public Snake() {
			body = new ArrayDeque<int[]>();
			delta = RIGHT_DELTA;
		}
		
		public void turn(String cmd) {
			
			if(cmd.equals("D")) {
				if(this.delta == RIGHT_DELTA) {
					this.delta = DOWN_DELTA;
				}
				else if(this.delta == LEFT_DELTA) {
					this.delta = UP_DELTA;
				}
				else if(this.delta == UP_DELTA) {
					this.delta = RIGHT_DELTA;
				}
				else if(this.delta == DOWN_DELTA) {
					this.delta = LEFT_DELTA;
				}
			} else {
				if(this.delta == RIGHT_DELTA) {
					this.delta = UP_DELTA;
				}
				else if(this.delta == LEFT_DELTA) {
					this.delta = DOWN_DELTA;
				}
				else if(this.delta == UP_DELTA) {
					this.delta = LEFT_DELTA;
				}
				else if(this.delta == DOWN_DELTA) {
					this.delta = RIGHT_DELTA;
				}
			}
			
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];
		
		Queue<Command> commandQueue = new ArrayDeque<Command>();
		Snake snake = new Snake();
		
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int appleRow = Integer.parseInt(st.nextToken()) - 1;
			int appleCol = Integer.parseInt(st.nextToken()) - 1;
			map[appleRow][appleCol] = APPLE;
		}
		
		int L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String cmd = st.nextToken();
			commandQueue.add(new Command(x, cmd));
		}
		
		boolean[][] isSnakeBody = new boolean[N][N]; 
		snake.body.add(new int[] {0, 0});
		isSnakeBody[0][0] = true;
		int time = 0;
		while(true) {
			
			// 움직임 시작
			int headRow = snake.body.peek()[0];
			int headCol = snake.body.peek()[1];
			
			headRow += snake.delta[0];
			headCol += snake.delta[1];
			
			if(isOutOfRange(headRow, headCol) || isSnakeBody[headRow][headCol]) break;
			
			snake.body.addFirst(new int[] {headRow, headCol});
			isSnakeBody[headRow][headCol] = true;
			if(map[headRow][headCol] == APPLE) {
				map[headRow][headCol] = false;
			} else {
				int[] deadTail = snake.body.pollLast();
				isSnakeBody[deadTail[0]][deadTail[1]] = false;
			}
			
			// 움직임이 끝났을 경우
			time++;
			if(!commandQueue.isEmpty() && commandQueue.peek().x == time) {
				snake.turn(commandQueue.poll().cmd);
			}
		}
		
		System.out.println(time+1);
		
	}
	
	public static boolean isOutOfRange(int r, int c) {
		return r<0 || c<0 || r>=N || c>=N;
	}
	
}
