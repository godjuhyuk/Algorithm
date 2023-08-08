import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int sum=0;
        boolean[][] grid = new boolean[101][101];
        for(int i=0; i<n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
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