import java.util.*;
import java.io.*;

class Main
{
	private static int N, K;
	
	public static class Student implements Comparable<Student> {
		int name;
		int ranking;
		
		public Student(int name, int ranking) {
			this.name = name;
			this.ranking = ranking;
		}
		
		@Override
		public int compareTo(Student o){
			if(this.name == o.name){
				return this.ranking - o.ranking;
			}
			return this.name - o.name;
		}
		
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Student[] arr = new Student[N];
		
		for(int i=0; i<N; i++){
			arr[i] = new Student(br.readLine().length(), i);
		}
		
		Arrays.sort(arr);
		
		long ans = 0;
		
		Queue<Student> q = new ArrayDeque<Student>();
		
		for(int i=0; i<N; i++) {
			
			if(q.isEmpty()) {
				q.offer(arr[i]);
			} 
			else if(q.peek().name == arr[i].name && Math.abs(q.peek().ranking - arr[i].ranking) <= K) {
				ans += q.size(); 
				q.offer(arr[i]);
			} else {
				q.poll();
				i--;
			}
			
		}
		
		System.out.println(ans);
		
	}
}