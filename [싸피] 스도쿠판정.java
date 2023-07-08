import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
        boolean testFlag = false;
		T=Integer.parseInt(sc.nextInt());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            // int[] row = new int[9];    
            // int[] col = new int[9];    
            // int[][] block = new int[9];
            int input;   
            int[][] grid = new int[9][9];
            for(int i=0; i<9; i++){
                boolean[] check = new boolen[9];
                for(int j=0; j<9; j++){
                    input = sc.nextInt() - 1;
                    if(check[input]) {
                        System.out.println(0);
                        testFlag = true;
                    }
                    grid[i][j] = input - 1;
                    check[input] = true;
                }
            }
            if(testFlag) break;
            for(int i=0; i<9; i++){
                boolean[] check = new boolen[9];
                for(int j=0; j<9; j++){
                    if(check[grid[j][i]]){
                        System.out.println(0);
                        testFlag = true;
                    }
                    check[grid[i][j]] = true;
                }
            }
            if(testFlag) break;
            for(int i=0; i<9; i++){
                boolean[] check = new boolen[9];
                int m = i/3, r=i%3;
                for(int j=3*m; j<3*m+3; j++){
                    for(int k=3*r; k<3*r+3; j++){
                        if(check[grid[j][k]]){
                            System.out.println(0);
                            testFlag = true;
                        }
                        check[grid[i][j]] = true;
                    }
                }
            }
            if(testFlag) break;
            System.out.println(1);
            


		}
	}
}