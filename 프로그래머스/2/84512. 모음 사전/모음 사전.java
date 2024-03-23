/*
문제 해석)
단어가 주어졌을 때 사전에서 몇번째 단어인지 구해야한다.

문제 해결을 위한 고민)
사전을 어떻게 구축해야하는가?

규칙을 파악해보자.

A, AA, AAA, AAAA, AAAAA
AAAAE, AAAAI, AAAAO, AAAAU
AAAE, AAAEA, AAAEE, AAAEI, AAAEO, AAAEU
AAAI, AAAIA, AAAIE, AAAII, AAAIO, AAAIU
AAAO, AAAOA, AAAOE, AAAOI, AAAOO, AAAOU
AAAU, ...
AAE, AAEA, AAEAA, AAEAE,
=> 그냥 재귀로 다 list에 때려놓고 정렬해서 조회?
캬
*/
import java.util.*;
class Solution {
    public List<String> dict;
    public int solution(String word) {
        int answer = 0;
        dict = new ArrayList();
        
        makeDictionary("", new String[] {"A", "E", "I", "O", "U"});
        Collections.sort(dict);
        
        for(int i=0; i<dict.size(); i++){
            if(dict.get(i).equals(word)){
                return i+1;
            }
        }
        
        
        return answer;
    }
    
    public void makeDictionary(String now, String[] candidateList){
        
        if(now.length() == 5) return;
        
        for(String candidate : candidateList){
            String temp = now + candidate;
            dict.add(temp);
            makeDictionary(temp, candidateList);
        }
        
        
    }
}