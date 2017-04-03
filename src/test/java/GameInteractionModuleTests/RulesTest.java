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

        coordinates = new ArrayList<Coordinate>();
        terrains = new ArrayList<TerrainType>();

        grid = new Grid(420);
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(100,101));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.JUNGLE);
        Hex[] hexes = new Hex[3];
        hexes[0] = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
        hexes[1] = new Hex(new Coordinate(101,101), TerrainType.GRASSLAND);
        hexes[2] = new Hex(new Coordinate(100,101), TerrainType.LAKE);

        tile = new Tile(hexes);

        grid.placeTile(tile);

    }
    @Test
    public void GameStartedTest(){
        assert Rules.GameStarted(grid) == true;
    }
}
