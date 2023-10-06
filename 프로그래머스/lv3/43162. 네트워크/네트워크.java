// 설계시작시간 : 22시 54분
// DFS로 네트워크의 개수를 세어보자.
// 0부터 for문으로 순회하면서 visit처리하며 카운트

import java.util.*;

class Solution {
    
    static int answer;
    static boolean[] visited;
    
    public void bfs(int[][] computers, int now, int N){
        
        Queue<Integer> q = new ArrayDeque();
        
        q.offer(now);
        
        while(!q.isEmpty()){
            
            int temp = q.poll();
            
            for(int i=0; i<N; i++){
                if(computers[temp][i] > 0 && !visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
            
        }
        
        
    }
    
    public int solution(int n, int[][] computers) {
        answer = 0;
        
        visited = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(!visited[i]) {
                visited[i] = true;
                bfs(computers, i, n);
                answer++;
            }
        }        
        
        return answer;
    }
}