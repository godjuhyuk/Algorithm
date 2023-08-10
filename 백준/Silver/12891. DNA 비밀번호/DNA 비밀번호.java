import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static char[] chr;
	private static HashMap<Character, Integer> map, check;
	private static boolean  passwordCheck() {
		return map.get(chr[0]) >= check.get(chr[0]) && map.get(chr[1]) >= check.get(chr[1]) && map.get(chr[2]) >= check.get(chr[2]) && map.get(chr[3]) >= check.get(chr[3]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		chr = new char[] {'A', 'C', 'G', 'T'};
		// 문자열 count용도
		map = new HashMap<Character, Integer>();
		// 비밀번호 조건 저장 map
		check = new HashMap<Character, Integer>();
		//비교할 문자열 대기 큐
		Queue<Character> queue = new LinkedList<Character>();
		map.put('A', 0);
		map.put('C', 0);
		map.put('G', 0);
		map.put('T', 0);
		
		
		//문자열 입력받기 (A,C,G,T)순
		String str = br.readLine();
		
		//비밀번호 조건
		st = new StringTokenizer(br.readLine());
		check.put('A', Integer.parseInt(st.nextToken()));
		check.put('C', Integer.parseInt(st.nextToken()));
		check.put('G', Integer.parseInt(st.nextToken()));
		check.put('T', Integer.parseInt(st.nextToken()));
		
		// 초기값 세팅
		for(int i=0; i<P-1; i++) {
			queue.add(str.charAt(i));
			map.put(str.charAt(i), map.get(str.charAt(i))+1);
		}
		
		
		//입력받은 문자열 char 배열로 만들기
		for(int i = P-1; i<S; i++) {
			
			queue.add(str.charAt(i));
			map.put(str.charAt(i), map.get(str.charAt(i))+1);
			
			if(passwordCheck()) {
				cnt++;
			}
			
			char temp = queue.poll();
			map.put(temp, map.get(temp)-1);
				
		}
		System.out.println(cnt);
		
	}

}