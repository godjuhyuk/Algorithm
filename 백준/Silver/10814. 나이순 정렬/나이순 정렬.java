import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static class UserInfo implements Comparable<UserInfo> {
		
		int age;
		int order;
		String name;
		
		public UserInfo(int age, int order, String name) {
			super();
			this.age = age;
			this.order = order;
			this.name = name;
		}
		
		public int compareTo(UserInfo o) {
			if(this.age == o.age) return this.order - o.order;
			return this.age - o.age;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new UserInfo(Integer.parseInt(st.nextToken()), i, st.nextToken()));
		}
		
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(UserInfo userInfo : list) sb.append(userInfo.age).append(' ').append(userInfo.name).append('\n');
		
		System.out.println(sb);
	}
	
}
