package snakesandladders;

public class PromptSpy implements Prompt {
    private int numberOfWinMessagesPrinted = 0;

    public int numberOfTimesWinMessageDisplayed() {
        return numberOfWinMessagesPrinted;
    }

    @Override
    public void printWinFor(String token) {
        numberOfWinMessagesPrinted++;
    }
}
