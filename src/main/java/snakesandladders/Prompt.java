package snakesandladders;

public interface Prompt {
    void printWinFor(String token);

    void printDiceRoll(String playerToken, int numberRolled);

    void print(Board board);
}
