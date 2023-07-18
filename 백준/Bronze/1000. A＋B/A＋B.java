import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        System.out.println(Integer.parseInt(strArr[0]) + Integer.parseInt(strArr[1]));
    }
}