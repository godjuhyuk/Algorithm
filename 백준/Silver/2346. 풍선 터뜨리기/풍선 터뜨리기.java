import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 이번엔 덱 풀이로 
 */
public class Main {

    private static class Balloon {

    	int idx;
        int num;

        public Balloon(int idx, int num) {
        	this.idx = idx;
            this.num = num;
        }

    }
    public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            Deque<Balloon> dq = new ArrayDeque<Balloon>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i=1; i<=N; i++) {
            	dq.offerLast(new Balloon(i, Integer.parseInt(st.nextToken())));
            }

            StringBuilder sb = new StringBuilder();
            
            while(dq.size() > 0) {
            	
            	Balloon bombBalloon = dq.pollFirst();
            	sb.append(bombBalloon.idx).append(' ');
            	
            	if(dq.size() == 0) break;
            	// -3 -1 2 1
            	// 
            	
            	int dist = Math.abs(bombBalloon.num) - 1;
            	if(bombBalloon.num < 0) {
            		
            		dq.offerFirst(dq.pollLast());
            		
            		for(int i=0; i<dist; i++) {
            			dq.offerFirst(dq.pollLast());
            		}
            	} else {
            		for(int i=0; i<dist; i++) {
            			dq.offerLast(dq.pollFirst());
            		}
            	}
            	
            }
            
            

            System.out.println(sb);


    }


}

