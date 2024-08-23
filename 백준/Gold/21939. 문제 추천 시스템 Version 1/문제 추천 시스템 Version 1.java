import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	private static class Problem implements Comparable<Problem> {
		
		int ver;
		int num;
		int level;
		public Problem(int num, int level, int ver) {
			super();
			this.ver = ver;
			this.num = num;
			this.level = level;
		}
		
		public int compareTo(Problem o) {
			if(this.level == o.level) return this.num - o.num;
			return this.level - o.level;
		};
		
	}
	
	private static int N, M;
	private static int[][] problemInfo = new int[100001][2];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		TreeSet<Problem> book = new TreeSet<Problem>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			problemInfo[num][0] = level;
			book.add(new Problem(num, level, problemInfo[num][1]));
		}
		
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if(cmd.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				problemInfo[num][0] = level;
				book.add(new Problem(num, level, problemInfo[num][1]));
			}
			else if (cmd.equals("solved")) {
				int num = Integer.parseInt(st.nextToken());
				problemInfo[num][1]++;
			}
			else {
				int cmdNum = Integer.parseInt(st.nextToken());
				if(cmdNum == -1) {
					
					while(book.first().ver != problemInfo[book.first().num][1]) book.pollFirst();
					sb.append(book.first().num).append('\n');
				}
				else {
					while(book.last().ver != problemInfo[book.last().num][1]) book.pollLast();
					sb.append(book.last().num).append('\n');
				}
			}
		}
		
		System.out.println(sb);
	}
	
	
}
