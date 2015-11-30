package snakesandladders;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void playerRollsDice() {
        Player player = new Player("one");

        int number = player.roll(new DiceStub(3));

        assertThat(number, is(3));
    }

    @Test
    public void getsPlayerToken() {
        Player player = new Player("token-name");

        String token = player.getToken();

        assertThat(token, is("token-name"));
    }

}
