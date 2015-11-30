package snakesandladders;

public class PlayerSpy extends Player {
    private int numberOfTimesPlayerHasRolledDice = 0;

    public PlayerSpy(String name) {
        super(name);
    }

    @Override
    public int roll(Dice dice) {
        numberOfTimesPlayerHasRolledDice++;
        return dice.roll();
    }

    public int getNumberTimesPlayerHasRolledDice() {
        return numberOfTimesPlayerHasRolledDice;
    }
}
