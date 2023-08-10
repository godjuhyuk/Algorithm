import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 9C7 조합 문제
 * 
 * 이걸 반대로 꼬아서
 * 전체 합에서 9C2로 2개를 뽑아서 빼보는건 어떨까? 
 *
 * 구현해야할것)
 * 
 * 모자 총합 구하기
 * 재귀를 이용한 조합
 * 		- 기저조건 : count = 2
 * 
 * => 아니면 2개이므로 완탐으로 해도 됨
 * 
 * 예상 시간 복잡도: O(N^2) 
 * @author SSAFY
 *
 */
public class Main {
	
	
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] hatNum = new int[9];
		int sum = 0;
		
		for(int i=0; i<9; i++) {
			hatNum[i] = Integer.parseInt(br.readLine());
			sum += hatNum[i];
		}
		
		loop:
		for(int i=0; i<8; i++) {
			for(int j=i+1; j<9; j++) {
				if(sum - hatNum[i] - hatNum[j] == 100) {
					hatNum[i] = -1;
					hatNum[j] = -1;
					break loop;
				} 
			}
		}
		
		for(int i=0; i<9; i++) {
			if(hatNum[i] != -1) {
				System.out.println(hatNum[i]);
			}
		}
	}
	
}