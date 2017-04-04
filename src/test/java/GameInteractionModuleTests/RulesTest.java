package GameInteractionModuleTests;

import GameInteractionModule.Rules.Rules;
import GameStateModule.*;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by KWong on 3/28/2017.
 */
public class RulesTest {

    private Grid grid;
    private Hex[][] gameboard;
    private ArrayList<Tile> placedTiles;
    private Tile tile;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<TerrainType> terrains;


    @Before
    public void setup(){

        coordinates = new ArrayList<>();
        terrains = new ArrayList<>();

        grid = new Grid(420);
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(99,101));
        coordinates.add(new Coordinate(100,101));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.JUNGLE);
        Hex[] hexes = new Hex[3];
        hexes[0] = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
        hexes[1] = new Hex(new Coordinate(99,101), TerrainType.GRASSLAND);
        hexes[2] = new Hex(new Coordinate(100,101), TerrainType.LAKE);

        tile = new Tile(hexes);

        grid.placeTile(tile);
        gameboard = Grid.getGameboard();

    }
    @Test
    public void GameStartedTest(){
        assert Rules.GameStarted(grid) == true;
    }

    @Test
    public void checkIfHexEmptyTest(){
        assert Rules.CheckIfHexEmpty(new Hex(new Coordinate(99, 100), TerrainType.LAKE), gameboard) == true;
    }

    @Test
    public void HexesAreAdjacentTest(){
        assert Rules.HexesAreAdjacent(tile.getHexes()[0], tile.getHexes()[1]);
    }

    @Test
    public void HexesAreNotAdjacentTest(){
        assert !Rules.HexesAreAdjacent(tile.getHexes()[0], new Hex(new Coordinate(101,101), TerrainType.GRASSLAND));
    }

    @Test
    public void downRightTest(){
        assert coordinates.get(2).getX() == 100;
        assert coordinates.get(2).getY() == 101;
        assert tile.getHexes()[2].equals(Rules.downRight(gameboard, coordinates.get(0)));
    }

    @Test
    public void downLeftTest(){
        assert coordinates.get(1).getX() == 99;
        assert coordinates.get(1).getY() == 101;
        assert tile.getHexes()[1].equals(Rules.downLeft(gameboard, coordinates.get(0)));
    }

    @Test
    public void topRightTest(){
        assert coordinates.get(0).getX() == 100;
        assert coordinates.get(0).getY() == 100;
        assert tile.getHexes()[0].equals(Rules.topRight(gameboard, coordinates.get(1)));
    }

    @Test
    public void topLeftTest(){
        assert coordinates.get(0).getX() == 100;
        assert coordinates.get(0).getY() == 100;
        assert tile.getHexes()[0].equals(Rules.topLeft(gameboard, coordinates.get(2)));
    }

    @Test
    public void leftOfHexTest(){
        assert coordinates.get(1).getX() == 99;
        assert coordinates.get(1).getY() == 101;
        assert tile.getHexes()[1].equals(Rules.leftOfHex(gameboard, coordinates.get(2)));
    }

    @Test
    public void rightOfHexTest(){
        assert coordinates.get(2).getX() == 100;
        assert coordinates.get(2).getY() == 101;
        assert tile.getHexes()[2].equals(Rules.rightOfHex(gameboard, coordinates.get(1)));
    }
}
