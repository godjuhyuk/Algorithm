import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    private static int ans;
    private static char[] seatInfo;
    private static int[] combList;
    private static Queue<Integer> checkQueue = new LinkedList();
    private static int[][] delta = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    
    private static int bfs( boolean[] visited) {
    	int checkCount = 0;
        while(!checkQueue.isEmpty()) {
        	int seatIdx = checkQueue.poll();
        	visited[seatIdx] = false;
        	checkCount++;
            int r = seatIdx / 5;
            int c = seatIdx % 5;
        	for(int[] d : delta) {
        		int nr = r + d[0];
        		int nc = c + d[1];
        		int checkIdx = nr*5 + nc;
        		// 이 부분만 전부 연결되어있는 형태임을 확인하면 solve
        		if( nr>=0 && nr<5 && nc>=0 && nc<5 && visited[checkIdx]) {
        			checkQueue.add(checkIdx);
        			visited[checkIdx] = false;
        		}
        	}
        }
        
        return checkCount;
    }
    
    private static void isLinked() {
    	
        int cnt = 0 ;
        boolean[] visited = new boolean[25];
        for(int i=0; i<combList.length; i++) {
            int seatIdx = combList[i];
            if(seatInfo[seatIdx] == 'S') {
                cnt++;
            }
            visited[seatIdx] = true;
        }
        // 다솜파 결성 조건 불만족
        if(cnt < 4) {
            return;
        }
        checkQueue.add(combList[0]);
        if(bfs(visited) == 7) {
        	ans++;
        };
    }
    private static void comb(int cnt, int start) {
        if(cnt == 7) {
            isLinked();
            return;
        }
        
        for(int i=start; i<25; i++) { //여기서 다솜파/비다솜파 체크한다음 바로 리턴해줘도됨
                combList[cnt] = i;
                comb(cnt+1, i+1);
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seatInfo = new char[25];
        combList = new int[7];
        for(int i=0; i<5; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<5; j++) {
                seatInfo[5*i + j] = input[j];
            }
        }
        
        
        comb(0, 0);
        System.out.println(ans);
    }    

}