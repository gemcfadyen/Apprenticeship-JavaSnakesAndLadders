package snakesandladders;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.List;

public class CommandLinePrompt implements Prompt {
    private final Reader reader;
    private final Writer writer;

    public CommandLinePrompt(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void printWinFor(String token) {
        print("Congratulations, " + token + " has won");

    }

    private void print(String message) {
        try {
            writer.write(message);
        } catch (IOException e) {
            throw new WriteException("Exception when writing to command line", e);
        }
    }

    @Override
    public void printDiceRoll(String playerToken, int numberRolled) {

    }

    @Override
    public void print(Board board) {
        StringBuffer display = new StringBuffer("");
        List<List<String>> rows = board.getRows();

        for (int rowNumber = rows.size() - 1; rowNumber >= 0; rowNumber--) {
            List<String> row = rows.get(rowNumber);

            if (rowNumber % 2 == 0) {
                display.append("| ");
                for (int i = 0; i < row.size(); i++) {
                    display.append(
                            pad(
                                    display(row.get(i), 1)) + " | ");
                }
                display.append("\n");
            } else {
                display.append("| ");
                for (int i = row.size() - 1; i >= 0; i--) {
                    display.append(
                            pad(display(row.get(i), 1)));
                    display.append(" | ");
                }
                display.append("\n");
            }

        }
        print(display.toString());
    }

    private String display(String value, int offset) {
        Integer squareNumber = Integer.valueOf(value) + offset;
        return squareNumber.toString();
    }

    private String pad(String number) {
        int lengthOfNumber = number.length();
        while (lengthOfNumber < 3) {
            number = " " + number;
            lengthOfNumber++;
        }
        return number;
    }

}
