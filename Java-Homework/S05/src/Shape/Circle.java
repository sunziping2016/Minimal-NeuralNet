package Shape;

/**
 * Created by sun on 4/2/16.
 *
 * Circle class.
 */
public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
        area = Math.PI * radius * radius;
    }

    @Override
    public double getArea() {
        return area;
    }
}
