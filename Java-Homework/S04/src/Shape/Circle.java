package Shape;

/**
 * Created by sun on 3/25/16.
 *
 * Circle class.
 */
public class Circle extends Shape {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return PI * r * r;
    }
}
