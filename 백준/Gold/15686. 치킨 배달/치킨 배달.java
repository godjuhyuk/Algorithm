import java.io.*;
import java.util.*;

public class Main {
	static int n, m, min;
	static boolean[] visited;
    static ArrayList<int[]> homeInfo;
    static ArrayList<int[]> chickenInfo;
    
    public static void dfs(int start, int cnt) {
    	
    	// m개의 치킨집을 전부 방문했으면 작동
        if(cnt == m) {
        	int sum = 0;
        	for(int i=0; i< homeInfo.size(); i++) {
        		
        		int minDist = Integer.MAX_VALUE;
        		int[] tempHome = homeInfo.get(i);
				
        		for(int j=0; j<visited.length; j++) {
					if(visited[j]) {
        				int[] tempChicken = chickenInfo.get(j);
        				minDist = Math.min(minDist, Math.abs(tempHome[0] - tempChicken[0]) + Math.abs(tempHome[1] - tempChicken[1]));
					}
        		}
				sum+= minDist;
				if(sum >= min) {
					return;
				}
        	}
        	min = Math.min(sum, min);
        	return;
        }
        
        for(int i=start; i<chickenInfo.size(); i++) {
        	visited[i] = true;
        	dfs(i+1, cnt+1);
        	visited[i] = false;
        }
    	
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        min = Integer.MAX_VALUE;
        homeInfo = new ArrayList<>();
        chickenInfo = new ArrayList<>();

        // 치킨집 위치와 집 위치를 각각 저장
        for(int i=1; i<=n; i++) {
            String[] temp = br.readLine().split(" ");
            for(int j=1; j<=n; j++) {
                if(temp[j-1].equals("2")) {
                    chickenInfo.add(new int[] {i,j});
                }
                else if(temp[j-1].equals("1")) {
                    homeInfo.add(new int[] {i,j});
                }
            }
        }
        visited = new boolean[chickenInfo.size()];
        dfs(0, 0);
        System.out.println(min);
        br.close();
    }
}


