/**
물에 잠긴 지역이 없다고 해보자.

1. 1로 맨윗줄, 맨 왼쪽줄을 채운다.
2. 윗줄부터 돌면서 2방탐색으로 채운다.

*/
class Solution {
    public int solution(int n, int m, int[][] puddles) {
        
        int[][] map = new int[n][m];
        for(int[] p : puddles) {
            if(p.length > 0){    
            
            map[p[0]-1][p[1]-1] = -1;
                
            }
        }
        
        int i = 0;
        int j = 0;
        
        while(j < m && i+1 < n && map[i][j] != -1){
            if(map[i+1][j] != -1){
                map[i+1][j] = 1;
                i++;
            }
            else {
                break;
            }
        }
        
        i = 0;
        j = 0;
        while(i < n && j+1 < m && map[i][j] != -1) {
            if(map[i][j+1] != -1){
                map[i][j+1] = 1;
                j++;
            }
            else {
                break;
            }
        }
        
        for(int r=1; r<n; r++) {
            for(int c=1; c<m; c++) {
                
                if(map[r][c] != 0) continue;
                
                if(map[r][c-1] != -1 && map[r-1][c] != -1) {
                    map[r][c] += map[r][c-1] + map[r-1][c];
                }
                else if(map[r][c-1] == -1 && map[r-1][c] != -1) {
                    map[r][c] += map[r-1][c];
                }
                else if(map[r][c-1] != -1 && map[r-1][c] == -1) {
                   
                    map[r][c] += map[r][c-1] ;
                    
                }
                map[r][c] %= 1_000_000_007;
            }
        }
        
                     
        return map[n-1][m-1] % 1_000_000_007;
    }
}