package Complex;

/**
 * Created by sun on 3/10/16.
 *
 * Class represents complex number.
 */
public class Complex {
    public Complex(double r, double i) {
        realPart = r;
        imaginaryPart = i;
    }
    public Complex add(Complex other) {
        return new Complex(other.realPart + realPart, other.imaginaryPart + imaginaryPart);
    }
    public Complex sub(Complex other) {
        return new Complex(realPart - other.realPart, imaginaryPart - other.imaginaryPart);
    }
    @Override
    public String toString() {
        return realPart + " + " + imaginaryPart + "i";
    }

    private double realPart, imaginaryPart;
}
