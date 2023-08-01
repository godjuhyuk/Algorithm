import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//	어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.
//	"재귀함수가 뭔가요?"
//	"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.
//	마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.
//	그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."
//	____"재귀함수가 뭔가요?"
//	____"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.
//	____마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.
//	____그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."
//	________"재귀함수가 뭔가요?"
//	________"재귀함수는 자기 자신을 호출하는 함수라네"
//	________라고 답변하였지.
//	____라고 답변하였지.
//	라고 답변하였지.
	static int n;
	static String[] recurList = {"\"재귀함수가 뭔가요?\"\n", 
			"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n", 
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n", 
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n"};
	static String[] returnList = {"\"재귀함수가 뭔가요?\"\n","\"재귀함수는 자기 자신을 호출하는 함수라네\"\n", 
			"라고 답변하였지.\n"}; 
	
	static StringBuilder sb;
	private static void recursive(int cnt) {
		
		String line = "";
		for(int i=0; i<cnt; i++) {
			line += "____";
		}
		if(cnt == n) {
			for(int i=0; i< returnList.length; i++) {
				sb.append(line);
				sb.append(returnList[i]);
			}

			return;
		}
		for(int i=0; i<recurList.length; i++) {
			sb.append(line);
			sb.append(recurList[i]);
		}
		recursive(cnt+1);
		sb.append(line);
		sb.append("라고 답변하였지.");
		sb.append("\n");

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		recursive(0);
		System.out.println(sb.toString());
		
		
	}
}