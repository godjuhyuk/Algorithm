import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        System.out.println(A* (B%10));
        System.out.println(A* ((B/10)%10));
        System.out.println(A* (B/100));
        System.out.println(A*B);
    }
}