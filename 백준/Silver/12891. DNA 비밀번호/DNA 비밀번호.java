import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 링크)
 * https://www.acmicpc.net/problem/12891
 * 
 * 비밀먼호 성립 조건을 만족하는 길이가 P인 순열을 구하는 문제이다.
 * 처음 입력을 훑으면서 count hash Map을 만든다.
 * 
 * => 근데 순열로 풀면 시간복잡도가 감당이 가능한가?
 *
 * 문제를 잘못 이해했었다!
 * 부분문자열이므로 하나 하나 뽑아서 비밀번호를 만드는게 아니고
 * 부분 부분 통째로 토막내서 풀면됨
 * => 슬라이딩 윈도우 사용 (시간복잡도 O(N)) 
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	private static int S, P, count;
	private static String str;
	
	private static void isAbleToBePassword(int[] window, int[] conditionArr) {
		
		for(int i=0; i<4; i++) {
			if(window[i] < conditionArr[i]) {
				return;
			}
		}
		
		count++;
		
	}
	private static void setWindow(int[] window, int newIdx, int originIdx) {
		switch(str.charAt(originIdx)) {
		case 'A':
			window[0]--;
			break;
		case 'C':
			window[1]--;
			break;
		case 'G':
			window[2]--;
			break;
		case 'T':
			window[3]--;
			break;
		}
		
		switch(str.charAt(newIdx)) {
		case 'A':
			window[0]++;
			break;
		case 'C':
			window[1]++;
			break;
		case 'G':
			window[2]++;
			break;
		case 'T':
			window[3]++;
			break;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		count = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		S = Integer.parseInt(input[0]);
		P = Integer.parseInt(input[1]);
		
		str = br.readLine();
		
		input = br.readLine().split(" ");
		
		int[] conditionArr = new int[4];
		for(int i=0; i<4; i++) {
			conditionArr[i] = Integer.parseInt(input[i]); 
		}
		
		// 초기값 세팅
		int[] window = new int[4];
		for(int i=0; i<P; i++) {
			switch(str.charAt(i)) {
			case 'A':
				window[0]++;
				break;
			case 'C':
				window[1]++;
				break;
			case 'G':
				window[2]++;
				break;
			case 'T':
				window[3]++;
				break;
			}
		}
		isAbleToBePassword(window, conditionArr);
		
		
		// 슬라이딩 윈도우 구현
		for(int i=P, j=0; i<S; i++, j++) {
			setWindow(window, i, j);
			isAbleToBePassword(window, conditionArr);
		}
		
		System.out.println(count);
	}
	
}