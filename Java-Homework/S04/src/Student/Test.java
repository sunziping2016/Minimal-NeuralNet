package Student;

/**
 * Created by sun on 3/25/16.
 *
 * Unit test for student package.
 */
public class Test {
    public static void main(String[] args) {
        Student undergraduate = new Undergraduate("sky", 23, "java"),
                graduate = new Graduate("sky", 23, "software");

        System.out.println(graduate.show());
        System.out.println(undergraduate.show());
    }
}
