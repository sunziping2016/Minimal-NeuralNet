package CompleteNum;

import java.util.Scanner;
/**
 * Created by sun on 3/4/16.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (CompleteNum.isCompleteNum(n))
            System.out.println("yes");
        else
            System.out.println("no");
    }
}
