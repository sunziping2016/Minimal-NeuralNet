package Complex;

import java.util.Scanner;
/**
 * Created by sun on 3/10/16.
 *
 * Test unit for Complex.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Complex a, b;
        a = new Complex(sc.nextInt(), sc.nextInt());
        b = new Complex(sc.nextInt(), sc.nextInt());
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + (a.add(b)));
        System.out.println("a - b = " + (a.sub(b)));
    }

}
