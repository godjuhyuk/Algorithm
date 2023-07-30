import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		String bomb = br.readLine();
		Stack<Character> str = new Stack<Character>();
		
		for(int i=0; i < input.length(); i++) {
			str.add(input.charAt(i));
			boolean flag = true;
			if(str.size()>=bomb.length()) {
				for(int j=0; j<bomb.length(); j++) {
					if(str.get(str.size() - bomb.length() + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int k=0; k<bomb.length(); k++) {
						str.pop();
					}
				}
				
			}
		}
		for(int i=0; i<str.size(); i++) {
			sb.append(str.get(i));
		}
		if(str.size() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb.toString());
		}
		
 	}
}
