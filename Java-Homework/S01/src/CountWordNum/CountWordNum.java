package CountWordNum;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by sun on 3/4/16.
 */
public class CountWordNum {
    public static int countWordNum(Collection<? extends String> array) {
        return new TreeSet<String>(array).size();
    }
}
