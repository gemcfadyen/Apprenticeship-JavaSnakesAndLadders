package snakesandladders;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void gameStartsWithPlayersOnFirstSquare() {
        Player player1 = new Player("one");
        Dice dice = new Dice();
        BoardSpy boardSpy = new BoardSpy();

        new Game(player1, dice, boardSpy);

        assertThat(boardSpy.getNumberOfPlayersRegistered(), is(1));
        assertThat(boardSpy.getPositionOf(player1.getToken()), is(0));
    }

    @Test
    public void playerMovesTheNumberOfSpacesRolledOnDice() {
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(3);
        BoardSpy boardSpy = new BoardSpy();

        Game game = new Game(playerSpy, dice, boardSpy);

        game.play();

        assertThat(playerSpy.getNumberTimesPlayerHasRolledDice(), is(1));
        assertThat(boardSpy.getPositionOf(playerSpy.getToken()), is(3));
    }

    @Test
    public void playerTakesTwoMovesAcrossBoard() {
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(3, 4);
        BoardSpy boardSpy = new BoardSpy();

        Game game = new Game(playerSpy, dice, boardSpy);

        game.play();
        game.play();

        assertThat(playerSpy.getNumberTimesPlayerHasRolledDice(), is(2));
        assertThat(boardSpy.getPositionOf(playerSpy.getToken()), is(7));
    }
}
