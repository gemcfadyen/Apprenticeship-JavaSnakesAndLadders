package snakesandladders;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void gameStartsWithPlayersOnFirstSquare() {
        PromptSpy prompt = new PromptSpy();
        Player player1 = new Player("one");
        Dice dice = new Dice();
        BoardSpy boardSpy = new BoardSpy(100);
        boardSpy.register(player1.getToken());

        new Game(prompt, player1, dice, boardSpy);

        assertThat(boardSpy.getNumberOfPlayersRegistered(), is(1));
        assertThat(boardSpy.getPositionOf(player1.getToken()), is(0));
    }

    @Test
    public void playerMovesTheNumberOfSpacesRolledOnDice() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(3);
        BoardSpy boardSpy = new BoardSpy(100);
        boardSpy.register(playerSpy.getToken());

        Game game = new Game(prompt, playerSpy, dice, boardSpy);

        game.takeMove();

        assertThat(playerSpy.getNumberTimesPlayerHasRolledDice(), is(1));
        assertThat(boardSpy.getPositionOf(playerSpy.getToken()), is(3));
    }

    @Test
    public void playerTakesTwoMovesAcrossBoard() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(3, 4);
        BoardSpy boardSpy = new BoardSpy(100);
        boardSpy.register(playerSpy.getToken());

        Game game = new Game(prompt, playerSpy, dice, boardSpy);

        game.takeMove();
        game.takeMove();

        assertThat(playerSpy.getNumberTimesPlayerHasRolledDice(), is(2));
        assertThat(boardSpy.getPositionOf(playerSpy.getToken()), is(7));
    }

    @Test
    public void playerWinsGame() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(4);
        Board board = initialSetup(95, playerSpy.getToken());
        Game game = new Game(prompt, playerSpy, dice, board);

        game.play();

        assertThat(prompt.numberOfTimesWinMessageDisplayed(), is(1));
        assertThat(board.getPositionOf(playerSpy.getToken()), is(99));
        assertThat(board.hasWinner(), is(true));
    }

    @Test
    public void playerOnlyWinsGameIfTheyThrowExactlyTheNumberOfRemainingSquaresOnBoard() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(5);
        Board board = initialSetup(95, playerSpy.getToken());
        Game game = new Game(prompt, playerSpy, dice, board);

        game.takeMove();

        assertThat(prompt.numberOfTimesWinMessageDisplayed(), is(0));
        assertThat(board.getPositionOf(playerSpy.getToken()), is(95));
        assertThat(board.hasWinner(), is(false));
    }

    @Test
    public void diceRollDisplayed() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(5);
        Board board = initialSetup(94, playerSpy.getToken());
        Game game = new Game(prompt, playerSpy, dice, board);

        game.takeMove();

        assertThat(prompt.numberOfTimesDiceRollHasBeenPrintedFor(playerSpy.getToken()), is(1));
    }

    @Test
    public void boardDisplayed() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Dice dice = new DiceStub(5);
        Board board = initialSetup(94, playerSpy.getToken());
        Game game = new Game(prompt, playerSpy, dice, board);

        game.takeMove();

        assertThat(prompt.hasDisplayedBoard(), is(true));
    }

    private Board initialSetup(int position, String playerToken) {
        String[] initialSetup = new String[100];
        for (int i = 0; i < initialSetup.length; i++) {
            initialSetup[i] = String.valueOf(i);
        }
        initialSetup[position] = playerToken;
        return new Board(initialSetup);
    }
}
