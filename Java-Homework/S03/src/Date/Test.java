package Date;

/**
 * Created by sun on 3/16/16.
 *
 * Test unit fpt Date.java.
 */
public class Test {
    public static void main(String[] args) {
        Date d1 = new Date("3", "16", "2015"), d2 = new Date("June", 14, 2014), d3 = new Date(2015, 3, 6);
        System.out.println(d1.format(0));
        System.out.println(d2.format(1));
        System.out.println(d3.format(2));
    }
}
