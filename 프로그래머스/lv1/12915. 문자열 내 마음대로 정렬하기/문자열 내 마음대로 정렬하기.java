import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        SortString.idx = n;
        List<SortString> answer = new ArrayList<>();
        
        for(String s : strings){
            answer.add(new SortString(s));
        }
        
        Collections.sort(answer);
        
        for(int i=0; i<answer.size(); i++){
            
            strings[i] = answer.get(i).text;
            
        }
        
        return strings;
    }
}

class SortString implements Comparable<SortString> {
    
    String text;
    static int idx;
    
    public SortString(String s){
        this.text = s;        
    }
    
    @Override
    public int compareTo(SortString o){
        
        char a = this.text.charAt(idx);
        char b = o.text.charAt(idx);
        
        if(a == b){
            return this.text.compareTo(o.text);
        }
        
        return Character.compare(a, b);
    }
    
}