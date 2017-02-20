package MostTimesNum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * Created by sun on 3/4/16.
 */
public class MostTimesNum {
    public static int mostTimesNum(int[] a) {
        int key = 0, value = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i: a)
            map.put(i, map.getOrDefault(i, 0) + 1);
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            if ( pair.getValue() > value ) {
                key = pair.getKey();
                value = pair.getValue();
            }
        }
        return key;
    }
}
