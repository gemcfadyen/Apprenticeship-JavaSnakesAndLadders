package snakesandladders;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void boardIsComprisedOf100Squares() {
        Board board = new Board();

        assertThat(board.size(), is(100));
    }

    @Test
    public void registerPlayerWithBoard() {
        Board board = new Board();

        board.register("one");

        assertThat(board.getPositionOf("one"), is(0));
    }

    @Test
    public void getSquareForToken() {
        String[] boardSetup = initialiseEmptyBoard();
        boardSetup[4] = "playerOne";
        Board board = new Board(boardSetup);

        int positionOfPlayerOne = board.getPositionOf("playerOne");

        assertThat(positionOfPlayerOne, is(4));
    }

    @Test
    public void movePlayerGivenNumberOfSquares() {
        String[] boardSetup = initialiseEmptyBoard();
        boardSetup[5] = "playerOne";
        Board board = new Board(boardSetup);

        board.update("playerOne", 2);

        assertThat(board.getPositionOf("playerOne"), is(7));
    }

    @Test
    public void getsPositionOfPlayerNotOnTheBoard() {
        String[] emptyBoard = initialiseEmptyBoard();

        Board board = new Board(emptyBoard);

        assertThat(board.getPositionOf("non-existent-player"), is(-1));
    }

    private String[] initialiseEmptyBoard() {
        String[] boardSetup = new String[100];
        for (int i = 0; i < 100; i++) {
            boardSetup[i] = "empty";
        }

        return boardSetup;
    }
}
