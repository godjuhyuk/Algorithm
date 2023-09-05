import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * 25만개 정수가 주어진다.
 * 이 25만개 정수를 차례대로 이진검색트리에 넣을 때
 * 모든 높이의 합은?
 * 
 * 시간복잡도)
 * 
 * log1 + log2 + log3 + ... + log25만 <= 25만 * log25만
 * 
 * 즉, 25만 * 18 = 400만 ? X
 * 높이로 시간복잡도를 따졌을떄 ( O(H) ),
 * 최악의 경우 러프하게 잡아도 O(10만)이 10만번 반복됨
 * 
 * => 최악의 경우를 방지하는 BST를 짜야함.
 *  
 * => 어차피 구해야할건 높이의 합.
 * => 끝다리에 붙는 애들을 추적하면?
 *  
 * => 그러다가 반대쪽ㅈ ㅏ식노드가 생기면 어떻게 count ?
 * 
 * => 끝 left, right tail 들을 관리한다면 O(1)로 삽입이 가능하다.
 * 
 * 이 케이스에서 최악의 경우
 * 
 *          2 (root)
 *      1        25만 
 *        249999
 *       249998
 *      249997
 *      .
 *     .
 *    .
 *   .   
 *        	
 * => 각 subtree 마다 left 끝 테일을 관리한다면 메모리 문제는 ?
 * => 괜찮을듯 ?
 * 
 * 설계)
 * => 모든 Node는 자신의 left, right 트리 + left끝, right끝 트리를 가지고 있다.
 * => 일단 값이 삽입되면 root노드는 자신의 값과 새 값을 비교
 * => 1) 새 값이 작다면? => 자신의 left와 비교하기 이전에 left 끝값과 비교하는 로직
 * => 2) 새 값이 크다면? => 자신의 right와 비교하기 전에 right 끝값과 비교하는 로직
 * 
 * 
 *	leaf node를 따로 관리해야하나?
 *
 *
 * 
 */
public class Main {
	
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long ans = 0;
		
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		heights = new int[N];
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(br.readLine());
			Integer ub = treeSet.higher(a);
            Integer lb = treeSet.lower(a);
			if(ub == null) {
				if(lb == null) {
					heights[a] = 1;
				} else {
					heights[a] = heights[lb] + 1;
				}
			} else {
				if(lb == null) {
					heights[a] = heights[ub] + 1;
				} else {
					heights[a] = Math.max(heights[lb], heights[ub])+ 1;
				}
			}
			ans += heights[a];
			treeSet.add(a);
			
		}
		
		System.out.println(ans);
		
	}
}