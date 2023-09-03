import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 설계 시작 시간 : 오후 2시 26분
 * 
 * 10가지 연산 수행하는 고스택 (스택변형)
 * 
 * 첫번째 수 : 가장 위에 저장된 수
 * 두번째 수 : 그 다음 수
 * 세번째 수 : 두번째 다음 수 
 * .
 * .
 * 
 * 명령어 모음
 * NUM X : X를 스택의 가장 위에 저장한다.(0<=X<=10^9)
 * POP : 스택 가장 위의 숫자를 제거한다.
 * INV : 첫 번째 수의 부호를 바꾼다. (42 -> -42)
 * DUP : 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
 * SWP : 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
 * ADD : 첫 번째 숫자와 두 번째 숫자를 더한다.
 * SUB : 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
 * MUL : 첫 번째 숫자와 두번째 숫자를 곱한다.
 * DIV : 첫 번째 숫자로 두번째 숫자를 나눈 몫을 저장한다. (두 / 첫 )
 * MOD : 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. (두 / 첫 )
 * 
 * 주의)
 * 1. 숫자가 부족해서 연산을 수행할 수 없을 때
 * 	  0으로 나눴을 때 (DIV, MOD)
 *    연산 결과의 절댓값이 109를 넘어갈 때는 모두 프로그램 에러이다.
 *    END가 나오면 끝난다.
 *    
 *    => 프로그램 에러가 발생했을 경우에는, 현재 프로그램의 수행을 멈추고
 *    그 다음 어떤 명령도 수행하지 않는다
 *    
 * 2. 음수 나눗셈에 대한 모호함을 피하기 위한 계싼법
 * 2-1) 피연산자 중 음수가 있을 때, 그 수는 절댓값을 씌워 계산
 * 2-2) 몫 :  피연산자 중 음수가 하나라면 몫의 부호는 음수로 스택ㅇ ㅔ넣음
 * 2-3) 나머지 : 나머지의 부호는 피제수의 부호와 같음.
 *
 * 명령어가 10만개, N이 1만이라면? 
 * 
 * 문제 해결 로직)
 * 
 * 1. 명령문을 다 담아놓은 List로 프로그램을 구현
 * 1-1) while(cmd != END)
 * 2. 명령어에 맞게 실행
 * 2-1) switch(cmd)
 * 3. 프로그램 종료 조건 모니터링
 * 3-1) if(isEnd()) break
 * 
 * 1,2,3 을 QUIT가 나올때까지 반복
 * 
 * 
 * 그래서, 각 N당 10만개의 명령어를 수행해야할때는?
 */
public class Main {
    
	static StringBuilder sb;
	static BufferedReader br;
	
	private static boolean operate(String cmd, Stack<Long> stack) {
		
		int size = stack.size();
		
		switch (cmd) {
		case "POP": {
			if(size == 0) return false;
			stack.pop();
			break;
		}
		case "INV": {
			if(size == 0) return false;
			stack.add(-1 * stack.pop());
			break;
		}
		case "DUP": {
			if(size == 0) return false;
			stack.add(stack.peek());
			break;
		}
		case "SWP": {
			if(size < 2) return false;
			long a = stack.pop();
			long b = stack.pop();
			
			stack.add(a);
			stack.add(b);
			break;
		}
		case "ADD": {
			if(size < 2) return false;
			long a = stack.pop();
			long b = stack.pop();
			
			if(Math.abs(a+b) > Math.pow(10, 9)) return false;
			stack.add(a+b);
			break;
		}
		case "SUB": {
			if(size < 2) return false;
			long a = stack.pop();
			long b = stack.pop();
			
			if(Math.abs(b-a) > Math.pow(10, 9)) return false;
			stack.add(b-a);
			break;
		}
		case "MUL": {
			if(size < 2) return false;
			long a = stack.pop();
			long b = stack.pop();
			
			if(Math.abs(a*b) > Math.pow(10, 9)) return false;
			stack.add(a*b);
			break;
		}
		case "DIV": {
			if(size < 2) return false;
			long a = stack.pop();
			long b = stack.pop();
			
			if(a == 0) return false;
			stack.add(b/a);
			break;
		}
		case "MOD": {
			if(size < 2) return false;
			long a = stack.pop();
			long b = stack.pop();
			
			if(a == 0) return false;
			stack.add(b%a);
			break;
		}
		// NUM X
		default: {
			stack.add(Long.parseLong(cmd.split(" ")[1]));
			break;
			}
		}
		
		return true;
		
	}
	
	private static void gameStart(long n, List<String> cmdList) throws IOException {
		
		Stack<Long> goStack = new Stack<Long>();
				
		goStack.add(n);
		boolean isError = false;
		for(String cmd : cmdList) {
			if(!operate(cmd, goStack)) {
				sb.append("ERROR").append('\n');
				isError = true;
				return; 
			}
		}
		if(goStack.size() != 1 && !isError) sb.append("ERROR").append('\n');
		else sb.append(goStack.peek()).append('\n');
		
	}
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		GoStack : while(true) {
			
			List<String> cmdList = new ArrayList<String>();
			
			// 명령어 리스트 갱신
			while(true) {
				String input = br.readLine();
				if(input.charAt(0) == 'E') break;
				if(input.charAt(0) == 'Q') break GoStack;
				cmdList.add(input);
			}
			long N = Integer.parseInt(br.readLine());
			
			// 프로그램에 넣을 숫자 갱신
			for(int i=0; i<N; i++) gameStart(Integer.parseInt(br.readLine()), cmdList);
			
			// 공백 처리
			sb.append('\n');
			br.readLine();
		}
		
		System.out.println(sb);
		
	}
	
}
