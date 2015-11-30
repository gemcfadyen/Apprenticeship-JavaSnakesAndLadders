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
    public void promptUserToRollDie() {
        print("Press enter to roll the dice.....");
    }

    @Override
    public void printDiceRoll(String playerToken, int numberRolled) {
        print("........." + playerToken + " rolled " + numberRolled + "............\n");
    }

    @Override
    public void printWinFor(String token) {
        print("Congratulations, " + token + " has won\n");
    }

    @Override
    public void print(Board board) {
        StringBuffer display = new StringBuffer("");
        List<List<String>> rows = board.getRows();

        for (int rowNumber = rows.size() - 1; rowNumber >= 0; rowNumber--) {
            List<String> row = rows.get(rowNumber);
            display.append(leftGrid());
            formatLinesInGrid(display, rowNumber, row);
            display.append(newLine());
        }
        print(display.toString() + "\n");
    }

    private void print(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new WriteException("Exception when writing to command line", e);
        }
    }

    private String leftGrid() {
        return "| ";
    }

    private String newLine() {
        return "\n";
    }

    private void formatLinesInGrid(StringBuffer display, int rowNumber, List<String> row) {
        if (evenRow(rowNumber)) {
            displayRowInAscendingOrder(display, row);
        } else {
            displayRowInDescendingOrder(display, row);
        }
    }

    private boolean evenRow(int rowNumber) {
        return rowNumber % 2 == 0;
    }

    private void displayRowInAscendingOrder(StringBuffer display, List<String> row) {
        for (String aRow : row) {
            display.append(pad(display(aRow)) + dividingGrid());
        }
    }

    private void displayRowInDescendingOrder(StringBuffer display, List<String> row) {
        for (int squareNumber = row.size() - 1; squareNumber >= 0; squareNumber--) {
            display.append(pad(display(row.get(squareNumber))));
            display.append(dividingGrid());
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

    private String dividingGrid() {
        return " | ";
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
}
