package TicTacToe;

/**
 * Created by sun on 3/16/16.
 *
 * Test unit of TicTacToe.java
 */
public class Test {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        for (String i: args[0].split("[\\(\\)]")) {
            if (i.length() == 0)
                continue;
            String[] pos = i.split(",");
            if (ticTacToe.put(Integer.parseInt(pos[0]) - 1, Integer.parseInt(pos[1]) - 1) == TicTacToe.State.FINISHED) {
                System.out.println("true");
                return;
            }
        }
        System.out.println("false");
    }
}
