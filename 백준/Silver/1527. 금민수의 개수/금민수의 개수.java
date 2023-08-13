import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static List<Integer> fourAndSevenList;
	
	private static void recursive(int cnt, int nowNum) {
		
		if(cnt == 9) {
			return;
		}
		fourAndSevenList.add(nowNum);
		
		int a = nowNum*10 + 4;
		int b = nowNum*10 + 7;
		recursive(cnt+1, a);
		recursive(cnt+1, b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		fourAndSevenList = new ArrayList<>();
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		recursive(0, 4);
		recursive(0, 7);
		
		int ans = 0;
		for(int 금민수 : fourAndSevenList) {
			if(금민수 >= A && 금민수 <= B) {
				ans++;
			} 
		}
		System.out.println(ans);
	}
}
