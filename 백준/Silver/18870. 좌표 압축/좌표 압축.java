import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 시작 시간: 오전 11시 45분
 *
 * 문제 설명)
 * N개의 좌표에 좌표 압축 적용
 * X_i를 압축한 결과인 X'_i의 값은 X_i > X_j를 만족하는 서로 다른 좌표 X_j의 개수와 같아야한다.
 * X1, X2, ..., Xn 에 좌표 압축을 적용한 결과 X'_1, ..., X'_...N을 출력해보자.
 *
 * 문제 풀이를 위한 고민)
 * 1. 100만개를 입력받고 정렬해서 (100만 log 100만 => 2천만)
 * 2. 100만개 순회하면서 HashMap에 0부터 넣는다.
 * 3. 다시 입력값을 순회하면서 HashMap값을 출력한다.
 *
 * => 정렬을 안하고 풀 수 있나? => 비효율
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ans = new int[N];
        int[] inputArr = new int[N];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            inputArr[i] = Integer.parseInt(st.nextToken());
            ans[i] = inputArr[i];
        }

        Arrays.sort(ans);

        for(int i=0, val=0; i<N; i++) {
            if(map.containsKey(ans[i])) continue;
            else map.put(ans[i], val++);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(map.get(inputArr[i])).append(' ');
        }
        System.out.println(sb);

    }

}
