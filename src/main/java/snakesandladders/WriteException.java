package snakesandladders;

public class WriteException extends RuntimeException {
    public WriteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
