package snakesandladders;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DiceTest {
    @Test
    public void diceRollsNumberGreaterThanZero() {
        Dice dice = new Dice();
        int number = dice.roll();
        assertThat(number, greaterThanOrEqualTo(1));
    }

    @Test
    public void diceRollsNumberLessThanSeven() {
        Dice dice = new Dice();
        int number = dice.roll();
        assertThat(number, lessThan(7));
    }

    @Test
    public void diceRolledMultipleTimesAlwaysFallsWithinRange() {
        Dice dice = new Dice();

        assertThat(areResultsOf100RollsWithinRange1To6(dice), is(true));
    }

    @Test
    public void diceRolledMultipleTimesResultsInARangeOfNumbers() {
        Dice dice = new Dice();

        assertThat(containsDifferentValues(numbersFrom100RollsOf(dice)), is(true));
    }

    private boolean containsDifferentValues(int[] numbers) {
        boolean containsDifferentValues = false;
        int firstNumber = numbers[0];
        for (int number : numbers) {
            if (number != firstNumber) {
                containsDifferentValues = true;
            }
        }
        return containsDifferentValues;
    }

    private int[] numbersFrom100RollsOf(Dice dice) {
        int[] numbers = new int[100];
        for (int i = 0; i < 100; i++) {
            numbers[i] = dice.roll();
        }

        return numbers;
    }

    private boolean areResultsOf100RollsWithinRange1To6(Dice dice) {
        boolean withinRange = true;
        for (int i = 0; i < 100; i++) {
            int number = dice.roll();
            if (number < 1 || number > 6) {
                withinRange = false;
            }
        }
        return withinRange;
    }
}
