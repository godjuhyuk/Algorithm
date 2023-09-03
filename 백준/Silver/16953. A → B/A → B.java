import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	private static int A, B, ans;
	private static HashMap<Integer, Boolean> hashmap;
	
	private static void backtracking(int a, int cnt) {
		
		if(a < A || hashmap.getOrDefault(a, false) == true) return;
		
		else if(a == A) {
			ans = Math.min(ans, cnt);
			return;
		}	
		if(a%10 == 1) {
			backtracking(a/10, cnt+1);
			hashmap.put(a/10, true);
		}
		
		if(a%2 == 0) {
			backtracking(a/2, cnt+1);
			hashmap.put(a/2, true);
		}
		

	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextInt();
		B = sc.nextInt();
		ans = Integer.MAX_VALUE;
		hashmap = new HashMap<Integer, Boolean>();
		backtracking(B, 0);
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans+1);
		
	}
	
	
}
