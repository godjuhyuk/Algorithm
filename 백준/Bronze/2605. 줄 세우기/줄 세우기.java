import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node {
		
		int num;
		int stuNo;
		Node tail = null;
		
		public Node(int num, int stuNo) {
			this.num = num;
			this.stuNo = stuNo;
		}
		
		public Node(int num, int stuNo, Node next) {
			this.num = num;
			this.stuNo = stuNo;
			this.tail = next;
		}
		
		private Node add(Node addedNode, int insertLoc) {
			
			if(insertLoc == 0) {
				addedNode.tail = this;
				return addedNode;
			} else {
				if(this.tail == null) {
					this.tail = addedNode;
				} else {
					Node tempHead = null;
					Node temp = this.tail;
					for(int i=0; i<insertLoc-1; i++) {
						tempHead = temp;
						temp = tempHead.tail;
					}
					
					addedNode.tail = temp;
					if(tempHead == null) {
						this.tail = addedNode;
					} else {
						tempHead.tail = addedNode;
					}
				}
				
				return this;
				
			}

		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Node head = new Node(Integer.parseInt(st.nextToken()), 1, null);
		for(int i=2; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int insertLoc = i - num -1;
			Node tempNode = new Node(num, i, null);
			head = head.add(tempNode, insertLoc);
		}
		while(head != null) {
			System.out.println(head.stuNo);
			head = head.tail;
		}
	}
}