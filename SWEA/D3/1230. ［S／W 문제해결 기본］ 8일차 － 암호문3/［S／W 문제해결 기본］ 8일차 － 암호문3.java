import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
0 ~ 999999 사이의 수로 표현되는 암호문이 있고, 이 암호문을 N개 모아 놓은 암호문 뭉치가 있다.

암호문 뭉치를 급히 수정해야 할 일이 발생했는데, 암호문은 특수 제작된 처리기로만 수정이 가능하다.

처리기는 다음과 같이 3개의 명령어로 제어한다.
 
1. I(삽입) x, y, s : 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입한다. s는 덧붙일 암호문들이다.[ ex) I 3 2 123152 487651 ]

2. D(삭제) x, y : 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다.[ ex) D 4 4 ]

3. A(추가) y, s : 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 덧붙일 암호문들이다. [ ex) A 2 421257 796813 ]
 * 
 * @author GODJUHYEOK
 *
 */
public class Solution {
	
	private static class Node {
		
		Node next;
		int val;
		
		public Node(Node next, int val) {
			this.next = next;
			this.val = val;
		}
		
	}
	
	private static class LinkedList {
		
		Node head;
		Node tail;
		
		public LinkedList(Node head, Node tail) {
			super();
			this.head = head;
			this.tail = tail;
		}
		
		public Node getNode(int x) {
			Node temp = this.head;
			for(int i=0; i<x-1; i++) {
				temp = temp.next;
			}	
			return temp;
		}
		
		public void insert(int x, Node insertNode) {
			
			
			if(x == 0) {
				Node temp = this.head;
				this.head = insertNode;
				this.head.next = temp;
			} else {
				
				Node node = getNode(x);
				Node temp = node.next;
				
				node.next = insertNode;
				insertNode.next = temp;
			}
			
		}
		
		public void insert(int x, Node first, Node last) {
			if(x == 0) {
				Node temp = this.head;
				this.head = first;
				last.next = temp;
			} else {
				Node node = getNode(x);
				Node temp = node.next;
				
				node.next = first;
				last.next = temp;
			}
			
		}
		
		public void delete(int x, int y) {
			
			if(x==0) {
				Node node = getNode(y);
				this.head = node.next;
				
			} else {
				Node node = getNode(x);
				Node temp = node;
				for(int i=0; i<y; i++) {
					temp = temp.next;
					if(temp == this.tail) {
						this.tail = node;
					}
				}
				node.next = temp;
			}
		}
		
		public void add(Node addNode) {
			this.tail.next = addNode;
			this.tail = addNode;
		}
		
		public void add(Node first, Node last) {
			this.tail.next = first;
			this.tail = last;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			LinkedList list = new LinkedList(null, null);
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			list.head = new Node(null, Integer.parseInt(st.nextToken()));
			list.tail = list.head;
			for(int i=1; i<N; i++) {
				int pw = Integer.parseInt(st.nextToken());
				list.tail.next = new Node(null, pw);
				list.tail = list.tail.next;
			}
			
			int C = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int c=0; c<C; c++) {
				
				String cmd = st.nextToken();
				
				if(cmd.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					Node newNode = new Node(null, Integer.parseInt(st.nextToken()));
					if(y==1) list.insert(x, newNode);
					else {
						Node temp = newNode;
						for (int k=0; k<y-1; k++) {
							temp.next = new Node(null, Integer.parseInt(st.nextToken()));
							temp = temp.next;
						}
						list.insert(x, newNode, temp);
					}
				}
				else if(cmd.equals("A")) {
					int y = Integer.parseInt(st.nextToken());
					Node newNode = new Node(null, Integer.parseInt(st.nextToken()));
					if(y==1) list.add(newNode);
					else {
						Node temp = newNode;
						for (int k=0; k<y-1; k++) {
							temp.next = new Node(null, Integer.parseInt(st.nextToken()));
							temp = temp.next;
						}
						list.add(newNode, temp);
					}
				}
				else {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					list.delete(x, y);
				}
			}
			
			sb.append("#").append(t).append(' ');
			Node node = list.head;
			for(int i=0; i<10; i++) {
				sb.append(node.val).append(' ');
				node = node.next;
			}
			sb.append('\n');
			
		}
		
		System.out.println(sb);
		
		 
		
	}
	
}
