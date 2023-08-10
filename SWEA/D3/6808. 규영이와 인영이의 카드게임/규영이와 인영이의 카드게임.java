import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	private static int winCnt;
	private static int loseCnt;
	private static int[] myCards;
	private static int[] oppoCards;
	private static int[] indexComb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase<=T; testCase++) {
			winCnt = 0;
			loseCnt = 0;
			indexComb = new int[9];
	
			myCards = new int[9];
			oppoCards = new int[9];
			boolean[] isMyCards = new boolean[19];
			
			String[] input = br.readLine().split(" ");
			
			for(int i=0; i<9; i++) {
				int getNum = Integer.parseInt(input[i]);
				myCards[i] = getNum;
				isMyCards[getNum] = true;
			}
			
			// 상대 카드 add
			for(int i=1, oppoIdx = 0; i<=18; i++) {
				if(!isMyCards[i]) {
					oppoCards[oppoIdx++] = i;
				}
			}
			
			perm(0, 0);
			
			sb.append('#').append(testCase).append(' ').append(winCnt).append(' ').append(loseCnt).append('\n');
			
		}
		System.out.println(sb);
	}
	
	private static void perm(int cnt, int flag) {
		if(cnt == 9) {
			whoIsWin(myCards, indexComb);
			return;
		}
		
		
		for(int i=0; i<9; i++) {
			if( (flag & 1<<i) > 0) continue;
			indexComb[cnt] = oppoCards[i];
			perm(cnt+1, flag | 1<< i);
			
		}
		

	}
	
	private static void whoIsWin(int[] myCards, int[] oppoCards) {
		
		int myScore = 0;
		int oppoScore = 0;
		
		for(int i=0; i<9; i++) {
			if(myCards[i] > oppoCards[i]) {
				myScore += myCards[i] + oppoCards[i];
			} else {
				oppoScore += myCards[i] + oppoCards[i];
			}
		}
		
		if(myScore > oppoScore) winCnt++;
		else if(oppoScore > myScore) loseCnt++;

	}
}