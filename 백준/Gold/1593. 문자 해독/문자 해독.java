import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 마야문자는 특정 규칙대신 그들이 보기에 좋도록 단어를 이루는 글자들을 아무렇게나 배열함
 * 
 * 고고학자들은 W라는 특정 단어를 발굴기록으로부터 찾고있음.
 * 그 단어를 구성하는 각 글자들은 무엇인지 알고 있지만, 어떤 형태로 숨어있는지는 다 알지 못함
 * 
 * W를 이루고 있는 g개의 그림문자와, 연구 대상인 벽화에 기록된 마야 문자열 S가 주어졌을 때
 * 단어 W가 마야 문자열 S에 들어있을 수 있는 모든 가짓수를 계산하는 프로그램 작성
 * 
 * 문제 해결을 위한 고민)
 * 슬라이딩 윈도우를 하면서 바로 검증을 마치는 방법이 있나?
 * 300만 * log3000 
 * 이거밖에 없는거같다 .
 * 
 * 
 * [a: 1, b: 1, c: 1]
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class Main {
	private static int[] W, temp; 
	
	public static void main(String[] args) throws IOException
	{
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		W = new int[130];
		temp = new int[130];
		
		String wInput = br.readLine();
		
		for(int i=0; i < g; i++) {
			W[wInput.charAt(i)]++;
		}
		
		int idx = 0, cnt = 0;
		String S = br.readLine();
		
		for(int i=0; i < s; i++) {
			
			temp[S.charAt(i)]++;
			
			if(i >= g-1) {
				
				if(Arrays.equals(W, temp)) cnt++;
				
				temp[S.charAt(idx++)]--;
			}
			
			
		}
		
		System.out.println(cnt);
		
	}
	
}