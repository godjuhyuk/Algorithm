/*
최소 필요 피로도: 탐험 시작하기 위해 필요한 피로도
소모 피로도: 탐험을 마쳤을 때 소모되는 피로도

최대한 이 던전을 많이 탐험해야함

k : 유저의 현재 피로도 <= 1000
dungeons: 8*2의 배열

8!의 경우의 수

유저가 탐험할 수 있는 최대 던전 수를 return

*/
class Solution {
    private static int answer;
    private static boolean[] isSelected;
    
    void recursive(int step, int count, int energy, int[][] dungeons){
        
        if(step == dungeons.length) {
            System.out.println(step);
            answer = Math.max(count, answer);
            return;
        }
                
        
        for(int i=0; i<dungeons.length; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                if(energy >= dungeons[i][0]){
                    recursive(step+1, count+1, energy-dungeons[i][1], dungeons);
                }  else {
                    recursive(step+1, count, energy, dungeons);
                }
                isSelected[i] = false;
            }
        }
        
        
    }
    
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        isSelected = new boolean[dungeons.length];
        recursive(0, 0, k, dungeons);
        
        return answer;
    }
}