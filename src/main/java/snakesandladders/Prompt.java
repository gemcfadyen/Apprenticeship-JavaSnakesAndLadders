package snakesandladders;

public interface Prompt {
    String readRollDieCommand();

    void promptUserToRollDie();

    void printDiceRoll(String playerToken, int numberRolled);

    void printWinFor(String token);

    void print(Board board);
}
