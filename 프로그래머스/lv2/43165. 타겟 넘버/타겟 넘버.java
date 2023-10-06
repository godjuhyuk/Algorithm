class Solution {
    static int answer = 0;
    static int target;
    public int solution(int[] numbers, int a) {
        target = a;
        dfs(numbers, 0, 0);
        return answer;
    }
    
    public void dfs(int[] numbers, int sum, int depths){
        
        // 기저조건
        if(depths == numbers.length){
            if(sum == target) answer++;
            return;   
        }
        
        dfs(numbers, sum + numbers[depths], depths+1);
        dfs(numbers, sum + -1 * numbers[depths], depths+1);
        
    }
    
    
}