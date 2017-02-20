package Student;

/**
 * Created by sun on 3/25/16.
 *
 * Graduate class derived from Student
 */
public class Graduate extends Student{
    private static final String degree = "Graduate";
    private String direction;

    public Graduate(String name, int age, String specialty) {
        super(name, age, degree);
        this.direction = specialty;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" direction:%s", direction);
    }
}
