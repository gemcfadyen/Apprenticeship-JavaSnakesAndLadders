package snakesandladders;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void playerPromptedToRollDie() {
        PromptSpy prompt = new PromptSpy();
        Player player1 = new Player("one");
        Game game = new Game(prompt, player1, createDie(), createBoardAndRegisterPlayer(player1));

        game.takeMove();

        assertThat(prompt.getNumberOfPlayersPromptedToRollDie(), is(1));
        assertThat(prompt.getNumberOfPlayersRolledDice(), is(1));
    }

    @Test
    public void playerMovesTheNumberOfSpacesRolledOnDice() {
        PlayerSpy playerSpy = new PlayerSpy("one");
        BoardSpy boardSpy = createBoardAndRegisterPlayer(playerSpy);
        Game game = new Game(new PromptSpy(), playerSpy, createDieWhichRolls(3), boardSpy);

        game.takeMove();

        assertThat(playerSpy.getNumberTimesPlayerHasRolledDice(), is(1));
        assertThat(boardSpy.getPositionOf(playerSpy.getToken()), is(3));
    }

    @Test
    public void playerTakesTwoMovesAcrossBoard() {
        PlayerSpy playerSpy = new PlayerSpy("one");
        BoardSpy boardSpy = createBoardAndRegisterPlayer(playerSpy);
        Game game = new Game(new PromptSpy(), playerSpy, new DiceStub(3, 4), boardSpy);

        game.takeMove();
        game.takeMove();

        assertThat(playerSpy.getNumberTimesPlayerHasRolledDice(), is(2));
        assertThat(boardSpy.getPositionOf(playerSpy.getToken()), is(7));
    }

    @Test
    public void playerWinsGame() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Board board = initialSetup(95, playerSpy.getToken());
        Game game = new Game(prompt, playerSpy, createDieWhichRolls(4), board);

        game.play();

        assertThat(prompt.numberOfTimesWinMessageDisplayed(), is(1));
        assertThat(prompt.getNumberOfTimesBoardIsPrinted(), is(2));
        assertThat(board.getPositionOf(playerSpy.getToken()), is(99));
        assertThat(board.hasWinner(), is(true));
    }

    @Test
    public void playerOnlyWinsGameIfTheyThrowExactlyTheNumberOfRemainingSquaresOnBoard() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Board board = initialSetup(95, playerSpy.getToken());
        Game game = new Game(prompt, playerSpy, createDieWhichRolls(5), board);

        game.takeMove();

        assertThat(prompt.numberOfTimesWinMessageDisplayed(), is(0));
        assertThat(board.getPositionOf(playerSpy.getToken()), is(95));
        assertThat(board.hasWinner(), is(false));
    }

    @Test
    public void diceRollDisplayed() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Game game = new Game(prompt, playerSpy, createDieWhichRolls(5), initialSetup(94, playerSpy.getToken()));

        game.takeMove();

        assertThat(prompt.numberOfTimesDiceRollHasBeenPrintedFor(playerSpy.getToken()), is(1));
    }

    @Test
    public void boardDisplayed() {
        PromptSpy prompt = new PromptSpy();
        PlayerSpy playerSpy = new PlayerSpy("one");
        Game game = new Game(prompt, playerSpy, createDieWhichRolls(5), initialSetup(94, playerSpy.getToken()));

        game.takeMove();

        assertThat(prompt.hasDisplayedBoard(), is(true));
    }

    @Test
    public void gamePlayedFromStartToFinish() {
        PromptSpy prompt = new PromptSpy();
        Dice dice = new DiceStub(6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3);
        BoardStub board = new BoardStub(100);
        Game game = new Game(prompt, new PlayerSpy("one"), dice, board);

        game.start();

        assertThat(prompt.numberOfTimesWinMessageDisplayed(), is(1));
        assertThat(board.hasPlayerRegistered(), is(true));
    }

    private Dice createDie() {
        return new Dice();
    }

    private DiceStub createDieWhichRolls(int i) {
        return new DiceStub(i);
    }

    private BoardSpy createBoardAndRegisterPlayer(Player player1) {
        BoardSpy boardSpy = new BoardSpy(100);
        boardSpy.register(player1.getToken());
        return boardSpy;
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
