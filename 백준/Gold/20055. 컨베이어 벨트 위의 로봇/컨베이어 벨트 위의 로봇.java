import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 시작 시간: 오후 8시 23분
 * 문제 해석)
 *
 * 올리는 위치: 1번
 * 내리는 위치: N번
 *
 * 올리는 위치에만 로봇이 올라간다.
 * 로봇이 올라가거나, 이동할때마다 그 칸의 내구도는 1씩 감소한다.
 *
 * 컨베이어 벨트를 이용해 로봇들을 건너편으로 옮기려고 한다. 로봇을 옮기는 과정에서는 아래와 같은 일이 순서대로 일어난다.
 *
 * 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
 * 3. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
 * 4. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 * 5. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 *
 * 종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자. 가장 처음 수행되는 단계는 1번째 단계이다.
 *
 * 문제 풀이를 위한 고민)
 *
 * 1. 벨트 idx 추적 방법
 * => 올리는 위치에 해당하는 벨트 index를 추적한다.
 * => 내리는 위치는 (index + N) % 2N
 * => 로봇 움직이는 반복분은 (내리는 위치 - 1) 부터 올리는 위치로
 * => 반복문의 i가 경계문을 벗어날때 처리해줘야함 ( + 2N )
 *
 * 2. 각 칸의 내구도 추적은 길이가 2N인 배열로?
 * => 칸 class에 index, 로봇유무, 내구도 관리하면 쉬울듯
 *
 * 3. 벨트 자체 회전
 * => 매 차례마다 내리는 위치 - 1, 올리는 위치 - 1
 *
 * 로직 구현)
 *
 * 1. 벨트 회전 (내리는 위치-1, 올리는 위치-1)
 * 2. 내리는 위치에 로봇이 있다면 내린다.
 * 3. 로봇 이동시키기
 * 3-1) 로봇 이동 가능 check & 내구도 감소
 * 4. 올리는 위치에 로봇 올리기
 * 4-1) 내구도 check
 * 5. 각 칸 내구도 체크하며 카운트 ( K개 이상이면 종료)
 *
 *
 */
public class Main {

    private static class Block {

        int index;
        int HP;
        boolean isRobotHere;

        Block(int index, int HP, boolean isRobotHere) {
            this.index = index;
            this.HP = HP;
            this.isRobotHere = isRobotHere;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "index=" + index +
                    ", HP=" + HP +
                    ", isRobotHere=" + isRobotHere +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Block[] belt = new Block[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) belt[i] = new Block(i, Integer.parseInt(st.nextToken()), false);

        int loadLoc = 0;
        int unloadLoc = N-1;

        int phase = 0;
        while(true){
            phase++;

            /**
             *  1. 벨트 회전 (내리는 위치-1, 올리는 위치-1)
             *  2. 내리는 위치에 로봇이 있다면 내린다.
             *  3. 로봇 이동시키기
             *  3-1) 로봇 이동 가능 check & 내구도 감소
             *  4. 올리는 위치에 로봇 올리기
             *  * 4-1) 내구도 check
             *  * 5. 각 칸 내구도 체크하며 카운트 ( K개 이상이면 종료)
             *
             */

            // 1. 벨트 회전 (내리는 위치-1, 올리는 위치-1)
            loadLoc = (loadLoc - 1 + 2*N) % (2*N);
            unloadLoc = (unloadLoc - 1 + 2*N) % (2*N);

            // 2. 내리는 위치에 로봇이 있다면 내린다.
            if(belt[unloadLoc].isRobotHere) belt[unloadLoc].isRobotHere = false;

            // 3. 로봇 이동시키기
            //  => 로봇 움직이는 반복분은 (내리는 위치 - 1) 부터 올리는 위치로
            //  => 반복문의 i가 경계문을 벗어날때 처리해줘야함 ( + 2N )
            for(int i = unloadLoc - 1; i >= unloadLoc - N; i--) {
                int tempIdx = i;
                if(tempIdx < 0) tempIdx += 2*N;
                if(belt[tempIdx].isRobotHere && !belt[(tempIdx+1) % (2*N)].isRobotHere && belt[(tempIdx+1) % (2*N)].HP > 0) {
                    belt[tempIdx].isRobotHere = false;
                    belt[(tempIdx+1) % (2*N)].isRobotHere = true;
                    belt[(tempIdx+1) % (2*N)].HP--;
                    if((tempIdx+1) % (2*N) == unloadLoc) belt[(tempIdx+1) % (2*N)].isRobotHere = false;
                }
            }

            // 4. 올리는 위치에 로봇 올리기
            if(belt[loadLoc].HP > 0) {
                belt[loadLoc].isRobotHere = true;
                belt[loadLoc].HP--;
            }
            // 5. 각 칸 내구도 체크하며 카운트 ( K개 이상이면 종료)
            int exitCount = 0;
            for(int i = unloadLoc - 1; i >= unloadLoc - 2*N; i--) {
                int tempIdx = i;
                if(tempIdx < 0) tempIdx += 2*N;
                if(belt[tempIdx].HP == 0) {
                    exitCount++;
                }
            }

            if(exitCount >= K) {
                System.out.println(phase);
                return;
            }


        }



    }

}
