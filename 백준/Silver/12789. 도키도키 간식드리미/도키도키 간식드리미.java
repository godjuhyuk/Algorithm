import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 시작 시간: 오전 9시 48분
 *
 * 한명씩 설 수 있는 공간을 stack 으로 구현
 *
 * 문제풀이로직)
 *
 * 1. isSelected의 idx = 1을 설정한다.
 * 2. 현재 idx가 나올때까지 전부 stack에 넣는다.
 * 3. idx넣고 1을 더한다.
 * 4. stack에서 pop 하면서 넣는데 만약 idx가 아니라면 바로 sad
 * 5. idx라면 3번으로
 *
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) throws IOException  {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nowIdx = 1, headIdx = 1;
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++) nums[i] = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();

        while(nowIdx <= N) {

            int temp = nums[nowIdx];

            if(temp == headIdx) {
                headIdx++;
                nowIdx++;
            }
            else if(stack.size() > 0 && stack.peek() == headIdx) {
                headIdx++;
                stack.pop();
            }
            else {
                stack.push(temp);
                nowIdx++;
            }
        }

        while(stack.size() > 0) {
            if(stack.peek() == headIdx) {
                headIdx++;
            }
            stack.pop();
        }

        if(headIdx == N+1) System.out.println("Nice");
        else System.out.println("Sad");

    }

}
