import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 
 * 설계 시작 시간 : 오후 4시 46분
 * 
 * 문제 해석)
 * M * N 크기의 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 후 빨간구슬을 구멍을 통해 빼내야함
 * 가장 바깥 행과 열은 모두 막혀있고, 보드에는 구멍이 하나 있다.
 * 이 때, 파란 구슬이 구멍에 들어가면 안된다.
 * 
 * 구슬을 중력을 이용하여 굴려야함.
 * 1) 왼쪽으로 기울이기
 * 2) 오른쪽으로 기울이기
 * 3) 위쪽으로 기울이기
 * 4) 아래쪽으로 기울이기
 * 
 * 각각의 동작에서 공은 동시에 움직인다.
 * 
 * 빨간구슬과 파랑구슬이 동시에 구멍에 빠져도 실패이다.
 * 또한 동시에 같은 칸에 있을 수도 없다.
 * 기울이는 동작을 그만하는 것은 더이상 구슬이 움직이지 않을 때 까지 이다.
 * (최대 10번 움직이기)
 *
 * 입출력)
 * 두 정수 N, M (3<= N, M <= 10)이 주어진다.
 * 
 * . => 빈칸
 * # => 공이 이동할 수 없는 장애물
 * o => 구멍 위치
 * R => 빨구
 * B => 파구
 *
 * 문제 해결을 위한 고민)
 * 
 * 10번동안 4번의 구르기를 다해보는 중복 순열
 * => 4^10
 * 
 * 게임 성공 조건 : 
 *     => 오로지 빨간공만 구멍에 들어갔을 때
 *
 *
 */
public class Main {
    
    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4; 
    private static int R, C, goalR, goalC, ans = 11;
    private static char[][] map;
    
    
    static class Ball {
        
        int r;
        int c;
        char color;
        public Ball(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
        
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        Ball red = null;
        Ball blue = null;
        for(int i=0; i<R; i++) {
            String input = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = input.charAt(j);
                
                if(map[i][j] == 'B') {
                    blue = new Ball(i, j, 'b');
                }
                else if(map[i][j] == 'R') {
                    red = new Ball(i, j, 'r');
                }
                else if(map[i][j] == 'O') {
                    goalR = i;
                    goalC = j;
                }
            }
        }
        
