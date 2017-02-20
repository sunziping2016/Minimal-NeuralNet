package HugeInteger;

import java.util.Scanner;
/**
 * Created by sun on 3/10/16.
 *
 * Test unit for HugeInteger.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HugeInteger a, b;
        a = new HugeInteger(sc.next());
        b = new HugeInteger(sc.next());
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + a.addHugeIntegers(b));
        System.out.println("a - b = " + a.subHugeIntegers(b));
        if (a.compareTo(b) < 0)
            System.out.println("a < b");
        else if (a.compareTo(b) == 0)
            System.out.println("a = b");
        else
            System.out.println("a > b");
    }
}
