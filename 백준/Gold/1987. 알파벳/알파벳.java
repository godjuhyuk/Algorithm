import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    static int R, C;
    static char[][] map;
    static int[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        visited = new int[R][C];
        dfs(0, 0, 1, 1<<(map[0][0] - 'A'));

        System.out.println(max);
    }

    static void dfs(int i, int j, int cnt, int flag) {
        if (visited[i][j] == flag) {
        	return;
        }

        visited[i][j] = flag;
        max = Math.max(max, cnt);

        for (int d = 0; d < 4; d++) {
            int newI = i + di[d];
            int newJ = j + dj[d];
            if (newI < 0 || newJ < 0 || newI > R-1 || newJ > C-1) continue;

            int alph = map[newI][newJ] - 'A';  // 몇번째 알파벳인지
            if ((flag & 1<<alph)==0) {  // 아직 방문하지 않은 알파벳인 경우
                dfs(newI, newJ, cnt+1, flag | 1<<alph);  // 카운트를 올려주고 플래그에 현재 알파벳 방문 처리
            }
        }
    }

}