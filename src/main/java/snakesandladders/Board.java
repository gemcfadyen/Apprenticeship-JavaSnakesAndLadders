package snakesandladders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private static final int UNREGISTERED_PLAYER = -1;
    private final String[] board;

    public Board(int numberOfSquaresOnBoard, String playerToken) {
        this.board = new String[numberOfSquaresOnBoard];
        for (int i = 0; i < numberOfSquaresOnBoard; i++) {
            board[i] = String.valueOf(i);
        }
        register(playerToken);
    }

    public Board(String[] initialSetup) {
        this.board = initialSetup;
    }

    public int getPositionOf(String token) {
        for (int squareIndex = 0; squareIndex < board.length; squareIndex++) {
            if (token.equals(board[squareIndex])) {
                return squareIndex;
            }
        }
        return UNREGISTERED_PLAYER;
    }

    public void update(String token, int numberOfMoves) {
        int currentPosition = getPositionOf(token);
        empty(currentPosition);
        board[currentPosition + numberOfMoves] = token;
    }

    public int size() {
        return board.length;
    }

    public void register(String token) {
        playersStartOnFirstSquare(token);
    }

    public boolean hasWinner() {
        String label = board[99];
        return !isNumeric(label);
    }

    public List<List<String>> getRows() {
        List<List<String>> rows = new ArrayList<>();

        int counter = 0;
        while (counter < board.length) {
            List<String> oneRow = getRowOfTen(counter);
            counter += 10;
            rows.add(oneRow);
        }
        return rows;
    }

    private void empty(int currentPosition) {
        board[currentPosition] = String.valueOf(currentPosition);
    }

    private void playersStartOnFirstSquare(String token) {
        board[0] = token;
    }

    private boolean isNumeric(String label) {
        try {
            Integer.valueOf(label);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ArrayList<String> getRowOfTen(int counter) {
        return new ArrayList<>(Arrays.asList(board).subList(counter, counter + 10));
    }
}
