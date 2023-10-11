import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 설계 시작 : 오후 7시 26분 
 * 
 * 크기가 N*N인 격자에 파이어볼 M개를 발사했다
 * 
 * 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸만큼 이동
 * 	- 이동하는 중에는 같은 칸에 여러개의 파이어볼이 있을수도 있음
 * 
 * 2. 이동이 끝나면, 2개 이상의 파이어볼이 있는 칸에서 다음과 같이 작동
 * 	2-1) 같은 칸에 있는 파이어볼은 모두 하나로 합쳐짐
 * 	2-2) 파이어볼은 4개의 파이어볼로 나누어진다.
 * 	2-3) 나누어진 파이어볼의 질량, 속력, 방향은 다음과가탇.
 * 		- 질량 : 합쳐진 파이어볼 질량합 / 5
 * 		- 속력 : 합쳐진 파이어볼 속력합 / 합쳐진 파이어볼 개수
 * 		- 합쳐지는 파이어볼의 방향이 모두 홀수 OR 모두 짞수이면 
 * 			=> 방향 : 0, 2, 4, 6
 * 		- 하나라도 다르면
 * 			=> 방향 : 1, 3, 5, 7
 * 	2-4). 질량이 0인 파이어볼은 없어짐
 * 
 * K번 이동을 명령한 후 남아있는 파이어볼 질량합은 ?
 * 
 * 문제 해결을 위한 고민)
 * 다음 두가지가 문제다.
 * 1. 1번 행과 N번 행 간의 이동, 1번 열과 N번 열과의 이동
 * => 1번행과 N번행은 연결되어있고, 1번열과 N번열은 연결되어있으므로
 * => 평소 isOutOfRange로 처리했던걸 나머지 연산자로 처리해야한다.
 * 
 * a) +로 초과한경우 : %N
 * b) -로 초과한경우 : (x+N)%N
 * 
 * 2. 파이어볼을 어떻게 합치고 나누는가?
 * - Fireball 클래스를 생성하는데 멤버변수는 다음과 같다.
 * - weight : 질량
 * - speed : 속도
 * - delta : 방향
 * - count : 합쳐진 개수
 * 
 * 이동이 끝난 곳에 파이어볼이 존재하면 합치는 것.
 * 
 * 그러고나서 전체순회하며 Fireball이 존재하면 4개로 쪼개든지, 갈길 가든지 하면 됨.
 * 
 */
public class Main {

	private static int N, M;
	private static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	private static Fireball[][] map, newMap;
	private static Queue<int[]> locQueue;
	static class Fireball {
		
		int r;
		int c;
		int weight;
		int speed;
		int delta;
		int count;
		boolean sameDir = true;
		
		public Fireball(int r, int c, int weight, int speed, int delta, int count) {
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.speed = speed;
			this.delta = delta;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Fireball [r=" + r + ", c=" + c + ", weight=" + weight + ", speed=" + speed + ", delta=" + delta
					+ ", count=" + count + "]";
		}

		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
//		locQueue = new ArrayDeque<int[]>();
		
		map = new Fireball[N][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Fireball(r, c, m, s, d, 1);
			
		}
		
		while(K-- > 0) {
			
			Fireball[][] newMap = new Fireball[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != null) {
						
						// 파이어볼 이동
						move(map[i][j], newMap);
						
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

	private static void move(Fireball fireball, Fireball[][] newMap) {
		
		if(fireball.count > 1) {
			
			int nw = fireball.weight / 5;
			int ns = fireball.speed / fireball.count;
			
			if(nw == 0) {
				return;
			}
			
			Fireball f1 = new Fireball(fireball.r, fireball.c, nw, ns, -1, 1);
			Fireball f2 = new Fireball(fireball.r, fireball.c, nw, ns, -1, 1);
			Fireball f3 = new Fireball(fireball.r, fireball.c, nw, ns, -1, 1);
			Fireball f4 = new Fireball(fireball.r, fireball.c, nw, ns, -1, 1);
			
			if(fireball.sameDir) {
				// 전부 홀수 or 짝수였다면
				f1.delta = 0;
				f2.delta = 2;
				f3.delta = 4;
				f4.delta = 6;
				
			} else {
				// 하나라도 달랐다면 
				f1.delta = 1;
				f2.delta = 3;
				f3.delta = 5;
				f4.delta = 7;
			}
			
			move(f1, newMap);
			move(f2, newMap);
			move(f3, newMap);
			move(f4, newMap);
			// 1, 3 and 1, 3
		} else {
				
			fireball.r = (N + fireball.r + (fireball.speed % N) * deltas[fireball.delta][0]) % N;
			fireball.c = (N + fireball.c + (fireball.speed % N) * deltas[fireball.delta][1]) % N;
			
			if(newMap[fireball.r][fireball.c] == null) newMap[fireball.r][fireball.c] = fireball;
			else {
				Fireball tempFireball = newMap[fireball.r][fireball.c];
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
