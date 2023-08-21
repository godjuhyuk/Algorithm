import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        if(N==K) {
            System.out.println(0);
            return;
        }

        boolean[] visited = new boolean[100001];

        Queue<Integer> queue = new ArrayDeque<Integer>();

        queue.add(N);

        int ans = 0;

        while(!queue.isEmpty()) {
            ans++;
            int qSize = queue.size();

            for(int i=0; i<qSize; i++) {
                int temp = queue.poll(); 
                if(temp-1 == K || temp+1 == K || temp*2 == K) {
                    System.out.println(ans);
                    return;
                }



                if(temp-1 >= 0 && !visited[temp-1]) {
                    queue.add(temp-1);
                    visited[temp-1] = true;
                }
                if(temp + 1 <= 100000 && !visited[temp+1]) {
                    queue.add(temp+1);
                    visited[temp+1] = true;
                }
                if(temp*2 <= 100000 && !visited[temp*2]) {
                    queue.add(temp*2);
                    visited[temp*2] = true;
                }
            }
        }
    }
}