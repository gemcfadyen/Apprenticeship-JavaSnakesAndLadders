package snakesandladders;

import java.util.HashMap;
import java.util.Map;

public class PromptSpy implements Prompt {
    private int numberOfWinMessagesPrinted = 0;
    private boolean hasPrintedBoard;
    private Map<String, Integer> numberOfTimesPlayerHasRolledDice = new HashMap<>();

    @Override
    public void printWinFor(String token) {
        numberOfWinMessagesPrinted++;
    }

    @Override
    public void printDiceRoll(String playerToken, int numberRolled) {
        int originalNumberOfRolls = numberOfTimesPlayerHasRolledDice.get(playerToken) == null
                ? 0
                : numberOfTimesPlayerHasRolledDice.get(playerToken);
        numberOfTimesPlayerHasRolledDice.put(playerToken, ++originalNumberOfRolls);
    }

    @Override
    public void print(Board board) {
        hasPrintedBoard = true;
    }

    public int numberOfTimesWinMessageDisplayed() {
        return numberOfWinMessagesPrinted;
    }

    public int numberOfTimesDiceRollHasBeenPrintedFor(String playerToken) {
        return numberOfTimesPlayerHasRolledDice.get(playerToken);
    }

    public boolean hasDisplayedBoard() {
       return hasPrintedBoard;
    }
}
