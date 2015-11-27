package snakesandladders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final String[] board;

    public Board(int numberOfSquaresOnBoard) {
        this.board = new String[numberOfSquaresOnBoard];
        for (int i = 0; i < numberOfSquaresOnBoard; i++) {
            board[i] = String.valueOf(i);
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
        board[currentPosition] = String.valueOf(currentPosition);
        board[currentPosition + numberOfMoves] = token;
    }

    public void register(String token) {
        board[0] = token;
    }

    public boolean hasWinner() {
        String label = board[99];
        return isAlpha(label);
    }

    private boolean isAlpha(String label) {
       try {
           Integer.valueOf(label);
           return false;
       } catch(Exception e) {
           return true;
       }
    }

    public List<List<String>> getRows() {
        List rows = new ArrayList<>();

        int counter = 0;
        while (counter < board.length) {
            List<String> oneRow = new ArrayList<>(Arrays.asList(board).subList(counter, counter + 10));
            counter += 10;
            rows.add(oneRow);
        }


        return rows;
    }
}
