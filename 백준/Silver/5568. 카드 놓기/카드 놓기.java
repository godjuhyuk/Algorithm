import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * permutation 구현하고 
 * hashmap으로 관리하기
 * 
 * 
 * @author GODJUHYEOK
 *
 */
public class Main {

	private static int N, K;
	private static String permList[], arr[];
	private static HashSet<String> hashSet;
	private static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new String[N];
		isSelected = new boolean[N];
		permList = new String[K];
		
		for(int i=0; i<N; i++) arr[i] = br.readLine();
		
		hashSet = new HashSet<String>();
		permutation(0);
		System.out.println(hashSet.size());
	}

	private static void permutation(int depths) {
		
		// 기저 조건
		if(depths == K) {
			String temp = "";
			for(String card: permList) temp += card;
			hashSet.add(temp);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				permList[depths] = arr[i];
				permutation(depths+1);
				isSelected[i] = false;
			}
		}
		
		
	}
	
}
