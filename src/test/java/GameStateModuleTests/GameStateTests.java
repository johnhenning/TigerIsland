package GameStateModuleTests;

import GameStateModule.*;
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
    private Player player1;

    @Before
    public void setup() throws Exception{
        gameState = new GameState();
        coordinates = new ArrayList<Coordinate>();
        terrains = new ArrayList<TerrainType>();
        player1 = new Player();
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

    @Test
    public void settlementFoundationTest() throws Exception{
        gameState.placeTile(tile);
        gameState.foundSettlement(coordinates.get(0), player1);
    }

    @Test
    public void settlementMergeTest() throws Exception{
        gameState.placeTile(tile);
        gameState.foundSettlement(coordinates.get(0), player1);
        gameState.foundSettlement(coordinates.get(2), player1);
        assert  gameState.getSettlementList().size() == 1;
    }

}
