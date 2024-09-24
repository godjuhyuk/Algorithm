import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int isPalindrome(String str, int left, int right, int idx, boolean isDel) {
    	if(idx == str.length() / 2) {
    		return isDel ? 1 : 0;
    	}
    	
    	if(str.charAt(left + idx) == str.charAt(right - idx)) return isPalindrome(str, left, right, idx + 1, isDel);
    	else if(isDel) return 2;
    	else {
    		return Math.min(isPalindrome(str, left+1, right, idx, true), isPalindrome(str, left, right-1, idx, true));
    	}

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            // 회문 체크
            int result = isPalindrome(input, 0, input.length()-1, 0, false);
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

}