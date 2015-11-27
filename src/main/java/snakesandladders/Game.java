package snakesandladders;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Game {
    private Prompt prompt;
    private final Player player;
    private final Dice dice;
    private Board board;

    public Game(Prompt prompt, Player player, Dice dice, Board board) {
        this.prompt = prompt;
        this.player = player;
        this.dice = dice;
        this.board = board;
    }

    public static void main(String... args) {
        Game game = new Game(buildPrompt(), new Player("ONE"), new Dice(), new Board(100));
        game.start();
    }

    public void start() {
        board.register(player.getToken());
        play();
    }

    void play() {
        do {
            takeMove();
        } while (!winning(board));

        prompt.printWinFor(player.getToken());
        prompt.print(board);
    }

    void takeMove() {
        prompt.print(board);
        int number = playerRollsDice();
        prompt.printDiceRoll(player.getToken(), number);

        int currentPosition = board.getPositionOf(player.getToken());
        if (calculateNewPosition(number, currentPosition) < board.size()) {
            board.update(player.getToken(), number);
        }
    }

    private boolean winning(Board board) {
        return board.hasWinner();
    }

    private int playerRollsDice() {
        prompt.promptUserToRollDie();
        prompt.readRollDieCommand();
        return player.roll(dice);
    }

    private int calculateNewPosition(int number, int currentPosition) {
        return currentPosition + number;
    }

    private static Prompt buildPrompt() {
        return new CommandLinePrompt(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );
    }
}
