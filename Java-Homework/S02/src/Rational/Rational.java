package Rational;

/**
 * Created by sun on 3/10/16.
 *
 * Class represent rational numbers.
 * TODO: Few consideration for integer overflow.
 */
public class Rational {
    public Rational(int numerator) {
        this(numerator, 1);
    }
    public Rational(int numerator, int denominator) {
        if (denominator == 0)
            throw new AssertionError("Denominator cannot be 0!");
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        }
        else if (denominator > 0) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        else {
            this.numerator = -numerator;
            this.denominator = -denominator;
        }
        reduction();
    }
    public Rational add(Rational other) {
        return new Rational(numerator * other.denominator + other.numerator * denominator, denominator * other.denominator);
    }
    public Rational sub(Rational other) {
        return new Rational(numerator * other.denominator - other.numerator * denominator, denominator * other.denominator);
    }
    public Rational mul(Rational other) {
        return new Rational(numerator * other.numerator, denominator * other.denominator);
    }
    public Rational div(Rational other) {
        if (other.numerator == 0)
            throw new AssertionError("Divided by 0!");
        return new Rational(numerator * other.denominator, denominator * other.numerator);
    }
    @Override
    public String toString() {
        if (denominator == 1)
            return Integer.toString(numerator);
        else
            return numerator + "/" + denominator;
    }
    public String printRational() {
        return this.toString();
    }
    public String printReal() {
        return Double.toString((double)numerator / denominator);
    }

    protected void reduction() {
        int num = gcd(numerator > 0 ? numerator : -numerator, denominator);
        numerator /= num;
        denominator /= num;
    }
    protected static int gcd(int a, int b) {
        int temp;
        while (a % b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    private int numerator, denominator;
}
