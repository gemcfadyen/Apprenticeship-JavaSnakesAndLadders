package snakesandladders;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void boardIsComprisedOf100Squares() {
        Board board = new Board(100, "one");

        assertThat(board.size(), is(100));
    }

    @Test
    public void registerPlayerWithBoard() {
        Board board = new Board(10, "one");

        board.register("one");

        assertThat(board.getPositionOf("one"), is(0));
    }

    @Test
    public void getSquareForToken() {
        Board boardSetup = new Board(10, "playerOne");
        boardSetup.update("playerOne", 4);

        int positionOfPlayerOne = boardSetup.getPositionOf("playerOne");

        assertThat(positionOfPlayerOne, is(4));
    }

    @Test
    public void movePlayerGivenNumberOfSquares() {
        Board board = new Board(10, "playerOne");

        board.update("playerOne", 5);
        board.update("playerOne", 2);

        assertThat(board.getPositionOf("playerOne"), is(7));
        List<List<String>> rows = board.getRows();
        assertThat(rows.get(0).get(5), is("5"));
    }

    @Test
    public void getsPositionOfPlayerNotOnTheBoard() {
        Board board = new Board(10, "a-player");

        assertThat(board.getPositionOf("non-existent-player"), is(-1));
    }

    @Test
    public void hasWinner() {
        Board board = new Board(100, "one");
        board.update("one", 99);

        assertThat(board.hasWinner(), is(true));
    }

    @Test
    public void getRows() {
        Board board = new Board(20, "one");
        List<List<String>> rows = board.getRows();
        List<String> expectedFirstLine = expectedRow(0, 10);
        expectedFirstLine.set(0, "one");
        List<String> expectedSecondLine = expectedRow(10, 20);

        assertThat(rows.size(), is(2));
        assertThat(rows.get(0), is(expectedFirstLine));
        assertThat(rows.get(1), is(expectedSecondLine));

    }

    private List<String> expectedRow(int startingValue, int endValue) {
        List<String> expectedLine = new ArrayList<>();
        for(int i= startingValue; i<endValue; i++) {
            expectedLine.add(String.valueOf(i));
        }
        return expectedLine;
    }
}
