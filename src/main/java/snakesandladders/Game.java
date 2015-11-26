package snakesandladders;

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


    public void play() {

        do {
            takeMove();
        } while (!winning(board));

        prompt.printWinFor(player.getToken());
    }

    void takeMove() {
        int number = player.roll(dice);
        int currentPosition = board.getPositionOf(player.getToken());
        int newPosition = currentPosition + number;
        if (newPosition < board.size()) {
            board.update(player.getToken(), number);
        }
    }

    private boolean winning(Board board) {
        return board.hasWinner();
    }

}
