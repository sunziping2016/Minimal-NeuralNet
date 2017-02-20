package Student;

/**
 * Created by sun on 3/25/16.
 *
 * Student base class.
 */
public class Student {
    private String name;
    private int age;
    private String degree;

    public Student(String name, int age, String degree) {
        this.name = name;
        this.age = age;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return String.format("name:%s age:%d degree:%s", name, age, degree);
    }
    public String show() {
        return this.toString();
    }
}
