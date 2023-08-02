import java.io.*;

public class Main {
    static void changeSwitch(int n, int sex, int getNum, int[] switchInfo){
        if(sex==1){
            for(int i = getNum-1; i< n; i+= getNum){
                switchInfo[i] = 1 - switchInfo[i];
            }
        } else {
            int idx = 1;
            getNum -= 1;
            switchInfo[getNum] = 1 - switchInfo[getNum];
            while(getNum+idx <n && getNum-idx >-1){
            	if(switchInfo[getNum+idx] == switchInfo[getNum-idx]){
	                switchInfo[getNum + idx] = 1 - switchInfo[getNum + idx];
	                switchInfo[getNum - idx] = 1 - switchInfo[getNum - idx];
	                idx +=1;
            	} else {
            		break;
            	}
        	}
	    }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] switchInfo = new int[n];
        String[] input;
        input = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            switchInfo[i] = Integer.parseInt(input[i]);                     
        }
        int stdNum = Integer.parseInt(br.readLine());
        int sex, getNum;
        for(int i = 0; i< stdNum; i++){
            input = br.readLine().split(" ");
            sex = Integer.parseInt(input[0]);
            getNum = Integer.parseInt(input[1]);
            changeSwitch(n, sex, getNum, switchInfo);
        }
        
        for(int i=0; i< n; i++){
            if(i%20 == 0 && i>0) System.out.print("\n");
            System.out.printf("%d ", switchInfo[i]);
            
        }
    }
}