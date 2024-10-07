import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * union find 복습
 */
public class Main {
    private static int[] parents;
    private static int find(int A) {
        if(A == parents[A]) return A;
        return parents[A] = find(parents[A]);
    }
    private static boolean union(int A, int B) {

        int rootA = find(A);
        int rootB = find(B);

        if(rootA == rootB) return true;

        parents[rootB] = rootA;
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];

        for(int i=0; i<=N; i++) parents[i] = i;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(cmd == 0) union(A, B);
            else if(find(A) == find(B)){
                sb.append("YES").append('\n');
            }
            else {
                sb.append("NO").append('\n');
            }
        }

        System.out.println(sb);

    }

}