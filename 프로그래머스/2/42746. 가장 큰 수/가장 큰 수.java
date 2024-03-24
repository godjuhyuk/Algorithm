/*
어떤 것 먼저?

*/
import java.util.*;
class Solution {
    public static class StringNum implements Comparable<StringNum> {
        
        String num;
        
        public StringNum(String num){
            this.num = num;
        }
        
        @Override
        public int compareTo(StringNum o1){
            return -1 * (this.num + o1.num).compareTo(o1.num + this.num);
        }
        
    }

    public String solution(int[] numbers) {
        String answer = "";
        
        StringNum[] nums = new StringNum[numbers.length];
        
        for(int i = 0; i < nums.length; i++) {
            nums[i] = new StringNum(String.valueOf(numbers[i]));
        }
        
        Arrays.sort(nums);
        
        if(nums[0].num.equals("0")) return "0";

        for(int i=0; i<nums.length; i++) answer += nums[i].num;
        
        return answer;
    }
}