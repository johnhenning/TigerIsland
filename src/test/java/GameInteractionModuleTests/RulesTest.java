package GameInteractionModuleTests;

import GameInteractionModule.Rules.Rules;
import GameStateModule.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by KWong on 3/28/2017.
 */
public class RulesTest {

    private Grid grid;
    private Tile tile;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<TerrainType> terrains;


    @Before
    public void setup(){

        coordinates = new ArrayList<Coordinate>();
        terrains = new ArrayList<TerrainType>();

        grid = new Grid(420);
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(99,101));
        coordinates.add(new Coordinate(100,101));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASS);
        terrains.add(TerrainType.JUNGLE);

        tile = new Tile(coordinates, terrains);

        grid.placeTile(tile);

    }
    @Test
    public void GameStartedTest(){
        assert Rules.GameStarted(grid) == true;
    }

    @Test

    public void checkIfHexEmptyTest(){
        assert Rules.CheckIfHexEmpty(new Hex(new Coordinate(99, 100), TerrainType.LAKE), grid) == true;
    }

    @Test
    public void HexesAreAdjacentTest(){
        assert Rules.HexesAreAdjacent(tile.getHexes().get(0), tile.getHexes().get(1));
    }

    @Test

    public void HexesAreNotAdjacentTest(){
        assert !Rules.HexesAreAdjacent(tile.getHexes().get(0), new Hex(new Coordinate(101,101), TerrainType.GRASS));
    }

    @Test
    public void downRightTest(){
        assert coordinates.get(2).getX() == 100;
        assert coordinates.get(2).getY() == 101;
        assert tile.getHexes().get(2).equals(Rules.downRight(grid, coordinates.get(0)));
    }

    @Test
    public void downLeftTest(){
        assert coordinates.get(1).getX() == 99;
        assert coordinates.get(1).getY() == 101;
        assert tile.getHexes().get(1).equals(Rules.downLeft(grid, coordinates.get(0)));
    }

    @Test
    public void topRightTest(){
        assert coordinates.get(0).getX() == 100;
        assert coordinates.get(0).getY() == 100;
        assert tile.getHexes().get(0).equals(Rules.topRight(grid, coordinates.get(1)));
    }

    @Test
    public void topLeftTest(){
        assert coordinates.get(0).getX() == 100;
        assert coordinates.get(0).getY() == 100;
        assert tile.getHexes().get(0).equals(Rules.topLeft(grid, coordinates.get(2)));
    }

    @Test
    public void leftOfHexTest(){
        assert coordinates.get(1).getX() == 99;
        assert coordinates.get(1).getY() == 101;
        assert tile.getHexes().get(1).equals(Rules.leftOfHex(grid, coordinates.get(2)));
    }

    @Test
    public void rightOfHexTest(){
        assert coordinates.get(2).getX() == 100;
        assert coordinates.get(2).getY() == 101;
        assert tile.getHexes().get(2).equals(Rules.rightOfHex(grid, coordinates.get(1)));
    }
}
