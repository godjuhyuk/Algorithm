/*

문제 해석)
words 에 있는 단어 중 before와 알파벳 하나만 다른 놈으로만 바꿀 수 있다.



*/
import java.util.*;
class Solution {
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        HashMap<String, Boolean> visited = new HashMap();
        for(String word : words) {
            visited.putIfAbsent(word, false);
        }
        
        answer = bfs(begin, target, words, visited);
        
        return answer;
    }
    
    public int bfs(String begin, String target, String[] words, HashMap<String, Boolean> visited) {
        visited.put(begin, false);
        
        Queue<String> q = new ArrayDeque();
        q.offer(begin);
        int max = Integer.MAX_VALUE;
        int cnt = 0;
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i=0; i<qSize; i++) {
                String temp = q.poll();
                if(temp.equals(target)) {
                    return cnt;
                }
                
                for(String word: words) {
                    if(isChangable(temp, word) && !visited.get(word)) {
                        visited.put(word, true);
                        q.offer(word);
                    }
                }
               
            }
            cnt++;
        }
        
        return 0;
        
    }
    
    public boolean isChangable(String target, String word) {
       
        int diffCnt = 0;
        for(int i=0; i<target.length(); i++) {
            if(target.charAt(i) != word.charAt(i)) diffCnt++;
        }
        
        return diffCnt == 1;
    }
    
}