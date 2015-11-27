package snakesandladders;

public class BoardSpy extends Board {

    private int numberOfPlayersRegistered = 0;

    public BoardSpy(int numberOfSquaresOnBoard) {
        super(numberOfSquaresOnBoard);
    }

    public void register(String token) {
        super.register(token);
        numberOfPlayersRegistered++;
    }

    public int getNumberOfPlayersRegistered() {
        return numberOfPlayersRegistered;
    }
}
