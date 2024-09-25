import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 문제 해석)
 *
 * 1번부터 N번까지 N개의 풍선이 원형으로 놓여있따.
 * i번 풍선 오른쪽에는 i+1번 풍선이, 왼쪽에는 i-1번 풍선이 있다.
 *
 * 단, 1번의 왼쪽에는 N번 풍선이, N번의 오른쪽에는 1번 풍선이 존재한다.
 *
 * 각 풍선 안에는 종이가 하나 들어있고, 종이엔 -N ~ N의 정수가 하나 들어있다
 *
 * 다음과 같은 규칙으로 풍선을 터트린다
 *
 * 1. 처음엔 1번 풍선을 터트린다
 * 2. 다음엔 풍선 안에 있는 종이를 꺼내어, 양수면 오른쪽, 음수면 왼쪽으로 이동한다.
 * 3. 다 터질때까지 반복
 *
 *
 * 문제 해결을 위한 고민)
 * 그냥 구현 문제인듯?
 * 배열에 넣어놓고 터지면 슼;ㅣㅂ
 *
 *
 *
 */
public class Main {

    private static class Ballon {

        int num;
        boolean isBoom;

        public Ballon(int num) {
            this.num = num;
        }

    }
    public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            Ballon[] ballonCircle = new Ballon[N];


            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++) {
                ballonCircle[i] = new Ballon(Integer.parseInt(st.nextToken()));
            }

            int boomIdx = 0, boomCnt = 0;
            StringBuilder sb = new StringBuilder();
            while(boomCnt < N) {
            	
                ballonCircle[boomIdx].isBoom = true;
                boomCnt++;
                sb.append(boomIdx+1).append(' ');

                if(boomCnt == N) break;

                int moveCnt = 0;
                int moveDist = ballonCircle[boomIdx].num;
                int direct = moveDist > 0 ? 1 : -1;

                while(moveCnt < Math.abs(moveDist)) {
                	boomIdx = (N + boomIdx + direct) % N;
                	
                    while (ballonCircle[boomIdx].isBoom) {
                        boomIdx = (N + boomIdx + direct) % N;
                    }
                    moveCnt++;
                }

            }

        System.out.println(sb);


    }


}

