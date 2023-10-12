import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 설계시작 : 오후 3시 44분
 * 
 * 
 * 문제 해석)
 * 
 * 달팽이 모양의 N*N 격자판이 있다.
 * 
 * 상어는 얼음파편을 M번 던지며, 던질때마다 발생하는 단계는 다음과 같다.
 * 
 * 1. 해당 방향으로 거리가 s 이하인 칸에 있는 구슬을 모두 파괴한다.
 * 2. 빈칸이 생긴 곳 부터 공간을 메우기 위해 구슬들이 안쪽으로 들어온다.
 * 3. 다시 메꾸고 나면, 연속된 값을 가지는 구슬이 4개 이상일 경우 전부 폭발한다.
 * 4. 폭발이 발생하지 않을 때 까지 3번을 반복한다.
 * 5. 구슬 변화가 발생한다. 연속하는 구슬들을 모두 그룹으로 묶는다. (1개도 마찬가지)
 * 6. 해당 그룹은 안쪽(번호가 낮은 칸)이 구슬의 개수, 뒤가 구슬의 번호로 바뀐다. (2칸으로 바뀐다.)
 * 
 * 이 때, 1 * 폭발한 1번구슬개수 + 2 * 폭발한 2번 + 3 * 폭발한 3번을 구하는 문제.
 * 
 * 문제 해결을 위한 고민)
 * 1. 달팽이 모양으로 움직이려면 어떻게 해야할까?
 * - 맵을 3차원으로 가져가자.
 *     => 0번 인덱스에는 현재 구슬의 값을, 1번 인덱스에는 달팽이 모양을 위해 움직여야하는 DELTA의 값을 넣는다.
 * 
 * 2. 구슬 변화 시에는 어떻게 해야하지?
 * => 일단 한번 전체순회하며 그룹 정보와 구슬번호를 모아놓고 한번에 갈아치우기 
 *
 */
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
            super();
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
        int move = 1;
        int d = LEFT;
        loop : while(true) {
            
            for(int i=0; i<move; i++) {
                r += deltas[d][0];
                c += deltas[d][1];
                // 반대방향으로 입력해야 구슬들이 들어온다
                map[r][c][1] = (d + 2)%4;
                if(r==0 && c==0) break loop;
            }
            // 방향전환 체크
            visited[r][c] = true;
            d = (d+1)%4;
            
            for(int i=0; i<move; i++) {
                r += deltas[d][0];
                c += deltas[d][1];
                // 반대방향으로 입력해야 구슬들이 들어온다
                map[r][c][1] = (d + 2)%4;
                if(r==0 && c==0) break loop;
            }
            
            visited[r][c] = true;
            d = (d+1)%4;
            
            move++;
            
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
            move = cmd[1];
            
            // 구슬 던진 후 폭발시키기
            blizard(d, move);
            
            // 구슬 던진거 체크
//            print();
            // 구슬 당기기 & 폭발 - while 문
            Shark shark = new Shark(sharkRow, sharkCol + deltas[LEFT][1]);
            int outDeltaIdx;
            
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
                
                if(visited[shark.r][shark.c]) outDeltaIdx = (map[shark.r][shark.c][1] + 3)%4;
                else outDeltaIdx = (map[shark.r][shark.c][1] + 2)%4;
                shark.r += deltas[outDeltaIdx][0];
                shark.c += deltas[outDeltaIdx][1];
                
                if(shark.r == 0 && shark.c == -1) break;
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
//        		System.out.println(q);
//        		System.out.println(newDeque);
//        		System.out.println(Arrays.toString(bombHistory)); 
//        		System.out.println("=====");
        		q = newDeque;
            };
            
//            System.out.println("after queing");
//            print();
//            print();
//            System.out.println("??????????");
            shark = new Shark(sharkRow, sharkCol + deltas[LEFT][1]);
            while(!q.isEmpty()) {
            	int groupCnt = 1;
            	int groupNum = q.pollFirst();
            	while(q.size() > 0 &&  groupNum == q.peekFirst()) {
            		q.pollFirst();
            		groupCnt++;
            	}
            	map[shark.r][shark.c][0] = groupCnt;
            	
        		if(visited[shark.r][shark.c]) outDeltaIdx = (map[shark.r][shark.c][1] + 3)%4;
                else outDeltaIdx = (map[shark.r][shark.c][1] + 2)%4;
                shark.r += deltas[outDeltaIdx][0];
                shark.c += deltas[outDeltaIdx][1];
                
                if(shark.r == 0 && shark.c == -1) break;
                map[shark.r][shark.c][0] = groupNum;
                
                if(visited[shark.r][shark.c]) outDeltaIdx = (map[shark.r][shark.c][1] + 3)%4;
                else outDeltaIdx = (map[shark.r][shark.c][1] + 2)%4;
                shark.r += deltas[outDeltaIdx][0];
                shark.c += deltas[outDeltaIdx][1];
                if(shark.r == 0 && shark.c == -1) break;
                
                
            }
//            System.out.println("after EMPTY : " + shark.r + " " + shark.c );
//            print();                
//            print();
            
        }
        
//        System.out.println(Arrays.toString(bombHistory));
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
//            bombHistory[map[nr][nc][0]]++;
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
    
    private static void print() {
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            System.out.print(map[i][j][0] + " ");
//            System.out.print(visited[i][j] + " ");
        }
        System.out.println();
    }
    System.out.println("=============");

    }
    
}