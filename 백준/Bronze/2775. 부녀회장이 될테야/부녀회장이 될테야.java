import java.io.*;

public class Main {
	static int[][] apartment;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		apartment = new int[15][15];
		for(int j=1; j<15; j++) {
				apartment[0][j] = j;
		}
	
		for(int i=1; i<15; i++) {
			for(int j=1; j<15; j++) {
				if(apartment[i][j] == 0) {
					apartment[i][j] = apartment[i-1][j];
				}
				apartment[i][j] += apartment[i][j-1];
			}
		}
		for(int i=0; i<n; i++) {
			int a = Integer.parseInt(br.readLine());
			int b = Integer.parseInt(br.readLine());
			
			System.out.println(apartment[a][b]);
			
		}
		
	}
}