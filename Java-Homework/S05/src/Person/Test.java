package Person;

import java.util.ArrayList;

/**
 * Created by sun on 4/2/16.
 *
 * Test unit for person.
 */
public class Test {
    public static void main(String[] args) {
        Person one = new Student();
        System.out.println(one.introduceSelf());
        test(new Student());
        Person[] many = new Person[] {
            new Student(),
            new Employee(),
            new Retired(),
        };
        for (Person i: many)
            System.out.println(i.introduceSelf());
    }
    static void test(Person p) {
        System.out.println(p.introduceSelf());
    }
}
