package Shape;

/**
 * Created by sun on 4/2/16.
 *
 * Rectangle class.
 */
public class Rectangle extends Shape {
    private double length;
    private double width;
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        area = length * width;
    }

    @Override
    public double getArea() {
        return area;
    }
}
