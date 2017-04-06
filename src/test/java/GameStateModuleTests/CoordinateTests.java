package GameStateModuleTests;

import GameStateModule.*;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTests {

    private Coordinate coordinate;
    private Coordinate coordinate2;

    @Before
    public void setup() throws Exception{
        coordinate = new Coordinate(6,9);
        coordinate2 = new Coordinate(4,20);
    }

    @Test
    public void getXTest() throws Exception{
        assert coordinate.getX() == 6;
    }

    @Test
    public void getYTest() throws Exception{
        assert coordinate.getY() == 9;
    }

    @Test
    public void equalsTest() throws Exception{
        assert !coordinate.equals(coordinate2);
    }

    @Test
    public void addTest() throws Exception{
        assert Coordinate.add(coordinate,coordinate2).getX() == 10;
        assert Coordinate.add(coordinate,coordinate2).getY() == 29;

    }

}
