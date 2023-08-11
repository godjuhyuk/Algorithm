import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 8자리 수라면? => 1억~9.9999억
 * 사실상 시간복잡도가 5억 * log(log(5억)) => 에라토스테네스체 불가
 * 
 * 
 * 
 * 
 * 구현 순서
 * 1. [2, 3, 5, 7]로 시작
 * 2. 맨 뒤에서 1, 3, 7, 9 를 붙여나가며 소수판정
 * 3. 기저 조건 : depths == N
 * 
 * @author SSAFY
 *
 */
public class Main {

	private static int N;
	private static int[] primeCandidate = {1, 3, 7, 9};
	
	private static void print(List<Integer> primeList) {
		
		for(int i=0; i<primeList.size(); i++) {
			System.out.println(primeList.get(i));
		}

	}
	
	private static boolean isPrime(int n) {
	
		for(int i=2; i<=Math.pow(n, 0.5); i++) {
			if(n%i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void dfs(int depths, List<Integer> primeList) {
		// TODO Auto-generated method stub
		if(depths == N) {
			print(primeList);
			return;
		}
		
		List<Integer> newPrimeList = new ArrayList();
		
		for(int i=0; i < primeList.size(); i++) {
			for(int j=0; j<4; j++) {
				int candidateNum = primeList.get(i) * 10 + primeCandidate[j];
				if(isPrime(candidateNum)) {
					newPrimeList.add(candidateNum);
				}
			}
		}
		
		dfs(depths+1, newPrimeList);

		
	}
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		List<Integer> primeList = new ArrayList<>();
		primeList.add(2);
		primeList.add(3);
		primeList.add(5);
		primeList.add(7);
		
		
		dfs(1, primeList);
		
		
	}
}