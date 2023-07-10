import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] A = new int[n];
        int minA=100, maxB=-1;
        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(input[i]);
            }
        }
        int[] B = new int[n];
        input = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            B[i] = Integer.parseInt(input[i]);
            if(B[i] > maxB){ 
                maxB = B[i];
                B[i] = -1;
            }
        }

        
        

        


    }
}