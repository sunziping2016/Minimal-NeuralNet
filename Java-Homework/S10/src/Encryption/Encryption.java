package Encryption;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sun on 5/14/16.
 *
 * Encryption class.
 */
public class Encryption {
    private static final char[] PLAIN_TEXT  = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
    private static final char[] CIPHER_TEXT = "veknohzf iljxdmygbrcswqupta".toCharArray();

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int index = 0; index < PLAIN_TEXT.length; ++index)
            map.put((int) PLAIN_TEXT[index], (int) CIPHER_TEXT[index]);
        StringBuilder result = new StringBuilder();
        new Scanner(System.in).nextLine().chars().map(Character::toLowerCase).map(map::get).forEach(e -> result.append((char) e));
        System.out.println(result);
    }
}
