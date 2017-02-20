package Shape;

/**
 * Created by sun on 3/25/16.
 *
 * Unit Test for Shape package.
 */
public class Test {
    public static void main(String[] args) {
        Shape circle = new Circle(5),
                square = new Square(5);

        System.out.println("Circle\'s area: " + circle.getArea());
        System.out.println("Square\'s area: " + square.getArea());
    }
}
