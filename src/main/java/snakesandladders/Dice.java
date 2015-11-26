package snakesandladders;

import java.security.SecureRandom;
import java.util.Random;

public class Dice {
    private static final int MAX_NUMBER = 6;
    private static final int MIN_NUMBER = 1;

    public int roll() {
        Random random = new SecureRandom();
        int number = random.nextInt(MAX_NUMBER) + MIN_NUMBER;
        return number;
    }
}
