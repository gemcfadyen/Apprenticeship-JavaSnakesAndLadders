package snakesandladders;

public interface Prompt {
    String readRollDieCommand();

    void promptUserToRollDice();

    void printWinFor(String token);

    void printDiceRoll(String playerToken, int numberRolled);

    void print(Board board);
}
