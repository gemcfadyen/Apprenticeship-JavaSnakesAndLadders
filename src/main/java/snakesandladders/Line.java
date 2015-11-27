package snakesandladders;

public class Line {
    private String[] squares;

    public Line(String... squares) {
        this.squares = squares;
    }

    public void setSquare(int index, String value) {
        squares[index] = value;
    }

    public String getSquare(int index) {
        return null;
    }
}
