import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 구현 시작시간 - 오후 2: 51
 * 
 * 
 * 문제 해석)
 * 
 * 최적의 Battery Charger를 선택하는 알고리즘
 * 10 x 10 
 * 
 * 충전 범위x : 위치로부터 4방으로 x번 뻗어나감
 * 충전 범위 내에 있으면 해당 충전기에 접속 가능
 * 거리 계산 : |x1 - x2| + |y1 - y2|
 * 
 * 사용자 A와 B의 이동 궤적이 주어진다.
 * 사용자들은 움직이다가 충전기의 범위에 들어가면 충전을 할 수 있다.
 * 단 2명이 같은 충전기를 선택한다면 충전량이 절반이 된다.
 * 사용자는 무조건 (1,1)과 (10,10)에서 출발한다.
 * 
 * BC의 개수 A : 1<=A<=8
 * 이동 시간 M : 20 <= M <= 100
 * 충전 범위 C : 1<=C<=4
 * 성능 P : 10 <= P <= 500
 * 
 * 사용자의 초기 위치(0초)부터 충전이 가능
 * 사용자 A, B가 동시에 같은 위치로 이동할 수는 있다.
 * 
 * 문제 해결을 위한 고민)
 * 최적 동선을 짜는 문제가 아님.
 * 충전의 최대양을 고려해야하는 문제.
 * => 제일 성능이 좋은 충전기 구역에 같은 인원이 있다면?
 * => 여러 구역에 겹친다면 무조건 성능1위, 2위 충전기를 각각 택해서 충전하는게 Best
 * 
 * 
 * 구현해야할 기능)
 * 1. M번 for문을 돈다.
 * 	- 선 충전 후 이동 (0초부터 충전가능하므로)
 * 
 * 2. 충전 기능
 * 2-1) 각자 사용자의 위치에서 충전기를 고려한다.
 * 2-2) 각자 자리에서 선택되지 않은 충전기 중 최고 성능 충전기를 택한다. (충전기를 택하는것도 연습할겸 비트마스킹으로 해보자)
 * 2-3) 선택하는 순서는 상관이 없다. 어차피 총합의 최대를 구하므로 A부터 고르도록하자
 * 
 * 3. 성능 순으로 비트를 정해야하므로 성능에 따른 int 인덱스 배열을 구현하자. 
 * => A가 사용중인 인덱스int를 정하고, 만약 B가 3번 충전기를 사용하고 싶다면 사용중인 인덱스가 3번인지 확인 후 아니라면 사용하는 방식
 * 
 */
public class Solution {
	
	private static int M, A, ans;
	private static int[][] map;
	private static int[][] deltas = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
	private static int[] moveInfoA, moveInfoB;
	private static Charger[] chargerArr;
	
	private static class Player {
		
		int r;
		int c;
		int bestChargerIndex;
		
		public Player(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		private void move(int cmd) {
			
			r += deltas[cmd][0];
			c += deltas[cmd][1];

		}
		
		private void findBestCharger() {
			
			bestChargerIndex = -1;
			
			for(int i=0; i<A; i++) {
				if( (map[r][c] & 1 << i) > 0) {
					bestChargerIndex = i;
					break;
				}
			}

		}
		
		private void charge(int idx) {
			if(idx==-1) return;
			ans += chargerArr[idx].power;
			
		}
		
	}
	
	private static class Charger implements Comparable<Charger>{
		
		int r;
		int c;
		int dist;
		int power;
		
		public Charger(int r, int c, int dist, int power) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.power = power;
		}
		
		public int compareTo(Charger o) {
			return o.power - this.power;
		};
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			moveInfoA = new int[M];
			moveInfoB = new int[M];
			chargerArr = new Charger[A];
			
			// A 이동 정보 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) moveInfoA[i] = Integer.parseInt(st.nextToken());
			
			// B 이동 정보 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) moveInfoB[i] = Integer.parseInt(st.nextToken());
			
			// 충전기 위치 및 성능 정보 입력
			for(int i=0; i<A; i++) {
				
				st = new StringTokenizer(br.readLine());
				
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				chargerArr[i] = new Charger(r, c, dist, power);
			}
			
			// 성능 기준 내림차순 정렬
			Arrays.sort(chargerArr);
			
			// 맵 생성
			map = new int[11][11];
			
			
			// 충전기별로 충전범위 부합 정보 기록 (비트마스킹) -- 이부분 맘에안드니깐 나중에 수정
			for(int k=0; k < A; k++) {
				
				Charger charger = chargerArr[k];
				marking(charger, charger.r, charger.c, k, 0);
				
			}
			
			// 플레이어 이동 차례
			// 이동 규칙
			// 0. 각자 이동
			// 1. 턴이 시작되면 플레이어끼리 충전 가능한 충전기 수를 카운트한다.
			// 2. 충전기 수가 적은 플레이어부터 충전한다.
			
			//만약 겹친다면?
			// 최우선 고려사항 : 충전가능한 개수가 적은 놈부터? 만약 2개와 3개라면?
			Player p1 = new Player(1, 1);
			Player p2 = new Player(10, 10);
			
			// 현재 위치에서 충전 
			goToCharge(p1, p2);
			
			
			
			for(int i=0; i<M; i++) {
				
				p1.move(moveInfoA[i]);
				p2.move(moveInfoB[i]);
				goToCharge(p1, p2);
				
			}
			sb.append('#').append(t).append(' ').append(ans).append('\n');
			
		}
		System.out.println(sb);
		
	}
	
	private static void goToCharge(Player p1, Player p2) {
		
		p1.findBestCharger();
		p2.findBestCharger();
		
		if(p1.bestChargerIndex == -1 && p2.bestChargerIndex == -1) return;
		
		// bestChargerIndex : 내가 있는 위치에서 충전 가능한 것중 가장 성능이 좋은 충전기
		// -1 : 내가 있는 위치에 충전기 아예x
		// second : 성능이 좋은순으로 탐색하면서 bestChargetIndex가 아닌 충전기 인덱스
		
		if(p1.bestChargerIndex == p2.bestChargerIndex) {

			chargeIfSame(p1, p2);
			
		}
		else {
			
			p1.charge(p1.bestChargerIndex);
			p2.charge(p2.bestChargerIndex);
			
		}
		
	}
	
	private static void chargeIfSame(Player p1, Player p2) {
		int cnt = 0;
		for(int i=0; i<A; i++) {
			if( (map[p1.r][p1.c] & 1 << i) > 0 || (map[p2.r][p2.c] & 1 << i) > 0 ) {
				ans += chargerArr[i].power;
				if(++cnt == 2) break;
			}
		}

	}
	
	private static void marking(Charger charger, int r, int c, int chargerIdx, int depths) {
		
		if(depths == charger.dist) {
			map[r][c] |= 1 << chargerIdx;
			return;
		}
		
		// map[r][c] = 0b0001 의 의미 : 성능이 가장 좋은 충전기를 사용할 수 있는 칸이다.
		map[r][c] |= 1 << chargerIdx;
		
		for(int[] d : deltas) {
			
			int nr = r + d[0];
			int nc = c + d[1];
			
			if(isOutOfRange(nr, nc)) continue;
			
			marking(charger, nr, nc, chargerIdx, depths+1);
			
		}
		
	}
	
	private static boolean isOutOfRange(int r, int c) {
		
		return 1 > r || r >= 11 || 1 > c || c >= 11;

	}
}