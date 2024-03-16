import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 시작 시간: 오후 12시 55분
 *
 * 문제)
 * 세로 두줄, 가로 N칸
 * 첫째줄에는 1 ~ N까지 차례대로 적혀있음
 * 두번재 줄의 각 칸에는 1이상 N이하인 정수가 들어있음
 *
 * 숫자 뽑는 조건
 *  그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다.
 *
 * 문제 해석)
 * 조합으로는 불가능
 * DFS로 순환하는걸 찾으면 될듯?
 *
 * 풀이)
 * 임시 visited배열과 정답 visited 배열을 만든다.
 * 임시 visited 배열을 체크해가면서 dfs하다가 사이클 감지하면
 * dfs 멈추고 그 위치부터 싸이클 카운트 시작하면서 정답 visited 배열에 true 반영
 *
 */
public class Main {

    private static int N, numArr[];
    private static boolean[] ans, temp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numArr = new int[N+1];
        ans = new boolean[N+1];
        temp = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++) {
            if(!ans[numArr[i]]) {
                circularCheck(numArr[i]);
                Arrays.fill(temp, false);
            }
        }

        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        for(int i=1; i<=N; i++) {
            if(ans[i]) {
                cnt++;
                sb.append(i).append('\n');
            }
        }
        System.out.println(cnt);
        System.out.println(sb);

    }

    private static void circularCheck(int now) {

        int next = numArr[now];

        // 기저 조건 - 싸이클 발생
        if(temp[next]) {
            circularCount(numArr[next]);
            return;
        }

        temp[next] = true;
        circularCheck(next);

    }

    private static void circularCount(int num) {

        ans[num] = true;

        int next = numArr[num];
        if(!ans[next]) {
            circularCount(next);
        } else {
            return;
        }

    }

}
