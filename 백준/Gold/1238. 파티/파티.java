import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * N개의 숫자로 구분된 마을에 각각 한명씩 살고있음.
 *
 * 총 N명의 학생이 X(1<=X<=N)번 마을에 모여서 파티를 하기로 함.
 * 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 T_i(1<=T_i<=100)의 시간을 소비함.
 *
 * 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다.
 * 허나 이 학생들은 최단시간으로 움직인다.
 *
 * 가장 많이 시간을 소비하는 학생은 누구일까?
 *
 * 입력)
 * 첫째줄에 N, M, X가 공백으로 주어짐.
 * 두번째줄부터 M개 줄에 걸쳐서 i번째 도로의 시작점, 끝점, 가중치가 들어옴.
 *
 * 문제 해결을 위한 고민)
 * 1. 1000명의 학생에 대해 전부 다익스트라?
 * 1000 *
 *
 *
 *
 */
public class Main {

    private static class Node implements Comparable<Node>{

        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int INF = 999999999;
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Node>[] list = new List[V+1];
        for(int i=1; i<=V; i++) list[i] = new ArrayList<>();

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, weight));
        }

        int[][] dist = new int[V+1][V+1];
        for(int i=1; i<=V; i++) Arrays.fill(dist[i], INF);
        dist[X][X] = 0;


        boolean[] visited = new boolean[V+1];
        // X to i
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));
        Arrays.fill(visited, false);
        while(!pq.isEmpty()) {

            Node temp = pq.poll();
            if(visited[temp.to]) continue;
            visited[temp.to] = true;

            for(Node node : list[temp.to]) {
                if(dist[X][node.to] > dist[X][temp.to] + node.weight) {
                    dist[X][node.to] = dist[X][temp.to] + node.weight;
                    pq.offer(new Node(node.to, dist[X][node.to]));
                }
            }
        }


        int ans = 0;
        for(int i=1; i<=V; i++) {

            if(i==X) continue;

            visited = new boolean[V+1];
            // i to X
            pq.offer(new Node(i, 0));
            dist[i][i] = 0;
            while(!pq.isEmpty()) {

                Node temp = pq.poll();
                if(visited[temp.to]) continue;
                visited[temp.to] = true;

                for(Node node : list[temp.to]) {
                    if(dist[i][node.to] > dist[i][temp.to] + node.weight) {
                        dist[i][node.to] = dist[i][temp.to] + node.weight;
                        pq.offer(new Node(node.to, dist[i][node.to]));
                    }
                }
            }

            if(ans < dist[i][X] + dist[X][i]) {
                ans = dist[i][X] + dist[X][i];
            }
        }

        System.out.println(ans);
    }

}