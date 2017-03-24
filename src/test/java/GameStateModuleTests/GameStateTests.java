package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.GameState;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */

//Done
public class GameStateTests {

    private GameState gameState;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<TerrainType> terrains;
    private Tile tile;

    @Before
    public void setup() throws Exception{
        gameState = new GameState();
        coordinates = new ArrayList<Coordinate>();
        terrains = new ArrayList<TerrainType>();

        coordinates.add(new Coordinate(1,1));
        coordinates.add(new Coordinate(1,2));
        coordinates.add(new Coordinate(2,2));

        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);

        tile = new Tile(coordinates,terrains);
    }

    @Test
    public void placeTileTest() throws Exception {
        gameState.placeTile(tile);
    }


    @Test
    public void placeMeepleTest() throws Exception {
        gameState.placeTile(tile);
        gameState.placeMeeple(coordinates.get(0));
    }

    @Test
    public void placeTotoroTest() throws Exception {
        gameState.placeTile(tile);
        gameState.placeTotoro(coordinates.get(0));
    }

}
