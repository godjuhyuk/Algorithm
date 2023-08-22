import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, ans;
	private static int[] startLineUp;
	private static boolean[] isSelected;
	private static int[][] players;
	
	private static void perm(int cnt) {
		
		if(cnt == 9) {
			playBall(startLineUp);
			return;
			
		}
		
		if(cnt == 3) {
			startLineUp[cnt] = 0;
			perm(cnt+1);
		}
		
		for(int i=1; i<=8; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				startLineUp[cnt] = i; 
				perm(cnt+1);
				isSelected[i] = false;
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		players = new int[9][N];
		startLineUp = new int[9];
		isSelected = new boolean[9];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				players[j][i] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		perm(0);
		
		System.out.println(ans);
		
	}
	
	
	private static void playBall(int[] startLineUp) {
		int totalScore = 0;
		
		// 1번타자(1번선수 X) 부터 진행한다.
		int hitOrder = 0;
		// N개 이닝동안 게임 진행
		for(int i=0; i<N; i++) {
			int outCount = 0;
			// 뒤에서부터 홈, 1루, 2루, 3루
			int baseStatus = 0b0000;
			while(outCount < 3) {
				
				// swing : 현재 타자의 i번쨰 이닝 퍼포먼스
				int swing = players[startLineUp[hitOrder]][i];
				
				if(swing == 0) {
					outCount++;
				} 
				else {
					// 주자 진루
					for(int k=3; k>=1; k--) {
						// k번째 루에 주자 있을때 && 홈으로 들어가는 경우
						if((baseStatus & 1 << k) > 0 && k + swing > 3) {
							totalScore++;
							baseStatus -= 1 << k;
						}
						else if((baseStatus & 1 << k) > 0) {
							// 진루
							baseStatus |= 1 << (k + swing);
							
							// k번째 루 초기화
							baseStatus -= 1 << k;
						}
					}
 					//타자 진루 
					if(swing == 4) {
						totalScore++;
					} else {
						baseStatus |= 1 << swing;
					}
				}
				hitOrder = (hitOrder + 1) % 9;
			}
		}
		ans = Math.max(totalScore, ans);
	}
	
}