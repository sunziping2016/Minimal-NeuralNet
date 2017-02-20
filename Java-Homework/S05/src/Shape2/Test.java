package Shape2;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by sun on 4/2/16.
 *
 * Unit test for Square.
 */
public class Test {
    public static void main(String[] args) {
        Shape[] many = new Shape[] {
                new Circle(1, "red", true),
                new Rectangle(2, 3, "yellow", false),
                new Square(4, "#563D7C", true),
        };
        for (Shape i: many) {
            System.out.println(i);
            System.out.println("Area: " + i.getArea());
            System.out.println("Perimeter: " + i.getPerimeter());
            System.out.println("Color: " + i.getColor());
            System.out.println("Filled: " + i.isFilled());
            if (i instanceof Circle)
                System.out.println("Radius: " + ((Circle)i).radius);
            else if (i instanceof Square)
                System.out.println("Side: " + ((Square)i).getSide());
            else {
                System.out.println("Width: " + ((Rectangle)i).getWidth());
                System.out.println("Length: " + ((Rectangle)i).getLength());
            }
        }
    }
}
