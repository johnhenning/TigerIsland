package GameStateModule;

import java.lang.reflect.Array;
import java.util.*;

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

    public int hashCode() {
        return (x * 31) ^ y;
    }
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate other = (Coordinate) o;
            return (x == other.x && y == other.y);
        }
        return false;
    }
    public boolean equals(Coordinate coordinate) {
        return (coordinate.getX() == x) && (coordinate.getY() == y);
    }

    public static Coordinate add(Coordinate c0, Coordinate c1) {
        Coordinate coordinate = new Coordinate(c0.getX() + c1.getX(), c0.getY() + c1.getY());
        return coordinate;
    }

    public static ArrayList<Coordinate> removeDuplicates(ArrayList<Coordinate> coordinates){
// add elements to al, including duplicates
        Set<Coordinate> coordsWithoutDuplicates = new LinkedHashSet<Coordinate>(coordinates);
        coordinates.clear();
        coordinates.addAll(coordsWithoutDuplicates);

        return coordinates;
    }
}
