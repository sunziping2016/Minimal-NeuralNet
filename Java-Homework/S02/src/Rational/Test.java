package Rational;

import java.util.Scanner;
/**
 * Created by sun on 3/10/16.
 *
 * Unit test for Rational.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Rational a = new Rational(sc.nextInt(), sc.nextInt()), b = new Rational(sc.nextInt(), sc.nextInt());
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + a.add(b));
        System.out.println("a - b = " + a.sub(b));
        System.out.println("a * b = " + a.mul(b));
        System.out.println("a / b = " + a.div(b));
    }
}
