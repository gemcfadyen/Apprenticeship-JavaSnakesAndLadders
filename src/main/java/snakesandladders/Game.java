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
    }

    void takeMove() {
        int number = player.roll(dice);
        prompt.printDiceRoll(player.getToken(), number);

        int currentPosition = board.getPositionOf(player.getToken());
        int newPosition = currentPosition + number;
        if (newPosition < board.size()) {
            board.update(player.getToken(), number);
        }
        prompt.print(board);
    }

    private boolean winning(Board board) {
        return board.hasWinner();
    }

    private static Prompt buildPrompt() {
        return new CommandLinePrompt(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );
    }


}
