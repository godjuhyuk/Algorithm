import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static class Work {
		
		int time;
		int score;
		
		public Work(int score, int time) {
			this.score = score;
			this.time = time;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int scoreSum = 0;
		
		Stack<Work> stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			String cmd = br.readLine();
			
			// 일이 주어졌다면 Work 객체 생성
			if(cmd.length() > 1) {
				String[] info = cmd.split(" ");
				stack.add(new Work(Integer.parseInt(info[1]), Integer.parseInt(info[2])-1));
				
				// 받자마자 끝난 경우
				if(stack.peek().time == 0) {
					scoreSum += stack.peek().score;
					stack.pop();
				}
				
			} else {
				// 스택이 비어있지않고 맨 위 time이 0보다 크다면
				if(!stack.isEmpty() && stack.peek().time > 0) {
					Work nowWork = stack.peek();
					nowWork.time--;
					// 일이 끝났다면
					if(nowWork.time == 0) {
						scoreSum += nowWork.score;
						stack.pop();
					}
					
					// 일을 받자마자 진행해서 끝났다면
				} else if(!stack.isEmpty() && stack.peek().time == 0) {
					scoreSum += stack.peek().score;
					stack.pop();
				}
			}
			
		}
		System.out.println(scoreSum);
	}
}