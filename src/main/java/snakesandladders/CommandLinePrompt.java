package snakesandladders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class CommandLinePrompt implements Prompt {
    private final BufferedReader reader;
    private final Writer writer;

    public CommandLinePrompt(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;
    }

    @Override
    public String readRollDieCommand() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadException(e.getMessage(), e);
        }
    }

    @Override
    public void promptUserToRollDice() {
        print("Press enter to roll the dice.....");
    }

    @Override
    public void printWinFor(String token) {
        print("Congratulations, " + token + " has won");

    }

    private void print(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new WriteException("Exception when writing to command line", e);
        }
    }

    @Override
    public void printDiceRoll(String playerToken, int numberRolled) {
        print("........." + playerToken + " rolled " + numberRolled + ".............\n");
    }

    @Override
    public void print(Board board) {
        StringBuffer display = new StringBuffer("");
        List<List<String>> rows = board.getRows();

        for (int rowNumber = rows.size() - 1; rowNumber >= 0; rowNumber--) {
            List<String> row = rows.get(rowNumber);
            display.append(leftGrid());

            if (evenRow(rowNumber)) {
                displayRowInAscendingOrder(display, row);
            } else {
                displayRowInDescendingOrder(display, row);
            }
            display.append(newLine());
        }
        print(display.toString() + "\n");
    }

    private void displayRowInDescendingOrder(StringBuffer display, List<String> row) {
        for (int i = row.size() - 1; i >= 0; i--) {
            display.append(pad(display(row.get(i))));
            display.append(dividingGrid());
        }
    }

    private void displayRowInAscendingOrder(StringBuffer display, List<String> row) {
        for (int i = 0; i < row.size(); i++) {
            display.append(pad(display(row.get(i))) + dividingGrid());
        }
    }

    private String leftGrid() {
        return "| ";
    }

    private String dividingGrid() {
        return " | ";
    }

    private String newLine() {
        return "\n";
    }

    private boolean evenRow(int rowNumber) {
        return rowNumber % 2 == 0;
    }

    private String display(String value) {
        return applyDisplayOffsetIfNumeric(value);
    }

    private String applyDisplayOffsetIfNumeric(String label) {
        try {
            Integer numericLabel = Integer.valueOf(label);
            return String.valueOf(numericLabel + 1);
        } catch (NumberFormatException e) {
            return label;
        }
    }

    private String pad(String squareLabel) {
        int lengthOfNumber = squareLabel.length();
        while (lengthOfNumber < 3) {
            squareLabel = " " + squareLabel;
            lengthOfNumber++;
        }
        return squareLabel;
    }


}
