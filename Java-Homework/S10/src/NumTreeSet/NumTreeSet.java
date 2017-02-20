package NumTreeSet;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sun on 5/14/16.
 *
 * NumTreeSet class.
 */
public class NumTreeSet {
    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> numSet = Stream.generate(() -> random.nextInt(100) + 1).limit(80).collect(Collectors.toSet());
        System.out.println(String.format("%d numbers in all:", numSet.size()));
        numSet.forEach(System.out::println);
    }
}
