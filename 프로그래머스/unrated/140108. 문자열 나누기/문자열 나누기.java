class Solution {
    public int solution(String s) {
        int answer = 0;
        
        char x = s.charAt(0);

        int idx = 1;
        int xCnt = 1;
        int otherCnt = 0;
        
        
        while(idx < s.length()){
            
            if(s.charAt(idx) == x) xCnt++;
            else otherCnt++;
            
            if(xCnt == otherCnt) {
                
                answer++;
                if(++idx < s.length()){
                    x = s.charAt(idx);
                    idx++;
                    xCnt = 1;
                    otherCnt = 0;
                }                
                
            } else {
                idx++;
            }           
        }
        
        if(xCnt != otherCnt) answer++;
        
        
        return answer;
    }
}