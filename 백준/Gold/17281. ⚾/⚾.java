import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해석)
 * 
 * 각 이닝당 9명의 타자가 어떤 성과를 내는지 주어짐.
 * 타선을 어떻게 정해야 최대 점수를 뽑아내는지 구하는 문제.
 * 
 * 쓰리아웃이 나올때까지 한 이닝 진행.
 * 0 : 아웃
 * 1 : 안타
 * 2 : 2루타
 * 3 : 3루타
 * 4 : 홈런
 * 
 * 최악 시간복잡도 : 8! * 50 * 27 = 약 5천만
 * 쓸모없는 로직 없이 구현해야 1초 맞출듯?
 * 
 * 구현해야하는 기능
 * 1. 8팩 (1번선수는 4번타자로 고정)
 * 2. 이닝 진행
 * 2-1) while 아웃카운트 < 3 
 * 2-2) 주자의 진루 상황을 어떻게 체크할까? + 득점?
 * 		=> 4칸짜리 배열로? 큐로?
 * 		=> 배열이 빠를거같다.
 * 		=> 첫 시도는 비트마스킹으로 해보자.
 * 		=> 0000 에서 안타치면 0001
 * 		=> 2루타치면? if (base & (1 << 2)) >0  이런식으로
 *   
 * 2-3) 득점 count & max 갱신  
 * 2-4) 이닝 마무리 시 다음 첫번째 타자로 누가나올지 기억해야함
 * 
 * 
 */
public class Main {
	private static int N, ans;
	private static int[][] players;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		players = new int[9][N];
		// 1번타자는 고정이므로
		int[] playersFactorialArr = new int[8];
		
		for(int i=0; i<8; i++) {
			playersFactorialArr[i] = i+1;
		}
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				players[j][i] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		do {
			playBall(playersFactorialArr);
		} while(np(playersFactorialArr));
		
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
				int swing = getNowPlayerPerform(i, startLineUp, hitOrder);
				
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
	
	private static int getNowPlayerPerform(int inning, int[] startLineUp, int nowPlayerOrder) {
		
		// startLineUp엔 1번 선수가 없는 상태이므로
		// 4번타자일때는 무조건 1번 선수를 리턴 
		if(nowPlayerOrder < 3) {
			return players[startLineUp[nowPlayerOrder]][inning];
		}
		else if(nowPlayerOrder == 3) {
			return players[0][inning];
		} else {
			return players[startLineUp[nowPlayerOrder-1]][inning];
		}

	}
	
	private static boolean np(int[] arr) {
		
		int i = 7;
		
		while(i>0 && arr[i-1] >= arr[i]) i--;
		if(i==0) return false;
		
		int j = 7;
		while(arr[i-1]>=arr[j]) j--;
		
		swap(arr, i-1, j);
		
		int k = 7;
		while(i<k) swap(arr, i++, k--);
		return true;
		
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}