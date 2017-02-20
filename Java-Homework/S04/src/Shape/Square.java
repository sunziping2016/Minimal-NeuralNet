package Shape;

/**
 * Created by sun on 3/25/16.
 *
 * Square class.
 */
public class Square extends Shape {
    private double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    public double getArea() {
        return a * a;
    }
}
