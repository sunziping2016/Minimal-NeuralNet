package Lab13;

/**
 * Created by sun on 4/7/16.
 *
 * Unit Test fot class.
 */
public class Test {
    public static void main(String[] args) {
        int a = Lab13.readKeyboardInt();
        String line = Lab13.readKeyboardLine();
        try {
            double n;
            n = Lab13.exp(2, 3);
            System.out.println("exp(2, 3) = " + n);
            n = Lab13.exp(2, 0);
            System.out.println("exp(2, 0) = " + n);
            n = Lab13.exp(2, -1);
            System.out.println("exp(2, -1) = " + n);
        }
        catch (Exception error) {
            System.out.println(error);
        }
        try {
            int n;
            n = Lab13.approach(0.1);
            System.out.println("approach(0.1) = " + n);
            n = Lab13.approach(-0.1);
            System.out.println("approach(-0.1) = " + n);
        }
        catch (Exception error) {
            System.out.println(error);
        }
    }
}
