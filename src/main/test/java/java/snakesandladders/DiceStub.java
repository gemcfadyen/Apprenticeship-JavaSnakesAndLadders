package snakesandladders;

public class DiceStub extends Dice {
    private int[] rolls;
    private int counter = 0;

    public DiceStub(int... rolls) {
        this.rolls = rolls;
    }

    @Override
    public int roll() {
        return rolls[counter++];
    }

}
