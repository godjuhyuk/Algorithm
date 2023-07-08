import java.io.*;
import java.util.*;

public class Main {
    public static int white = 0;
    public static int blue = 0;
    public static int[][] grid;
    public static boolean checkGrid(int[][] grid, int k){
        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++){
                if(grid[i][j] != grid[0][0]) return false;
            }
        }
        return true;
    }
    public static void partition(int row, int col, int size){

        if(checkGrid(grid, size)){
            if(grid[0][0] == 1) blue ++;
            else white ++;
            return;
        }
        else {
            size /= 2;
            partition(row, col, size);
            partition(row, col+size, size);
            partition(row+size, col, size);
            partition(row+size, col+size, size);
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                if(input[j] == "1") grid[i][j] = 1;
                else grid[i][j] = 0;
            }
        }
        partition(0, 0, N);
        System.out.println(blue);
        System.out.println(white);
    }

}
