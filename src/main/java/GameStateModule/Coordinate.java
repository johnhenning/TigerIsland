package GameStateModule;

/**
 * Created by johnhenning on 3/20/17.
 */
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Coordinate coordinate) {
        return (coordinate.getX() == x) && (coordinate.getY() == y);
    }

    public static Coordinate add(Coordinate c0, Coordinate c1) {
        Coordinate coordinate = new Coordinate(c0.getX() + c1.getX(), c0.getY() + c1.getY());
        return coordinate;
    }
}
