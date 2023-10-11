import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	private static int N, M;
	private static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	private static Fireball[][] map;
	static class Fireball {
		int weight;
		int speed;
		int delta;
		int count;
		boolean sameDir = true;
		public Fireball(int weight, int speed, int delta, int count) {
			this.weight = weight;
			this.speed = speed;
			this.delta = delta;
			this.count = count;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new Fireball[N][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Fireball(m, s, d, 1);
			
		}
		while(K-- > 0) {
			Fireball[][] newMap = new Fireball[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != null) {
						move(map[i][j], i, j, newMap);
					}
				}
			}
			map = newMap;
		}
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != null) {
					if(map[i][j].count > 1) ans += (map[i][j].weight/5) * 4;
					else ans += map[i][j].weight;
				}
			}
		}
		System.out.println(ans);
	}
	private static void move(Fireball fireball, int r, int c, Fireball[][] newMap) {
		if(fireball.count > 1) {
			int nw = fireball.weight / 5;
			int ns = fireball.speed / fireball.count;
			if(nw == 0) return;
			Fireball f1 = new Fireball(nw, ns, -1, 1);
			Fireball f2 = new Fireball(nw, ns, -1, 1);
			Fireball f3 = new Fireball(nw, ns, -1, 1);
			Fireball f4 = new Fireball(nw, ns, -1, 1);
			if(fireball.sameDir) {
				f1.delta = 0;
				f2.delta = 2;
				f3.delta = 4;
				f4.delta = 6;
			} else {
				f1.delta = 1;
				f2.delta = 3;
				f3.delta = 5;
				f4.delta = 7;
			}
			move(f1, r, c, newMap);
			move(f2, r, c, newMap);
			move(f3, r, c, newMap);
			move(f4, r, c, newMap);
		} 
		else {
			r = (N + r + (fireball.speed % N) * deltas[fireball.delta][0]) % N;
			c = (N + c + (fireball.speed % N) * deltas[fireball.delta][1]) % N;
			if(newMap[r][c] == null) newMap[r][c] = fireball;
			else {
				Fireball tempFireball = newMap[r][c];
				tempFireball.weight += fireball.weight;
				tempFireball.speed += fireball.speed;
				tempFireball.count++;
				if((tempFireball.delta + fireball.delta) % 2 == 1) {
					tempFireball.sameDir = false;
				} 
			}
		}
	}
}