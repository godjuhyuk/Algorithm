import java.io.*;
	import java.util.*;
	
	public class Main {
	    
		private static int size = 0;
		private static int[] stack;
		private static StringBuilder sb;
		
		private static void check() {
			
			if(isEmpty()) {
				sb.append("-1").append('\n');
			} else {
				sb.append(stack[size-1]).append('\n');
			}
		}
		private static void getSize() {
			sb.append(size).append('\n');
			}
		private static boolean isEmpty() {
			if(size>0) {
				return false;
			} else {
				return true;
			}
		}
		private static void poll() {
			if(isEmpty()) {
				sb.append("-1").append('\n');
			} else {
				sb.append(stack[--size]).append('\n');
			}
		}
		private static void add(String cmd) {
			stack[size] = Integer.parseInt(cmd);
			size++;
			
		}
	
		private static void operate(String cmd) {
			if(cmd.equals("2")) {
				poll();
			}
			else if(cmd.equals("3")) {
				getSize();
			}
			else if(cmd.equals("4")) {
				if(isEmpty()) {
					sb.append("1").append('\n');
				} else {
					sb.append("0").append('\n');
				}
			}
			else if(cmd.equals("5")) {
				check();
			}
			else{
				add(cmd.split(" ")[1]);
			}
		}
		
		public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        sb = new StringBuilder();
	        int n = Integer.parseInt(br.readLine());
	        stack = new int[n];
	        String[] commandList = new String[n];
	        for(int i=0; i<n; i++) {
	        	commandList[i] = br.readLine();
	        }
	        
	        for(String cmd : commandList) {
	        	operate(cmd);
	        }
	        	
	        System.out.println(sb);
	 }
	}