import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 시작시간 - 19:34
 * 종료시간 -
 * 
 * 문제 해석)
 * N x N 맵이 주어짐
 * M개의 꿀통에서 꿀을 채취
 * 각 꿀통당 최대 C만큼 채취 가능
 * 판매금액 : 채취한 꿀칸의 제곱
 * 
 * 1. M개의 꿀통에서 C를 만족하면서 어떡하면 최대값을 쉽게 뽑아낼 수 있는가?
 * 
 * 3 <= N <= 10
 * 1 <= M <= 5, M<=N
 * 10 <= C <= 30
 * 1<= 꿀 < 10
 * 
 * 한 테케당 연산횟수 600만번
 * 
 * 1. 무식하게 접근
 * 
 * 100CM * 98CM * 최대가격 계싼?
 * 
 * 결국 최대값을 어떻게 뽑냐?
 * 문제 단순화 => M개 중 C를 만족하는 최대값 뽑기
  * 
 * 100 * 100 * 2^M * 2^M 
 * 
 * 100개에서 연속된 M개씩 2개 set을 뽑고 부분집합 고려해서 최댓값 구한다면?
 * 
 * => 시간초과 안될거같음
 * 
 * 구현 
 * 1. 맵 입출력받기
 * 2. 100개에서 연속된 
 * 
 * => 그냥 100개에서 4개 뽑자 
 * ===========
 * 이 문제가 안풀리는 이유:
 * 1. 어떻게 경우의 수를 구하는가
 * 2. 최댓값 구하는 로직이 맞나
 * 
 */
class Solution
{
	private static int N,M,C, ans;
	private static StringBuilder sb;
	private static int[][] map;
	private static List<Worker> workers;
	private static Worker[] combWorkers;
	
	private static class Worker{
		
		int row;
		int[] honeyArea;
		int[] honeyInfo;
		
		public Worker(int row, int[] honeyArea, int[] honeyInfo) {
			this.row = row;
            this.honeyArea = honeyArea;
			this.honeyInfo = honeyInfo;
		}
		
		public int maxHoney() {
			int max = 0;
			for(int flag = 1; flag<Math.pow(2, M); flag++) {
				int sum = 0;
				int price = 0;
				for(int i=0; i<M; i++) {
					if( (flag & 1<<i) > 0) {
						sum += honeyInfo[i];
						price += honeyInfo[i] *honeyInfo[i];
					}
				}
				if(sum <= C) {
					max = Math.max(max, price);
				}
				
			}
			return max;
		}
		
	}
	
	private static boolean canGetHoneyTest(Worker w1, Worker w2) {
		
		if(w1.row == w2.row && (N-M) * 2 > N) {
			return false;
		} else if(w1.row == w2.row) {
			for(int i=0; i<M; i++) {
				for(int j=0; j<M; j++) {
					if(w1.honeyArea[i] == w2.honeyArea[j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private static void solve() {
		Worker worker1 = combWorkers[0];
		Worker worker2 = combWorkers[1];
		
		if(canGetHoneyTest(worker1, worker2)) {
			ans = Math.max(ans, worker1.maxHoney() + worker2.maxHoney());
		}
		
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == 2) {
			solve();
			return;
		}
		
		for(int i=start; i<workers.size(); i++) {
			combWorkers[cnt] = workers.get(i);
			comb(cnt+1, i+1);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase<=T; testCase++ ) {
			
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			ans = 0;
			
			map = new int[N][N];
			workers = new ArrayList<>();
			combWorkers = new Worker[2];
			
			
			for(int i=0; i<N; i++) {
				input = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			for(int r=0; r<N; r++) {
				for(int i=0; i<N-M+1; i++) {
					int[] temp = new int[M];
					int[] honeyInfo = new int[M];
					for(int j=i, idx=0; j<M+i; j++, idx++) {
						temp[idx] = j;
						honeyInfo[idx] = map[r][j];
					}
					workers.add(new Worker(r, temp, honeyInfo));
				}
			}
			
			comb(0, 0);
			sb.append('#').append(testCase).append(' ').append(ans).append('\n');
			
		}
		System.out.println(sb);
		
	}
}