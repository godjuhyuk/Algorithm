import java.util.*;
class Solution {
    
    public class Song implements Comparable<Song> {
        
        int id;
        int plays;
        
        public Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Song o1) {
            if(o1.plays == this.plays) return this.id - o1.id;
            return o1.plays - this.plays;
        }
        
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList();
        
        HashMap<String, Integer> rankMap = new HashMap();
        HashMap<String, Boolean> isSelected = new HashMap();
        HashMap<String, PriorityQueue<Song>> test = new HashMap();
        List<Integer> list = new ArrayList();
        
        for(String genre : genres) {
            rankMap.putIfAbsent(genre, 0);
            test.putIfAbsent(genre, new PriorityQueue());
            isSelected.putIfAbsent(genre, false);
        }
        for(int i=0; i<genres.length; i++) {
            
            test.get(genres[i]).offer(new Song(i, plays[i]));
            rankMap.put(genres[i], rankMap.get(genres[i]) + plays[i]);
        }
        
        for(String genre : genres) {
            list.add(rankMap.get(genre));
        }
        
        Collections.sort(list, Collections.reverseOrder());
        
        for(int i=0; i<list.size(); i++) {
            for(String genre: genres) {
                if(rankMap.get(genre) == list.get(i) && !isSelected.get(genre)) {
                    answer.add(test.get(genre).poll().id);
                    if(test.get(genre).size() > 0) answer.add(test.get(genre).poll().id);
                    isSelected.put(genre, true);
                }
            }
        }
        int[] temp = new int[answer.size()];
        for(int i=0; i<answer.size(); i++) temp[i] = answer.get(i);
        
        return temp;
    }
}