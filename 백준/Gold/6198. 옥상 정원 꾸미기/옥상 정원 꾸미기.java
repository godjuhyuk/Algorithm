import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 도시에는 N개의 빌딩이 있다.

	빌딩 관리인들은 매우 성실 하기 때문에, 다른 빌딩의 옥상 정원을 벤치마킹 하고 싶어한다.
	
	i번째 빌딩의 키가 hi이고, 모든 빌딩은 일렬로 서 있고 오른쪽으로만 볼 수 	있다.
	
	i번째 빌딩 관리인이 볼 수 있는 다른 빌딩의 옥상 정원은 i+1, i+2, .... , N이다.
	
	그런데 자신이 위치한 빌딩보다 높거나 같은 빌딩이 있으면 그 다음에 있는 모든 빌딩의 옥상은 보지 못한다.
	
	예) N=6, H = {10, 3, 7, 4, 12, 2}인 경우
 *
 * 문제 해석)
 * 스택으로 받는다면?
 * 
 * 2
 * 12
 * 4
 * 7
 * 3
 * 10 3 7 4 12 2
 * 
 * 12
 * 4
 * 3
 * 7
 * 10
 *
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<Integer>();
		
		long ans = 0;
		
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			
			while(stack.size() > 0 && stack.peek() <= input) {
				stack.pop();
			}
			
			stack.add(input);
			
			ans += stack.size() - 1;
		}
		
		System.out.println(ans);
		
	}

}
