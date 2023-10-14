import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    private static int max, M, sharkRow, sharkCol;
    private static int[] moveHistory, maxHistory;
    private static final int UP = 1, LEFT = 2, DOWN = 3, RIGHT = 4;
    private static int[][] smellMap, fishDeltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    private static int[][] sharkDeltas = {null, {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static boolean[][] visited;
    private static Queue<Fish>[][] map, newMap;
    
    static class Fish {
        
        int r;
        int c;
        int d; // deltaIdx;
        boolean isMoved = false;
        
        public Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken()); // 물고기 수
        int S = Integer.parseInt(st.nextToken()); // 마법 연습 횟수
        
        map = new ArrayDeque[4][4];
        newMap = new ArrayDeque[4][4];
        maxHistory = new int[3];
        moveHistory = new int[3];
        smellMap = new int[4][4];
        visited = new boolean[4][4];
        
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                map[i][j] = new ArrayDeque<Fish>();
                newMap[i][j] = new ArrayDeque<Fish>();
            }
        }
        
        // 물고기 정보 
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;
            
            map[r][c].add(new Fish(r, c, d));
        }
        
        // 상어 정보 
        st = new StringTokenizer(br.readLine());
        sharkRow = Integer.parseInt(st.nextToken()) - 1;
        sharkCol = Integer.parseInt(st.nextToken()) - 1;
        
        while(S-- > 0) {
            
            // 상어 복제
            copyMagic();
            
            // 모든 물고기 한칸 이동
            moveFish();
            
            // 상어의 이동을 위한 backtracking
            max = -1;
        	moveShark(sharkRow, sharkCol, 0, 0);
            // maxHistory만큼 움직이기 - 실제 이동
            for(int d : maxHistory) {
            	sharkRow += sharkDeltas[d][0];
            	sharkCol += sharkDeltas[d][1];
            	if(map[sharkRow][sharkCol].size() > 0) smellMap[sharkRow][sharkCol] = 3;
            	map[sharkRow][sharkCol].clear();
            }
            // 물고기 냄새 사라지기
            for(int i=0; i<4; i++) {
            	for(int j=0; j<4; j++) {
            		if(smellMap[i][j] > 0) smellMap[i][j]--;
            	}
            }
            // newMap에서 map으로 옮기기.
            for(int i=0; i<4; i++) {
            	for(int j=0; j<4; j++) {
        			while(!newMap[i][j].isEmpty()) {
        				map[i][j].add(newMap[i][j].poll());
        			}
            	}
            }
        }
        // 남아있는 물고기 수
        int ans = 0;
        for(int i=0; i<4; i++) {
        	for(int j=0; j<4; j++) {
        		if(map[i][j].size() > 0) {
        			ans += map[i][j].size();
        		}
        	}
        }
        System.out.println(ans);
        
        
    }

    private static void moveShark(int sr, int sc, int depths, int fishCount) {
    	// 기저 조건
    	if(depths == 3) {
    		if(fishCount > max) {
    			maxHistory[0] = moveHistory[0];
    			maxHistory[1] = moveHistory[1];
    			maxHistory[2] = moveHistory[2];
    			max = fishCount;
    		}
    		return;
    	}
    	
    	for(int i=1; i<=4; i++) {
    		if(isOutOfRange(sr + sharkDeltas[i][0], sc + sharkDeltas[i][1]) ){
        		continue;
        	}
    		int newCount = fishCount;
    		int nr = sr + sharkDeltas[i][0];
    		int nc = sc + sharkDeltas[i][1];
    		boolean flag =  false;
    		if(visited[nr][nc]) flag = true;
			if(!visited[nr][nc]) newCount += map[nr][nc].size();
    		visited[nr][nc] = true;
        	moveHistory[depths] = i;
    		moveShark(nr, nc, depths+1, newCount);
    		if(!flag) visited[nr][nc] = false;
    	}
	}

	private static void moveFish() {
		Queue<Fish>[][] moveMap = new ArrayDeque[4][4];
        
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
            	moveMap[i][j] = new ArrayDeque<Fish>();
            }
        }
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(map[i][j].size()>0) {
                    while(!map[i][j].isEmpty()){
                        Fish fish = map[i][j].poll();
                        int nextRow = fish.r + fishDeltas[fish.d][0];
                        int nextCol = fish.c + fishDeltas[fish.d][1];
                        int cnt = 1;
                        int d = fish.d;
                        // 이동 가능할때까지 반시계 회전
                        while(isOutOfRange(nextRow, nextCol) || (nextRow == sharkRow && nextCol == sharkCol) || smellMap[nextRow][nextCol] > 0) {
                            d = (8 + d - 1 ) % 8;
                            nextRow = fish.r + fishDeltas[d][0];
                            nextCol = fish.c + fishDeltas[d][1];
                            if(++cnt == 9) break;
                        }
                        
                        // 한칸 이동 - cnt가 9일땐 똑같아야함
                        fish.d = d;
                        if(cnt < 9) {
                        	fish.r = nextRow;
                            fish.c = nextCol;
                            fish.isMoved = true;
                        	moveMap[nextRow][nextCol].offer(fish);
                        }
                        else moveMap[fish.r][fish.c].offer(fish);
                        
                    }
                }
            }
        }
        
        map = moveMap;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= 4 || c < 0 || c>= 4;
    }

    private static void copyMagic() {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(map[i][j].size()>0) {
                    for(Fish fish : map[i][j]) {
                        newMap[i][j].add(new Fish(fish.r, fish.c, fish.d));
                    }
                }
            }
        }
    }
}