package Student;

/**
 * Created by sun on 3/25/16.
 *
 * Undergraduate class derived from Student
 */
public class Undergraduate extends Student{
    private static final String degree = "Undergraduate";
    private String specialty;

    public Undergraduate(String name, int age, String specialty) {
        super(name, age, degree);
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" specialty:%s", specialty);
    }
}
