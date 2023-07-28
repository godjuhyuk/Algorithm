import java.io.*;
import java.util.*;

public class Main {
	static int n, m, aCnt, bCnt;
	static boolean[] visited;
	static String[] strList;
	static List<String> pwList;
	static StringBuilder sb;
	
	
	public static void charCount(String str) {
		if(str.equals("a") || str.equals("e") || str.equals("i") || str.equals("o") || str.equals("u")) {
			aCnt++;
		} else {
			bCnt++;
		}
		
	}
	
	public static void dfs(int start, int depths) {
		if(depths==n) {
			for(int i=0; i<n; i++) {
				String temp = pwList.get(i);
				charCount(temp);
			}
			if(aCnt>=1 && bCnt>=2) {
				for(int i=0; i<n; i++) {
					sb.append(pwList.get(i));
				}
				sb.append("\n");
			}
			aCnt=0;
			bCnt=0;
			return;
		}
		for(int i=start; i<strList.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				pwList.add(strList[i]);
				dfs(i, depths+1);
				visited[i] = false;
				pwList.remove(strList[i]);
			}
		}
		
		
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        strList = br.readLine().split(" ");
        Arrays.sort(strList);
        pwList = new ArrayList();
        visited = new boolean[m];
        dfs(0, 0);
    	System.out.println(sb.toString());
    	
        
    }
}
