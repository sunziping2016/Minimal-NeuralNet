package TicTacToe;

/**
 * Created by sun on 3/16/16.
 *
 * TicTacToe class represents tic-tac-toe game.
 */
public class TicTacToe {
    public enum Player {
        PLAYER1(1),
        PLAYER2(2);

        Player(int id) {
            this.id = id;
        }
        public Player other() {
            if (this.id == PLAYER1.id)
                return PLAYER2;
            else
                return PLAYER1;
        }
        public final int id;
    }
    public enum State {GOING_ON, FINISHED}

    public State put(int x, int y) {
        if (map[x][y] != 0)
            throw new AssertionError("position occupied by other player.");
        if (state == State.FINISHED)
            throw new AssertionError("can not put on a finished game.");
        turn = turn.other();
        map[x][y] = turn.id;
        updateState();
//        for (int i = 0; i < 3; ++i) {
//            for (int j = 0; j < 3; ++j)
//                System.out.print(map[i][j]);
//            System.out.println();
//        }
        return state;
    }
    public State putAI() {
        if (state == State.FINISHED)
            throw new AssertionError("can not put on a finished game.");
        // TODO: Not implemented.
        return state;
    }
    public void updateState() {
        if (state == State.FINISHED)
            return;
        for (int i = 0; i < 3; ++i) {
            if (map[i][0] != 0 && map[i][0] == map[i][1] && map[i][1] == map[i][2] ||
                    map[0][i] != 0 && map[0][i] == map[1][i] && map[1][i] == map[2][i])  {
                state = State.FINISHED;
                return;
            }
        }
        if (map[0][0] != 0 && map[0][0] == map[1][1] && map[1][1] == map[2][2])
            state = State.FINISHED;
        else if (map[2][0] != 0 && map[2][0] == map[1][1] && map[1][1] == map[0][2])
            state = State.FINISHED;
    }

    private int[][] map = new int[][] {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
    };
    private Player turn = Player.PLAYER2;
    private State state = State.GOING_ON;
}
