package snakesandladders;

public class Game {
    private final Player player;
    private final Dice dice;
    private Board board;

    public Game(Player player, Dice dice, Board board) {
        this.player = player;
        this.dice = dice;
        this.board = board;
        board.register(player.getToken());
    }

    public void play() {
        int number = player.roll(dice);
        board.update(player.getToken(), number);
    }
}
