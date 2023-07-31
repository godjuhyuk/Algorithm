import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {1, 0, 1, -1}; // 방향 탐색용 델타 행 배열 {하단, 우단, 우하단, 우상단}
	static int[] dc = {0, 1, 1, 1}; // 방향 탐색용 델타 열 배열 {하단, 우단, 우하단, 우상단}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력을 위해 Scanner 대신 BufferedReader 사용
		StringTokenizer st; // 공백으로 나뉜 문자열을 파싱하기 위해 StringTokenizer 사용
		char[][] board = new char[19][19]; // 바둑판의 상태를 저장하는 2차원 배열
		
		// 바둑판 상태 저장
		for (int i = 0; i < board.length; i++) {
			st = new StringTokenizer(br.readLine()); // StringTokenizer 객체에 입력 한 줄 할당, delimeter 지정하지 않으면 공백 기준으로 토큰화
			for (int j = 0; j < board.length; j++) {
				board[i][j] = st.nextToken().charAt(0); // char형 배열이기 때문에 String 형태를 char형으로 변환
			}
		}
		
		boolean hasWinner = false; // 승자가 있는지 체크하는 boolean형 변수
		fo : for (int i = 0; i < board.length; i++) { // 승자가 생기면 바로 탈출하기 위해 라벨링
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == '0') continue; // 돌이 없는 곳은 탐색할 필요 없음
				
				if (i <= board.length - 5) { // 하단으로 5개가 있으려면 5칸의 공간 필요
					if (checkFive(board, i, j, 0)) { // 승자 체크 메서드
						hasWinner = true; // 승자가 정해짐
					}
				}
				
				if (j <= board.length - 5) { // 우단으로 5개가 있으려면 5칸의 공간 필요
					if (checkFive(board, i, j, 1)) { // 승자 체크 메서드
						hasWinner = true; // 승자가 정해짐
					}
				}
				
				if (i <= board.length - 5 && j <= board.length - 5) {  // 우하단으로 5개가 있으려면 5칸의 공간 필요
					if (checkFive(board, i, j, 2)) { // 승자 체크 메서드
						hasWinner = true; // 승자가 정해짐
					}
				}
				
				if (i >= 4 && j <= board.length - 5) {  // 우상단으로 5개가 있으려면 5칸의 공간 필요
					if (checkFive(board, i, j, 3)) { // 승자 체크 메서드
						hasWinner = true; // 승자가 정해짐
					}
				}
				
				if (hasWinner) { // 승자가 정해졌다면
					System.out.printf("%c%n%d %d", board[i][j], i + 1, j + 1); // 결과 출력
					break fo;
				}
			}
			
		}
		
		if (!hasWinner) { // 전체 탐색했음에도 승자가 없다면
			System.out.println(0); // 결과 출력
		}
	}

	/**
	 * 
	 * @param board 바둑판
	 * @param r 현재 행
	 * @param c 연재 열
	 * @param dir 탐색 방향
	 * @return
	 */
	private static boolean checkFive(char[][] board, int r, int c, int dir) {
		char nowColor = board[r][c]; // 현재 인덱스 바둑알 색깔
		
		int nextR, nextC;
		for (int i = 0; i < 5; i++) { // 탐색 방향으로 5칸 탐색
			nextR = r + dr[dir] * i;
			nextC = c + dc[dir] * i;
			
			// 바둑알이 이어지지 않는다면 false 반환
			if (board[nextR][nextC] != nowColor) return false;
		}
		
		// 탐색 반대방향의 바둑알이 연속되면 6칸 이상이 연속되는 것이므로 탐색 반대방향 체크
		nextR = r - dr[dir];
		nextC = c - dc[dir];
		if (isInRange(nextR, nextC) && board[nextR][nextC] == nowColor) return false;
		
		// 탐색 방향의 6칸째 지점이 연속되면 6칸 이상이 연속되는 것이므로 6칸째 체크
		nextR = r + dr[dir] * 5;
		nextC = c + dc[dir] * 5;
		if (!isInRange(nextR, nextC) || board[nextR][nextC] != nowColor) return true;
		return false;
	}

	// 바툭판 크기를 벗어나는지 체크하는 메서드
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < 19 && c >= 0 && c < 19;
	}
}