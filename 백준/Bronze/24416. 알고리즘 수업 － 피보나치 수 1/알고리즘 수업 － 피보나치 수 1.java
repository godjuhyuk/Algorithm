import java.util.Scanner;

/**
 * 피보나치 수 재귀호출 의사 코드는 다음과 같다.

fib(n) {
    if (n = 1 or n = 2)
    then return 1;  # 코드1
    else return (fib(n - 1) + fib(n - 2));
}
피보나치 수 동적 프로그래밍 의사 코드는 다음과 같다.

fibonacci(n) {
    f[1] <- f[2] <- 1;
    for i <- 3 to n
        f[i] <- f[i - 1] + f[i - 2];  # 코드2
    return f[n];
}
 * 
 */
public class Main {
	
	private static int cnt1, cnt2;
	
	private static int fibo2(int n) {
		int arr[] = new int[n+1];
		if(n>=1) arr[1] = 1;
		if(n>=2) arr[2] = 1;
		for(int i=3; i<=n; i++) {
			cnt2++;
			arr[i] = arr[i-2] + arr[i-1];
		}
		
		return arr[n];

	}
	
	private static int fibo1(int n) {
		if(n<=2) {
			cnt1++;
			return 1;
		} else {
			return fibo1(n-1) + fibo1(n-2);
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		fibo1(n);
		fibo2(n);
		System.out.println(cnt1 + " " + cnt2);
	}
	
}
