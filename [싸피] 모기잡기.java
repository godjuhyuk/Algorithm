import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=Integer.parseInt(sc.nextLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N, M, max = 0, sum1 = 0, sum2 = 0;
            N= Integer.parseInt(sc.nextInt());
            M= Integer.parseInt(sc.nextInt());
            int[][] grid = new int[N][N];
            for(int i=0; i< N; i++){
                for(int j=0; j<N; j++){
                    grid[i][j] = sc.nextInt();
                }
            }
            for(int i=0; i< N; i++){
                for(int j=0; j<N; j++){
                    sum1 = grid[i][j];
                    sum2 = grid[i][j];
                    for(int p=1; p<M; p++){
                        if(i + p < N ) sum1 += grid[i+p][j];
                        if(j + p < N ) sum1 += grid[i][j+p];
                        if(i - p >= 0 ) sum1 += grid[i-p][j];
                        if(j - p >= 0 ) sum1 += grid[i][j-p];
                    }

                    for(int p=1; p<M; p++){
                        if(i + p < N && j + p < N ) sum2 += grid[i+p][j+p];
                        if(i + p < N && j - p >= 0 ) sum2 += grid[i+p][j-p];
                        if(i - p >= 0 && j + p < N ) sum2 += grid[i-p][j+p];
                        if(i - p >= 0 && j - p >= 0 ) sum2 += grid[i-p][j-p];
                    }
                    // System.out.printf("(%d, %d) : %d\n", i, j, sum2); 
                    if(sum1 > max) max = sum1;
                    if(sum2 > max) max = sum2;
                }
            }
            System.out.println(max);
            


		}
	}
}