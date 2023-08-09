import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**문제 링크)
 * https://www.acmicpc.net/problem/11286
 * 
 * 문제 설명)
 * 
 * 절댓값 힙은 다음과 같은 연산을 지원하는 자료구조이다.
 * 		
 * 배열에 정수 x (x ≠ 0)를 넣는다.
 * 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 
 * 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
 * 
 * 입력 제한)
 * 첫째 줄에 연산의 개수 N(1≤N≤100,000)이 주어진다. 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다. 
 * 만약 x가 0이 아니라면 배열에 x라는 값을 넣는(추가하는) 연산이고, x가 0이라면 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우이다. 
 * 입력되는 정수는 -231보다 크고, 231보다 작다.
 * 
 * 
 * absNum 클래스를 만들고,
 * comparable로 정렬 기준을 첫번째 절댓값 비교, 절댓값이 같다면 두번째로 기존 값을 비교하게 하면 될듯?
 * 
 * 시간복잡도는 연산당 log(배열의 크기) 이므로 Nlog(배열의 크기)
 * 
 * 1. comparable로 풀어보기
 * 2. comparator로 풀어보기
 * 
 * @author SSAFY
 *
 */
public class Main {
	
	private static PriorityQueue<Integer> priQueue;
	private static StringBuilder sb;
	
	private static void add(int cmd) {
		priQueue.add(cmd);
	}
	
	private static void poll() {
		if(priQueue.size() == 0) {
			sb.append(0).append('\n');
		} else {
			sb.append(priQueue.poll()).append('\n');
		}
	}
	
	private static void func(int cmd) {
		if(cmd == 0) {
			poll();
		}
		else {
			add(cmd);
		}

	}
	
	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		;priQueue = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				
				if(Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				}
				return Math.abs(o1) - Math.abs(o2);
			};
		});
		
		for(int i=0; i<N; i++) {
			int cmd = Integer.parseInt(br.readLine());
			func(cmd);
		}
		
		System.out.println(sb);
		
	}
	
}