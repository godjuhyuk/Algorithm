import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	private static int N, M;
	
	private static class Student implements Comparable<Student> {
		
		int num;
		int like;
		int turn;
		boolean isInFrame = false;
		
		public Student(int num, int like, int turn){
			this.num = num;
			this.like = like;
			this.turn = turn;
		}
		
		@Override
		public int compareTo(Student o){
			if(this.like == o.like){
				return o.turn - this.turn;
			}
			return o.like - this.like;
		}
		
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Student[] school = new Student[101];
		List<Student> frame = new ArrayList<Student>();
		
		for(int i=0; i<M; i++) {
			int recommendNum = Integer.parseInt(st.nextToken());
			if(school[recommendNum] == null) {
				school[recommendNum] = new Student(recommendNum, 1, i);
			} 
			
			if(school[recommendNum].isInFrame){
				school[recommendNum].like++;
			} 
			else {
				school[recommendNum].turn = i;
				if(frame.size() == N) {
					Collections.sort(frame);
					Student pollStd = frame.get(N-1);
					frame.remove(N-1);
					school[pollStd.num].like = 0;
					school[pollStd.num].isInFrame = false;
				}
				frame.add(school[recommendNum]);
				school[recommendNum].like = 1;
				school[recommendNum].isInFrame = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(Student std : school) {
			if(std != null && std.isInFrame){
				sb.append(std.num).append(' ');
			}
		}
        System.out.println(sb);
		
	}
}