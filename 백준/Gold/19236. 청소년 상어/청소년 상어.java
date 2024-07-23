
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 문제 해석)
 * - 4*4 공간
 * - 한 칸에는 물고기가 한마리 존재
 * - 물고기는 번호와 방향을 가지고 있음 (1 <= 번호 <= 16)
 * - 방향은 8방임
 * - 1. 상어는 (0, 0)에 있는 물고기를 먹고 (0, 0)에 들어가게 됨.
 *     -상어는 먹은 물고기의 방향을 가진다.
 * - 2. 이후 물고기가 이동함.
 *      - 번호가 작은 물고기부터 순서대로 이동
 *      - 물고기는 한 칸 이동
 *      - 이동할 수 있는 칸은 빈칸, 다른물고기가 있는 칸
 *      - 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
 *      - 각 물고기는 방향이 이동할 수 있는 칸을 향할 때 까지 45도 반시계 회전함
 *      - 만약 이동할 수 있는 칸이 없으면 이동 X
 *      - 물고기가 다른 물고기가 있는 칸으로 이동하는 경우, 서로의 위치를 바꾸는 방식임
*  - 3. 물고기 이동이 끝나면 상어가 이동함
 *      - 상어는 한 번에 여러 개의 칸을 이동
 *      - 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고 방향을 가지게 됨.
 *      - 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다.
 *      - 물고기가 없는 칸으로는 이동할 수 없다.
 *      - 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
 *      - 이동 후에는 2번으로 간다.
 *
 * 문제 풀이를 위한 고민)
 * - 백트래킹? DFS!
 * - 모든 경우의 수를 다 봐야할 것 같다
 * - 매 턴마다 최대 3개의 경우의 수가 생긴다!
 * - 16개의 Array에 물고기 객체를 담아놓자
 * -
 *
 *
 */
public class Main {

    private static int max = 0;
    private static int[][] deltas = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
//    private static Fish[] fishes;

    private static class Fish {

        int id;
        int delta;
        int r;
        int c;
        boolean alive;

        public Fish(int id, int r, int c, int delta, boolean alive) {
            this.id = id;
            this.delta = delta;
            this.r = r;
            this.c = c;
            this.alive = alive;
        }

        public void move(int[][] map, Fish[] fishes) {

            int tempDelta = this.delta;

            while(!isAbleToMove(delta, map)) {;
                delta = (delta + 1) % deltas.length;
                if(tempDelta == this.delta + 1) break;
            }

            if(isAbleToMove(delta, map)) {

                int nr = r + deltas[delta][0];
                int nc = c + deltas[delta][1];
                
                if(map[nr][nc] == 0 || !fishes[map[nr][nc]-1].alive) {

                    map[this.r][this.c] = 0;
                    this.r = nr;
                    this.c = nc;

                } else {

                    Fish anotherFish = fishes[map[nr][nc]-1];
                    map[this.r][this.c] = anotherFish.id;

                    int tempRow = anotherFish.r;
                    int tempCol = anotherFish.c;

                    anotherFish.r = this.r;
                    anotherFish.c = this.c;

                    this.r = tempRow;
                    this.c = tempCol;

                }

                map[nr][nc] = this.id;

            }

        }

        private boolean isAbleToMove(int tempDelta, int[][] map) {

            int nr = r + deltas[tempDelta][0];
            int nc = c + deltas[tempDelta][1];

            if(isOutOfRange(nr, nc) || map[nr][nc] == -1) return false;

            return true;
        }

    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[16];

        for(int r=0; r<4; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c=0; c<4; c++) {

                int id = Integer.parseInt(st.nextToken());
                int delta = Integer.parseInt(st.nextToken()) - 1;
                fishes[id - 1] = new Fish(id, r, c, delta, true);
                map[r][c] = id;
            }
        }

        int sharkWeight = map[0][0];
        int sharkDelta = fishes[map[0][0]-1].delta;
        fishes[map[0][0]-1].alive = false;
        map[0][0] = -1;
        maxSearchDFS(0, 0, sharkWeight, sharkDelta, map, fishes);
        System.out.println(max);
    }

    private static void maxSearchDFS(int r, int c, int sharkWeight, int sharkDelta, int[][] map, Fish[] fishes) {

        // 물고기 이동
        for(Fish fish : fishes) {
            if(fish.alive) fish.move(map, fishes);
        }

//        print(map);

        // 상어 이동
        int deltaRow = deltas[sharkDelta][0];
        int deltaCol = deltas[sharkDelta][1];

        boolean gameOver = true;

        int sharkInitialRow = r;
        int sharkInitialCol = c;

        while(!isOutOfRange(r + deltaRow, c + deltaCol)) {

            r += deltaRow;
            c += deltaCol;

            if(map[r][c] == 0 || !fishes[map[r][c]-1].alive) continue;
            else {
                gameOver = false;
                Fish deadFish = fishes[map[r][c]-1];

                map[sharkInitialRow][sharkInitialCol] = 0;
                map[deadFish.r][deadFish.c] = -1;
                deadFish.alive = false;
                maxSearchDFS(deadFish.r, deadFish.c, sharkWeight + deadFish.id, deadFish.delta, getNewMap(map), getNewFishes(fishes));

                deadFish.alive = true;
                map[deadFish.r][deadFish.c] = deadFish.id;
                map[sharkInitialRow][sharkInitialCol] = -1;
            }
        }

        if(gameOver) {
            max = Math.max(max, sharkWeight);
        }

    }

    private static int[][] getNewMap(int[][] map) {
        int[][] newMap = new int[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++) newMap[i][j] = map[i][j];
        }
        return newMap;
    }
    
    private static Fish[] getNewFishes(Fish[] fishes) {
        Fish[] newFishes = new Fish[16];
        
        for(int i=0; i<16; i++) {
        	Fish tempFish = fishes[i];
        	newFishes[i] = new Fish(tempFish.id, tempFish.r, tempFish.c, tempFish.delta, tempFish.alive);
        }
        
        return newFishes;
    }

    private static boolean isOutOfRange(int r, int c) {
        return  r < 0 || c < 0 || r >= 4 || c >= 4;
    }

    private static void print(int[][] map){
        for(int i=0; i<4; i++) System.out.println(Arrays.toString(map[i]));
        System.out.println("==================");
    }
}
