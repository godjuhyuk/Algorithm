import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 한번에 1000 씩 이동할 수 있다.
 * 즉 거리가 1000보다 멀면 못간다.
 * 
 * 그러면 인접행렬을 어떻게 구성해야할까?
 * 각 포인트간의 거리가 1000 이하면 0으로 해도되나?
 * 
 * 집에서 출발해서 락페 좌표로 갈 수 있는지를 구해야한다.
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	private static class Node {
		
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			int n = Integer.parseInt(br.readLine());
			Node[] adjArr = new Node[n+2];
			int[][] adjMatrix = new int[n+2][n+2];
			
			//adjMatrix[3][2] => 3에서 2로가는 최소비용
			for(int i=0; i<n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				adjArr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			for(int i=0; i<n+1; i++) {
				for(int j=i; j<n+2; j++) {
					Node from = adjArr[i];
					Node to = adjArr[j];
					
					adjMatrix[j][i] = adjMatrix[i][j] = getDistance(from, to);
					
				}
			}
			
			boolean gameOver = false;
			
			for(int k=0; k<n+2; k++) { // 경유지
				for(int i=0; i<n+2; i++) { // 출발지
					if(i==k) continue;
					for(int j=0; j<n+2; j++) { // 도착지
						if(j==i || j==k) continue;
						adjMatrix[i][j] = Math.min(adjMatrix[i][k] + adjMatrix[k][j], adjMatrix[i][j]);
					}
				}
				
				if(adjMatrix[0][n+1] == 0) {
					System.out.println("happy");
					gameOver = true;
					break;
				}
				//k=0인 경유지를 마쳤을 때
				//adjMatrix[3][2] => 3=>2비용  , 3=>0=>2
				
				//k=1인 경유지를 마쳤을 때
				//adjMatrix[3][2] => adjMatrix[3][2] , adjMatrix[3][1] + adjMatrix[1][2]
				
				
			}
//			System.out.println(adjMatrix[0][n+1]);
//			adjMatrix[0][n+1] : 0부터 n+1까지 최단거리 
			
			if(!gameOver) {
				System.out.println("sad");
			}
			
			
		}
		
	}

	private static int getDistance(Node from, Node to) {
		int returnVal = Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
		if(returnVal <= 1000) return 0;
		return returnVal;
	}
	
}