import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 인접행렬이 주어진다.
 * 
 * 인접 경로끼리 유니온 파인드로 전부 유니온하고
 * 실제 경로가 주어지면 다 같은 유니온인지 확인   
 */
public class Main {
	static int[] parents;
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;

	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
 	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		for(int i=1; i<=N; i++) parents[i] = i;
		int[][] adjMatrix = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
				if(adjMatrix[i][j] == 1) {
					union(i, j);
				}
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int route[] = new int[M];
		for(int i=0; i<M; i++) route[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M-1; i++) {
			for(int j=0; j<M; j++) {
				if(union(route[i], route[j])) {
					System.out.println("NO");
					return;
				}
			}
		}
		
		System.out.println("YES");
		
		
	}

}
