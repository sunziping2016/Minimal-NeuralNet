package NumArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sun on 5/14/16.
 *
 * NumArrayList class.
 */
public class NumArrayList {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> numList = Stream.generate(() -> random.nextInt(1001)).limit(20).collect(Collectors.toList());
        for (Iterator<Integer> iter = numList.iterator(); iter.hasNext();)
            if (iter.next() < 500)
                iter.remove();
        numList.forEach(System.out::println);
    }
}
