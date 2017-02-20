package Polymorphism;

/**
 * Created by sun on 4/2/16.
 *
 * Test for polymorphism.
 */
public class Test {
    public static void main(String[] args) {
        Number[] numbers = new Number[] {
                new Integer(1),
                new Double(2.0),
        };
        for (Number i: numbers) {
            System.out.println(i.getClass().getName());
            System.out.println("toString() = " + i.toString());
            System.out.println("intValue() = " + i.intValue());
            System.out.println("doubleValue() = " + i.doubleValue());
        }
    }
}
