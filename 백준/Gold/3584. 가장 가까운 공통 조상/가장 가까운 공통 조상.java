import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 해석)
 * 루트가 있는 트리가 주어지고
 * 두 정점이 주어질 때, 가장 가까운 공통 조상은 다음과 같이 정의된다.
 * => 두 노드를 가지면서 깊이가 가장 깊은, 즉 두 노드에서 제일 가까운 노드
 *
 * 입력)
 * 첫줄 테케 T
 * 각 테케마다
 * 첫줄에 트리를 구성하는 노드 수 N ( 2 <= N <= 10000 )
 * 다음 N-1개의 줄에 트리를 구성하는 간선 정보 A B가 주어짐. A가 B의 부모
 *
 * 테케의 마지막 줄에 가장 가까운 공통 조상을 구할 노드가 주어짐.
 *
 * 문제 해결을 위한 고민)
 *
 * 어떻게 공통 조상을 찾지?
 * 공통 조상을 구할 노드 X_i와 X_j가 주어질 때
 * O(N(높이))으로 찾으면 되는 거긴한데
 * visited[N]으로 관리하면서
 * 하나 하나 올라가서 찾아보자
 * 노드 수가 10000개니깐 인접행렬보다는 인접리스트로
 *
 */
public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] parentInfo = new int[N+1];

            for(int i=0; i<N-1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parentInfo[B] = A;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[N+1];
            do {
                visited[nodeA] = true;
                nodeA = parentInfo[nodeA];
            } while(parentInfo[nodeA] != 0);

            while(parentInfo[nodeB] != 0 && !visited[nodeB]) {
                visited[nodeB] = true;
                nodeB = parentInfo[nodeB];
            }

            sb.append(nodeB).append('\n');

        }
        System.out.println(sb);

    }


}
