import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * 
 * 3kg. 5kg 봉지가 있다.
 * N이 주어졌을때
 * 3x + 5y = N에서 x+y가 최소가 되는 값을 구해야함.
 * 
 * 무식한 방법
 * y를 늘려가면서 N - 5y = 3의 배수가 되는 케이스에서
 * y의 최댓값을 찾으면 된다.
 * 
 * 아무 값도 찾지못한다면 N이 3의 배수인지 확인해보고, 그것도 아니면 -1을 리턴하면 된다.
 * 
 * 연산횟수 : 1000*3 + 1 = 3001
 * 
 * ===============================================
 * 
 * 5를 빼고 3kg 봉지 2개를 넣어가면서 5의 배수가 될때까지 반복
 * 
 * 
 * @author SSAFY
 *
 */


public class Main {
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int x = 0;
		if(N%3 == 0) {
			x = N/3; 
		}
		
		int y = N / 5 ;
		
		do {
			if( (N - 5*y) % 3 == 0) {
				
				x = (N - 5*y) / 3;
				break;
			}
			y-=1;
		} while(y > 0);
		
		if(x==0 && y<=0) {
			System.out.println(-1);
		} else {
			System.out.println(x+y);
		}
	}
}