        for(int i=1; i<=4; i++) {
        	backtracking(new Ball(red.r, red.c, 'r'), new Ball(blue.r, blue.c, 'b'), i, 1, -1, -1);
        }
        if(ans != 11) System.out.println(ans);
        else  System.out.println(-1);
        
    }
    private static void backtracking(Ball red, Ball blue, int MOVE, int turn, int isRedGoal, int isBlueGoal) {

    	
    	
    	if(ans <= turn) return;
    	
//    	int tempR = red.r;
//    	int tempC = red.c;
//    	int tempBR = blue.r;
//    	int tempBC = blue.c;
    	
    	// 기저 조건
        if(turn > 10) {
        	return;
        }
        // MOVE에 따른 공 이동
        switch (MOVE) {
        case UP:
            moveUp(red, blue, isRedGoal, isBlueGoal);
            break;
        case DOWN:
            moveDown(red, blue, isRedGoal, isBlueGoal);        
            break;
        case LEFT:
            moveLeft(red, blue, isRedGoal, isBlueGoal);
            break;
        case RIGHT:
            moveRight(red, blue, isRedGoal, isBlueGoal);
            break;
        }
        
        // 움직인 후 게임 종료 조건 판단
        if(map[blue.r][blue.c] == 'O') {
        	return;
        }
//        if(turn== 3&& red.r == 2 && red.c == 1) {
//        	System.out.println(MOVE);
//        	System.out.println(tempR+ " " + tempC);
//        	System.out.println(tempBR+ " " + tempBC);
//        	System.out.println(blue.r + " " + blue.c);
//        	System.exit(0);
//        }
        if(map[red.r][red.c] == 'O' && isRedGoal == -1) {
            ans = Math.min(ans, turn);
            return;
        }
        // 게임이 끝나지 않았다면 다음턴
        for(int i=1; i<=4; i++) {
            backtracking(new Ball(red.r, red.c, 'r'), new Ball(blue.r, blue.c, 'b'), i, turn+1, isRedGoal, isBlueGoal);
        }
        
    }
    private static void moveRight(Ball red, Ball blue, int isRedGoal, int isBlueGoal) {
        // red가 오른쪽에 있는 경우
        if(red.c > blue.c) {
            while(map[red.r][red.c+1] != '#' && isRedGoal == -1) {
                red.c++;
                if(red.r == goalR && red.c == goalC) break;
            }
            while(map[blue.r][blue.c+1] != '#' && isBlueGoal == -1) {
                
                if(red.r == blue.r && red.c == blue.c+1 && map[red.r][red.c] != 'O') {
                    break;
                }
                blue.c++;
                if(blue.r == goalR && blue.c == goalC) break;
            }
        }
        // blue가 오른쪽에 있는 경우
        else {
            while(map[blue.r][blue.c+1] != '#' && isBlueGoal == -1) {
                blue.c++;
                if(blue.r == goalR && blue.c == goalC) break;
            }
            while(map[red.r][red.c+1] != '#' && isRedGoal == -1) {
                if(red.r == blue.r && red.c+1 == blue.c && map[blue.r][blue.c] != 'O') {
                    break;
                }
                red.c++;
                if(red.r == goalR && red.c == goalC) break;
            }
        }
    }
    private static void moveLeft(Ball red, Ball blue, int isRedGoal, int isBlueGoal) {
        
        // red가 왼쪽에 있는 경우
        if(red.c < blue.c) {
            while(map[red.r][red.c-1] != '#' && isRedGoal == -1) {
                red.c--;
                if(red.r == goalR && red.c == goalC) break;
            }
            while(map[blue.r][blue.c-1] != '#' && isBlueGoal == -1) {
                if(red.r == blue.r && red.c == blue.c-1 && map[red.r][red.c] != 'O') {
                    break;
                }
                blue.c--;
                if(blue.r == goalR && blue.c == goalC) break;
            }
        }
        // blue가 왼쪽에 있는 경우
        else {
            while(map[blue.r][blue.c-1] != '#' && isBlueGoal == -1) {
                blue.c--;
                if(blue.r == goalR && blue.c == goalC) break;
            }
            while(map[red.r][red.c-1] != '#' && isRedGoal == -1) {
                
                if(red.r == blue.r && red.c-1 == blue.c && map[blue.r][blue.c] != 'O') {
                    break;
                }
                red.c--;
                if(red.r == goalR && red.c == goalC) break;
                
            }
        }
    }
    private static void moveDown(Ball red, Ball blue, int isRedGoal, int isBlueGoal) {
        // red가 아래 있는 경우
        if(red.r > blue.r) {
            while(map[red.r+1][red.c] != '#' && isRedGoal == -1) {
                red.r++;
                if(red.r == goalR && red.c == goalC) break;
            }
            while(map[blue.r+1][blue.c] != '#' && isBlueGoal == -1) {
                
                if(red.r == blue.r+1 && red.c == blue.c && map[red.r][red.c] != 'O') {
                    break;
                }
                blue.r++;
                if(blue.r == goalR && blue.c == goalC) break;
            }
        }
        // blue가 왼쪽에 있는 경우
        else {
        	
            while(map[blue.r+1][blue.c] != '#' && isBlueGoal == -1) {
                blue.r++;
                if(blue.r == goalR && blue.c == goalC) break;
            }
            while(map[red.r+1][red.c] != '#' && isRedGoal == -1) {
                
                if(blue.r == red.r+1 && blue.c == red.c && map[blue.r][blue.c] != 'O') {
                    break;
                }
                red.r++;
                if(red.r == goalR && red.c == goalC) break;
            }
        }
        
    }
    private static void moveUp(Ball red, Ball blue, int isRedGoal, int isBlueGoal) {
        // red가 위에 있는 경우
        if(red.r < blue.r) {
            while(map[red.r-1][red.c] != '#' && isRedGoal == -1) {
                red.r--;
                if(red.r == goalR && red.c == goalC) break;
            }
            while(map[blue.r-1][blue.c] != '#' && isBlueGoal == -1) {
                
                if(red.r == blue.r-1 && red.c == blue.c && map[red.r][red.c] != 'O') {
                    break;
                }
                blue.r--;
                if(blue.r == goalR && blue.c == goalC) break;
            }
        }
        // blue가 위에 있는 경우
        else {
            while(map[blue.r-1][blue.c] != '#' && isBlueGoal == -1) {
                blue.r--;
                if(blue.r == goalR && blue.c == goalC) break;
            }
            while(map[red.r-1][red.c] != '#' && isRedGoal == -1) {
                
                if(blue.r == red.r-1 && blue.c == red.c && map[blue.r][blue.c] != 'O') {
                    break;
                }
                red.r--;
                if(red.r == goalR && red.c == goalC) break;
            }
        }        
    }
    
}