/**

문제 해결을 위한 고민)
1. n이 0이 될때까지 while문 돌리자
2. 심사대가 빌 때, 사람을 넣어야하는데 만약 더 일잘하는 심사관이 몇분 안남았다면?
2-1) 1. 빈 심사관보다 일잘하는 사람들을 전부 탐색을 할 순 없는데.. lowerbound로 찾자
=> 이득보는걸 어떻게 알지? 
=> 남은 시간 + 일하는 시간 > 현재 심사관이 걸리는 시간 

이러면 시간복잡도는?


*/

import java.util.*;
class Solution {
    public long solution(int n, int[] times) {

        Arrays.sort(times);
        
        long left = 0, right = (long) times[times.length-1] * n;
        
        while(left < right) {
            
            long mid = (left + right) / 2;
            long sum = 0;
            
            for(int time: times) sum += mid / time;
            
            if(sum >= n) right = mid;
            else left = mid + 1;
            
        }
        
        
        return left;
    }
}