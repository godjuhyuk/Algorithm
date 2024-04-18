import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 시작 시간: 오후 9시 55분
 *
 * 문제 해석)
 *
 * 총 N개의 강의, 순서 유지
 * i < j에 대해, i번 강의와 j번 강의를 한 영상에 넣으려면 i와 j 사이의 강의도 넣어야한다.
 *
 * M개의 블루레이에 N개의 영상을 담아야한다.
 * 단, 모든 블루레이의 크기는 같은 크기여야한다.
 *
 * 연속으로 담으면서, 분산을 최소로 해야한다.
 *
 * 문제 풀이를 위한 고민)
 *
 * 1. 그리디?
 * - 주어진 값에 따라 방법이 너무 달라지기 때문에 아닌거같음
 *
 * 2. 완탐?
 * i) M == 1일 경우
 * => 총합이 정답
 *
 * ii) M == N일 경우
 * => 주어진 N개 값 중 최댓값이 정답
 *
 *  => 정답의 범위: 최댓값 <= x <= all
 *  => 최악 범위: 9999 <= x <= 10만 * 9999 => 약 10억 => 10^9
 *  => 이진탐색으로 30번
 *
 * 로직)
 *
 * 1. max of N과 총합을 구한다.
 * 2. 이진탐색해서 a를 뽑는다.
 * 3. a로 M개의 블루레이 제작이 가능한지 check
 * 3-1)
 * idx: 1부터 시작해서 합을 구하다가 a 넘으면 블루레이 1개 적립
 * 4. 성공하면 a는 정답 후보가 된다.
 * 5. 성공/실패에 관계없이 특정될때까지 계속해야함
 *
 */
public class Main {
    private static int N, M, nums[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int max = 0;
        int sum = 0;

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if(max < nums[i]) max = nums[i];
            sum += nums[i];
        }

        int left = max;
        int right = sum;

        while(left <= right) {
            int mid = (left + right) / 2;
            int check = ansCheck(mid);
            if(check == M) {
                right = mid - 1;
            }
            else if(check > M) {
                left = mid+1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(left);

    }

    private static int ansCheck(int mid) {
        int bluerayCount = 0;

        // 1부터 시작해서 합을 구하다가 mid 넘으면 블루레이 1개 적립
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
//            sum += nums[i];
//            if(sum > mid) {
//                bluerayCount++;
//                i--;
//                sum = 0;
//            }

            if(sum+nums[i]>mid){
                bluerayCount++;
                sum = 0;
            }
            sum = sum+nums[i];

        }

        if(sum != 0) bluerayCount++;
        return bluerayCount;
    }
}
