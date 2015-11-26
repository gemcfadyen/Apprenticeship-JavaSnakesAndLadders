package snakesandladders;

public class Player {
    private String token;

    public Player(String token) {
        this.token = token;
    }

    public int roll(Dice dice) {
        return dice.roll();
    }

    public String getToken() {
        return token;
    }
}
