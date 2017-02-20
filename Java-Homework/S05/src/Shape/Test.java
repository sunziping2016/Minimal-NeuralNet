package Shape;

/**
 * Created by sun on 4/2/16.
 *
 * Test unit for Shape
 */
public class Test {
    public static void main(String[] args) {
        Shape one = new Rectangle(2, 3);
        System.out.println(one.getArea());
        test(new Rectangle(2, 4));
        Shape[] many = new Shape[] {
                new Rectangle(2, 5),
                new Circle(1),
        };
        for (Shape i: many)
            System.out.println(i.getArea());
    }
    static void test(Shape shape) {
        System.out.println(shape.getArea());
    }
}
