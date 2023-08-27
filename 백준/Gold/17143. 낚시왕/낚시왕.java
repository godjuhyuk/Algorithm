import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 상어의 위치(r, c) , 속력(s), 이동방향(d), 크기(z)가 주어진다.
 * 
 * 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 
 * 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 상어가 이동한다.
 * 
 * 구현해야할것)
 * 
 * 1. 낚시왕이 for문을 돌며 움직인다.
 * 2. 낚시왕이 있는 열에서 탐색을하여 상어를 잡는다.
 * 3. 상어 클래스를 구현한다.
 * 3-1) 상어의 이동과, 같은 칸에 있을때 먹는 코드 구현
 * 
 * 상어가 같은 칸에 여러마리 있을때 어떻게 구현? -> 어떤 자료구조를 써야할까?
 * 
 * 상어에 isAlive boolean을 추가해서 무식하게 돌아보자
 * 
 * 
 * 시간복잡도)
 * 최악의 경우, 100(C)* 10000 * 1000  (상어탐색)
 * 
 * 
 * 
 */
public class Main {
	private static int R, C, M, ans;
	private static int[][] map;
	private static Shark[] sharks;
	
	static class Shark {
		int row;
		int col;
		int speed;
		int dx;
		int dy;
		int weight;
		boolean isAlive;
		
		public Shark(int row, int col, int speed, int direct, int weight) {
			
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.weight = weight;
			getDeltas(direct);
			this.isAlive = true;
			
		}
		
		private void getDeltas(int direct) {
			switch(direct) {
			case 1:
				this.dx = -1;
				this.dy = 0;
				this.speed %= (R-1)*2;
				break;
			case 2:
				this.dx = 1;
				this.dy = 0;
				this.speed %= (R-1)*2;
				break;
			case 3:
				this.dx = 0;
				this.dy = 1;
				this.speed %= (C-1)*2;
				break;
			case 4:
				this.dx = 0;
				this.dy = -1;
				this.speed %= (C-1)*2;
				break;
			}

		}
		
		// 이동을 완료한 상어끼리 만났을 경우, 무게가 큰 상어가 이긴다.
		private void isAbleToEat(Shark o, int otherIdx, int myIdx) {
			if(this.weight > o.weight) {
				o.isAlive = false;
				map[row][col] = myIdx;
			} 
			else if(this.weight < o.weight){
				this.isAlive = false;
				map[row][col] = otherIdx;
			}
		}
		
	}
	
	private static boolean isOutOfRange(int r, int c, int dx, int dy) {
		
		int nr = r + dx;
		int nc = c + dy;
		
		return nr < 1 || nr > R || nc < 1 || nc > C;

	}
	
	// 상어 이동
	private static void move(int sharkIdx) {
		int r = sharks[sharkIdx].row;
		int c = sharks[sharkIdx].col;
		
		int dx = sharks[sharkIdx].dx;
		int dy = sharks[sharkIdx].dy;
		
		for(int i=0; i<sharks[sharkIdx].speed; i++) {

			if(isOutOfRange(r, c, dx, dy)) {
				dx *= -1;
				dy *= -1;
			}
			
			r += dx;
			c += dy;
			
		}
		
		sharks[sharkIdx].row = r;
		sharks[sharkIdx].col = c;
		sharks[sharkIdx].dx = dx;
		sharks[sharkIdx].dy = dy;
		
		if(map[r][c] != 0 &&  map[r][c] < sharkIdx) {

		// 내가 도착한 곳에 다른 상어가 이미 존재 && 이미 존재했던 상어가 이동을 완료한 상어일 때
			sharks[sharkIdx].isAbleToEat(sharks[map[r][c]], map[r][c], sharkIdx);

		} else {

			map[r][c] = sharkIdx;
		}
		
		
	}
	

	private static void sharkMove() {
		for(int i=1; i<=M; i++) {
			if(sharks[i].isAlive && map[sharks[i].row][sharks[i].col] == i) {

				// 살아있는 상어일 때 && 이동 전에 다른 상어가 도착하지않았다면 맵을 0으로 만들고 떠난다.
				map[sharks[i].row][sharks[i].col] = 0;
				move(i);

			} else if(sharks[i].isAlive) {

				// 살아있는 상어일 때 && 이동 전에 다른 상어가 도착했으므로 맵 값이 내 인덱스가 아니라면 그냥 떠난다.
				move(i);

			}
		}
	}
	
	
	private static void getShark(int col) {
		
		// 낚시 시작

		for(int i=1; i<=R; i++) {
			int sharkIdx = map[i][col];

			// 상어를 발견하면 즉시 포획			
			if(sharkIdx > 0 && sharks[sharkIdx].isAlive) {
				sharks[sharkIdx].isAlive = false;
				ans += sharks[sharkIdx].weight;
				map[i][col] = 0;
				return;
			}
		}

	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		M = Integer.parseInt(input[2]);
		
		map = new int[R+1][C+1];
		sharks = new Shark[M+1];
		for(int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int s = Integer.parseInt(input[2]);
			int d = Integer.parseInt(input[3]);
			int z = Integer.parseInt(input[4]);
			
			sharks[i] = new Shark(r, c, s, d, z);
			map[r][c] = i;
			
		}
		
		for(int i=1; i<=C; i++) {
			getShark(i);
			sharkMove();
		}
		
		System.out.println(ans);
		
	}
}