import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시작 시간: 오후 5시 44분
 * 문제)
 * N*N 교실, N^2 학생
 * 교실 가장 왼쪽칸: (1,1), 가장오른쪽아래: (N,N)
 *
 * 각 학생이 좋아하는 학생 4명 조사
 * 가우스 거리가 1인 두 칸이 인접하다. 라고 함
 *
 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정함
 * 2. 1을 만족하는 칸이 여러개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정함
 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러개이면 열의 번호가 가장 작은 칸으로
 *
 * 문제 해결을 위한 고민)
 * 각 조건에 해당하는 체크 로직을 어떻게 짤건지?
 * 1. 비어있는 칸을 전부 돌면서 4방탐색 (N^2 * 4)
 * 2. 비어있는 칸을 전부 돌면서 다시 4방 탐색하며
 * 3. 2번의 2중for문에서 열부터 보면서 다시 탐색하다가
 *
 * 시간복잡도(
 * N^2 명의 학생을 앉혀야한다.
 * 각 학생이 앉을 때 마다 N^2 이 걸리므로 N^4 => 20^4
 *
 *
 */
public class Main {

    private static int N;
    private static int[] pointArr = {0, 1, 10, 100, 1000};
    private static int[][] map, likeArr, deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        likeArr = new int[N*N+1][4];

        for(int i=0; i<N*N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int stdNum = Integer.parseInt(st.nextToken());

            likeArr[stdNum][0] = Integer.parseInt(st.nextToken());
            likeArr[stdNum][1] = Integer.parseInt(st.nextToken());
            likeArr[stdNum][2] = Integer.parseInt(st.nextToken());
            likeArr[stdNum][3] = Integer.parseInt(st.nextToken());

            // 자리 탐색 메소드
            searchSeat(stdNum);
        }
        
        int ans = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++) {
                ans += calculPoint(map[r][c], r, c);
            }
        }


        System.out.println(ans);

    }

    private static int calculPoint(int stdNum, int r, int c) {

        int cnt = 0;
        for(int[] d : deltas) {
            int nr = r + d[0];
            int nc = c + d[1];

            if(isOutOfRange(nr, nc)) continue;

            for(int i=0; i<4; i++) {
                if(map[nr][nc] == likeArr[stdNum][i]) cnt++;
            }
        }

        return pointArr[cnt];

    }

    private static void searchSeat(int stdNum) {

        int rowMin = N+1;
        int colMin = N+1;
        int likeMax = -1;
        int blankMax = -1;

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++){

                if(map[r][c] > 0) continue;

                int tempBlankCnt = 0;
                int tempLikeCnt = 0;

                for(int[] d : deltas) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if(isOutOfRange(nr, nc)) continue;

                    if(map[nr][nc] == 0) tempBlankCnt++;
                    else {
                        for(int i=0; i<4; i++) {
                            if(map[nr][nc] == likeArr[stdNum][i]) tempLikeCnt++;
                        }
                    }
                }

                if(likeMax < tempLikeCnt) {
                    rowMin = r;
                    colMin = c;
                    likeMax = tempLikeCnt;
                    blankMax = tempBlankCnt;
                }
                else if(likeMax == tempLikeCnt && blankMax < tempBlankCnt){
                    rowMin = r;
                    colMin = c;
                    blankMax = tempBlankCnt;
                }
            }
        }

        map[rowMin][colMin] = stdNum;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r<0 || c < 0 || r>=N || c >=N;
    }

}
