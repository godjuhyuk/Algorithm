/**
시작시간: 오전 11시 48분

논문 n편 중 h번 이상 인용된 논문이 h편 이상이고 
나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 H-Index임

규칙 찾기:
[3, 0, 6, 1, 5]

h=0 => x
h=1 => 5편 중 1번 이상 인용된 논문이 4편, 나머지 논문이 1번 이하 인용
h=2 => 5편 중 2번 이상 인용된 논문이 3편, 나머지 논문이 2번 이하 인용
h=3 => 5편 중 3번 이상 인용된 논문이 3편, 나머지 논문이 3번 이하 인용

조건이 이상과 이하이기 때문에
정렬해서 h에 따라서 높은 수부터 카운트 하고, 카운트가 끝나는 곳에서 나머지 길이로 조건 만족하는지 보면 됨
*/

import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int max = 0;
        for(int i=0; i<citations.length; i++) max = Math.max(max, citations[i]);
        
        for(int h=max; h>0; h--) {
            int cnt1 = 0;
            for(int j=citations.length-1; j>=0; j--){
                
                if(citations[j] >= h) {
                    cnt1++;
                }
            }
            
            if(cnt1 >= h && citations.length - cnt1 <= h) return h;
        }
        
        return answer;
    }
}