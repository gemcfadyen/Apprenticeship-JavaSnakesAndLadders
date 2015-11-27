package snakesandladders;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CommandLinePromptTest {

    @Test
    public void printsWinner() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandLinePrompt(new StringReader(""), writer);

        prompt.printWinFor("one");

        assertThat(writer.toString(), is("Congratulations, one has won\n"));
    }

    @Test
    public void promptsUserToRollDice() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandLinePrompt(new StringReader(""), writer);

        prompt.promptUserToRollDie();

        assertThat(writer.toString(), is("Press enter to roll the dice....."));
    }

    @Test(expected = WriteException.class)
    public void exceptionThrownWhenErrorInWriting() {
        Writer writerWhichThrowsExceptionStub = new WriterExceptionStub();
        Prompt prompt = new CommandLinePrompt(new StringReader(""), writerWhichThrowsExceptionStub);

        prompt.printWinFor("one");
    }

    @Test
    public void readsRollCommand() {
        Prompt prompt = new CommandLinePrompt(new StringReader("\n"), new StringWriter());

        String command = prompt.readRollDieCommand();

        assertThat(command, is(""));
    }

    @Test
    public void printsDiceRoll() {
        Writer writer = new StringWriter();
        Prompt prompt = new CommandLinePrompt(new StringReader(""), writer);

        prompt.printDiceRoll("one", 4);

        assertThat(writer.toString(), is(".........one rolled 4............\n"));
    }

    @Test
    public void printsEmptyBoard() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandLinePrompt(new StringReader(""), writer);

        Board board = new Board(100);
        prompt.print(board);

        assertThat(writer.toString(), is(emptyBoard()));
    }

    @Test
    public void printsBoardWithPlayer() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandLinePrompt(new StringReader(""), writer);

        Board board = new Board(100);
        board.register("ONE");
        prompt.print(board);

        assertThat(writer.toString(), is(boardWithPlayer()));

    }

    private String emptyBoard() {
        return "| 100 |  99 |  98 |  97 |  96 |  95 |  94 |  93 |  92 |  91 | \n" +
                "|  81 |  82 |  83 |  84 |  85 |  86 |  87 |  88 |  89 |  90 | \n" +
                "|  80 |  79 |  78 |  77 |  76 |  75 |  74 |  73 |  72 |  71 | \n" +
                "|  61 |  62 |  63 |  64 |  65 |  66 |  67 |  68 |  69 |  70 | \n" +
                "|  60 |  59 |  58 |  57 |  56 |  55 |  54 |  53 |  52 |  51 | \n" +
                "|  41 |  42 |  43 |  44 |  45 |  46 |  47 |  48 |  49 |  50 | \n" +
                "|  40 |  39 |  38 |  37 |  36 |  35 |  34 |  33 |  32 |  31 | \n" +
                "|  21 |  22 |  23 |  24 |  25 |  26 |  27 |  28 |  29 |  30 | \n" +
                "|  20 |  19 |  18 |  17 |  16 |  15 |  14 |  13 |  12 |  11 | \n" +
                "|   1 |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 | \n\n";


    }

    private String boardWithPlayer() {
        return "| 100 |  99 |  98 |  97 |  96 |  95 |  94 |  93 |  92 |  91 | \n" +
                "|  81 |  82 |  83 |  84 |  85 |  86 |  87 |  88 |  89 |  90 | \n" +
                "|  80 |  79 |  78 |  77 |  76 |  75 |  74 |  73 |  72 |  71 | \n" +
                "|  61 |  62 |  63 |  64 |  65 |  66 |  67 |  68 |  69 |  70 | \n" +
                "|  60 |  59 |  58 |  57 |  56 |  55 |  54 |  53 |  52 |  51 | \n" +
                "|  41 |  42 |  43 |  44 |  45 |  46 |  47 |  48 |  49 |  50 | \n" +
                "|  40 |  39 |  38 |  37 |  36 |  35 |  34 |  33 |  32 |  31 | \n" +
                "|  21 |  22 |  23 |  24 |  25 |  26 |  27 |  28 |  29 |  30 | \n" +
                "|  20 |  19 |  18 |  17 |  16 |  15 |  14 |  13 |  12 |  11 | \n" +
                "| ONE |   2 |   3 |   4 |   5 |   6 |   7 |   8 |   9 |  10 | \n\n";


    }
}
