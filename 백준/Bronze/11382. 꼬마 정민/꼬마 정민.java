import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ABC = br.readLine().split(" ");
        long A = Long.parseLong(ABC[0]);
        long B = Long.parseLong(ABC[1]);
        long C = Long.parseLong(ABC[2]);
        System.out.println(A+B+C);
    }
}