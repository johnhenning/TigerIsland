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

        grid = new Grid(69);
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(100,101));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.JUNGLE);

        tile = new Tile(coordinates,terrains);

        grid.placeTile(tile);

    }
    @Test
    public void GameStartedTest(){
        //TODO: THIS DOESN'T WORK
        //Rules.GameStarted(grid);
    }
}
