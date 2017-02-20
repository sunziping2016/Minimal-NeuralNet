package NumSplit;

/**
 * Created by sun on 3/4/16.
 */
public class NumSplit {
    public static String split(int n) {
        StringBuilder s = new StringBuilder();
        if (n == 0) return "0";
        s.append(n % 10);
        n /= 10;
        while (n != 0) {
            s.append(' ');
            s.append(n % 10);
            n /= 10;
        }
        return s.reverse().toString();
    }
}

