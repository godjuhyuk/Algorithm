import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 시작 시간: 오후 7시 37분
 *
 * 문제 해석)
 * N*N 블록, 초기 모든 격자엔 블록이 하나씩 있음
 * 블록 종류 : 검은 블록, 무지개 블록, 일반 블록
 * - 일반 블록 : M가지 색상이 있고 색은 M이하 자연수로 표현
 * - 검은색 블록 : -1
 * - 무지개 블록 : 0
 *
 * 블록 그룹은 연결된 블록의 집합
 * - 그룹에는 적어도 하나의 일반 블록이 있어야하며, 일반 블록의 색은 모두 같아야한다.
 * - 검은색 블록은 포함하면 안된다.
 * - 무지개 블록은 얼마나 있어도 상관없다.
 * - 그룹에 속한 블록의 개수는 2보다 크거나 같아야한다.
 * - 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야한다.
 * - 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 같다면 열의 번호가 가장 작은 블록
 *
 * 다음 기능이 반복되어야한다.
 * 1. 크기가 가장 큰 블록 그룹 찾는다. 그런 그룹이 여러개라면 무지개 블록을 가장 많이 포함한 블록, 여러개라면 기준 블록의 행이 가장 큰 것, 여러개라면 열이 가장 큰것
 * 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라 하면 B^2 점을 획득한다.
 * 3. 격자에 중력이 작용한다.
 * 4. 격자가 90도 반시계 방향으로 회전
 * 5. 다시 격자에 중력이 작용
 *
 * 중력 : 격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.
 *
 * 문제 풀이를 위한 고민)
 *
 * 1. 블록 그룹을 어떻게 찾을 것인지?
 * - visited를 만들고, 0은 모아놨다가 블록 형성이 끝나면 다시 0을 꺼내면서 visited false로 바꾸기
 * - 대표 번호를 신경써야하니 그룹 class를 따로 만들자.
 * - class에는 일반 블록 번호, 대표번호, 크기
 *
 * 2. 블록 제거 로직
 * - 메모리가 널널하니까 class에 블록 리스트도 담아놓자.
 * - 점수 획득
 *
 * 3. 중력 작용 로직
 * - 바닥 찾고 바닥부터 위로 올라가면서 당길 수 있는 블록 찾으면 당기기?
 * - 다시 검은색 블록 만나는 경우 생각하기
 *
 * 4. 90도 반시계 로직
 * - 0, 0 부터 시작해서 < N/2 ?*
 *
 * 5. 한번 더 중력
 *
 */
public class Main {

    private static int N, M, ans;
    private static final int AIR = -9999;
    private static int[][] map, deltas = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};

    static class BlockGroup {

        int color;
        int colorCnt = 1;
        int bossRow;
        int bossCol;
        List<int[]> blockList = new ArrayList<>();

        public BlockGroup(int color, int bossRow, int bossCol) {
            this.color = color;
            this.bossRow = bossRow;
            this.bossCol = bossCol;        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {

            // 블록 그룹 찾기
            BlockGroup blockGroup = findGroup();
            if(blockGroup.blockList.size() < 2) break;

            // 블록 제거
            for(int[] block : blockGroup.blockList){
                map[block[0]][block[1]] = AIR;
            }
            ans += blockGroup.blockList.size() * blockGroup.blockList.size();

            // map 중력 작용
            gravity();

            // map 90도 반시계 회전
            turnCounterClockwise();

            // map 중력 작용
            gravity();




        }

        System.out.println(ans);

    }

    private static void turnCounterClockwise() {

        for(int r=0, c=0, size=N; r<N/2; r++, c++, size-=2){
            for(int i=0; i<size-1; i++){
                turn(r, c, size);
            }
        }
    }

    private static void turn(int r, int c, int size) {

        if(size == 1) return;

        int memo = map[r][c];
        int tempRow = r;
        int tempCol = c;

        // r, c 부터 r, c+size 당기기
        while(tempCol < c + size - 1){
            map[tempRow][tempCol] = map[tempRow][tempCol+1];
            tempCol++;
        }

        // r, c + size 부터 r + size, c+size 당기기
        while(tempRow < r + size - 1){
            map[tempRow][tempCol] = map[tempRow+1][tempCol];
            tempRow++;
        }

        // r + size, c + size 부터 r + size, c 당기기
        while(tempCol > c){
            map[tempRow][tempCol] = map[tempRow][tempCol-1];
            tempCol--;
        }

        // r + size, c 부터 r, c 당기기
        while(tempRow > r+1){
            map[tempRow][tempCol] = map[tempRow-1][tempCol];
            tempRow--;
        }

        map[tempRow][tempCol] = memo;
    }

    private static void gravity() {

        int memo = 0;
        for(int c=N-1; c>=0; c--){
            int bottom = N-1;
            while(bottom >= 0){
                if(map[bottom][c] != AIR) bottom--;
                else {
                    memo = bottom;
                    while(bottom >= 0 && map[bottom][c] == AIR) bottom--;
                    if(bottom == -1) break;
                    else if(map[bottom][c] == -1) {
                        bottom = bottom-1;
                    }
                    else {
                        map[memo][c] = map[bottom][c];
                        map[bottom][c] = AIR;
                        bottom = memo - 1;
                    }
                }
            }
        }

    }

    private static BlockGroup findGroup() {

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> rainbowQueue = new ArrayDeque<>();
        BlockGroup maxGroup = new BlockGroup(0, 0, 0);
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++){
                if(visited[r][c] || map[r][c] <= 0)  continue;
                BlockGroup tempGroup = new BlockGroup(map[r][c], r, c);
                tempGroup.blockList.add(new int[] {r, c});
                Queue<int[]> blockQueue = new ArrayDeque<>();
                blockQueue.offer(new int[] {r, c});
                visited[r][c] = true;

                while(!blockQueue.isEmpty()) {
                    int[] tempBlock = blockQueue.poll();
                    for(int[] d: deltas) {
                        int nr = tempBlock[0] + d[0];
                        int nc = tempBlock[1] + d[1];

                        if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == -1 || map[nr][nc] == AIR || (map[nr][nc] != 0 && map[nr][nc] !=tempGroup.color)) continue;
                        int[] addBlock = new int[] {nr, nc};
                        if(map[nr][nc] == 0) {
                            rainbowQueue.offer(addBlock);
                        }
                        else tempGroup.colorCnt++;
                        blockQueue.offer(addBlock);
                        visited[nr][nc] = true;
                        tempGroup.blockList.add(addBlock);
                    }
                }

                // 무지개 블록 visited 원상복귀
                while(!rainbowQueue.isEmpty()){
                    int[] tempBlock = rainbowQueue.poll();
                    visited[tempBlock[0]][tempBlock[1]] = false;
                }

                if(tempGroup.blockList.size() < maxGroup.blockList.size()) continue;
                else if(tempGroup.blockList.size() > maxGroup.blockList.size()) maxGroup = tempGroup;
                else if(tempGroup.blockList.size() - tempGroup.colorCnt < maxGroup.blockList.size() - maxGroup.colorCnt) continue;
                else if(tempGroup.blockList.size() - tempGroup.colorCnt > maxGroup.blockList.size() - maxGroup.colorCnt) maxGroup = tempGroup;
                else if(tempGroup.bossRow < maxGroup.bossRow) continue;
                else if(tempGroup.bossRow > maxGroup.bossRow) maxGroup = tempGroup;
                else if(tempGroup.bossCol > maxGroup.bossCol) maxGroup = tempGroup;

            }
        }

        return maxGroup;
    }

    private static boolean isOutOfRange(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= N;
    }


}
