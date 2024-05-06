import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX = 1001;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Pair>[] adj = new ArrayList[MAX];
    static int[] dist = new int[MAX];
    static int[] dp = new int[MAX];

    static class Pair implements Comparable<Pair> {
        int cost, vertex;
        public Pair(int cost, int vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }
        public int compareTo(Pair other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Pair(0, start));
        while (!pq.isEmpty()) {
            Pair top = pq.poll();
            int curCost = top.cost;
            int cur = top.vertex;
            if (dist[cur] != curCost) continue;
            for (Pair edge : adj[cur]) {
                int nextCost = edge.cost;
                int next = edge.vertex;
                if (dist[next] > curCost + nextCost) {
                    dist[next] = curCost + nextCost;
                    pq.add(new Pair(dist[next], next));
                }
                if (dist[cur] > dist[next]) {
                    dp[cur] += dp[next];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < MAX; i++) {
            adj[i] = new ArrayList<>();
        }
        dp[2] = 1;
        while (m-- > 0) {
        	st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Pair(c, b));
            adj[b].add(new Pair(c, a));
        }
        dijkstra(2);
        System.out.println(dp[1]);
    }
}
