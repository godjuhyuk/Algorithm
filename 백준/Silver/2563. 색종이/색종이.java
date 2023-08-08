import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
        int n = Integer.parseInt(br.readLine());
        int x = 0;
        int y = 0;
        int sum = 0;
        boolean[][] grid = new boolean[101][101];
        for(int i=0; i<n; i++){
        	String[] input = br.readLine().split(" ");
        	
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            
            for(int a=x; a<x+10; a++){
                for(int b=y; b<y+10; b++){
                    if(!grid[a][b]){
                        sum++;
                        grid[a][b] = true;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}