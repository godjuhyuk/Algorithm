import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
	
	/**
	 * 
	 * 재귀를 이요하여 하노이 탑을 풀어보자
	 * 
	 * flat한 시야:
	 * 		1. 재귀 한번당 target 기둥으로 옮기면서 cnt
	 * 		2. 기저조건: cnt가 원판 개수가 되었을때
	 * 		3. 유도파트: cnt + 1 && 현재, 임시, 타겟을 적절히 분배 
	 * 
	 */
	
	public class Main {
		static int N, ans;
		static StringBuilder sb;
		
//		private static void hanoi2(int targetNum, int now, int temp, int target) {
//			ans++;
//			if(targetNum == 1) {
//				return;
//			}
//			hanoi2(targetNum-1, now, target, temp);
//			hanoi2(targetNum-1, temp, now, target);
//	
//		}
		
		private static void hanoi(int targetNum, int now, int temp, int target) {
			ans++;
			if(targetNum == 1) {
				sb.append(now).append(' ').append(target).append('\n');
				return;
			}
			hanoi(targetNum-1, now, target, temp);
			sb.append(now).append(' ').append(target).append('\n');
			hanoi(targetNum-1, temp, now, target);
	
		}
		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			if(N<=20) {
				hanoi(N, 1, 2, 3);
				System.out.println(ans);
				System.out.println(sb);
			} else {
				BigDecimal base = new BigDecimal(2);
		        BigDecimal exponent = new BigDecimal(N);
		        BigDecimal result = base.pow(exponent.intValue());

		        System.out.println(result.add(new BigDecimal(-1)));
			}
		}
	}