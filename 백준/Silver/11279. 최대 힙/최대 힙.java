import java.util.*;
import java.io.*;

public class Main {
    
	private static PriorityQueue<Integer> priQueue;
	private static StringBuilder sb;
	
	private static void operate(int cmd) {
		
		if(cmd == 0) {
			Integer a = priQueue.poll();
			if(a == null) {
				sb.append(0).append('\n');
			} else {
				sb.append(a).append('\n');
			}
		} else {
			priQueue.add(cmd);
		}

	}
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] commandList = new int[n];
        for(int i=0; i<n; i++) {
        	commandList[i] = Integer.parseInt(br.readLine());
        }
        
        priQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for(int i=0; i<n; i++) {
        	int x = commandList[i];
        	operate(x);	
        }
        
        System.out.println(sb);
 }
}