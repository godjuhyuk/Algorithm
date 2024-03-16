import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 시작: 오후 1시 53분
 *
 * 문제)
 * 8*8 체스판에 왕이 하나 있음 (돌도)
 * 위치와 움직임 정보가 주어지면, 돌이나 왕이 밖에 나가는 움직임을 거넌뛰면서 움직여서
 * 왕과 돌의 최종 위치를 반환
 *
 * 문제 풀이를 위한 고민)
 * 위치 어떻게 치환할건지?
 * - 미리 치환하는 데이터를 만들어놔도 되겠지만 연습이니 문자열을 잘라서 해보자
 *
 *
 * 프로그램 로직)
 * 1. 움직임 정보 받는다.
 * 2-1) 왕이 이동하려는 곳이 밖에 나간다면 skip
 * 2-2) 왕이 이동하려는 곳에 돌이 있다면, 돌이 같은 방향으로 이동했을 때 밖에 나간다면 skip
 * 3. 움직인다.
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String kingInfo = st.nextToken();
        int kingCol = kingInfo.charAt(0) - 'A';
        int kingRow = 7 - (kingInfo.charAt(1) - '1');

        String stoneInfo = st.nextToken();
        int stoneCol = stoneInfo.charAt(0) - 'A';
        int stoneRow = 7 - (stoneInfo.charAt(1) - '1');

        int N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            String moveInfo = br.readLine();
            int[] nextKingLoc = getNextLoc(kingRow, kingCol, moveInfo);
            if(isOutOfRange(nextKingLoc)) continue;
            else if(nextKingLoc[0] == stoneRow && nextKingLoc[1] == stoneCol) {
                // 왕 이동 위치에 돌이 있을 경우
                int[] nextStoneLoc = getNextLoc(stoneRow, stoneCol, moveInfo);
                if(isOutOfRange(nextStoneLoc)) continue;
                else {
                    stoneRow = nextStoneLoc[0];
                    stoneCol = nextStoneLoc[1];
                    kingRow = nextKingLoc[0];
                    kingCol = nextKingLoc[1];
                }
            } else {
                // 왕 이동 위치로 이동
                kingRow = nextKingLoc[0];
                kingCol = nextKingLoc[1];
            }
        }


        String finalKingLoc = "";
        String finalStoneLoc = "";

        finalKingLoc += String.valueOf((char)(kingCol + 'A'));
        finalKingLoc += String.valueOf(8 - kingRow);

        finalStoneLoc += String.valueOf((char)(stoneCol + 'A'));
        finalStoneLoc += String.valueOf(8 - stoneRow);

        System.out.println(finalKingLoc);
        System.out.println(finalStoneLoc);


    }

    private static boolean isOutOfRange(int[] nextLoc) {
        return nextLoc[0] < 0 || nextLoc[0] > 7 || nextLoc[1] < 0 || nextLoc[1] > 7;
    }

    private static int[] getNextLoc(int r, int c, String moveInfo) {
        int[] nextLoc = {r, c};

        switch(moveInfo){
            case "R":
                nextLoc[1]++;
                break;
            case "L":
                nextLoc[1]--;
                break;
            case "B":
                nextLoc[0]++;
                break;
            case "T":
                nextLoc[0]--;
                break;
            case "RT":
                nextLoc[0]--;
                nextLoc[1]++;
                break;
            case "LT":
                nextLoc[0]--;
                nextLoc[1]--;
                break;
            case "RB":
                nextLoc[0]++;
                nextLoc[1]++;
                break;
            case "LB":
                nextLoc[0]++;
                nextLoc[1]--;
                break;
        }

        return nextLoc;
    }

}
