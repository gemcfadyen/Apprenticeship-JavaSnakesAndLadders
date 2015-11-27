package snakesandladders;

public class BoardStub extends Board {
    private boolean hasPlayerRegistered = false;

    public BoardStub(int dimension) {
        super(dimension);
    }

    @Override
    public void register(String token) {
        hasPlayerRegistered = true;
        super.register(token);
    }

    public boolean hasPlayerRegistered() {
        return hasPlayerRegistered;
    }
}
