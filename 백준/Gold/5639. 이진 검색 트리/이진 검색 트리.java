import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static class Node {
		int val;
		Node left;
		Node right;
		public Node(int val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	static class BinarySearchTree{

		int size;
		Node root;
		
		public boolean add(int value) {
			if(size==0) {
				root = new Node(value, null, null);
				size++;
				return true;
			} 
			
			return add(root, value);
		}
		
		private boolean add(Node node, int value) {
			
			// 왼쪽 삽입
			if(node.val > value) {
				if(node.left == null) {
					node.left = new Node(value, null, null);
				} else {
					return add(node.left, value);
				}
			}
			else {
				if(node.right == null) {
					node.right = new Node(value, null, null);
				} else {
					return add(node.right, value);
				}
			}
			
			size++;
			return true;
		}

		
		public void preorder(Node node) {
			
			if(node != null) {
				
				if(node.left != null) preorder(node.left);
				if(node.right != null) preorder(node.right);
				
				System.out.println(node.val);
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BinarySearchTree bst = new BinarySearchTree();
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			bst.add(sc.nextInt());
		}
		bst.preorder(bst.root);
		
	}
}