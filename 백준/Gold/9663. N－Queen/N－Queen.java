import java.util.*;
import java.io.*;
public class Main {
	static int n, cnt;
	static int[][] map;
	
	public static void reloadMap(int r, int c) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == r+1) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static boolean isAttackable(double r, double c, double qr, double qc) {
		
		double inclination = (qr - r) / (qc - c);
		
		if(r==qr || c == qc || inclination == 1 || inclination == -1) {
			return true;
		}
		return false;
	}
	
	
	
	public static void attack(int r, int c) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 0 && isAttackable(r, c, i, j)) {
					map[i][j] = r+1;
				}
			}
		}
	}
	
	public static void nQueen(int depths) {
		if(depths == n) {
			cnt++;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(map[depths][i] == 0) {
				attack(depths, i);
				nQueen(depths+1);
				reloadMap(depths, i);
			}
		}

	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		nQueen(0);
		System.out.println(cnt);
 	}
}