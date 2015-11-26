package snakesandladders;

public class Board {

    private final String[] board;

    public Board() {
        this.board = new String[100];
        for (int i = 0; i < 100; i++) {
            board[i] = "empty";
        }
    }

    public Board(String[] initialSetup) {
        this.board = initialSetup;
    }

    public int size() {
        return board.length;
    }

    public int getPositionOf(String token) {
        for (int i = 0; i < board.length; i++) {
            if (token.equals(board[i])) {
                return i;
            }
        }

        return -1;
    }

    public void update(String token, int numberOfMoves) {
        int currentPosition = getPositionOf(token);
        board[currentPosition] = "empty";
        board[currentPosition + numberOfMoves] = token;
    }

    public void register(String token) {
        board[0] = token;
    }
}
