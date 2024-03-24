import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Member[] members;
    static class Member implements Comparable<Member> {

        int num;
        int age;
        String name;

        public Member(int num, int age, String name) {
            this.num = num;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member om){
            if(this.age == om.age) {
                return Integer.compare(this.num, om.num);
            }
            return Integer.compare(this.age, om.age);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        members = new Member[N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members[i] = new Member(i, age, name);
        }

        Arrays.sort(members);
        StringBuilder sb = new StringBuilder();
        for(Member member: members) {
            sb.append(member.age).append(' ').append(member.name).append('\n');
        }
        System.out.println(sb);
    }

}