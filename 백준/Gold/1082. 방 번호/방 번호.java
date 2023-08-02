import java.util.*;
import java.io.*;
public class Main {
    static int n, m, tempM, maxMoney, maxLength;
    static int[][] numbers;
    static List<Integer> ansList;

    private static List<Integer> whoIsBig(List<Integer> a, List<Integer> b) {
        int idx = 0;
        while(idx < a.size()) {
            if(a.get(idx) >= b.get(idx)) {
                idx++;
            } else {
            	maxMoney = tempM;
                return b;
            }
        }

        return a;
    }

    public static void main(String[] args) throws IOException{

        // 입력값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(br.readLine());

        numbers = new int[n][2];
        ansList = new ArrayList<Integer>();

        // numbers[x][0] : 방번호
        // numbers[x][1] : 가격
        for(int i=0; i<n; i++) {
            numbers[i][0] = i;
            numbers[i][1] = Integer.parseInt(input[i]);
        }

        // 제일 싼것 순으로 정렬 -> 정렬하는 순간 아래에서 maxNumber의 index를 찾기가 번거로워지므로 코드 주석처리
        Arrays.sort(numbers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                if(o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        });
        
        for(int i=0; i < n; i++) {
            List<Integer> tempList = new ArrayList<Integer>();
            if(numbers[i][0] == 0) {
                continue;
            }

            // 제일 맨 앞자리 setting
            tempM = m - numbers[i][1];
            if(tempM>=0) {
                tempList.add(numbers[i][0]);
            }

            // 제일 긴자리 수 구하기
            while(tempM > 0) {
                if(tempM - numbers[0][1]>=0) {
                    tempM -= numbers[0][1];
                    tempList.add(numbers[0][0]);
                } else {
                    break;
                }
            }
            if(ansList.size() < tempList.size()) {
                ansList = tempList;
                maxMoney = tempM;
            }
            else if(ansList.size() == tempList.size()) {
                ansList = whoIsBig(ansList, tempList);
            }
        }
        
        // 정렬 원위치
        Arrays.sort(numbers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                return o1[0] - o2[0];
            }
        });

        // ansList가 빈 배열이라면 0
        if(ansList.size() == 0) {
            System.out.println(0);
            return;
        } else {

        	for(int i=0; i<ansList.size(); i++) {
                int maxNumber = ansList.get(i);
                for(int j=0; j<n; j++) {
                	
                	// numbers를 순회해가며 max number보다 큰데 구매가 가능하다면 구매한다.
                    if(numbers[j][0] > maxNumber && maxMoney + numbers[maxNumber][1] - numbers[j][1] >= 0) {
                    	maxMoney += numbers[maxNumber][1] - numbers[j][1] ;
                        ansList.set(i, numbers[j][0]);
                        maxNumber = numbers[j][0];
                    }
                }
            }
        	
            for(int i=0; i<ansList.size(); i++) {
                sb.append(ansList.get(i));
            }
            System.out.println(sb.toString());
        }



     }
}