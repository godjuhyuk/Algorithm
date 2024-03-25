import java.util.*;

class Solution {
    
    int N, M, answer;
    int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    boolean[][] visited;
        
    
    public int solution(int[][] maps) {
        answer = Integer.MAX_VALUE;
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        
        BFS(maps);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public void BFS(int[][] map) {
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        int turn = 0;
        while(!q.isEmpty()) {
            
            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int[] temp = q.poll();
                
                if(temp[0] == N-1 && temp[1] == M-1) {
                    answer = Math.min(answer, turn+1);
                    continue;
                }

                for(int[] d: deltas) {
                    int nr = temp[0] + d[0];
                    int nc = temp[1] + d[1];

                    if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;

                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});

                }
            }
            turn++;
            
        }
        
        
    }
    
    public boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    
}