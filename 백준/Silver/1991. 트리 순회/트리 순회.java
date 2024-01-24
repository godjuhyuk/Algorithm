import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static char tree[][];
	
	private static void preorderTraverse(int node) {
		
        // 뿌리 노드 출력
		System.out.print(tree[node][0]);
		
		// 왼쪽 자식 노드 방문
		if(tree[node][1]!='.') {
			for (int i=0; i<N; i++) {
				if (tree[node][1]==tree[i][0]) preorderTraverse(i);
			}
		}
		
		// 오른쪽 자식 노드 방문
		if(tree[node][2]!='.') {
			for (int i=0; i<N; i++) {
				if (tree[node][2]==tree[i][0]) preorderTraverse(i);
			}
		}
	}
	
	// 중위 순회
	private static void inorderTraverse(int node) {
		if(tree[node][1]!='.') {
			for (int i=0; i<N; i++) {
				if (tree[node][1]==tree[i][0]) inorderTraverse(i);
			}
		}
		System.out.print(tree[node][0]);
		
		if(tree[node][2]!='.') {
			for (int i=0; i<N; i++) {
				if (tree[node][2]==tree[i][0]) inorderTraverse(i);
			}
		}
	}
	
	// 후위 순회
	private static void postorderTraverse(int node) {
		if(tree[node][1]!='.') {
			for (int i=0; i<N; i++) {
				if (tree[node][1]==tree[i][0]) postorderTraverse(i);
			}
		}
		if(tree[node][2]!='.') {
			for (int i=0; i<N; i++) {
				if (tree[node][2]==tree[i][0]) postorderTraverse(i);
			}
		}
		System.out.print(tree[node][0]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		tree = new char [N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			tree[i][0] = st.nextToken().charAt(0);
			tree[i][1] = st.nextToken().charAt(0);
			tree[i][2] = st.nextToken().charAt(0);
		}
		
		preorderTraverse(0); 
		System.out.println();
		inorderTraverse(0); 
		System.out.println();
		postorderTraverse(0);
	}
}