import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    private static int n, k;
    private static String[] num;
    private static HashSet<String> set;
    private static boolean[] isSelected;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        set = new HashSet<>();
        num = new String[n];

        for(int i=0; i<n; i++) num[i] = br.readLine();


        isSelected = new boolean[n];
        perm("", 0);

        System.out.println(set.size());
    }

    private static void perm(String now, int depths) {

        // 기저 조건
        if(depths == k) {
            set.add(now);
            return;
        }

        // 유도 파트
        for(int i=0; i<n; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                perm(now + num[i], depths+1);
                isSelected[i] = false;
            }
        }

    }

}
