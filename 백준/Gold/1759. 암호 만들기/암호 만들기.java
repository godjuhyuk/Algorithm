import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작시간: 3시 12분
 * 종료시간: 3시 19분
 * 
 * 출력조건
 * a. 최소 1개의 모음, 2개의 자음으로 구성
 * b. 오름차순 정렬된 상태로 출력
 * 
 * 풀이방법
 * 
 * 재귀를 이용한 순열 구현 (정렬을 해놓고 시작한다면 b를 만족시키기는 편함) => a만 만족하도록 하면 됨
 * 
 * 
 * 구현해야 할것
 * 1. 모음 cnt, 자음 cnt 하는 자료구조 and 메소드 구현
 * => int로 처리
 * 
 * 2. 순열 재귀함수 구현
 * => 순서 신경써서 start parameter를 넘겨주기
 * 
 * 3. 출력 => StringBuilder를 전역으로 선언해서 사용
 *  
 * @author SSAFY
 *
 */
public class Main {
	private static int L, C;
	private static String[] candidates, combList;
	private static StringBuilder sb;
	
	private static void print() {
		for(int i=0; i<L; i++) {
			sb.append(combList[i]);
		}
		sb.append('\n');
	}
	
	private static void combi(int depths, int start, int mCnt, int jCnt) {
		
		if(depths == L) {
			if(mCnt >= 1 && jCnt >= 2) {
				print();
			}
			return;
		}
		
		for(int i=start; i<C; i++) {
			combList[depths] = candidates[i];
			char tempChar = candidates[i].charAt(0);
			if(tempChar == 'a' || tempChar == 'e' || tempChar == 'i' || tempChar == 'o' || tempChar == 'u') {
				combi(depths+1, i+1, mCnt+1, jCnt);
			} else {
				combi(depths+1, i+1, mCnt, jCnt+1);
			}
		}
			

	}
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		combList = new String[L];
		candidates = br.readLine().split(" ");
		Arrays.sort(candidates);
		
		combi(0, 0, 0, 0);
		System.out.println(sb);
	}
}