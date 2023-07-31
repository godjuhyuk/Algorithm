import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static long max = Long.MIN_VALUE;

	static long[] number;
	static char[] operator;
	static boolean[] isBrackets;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		operator = new char[N / 2];
		number = new long[N / 2 + 1];
		isBrackets = new boolean[N / 2];

		for (int i = 0; i < input.length(); i++) {
			if (i % 2 == 1) {
				operator[i / 2] = input.charAt(i);
			} else {
				number[i / 2] = input.charAt(i) - '0';
			}
		}

		backTracking(0);
		System.out.println(max);
	}

	private static void backTracking(int depth) {
		if (depth == N / 2) {
			// logic
			char[] nowOperator = operator.clone();
			long[] nowNumber = number.clone();

			for (int i = 0; i < isBrackets.length; i++) {
				if (isBrackets[i]) {
					nowOperator[i] = '+';
					nowNumber[i + 1] = 0;
					nowNumber[i] = calc(number[i], operator[i], number[i + 1]);
				}
			}
			
			long ans = nowNumber[0];
			for (int i = 0; i < nowOperator.length; i++) {
				ans = calc(ans, nowOperator[i], nowNumber[i + 1]);
			}

			max = Math.max(ans, max);
			return;
		}

		if (depth != 0 && !isBrackets[depth - 1]) {
			isBrackets[depth] = true;
			backTracking(depth + 1);
		}

		isBrackets[depth] = false;
		backTracking(depth + 1);
	}

	private static long calc(long left, char oper, long right) {
		if (oper == '+') {
			return left + right;
		} else if (oper == '-') {
			return left - right;
		} else {
			return left * right;
		}
	}
}