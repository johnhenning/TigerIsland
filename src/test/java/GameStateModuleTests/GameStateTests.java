package GameStateModuleTests;

import GameStateModule.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */


public class GameStateTests {

    private GameState gameState;
    private ArrayList<Coordinate> coordinates, coordinates2;
    private ArrayList<TerrainType> terrains, terrains2;
    private Tile tile, tile2;
    private Player player1;

    @Before
    public void setup() throws Exception{
        gameState = new GameState();
        coordinates = new ArrayList<>();
        coordinates2 = new ArrayList<>();
        terrains = new ArrayList<>();
        terrains2 = new ArrayList<>();
        player1 = new Player();
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(101,102));
        coordinates.add(new Coordinate(102,102));

        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.VOLCANO);

        tile = new Tile(coordinates,terrains);

        coordinates2.add(new Coordinate(102,100));
        coordinates2.add(new Coordinate(101,100));
        coordinates2.add(new Coordinate(101,99));

        terrains2.add(TerrainType.GRASSLAND);
        terrains2.add(TerrainType.LAKE);
        terrains2.add(TerrainType.VOLCANO);

        tile2 = new Tile(coordinates2, terrains2);

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
        //TODO: needs to be looked at
        assert false;
//        gameState.placeTile(tile);
//        gameState.placeTotoro(coordinates.get(0));
    }

    @Test
    public void settlementFoundationTest() throws Exception {
        gameState.placeTile(tile);
        gameState.foundSettlement(coordinates.get(0), player1);
    }

    @Test
    public void settlementMergeTest() throws Exception {
        gameState.placeTile(tile);
        gameState.foundSettlement(coordinates.get(0), player1);
        gameState.foundSettlement(coordinates.get(1), player1);
        assert  gameState.getSettlementList().size() == 1;
    }

    @Test
    public void settlementExpansionTest() throws Exception{
        gameState.placeTile(tile);
        gameState.placeTile(tile2);
        gameState.foundSettlement(coordinates.get(0), player1);
        gameState.foundSettlement(coordinates2.get(1),player1);
        gameState.expandSettlement(coordinates.get(0),player1,TerrainType.GRASSLAND);
        assert gameState.getSettlementList().get(0).getSize() == 5;

    }
    @Test
    public void switchPlayerTest() throws Exception {
        Player originalPlayer = gameState.getCurrentPlayer();
        gameState.switchPlayer();
        assert gameState.getCurrentPlayer() != originalPlayer;
    }
}
