import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    
    private static final int LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3 ;
    private static int N, M, sharkRow, sharkCol;
    private static int[] bombHistory;
    private static int[][] deltas = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static boolean[][] visited;
    private static int[][][] map;
    
    static class Shark {
        
        int r;
        int c;
        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        sharkRow = N >> 1;
        sharkCol = N >> 1;
        
        bombHistory = new int[4];
        map = new int[N][N][2];
        visited = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 격자판의 방향 마킹하기
        int r = sharkRow;
        int c = sharkCol;
        int speed = 1;
        int d = LEFT;
        loop : while(true) {
            
            for(int i=0; i<speed; i++) {
                r += deltas[d][0];
                c += deltas[d][1];
                // 반대방향으로 입력해야 구슬들이 들어온다
                map[r][c][1] = (d + 2)%4;
                if(r==0 && c==0) break loop;
            }
            // 방향전환 체크
            visited[r][c] = true;
            d = (d+1)%4;
            
            for(int i=0; i<speed; i++) {
                r += deltas[d][0];
                c += deltas[d][1];
                // 반대방향으로 입력해야 구슬들이 들어온다
                map[r][c][1] = (d + 2)%4;
                if(r==0 && c==0) break loop;
            }
            
            visited[r][c] = true;
            d = (d+1)%4;
            
            speed++;
            
        }
        
        // 명령어 입력
        int[][] cmdArr = new int[M][2];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int inputDir = Integer.parseInt(st.nextToken());
            getDeltas(inputDir, cmdArr, i);
            cmdArr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 명령어에 따라 구슬 던진 후 구슬 당기기 & 폭발 & 그룹화
        for(int[] cmd : cmdArr) {
            
            d = cmd[0];
            speed = cmd[1];
            
            // 구슬 던진 후 폭발시키기
            blizard(d, speed);
            
            // 구슬 당기기 & 폭발 - while 문
            Shark shark = new Shark(sharkRow, sharkCol + deltas[LEFT][1]);
            
            // 잔여 구슬 넣을 Queue
            Deque<Integer> q = new ArrayDeque<Integer>();
            int cnt = 1;
            while(true) {
                int input = map[shark.r][shark.c][0];
                if(input> 0) {
                	if(!q.isEmpty() && input == q.peekLast()) cnt++;
                	else if(!q.isEmpty() && input != q.peekLast() && cnt < 4){
                		cnt = 1;
                	} 
                	else if(!q.isEmpty() && input != q.peekLast() && cnt >= 4){
                		for(int i=0; i<cnt; i++) {
                			int temp = q.pollLast();
                			bombHistory[temp]++;
                		}
                		cnt = 1;
                	}
                	q.addLast(input);
                }
                map[shark.r][shark.c][0] = 0;
                if(move(shark)) break;
            }
            
            boolean returnFlag = true;
            while(returnFlag) {
            	returnFlag = false;
        		Deque<Integer> newDeque = new ArrayDeque<Integer>();
        		cnt = 1;
        		int memory = -1;
        		while(!q.isEmpty()) {
        			
        			int input = q.pollFirst();
        			
        			if(q.isEmpty()) {
        				
        				if(memory == -1) newDeque.addLast(input);
        				else {
        					if(input == memory && cnt >=4) {
        						returnFlag = true;
                        		for(int i=0; i<cnt; i++) {
                        			bombHistory[input]++;
                        		}
        					}
        					else if(input == memory && cnt < 4){
                        		for(int i=0; i<cnt; i++) {
                        			newDeque.addLast(input);
                        		}
        					}
        					else newDeque.addLast(input);
        				}
        			}
        			
        			
                	if(!q.isEmpty() && input == q.peekFirst()) cnt++;
                	else if(!q.isEmpty() && input != q.peekFirst() && cnt < 4){
                		
                		for(int i=0; i < cnt; i++) {
                			newDeque.addLast(input);
                		}
                		cnt = 1;
                	} 
                	else if(!q.isEmpty() && input != q.peekFirst() && cnt >= 4){
                		returnFlag = true;
                		for(int i=0; i<cnt; i++) {
                			bombHistory[input]++;
                		}
                		cnt = 1;
                	}
                	
                	memory = input;
                	
                	
        		}
        		
        		q = newDeque;
            };
            
            // 초기 위치에서부터 그룹핑 시작
            shark = new Shark(sharkRow, sharkCol + deltas[LEFT][1]);
            while(!q.isEmpty()) {
            	int groupCnt = 1;
            	int groupNum = q.pollFirst();
            	while(q.size() > 0 &&  groupNum == q.peekFirst()) {
            		q.pollFirst();
            		groupCnt++;
            	}
            	map[shark.r][shark.c][0] = groupCnt;
                if(move(shark)) break;
                map[shark.r][shark.c][0] = groupNum;
                if(move(shark)) break;
                
            }
        }
        
        System.out.println(bombHistory[1] + bombHistory[2] * 2 + bombHistory[3] * 3);
        
    }
    

	private static void blizard(int d, int move) {
        
        // 상어 위치에서 던지기 때문에
        int r = sharkRow;
        int c = sharkCol;
        
        for(int i=1; i <= move; i++) {
            int nr = r + deltas[d][0] * i;
            int nc = c + deltas[d][1] * i;
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            map[nr][nc][0] = 0;
        }
        
    }

    public static void getDeltas(int inputDir, int[][] cmdArr, int idx) {
        switch (inputDir) {
        case 1:
            cmdArr[idx][0] = UP;
            break;
        case 2:
            cmdArr[idx][0] = DOWN;
            break;
        case 3:
            cmdArr[idx][0] = LEFT;
            break;
        case 4:
        	
            cmdArr[idx][0] = RIGHT;
            break;
        }
    }
    
    private static boolean move(Shark shark) {
    	int outDeltaIdx;
    	
    	if(visited[shark.r][shark.c]) outDeltaIdx = (map[shark.r][shark.c][1] + 3)%4;
        else outDeltaIdx = (map[shark.r][shark.c][1] + 2)%4;
    	shark.r += deltas[outDeltaIdx][0];
    	shark.c += deltas[outDeltaIdx][1];
    	
    	return shark.r == 0 && shark.c == -1;
    	
	}
    
}