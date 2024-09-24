import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // charAt ver
    static int isPalindrome(String str, int left, int right, boolean isDel) {
    	
    	if(left >= right) {
    		return isDel ? 1 : 0;
    	}
    	
    	if(str.charAt(left) == str.charAt(right)) return isPalindrome(str, left+1, right-1, isDel);
    	else if(isDel) return 2;
    	else {
    		return Math.min(isPalindrome(str, left+1, right, true), isPalindrome(str, left, right-1, true));
    	}

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            // 회문 체크
            int result = isPalindrome(input, 0, input.length()-1, false);
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

}