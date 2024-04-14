import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 시작 시간: 오후 9시 52분
 * 종료 시간: 
 * 
 * 문제 설명)
 * N개의 수 중에서, 어떤 수를 다른 두 수의 합으로 나타낼 수 있으면 카운트한다.
 * 수의 위치가 다르면, 값이 같아도 다른 수이다.
 * 
 * 문제 풀이를 위한 고민)
 * 1. 수의 개수가 2000개이므로 완탐으로 한다면?
 * => 2000C2 로 가능한 경우의 수 전부 체크하고 (HashMap) keyset 순회하면서 더하면 되지 않을까?
 * 
 * 2. 자기 자신이 수식에 포함되었을 때의 결과가 자기 자신인 경우는 어떻게 check?
 * => 2 + 0 = 2 인 경우는 제외해야함 (2가 하나만 주어졌을 경우만 제외)
 * => 0 + 0 인 경우는 0의 개수가 총 2개일 경우는 제외해야함

 * 
 * @author GODJUHYEOK
 *
 */
public class Main {
	
	private static int N;
	private static int[] comb, nums;
	private static HashMap<Integer, Integer> countMap;
	private static HashMap<Integer, Boolean> map;
	public static void main(String[] args) throws IOException {
		
		// 입력값 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		map = new HashMap<Integer, Boolean>();
		countMap = new HashMap<Integer, Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			countMap.putIfAbsent(nums[i], 0);
			countMap.put(nums[i], countMap.get(nums[i]) + 1);
		}
		
		comb = new int[2];
		
		// 두 수를 뽑아서 HashMap에 저장하기
		getComb(0, 0);
		
		// 카운트 시작
		int ans = 0;
		for(int i=0; i < N; i++) {
			if(map.getOrDefault(nums[i], false)) {
				ans++;
			}
		}
		
		System.out.println(ans);
		
	}
	private static void getComb(int start, int depths) {
		
		// 기저 조건
		if(depths == 2) {
			
			if(comb[0] == 0 && comb[1] == 0 && countMap.get(0) >= 3 ) {
				map.putIfAbsent(comb[0] + comb[1], true);
			}
			else if(comb[0] == 0 && comb[1] != 0 && countMap.get(comb[1]) > 1) {
				map.putIfAbsent(comb[0] + comb[1], true);
			}
			else if(comb[1] == 0 && comb[0] != 0 && countMap.get(comb[0]) > 1) {
				map.putIfAbsent(comb[0] + comb[1], true);
			}
			else if(comb[0] != 0 && comb[1] != 0)map.putIfAbsent(comb[0] + comb[1], true);
			return;
		}
		
		// 유도 파트
		for(int i=start; i<N; i++) {
			comb[depths] = nums[i];
			getComb(i+1, depths+1);
		}
		
		
	}

}
