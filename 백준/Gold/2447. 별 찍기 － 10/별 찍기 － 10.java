import java.io.*;
import java.util.*;

public class Main {
	static int originN;
    static char[][] map;
    public static void getStar(int len, int r, int c) {
    	len /= 3;
        if(len == 0) {
            return;
        }
        for(int i = r+len; i<r+ 2*len; i++) {
            for(int j = c + len ; j< c+ 2*len; j++) {
                map[i][j] = ' ';
                }
            }
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                getStar(len, r + len*i, c + len*j);
            }
        }
}
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        originN = n / 3;
        map = new char[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(map[i], '*');
        }
        getStar(n, 0, 0);
        for(int i=0; i<n; i++) {
            System.out.println(new String(map[i]));
        }
    }
}
