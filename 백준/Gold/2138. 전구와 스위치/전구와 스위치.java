import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 해석)
 *
 * N개의 스위치, N개의 전구가 있다.
 * 1 < i < N에 대해, i번 스위치를 누르면 i-1, i, i+1 전구 토글
 * i = 1혹은 N일때는 (0, 1), (N-1, N)만 토글
 *
 * 현재 전구 상태와 우리가 만들고자 하는 상태가 주어질 때, 최소 조작으로 해당 상태를 만들어야한다.
 *
 * 문제 풀이를 위한 고민)
 *
 * - 규칙 찾아보기
 * 000
 * 010
 *
 *
 *
 *
 */
public class Main {
    private static int n, ans = Integer.MAX_VALUE;
    private static char[] input, want;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        input = br.readLine().toCharArray();
        want =  br.readLine().toCharArray();

        if(equalCheck(input, want)) {
            System.out.println(0);
            return;
        }

        solve(0, Arrays.copyOf(input, n));
        solve(1, Arrays.copyOf(input, n));

        if(ans != Integer.MAX_VALUE )System.out.println(ans);
        else System.out.println(-1);
    }

    private static void solve(int flag, char[] origin) {
        int cnt = 0;

        if(flag == 1) {
            click(0, origin);
            cnt++;
        }

        for(int i = 0; i < n-1; i++) {
            if(origin[i] != want[i]) {
                cnt++;
                click(i+1, origin);
            }
            if(equalCheck(origin, want)) ans = Math.min(ans, cnt);
        }

    }

    private static boolean equalCheck(char[] origin, char[] want) {

        for(int i = 0; i < n; i++) {
            if(origin[i] != want[i]) return false;
        }

        return true;
    }

    private static void click(int i, char[] origin) {

        for(int j=i-1; j<n && j<=i+1; j++) {
            if(j<0) continue;

            if(origin[j] == '1') origin[j] = '0';
            else origin[j] = '1';
        }

    }
}
