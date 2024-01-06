import java.io.*;
import java.util.*;

public class Main {

	private static int N, P;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] sarr = br.readLine().split(" ");
		N = Integer.parseInt(sarr[0]);
		P = Integer.parseInt(sarr[1]);

		Stack<Integer>[] stack = new Stack[7];

		for (int i = 0; i <= 6; i++)
			stack[i] = new Stack<>();

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			sarr = br.readLine().split(" ");
			int n = Integer.parseInt(sarr[0]);
			int m = Integer.parseInt(sarr[1]);

			while (!stack[n].isEmpty() && stack[n].peek() > m) {
				stack[n].pop();
				cnt++;
			}

			if (stack[n].empty() || (!stack[n].isEmpty() && stack[n].peek() < m)) {
				stack[n].push(m);
				cnt++;
			}
		}

		bw.write(cnt + "\n");
		bw.flush();

	}

}