package GameStateModuleTests;

import GameStateModule.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */


public class GameStateTests {

    boolean exceptionThrown;
    private GameState gameState;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<TerrainType> terrains;
    private Tile tile;
    private Tile tile1;
    private Tile tile2;
    private Tile tile3;
    private Tile tile4;
    private Tile tile5;
    private Player player1;

    @Before
    public void setup() throws Exception{
        gameState = new GameState();
        coordinates = new ArrayList<>();
        terrains = new ArrayList<>();
        player1 = new Player();
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(101,102));
        coordinates.add(new Coordinate(102,102));

        terrains.add(TerrainType.GRASS);
        terrains.add(TerrainType.GRASS);
        terrains.add(TerrainType.VOLCANO);

        setupHexAndTilesOnGameState(gameState);
        tile = new Tile(coordinates,terrains);

    }

    @Test
    public void placeTileTest() throws Exception {
        gameState.placeTile(tile1);
    }


    @Test
    public void placeMeepleTest() throws Exception {
        gameState.placeTile(tile1);
        gameState.placeMeeple(tile1.getCoords().get(1));
    }

    @Test
    public void placeTotoroTest() throws Exception {
          gameState.placeTile(tile1);
          gameState.placeTile(tile2);
          gameState.placeTile(tile3);
          gameState.placeTile(tile4);
          gameState.placeTile(tile5);
        gameState.foundSettlement(tile1.getCoords().get(1), gameState.getCurrentPlayer(), false);
        gameState.foundSettlement(tile1.getCoords().get(2), gameState.getCurrentPlayer(), false);
        gameState.foundSettlement(tile2.getCoords().get(1), gameState.getCurrentPlayer(), false);
        gameState.foundSettlement(tile2.getCoords().get(2), gameState.getCurrentPlayer(), false);
        gameState.foundSettlement(tile3.getCoords().get(2), gameState.getCurrentPlayer(), false);
          gameState.placeTotoro(tile3.getCoords().get(1));
    }

    @Test
    public void settlementFoundationTest() throws Exception {
        gameState.placeTile(tile1);
        gameState.foundSettlement(tile1.getCoords().get(1), player1, false);
    }

    @Test
    public void shangrilaFoundationTest() throws Exception {
        gameState.placeTile(tile1);
        gameState.foundSettlement(tile1.getCoords().get(1), player1, true);

        ArrayList<Settlement> settlements = gameState.getSettlementList();
        Assert.assertEquals(true, settlements.get(settlements.size() - 1).HasShaman());
    }

    @Test
    public void settlementMergeTest() throws Exception {
        gameState.placeTile(tile1);
        gameState.foundSettlement(tile1.getCoords().get(1), player1, false);
        assert  gameState.getSettlementList().size() == 1;

        gameState.placeTile(tile2);
        gameState.placeTile(tile3);
        gameState.foundSettlement(new Coordinate(99, 99), player1, false);
        gameState.foundSettlement(new Coordinate(100, 99), player1, false);
        gameState.foundSettlement(new Coordinate(100, 101), player1, false);
        gameState.foundSettlement(new Coordinate(101, 101), player1, false);
        //tile1.getHexes().get(2).setLevel(3);

        gameState.foundSettlement(new Coordinate(102, 98), player1, false);
        gameState.foundSettlement(new Coordinate(102, 99), player1, false);

        assert gameState.getSettlementList().size() == 2;

    }

    @Test
    public void settlmentExpansionTest() throws Exception{
        gameState.placeTile(tile1);
        gameState.placeTile(tile2);
        gameState.foundSettlement(tile1.getCoords().get(2), player1, false);
        player1.removeMeeple(17);
        try {gameState.expandSettlement(tile1.getCoords().get(2), player1, TerrainType.GRASS);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert exceptionThrown;

    }

    @Test
    public void switchPlayerTest() throws Exception {
        Player originalPlayer = gameState.getCurrentPlayer();
        gameState.switchPlayer();
        assert gameState.getCurrentPlayer() != originalPlayer;
    }

    @Test
    public void tilePlaceTest(){
        gameState.placeTile(tile1);
        gameState.placeTile(tile2);
        gameState.placeTile(tile3);
        gameState.placeTile(tile4);


        ArrayList<Coordinate> hexesCoord6 = new ArrayList<>();
        hexesCoord6.add(new Coordinate(105, 104));
        hexesCoord6.add(new Coordinate(105, 105));
        hexesCoord6.add(new Coordinate(104, 105));

        ArrayList<TerrainType> terrains6 = new ArrayList<>();
        terrains6.add(TerrainType.VOLCANO);
        terrains6.add(TerrainType.JUNGLE);
        terrains6.add(TerrainType.JUNGLE);
        Tile tile6 = new Tile(hexesCoord6, terrains6);

        assert !gameState.placeTile(tile6);


        Hex h = gameState.getHex(hexesCoord6.get(0));
        assert h == null;
    }

    public  void setupHexAndTilesOnGameState(GameState game){


        ArrayList<Coordinate> hexesCoord = new ArrayList<>();
        hexesCoord.add( new Coordinate(101, 99));
        hexesCoord.add(new Coordinate(101, 100));
        hexesCoord.add(new Coordinate(102, 100));
        ArrayList<Coordinate> hexesCoord2 = new ArrayList<>();
        hexesCoord2.add(new Coordinate(102, 102));
        hexesCoord2.add(new Coordinate(101, 101));
        hexesCoord2.add(new Coordinate(101, 102));
        ArrayList<Coordinate> hexesCoord3 = new ArrayList<>();
        hexesCoord3.add(new Coordinate(103, 98));
        hexesCoord3.add(new Coordinate(102, 98));
        hexesCoord3.add(new Coordinate(102, 99));
        ArrayList<Coordinate> hexesCoord4 = new ArrayList<>();
        hexesCoord4.add(new Coordinate(98, 100));
        hexesCoord4.add(new Coordinate(99, 100));
        hexesCoord4.add(new Coordinate(98, 101));
        ArrayList<Coordinate> hexesCoord5 = new ArrayList<>();
        hexesCoord5.add(new Coordinate(99, 98));
        hexesCoord5.add(new Coordinate(98, 99));
        hexesCoord5.add(new Coordinate(98, 98));

        ArrayList<TerrainType> terrains1 = new ArrayList<>();
        ArrayList<TerrainType> terrains2 = new ArrayList<>();
        ArrayList<TerrainType> terrains3 = new ArrayList<>();
        ArrayList<TerrainType> terrains4 = new ArrayList<>();
        ArrayList<TerrainType> terrains5 = new ArrayList<>();

        terrains1.add(TerrainType.VOLCANO);
        terrains1.add(TerrainType.LAKE);
        terrains1.add(TerrainType.GRASS);

        terrains2.add(TerrainType.VOLCANO);
        terrains2.add(TerrainType.GRASS);
        terrains2.add(TerrainType.GRASS);

        terrains3.add(TerrainType.VOLCANO);
        terrains3.add(TerrainType.GRASS);
        terrains3.add(TerrainType.GRASS);

        terrains4.add(TerrainType.VOLCANO);
        terrains4.add(TerrainType.JUNGLE);
        terrains4.add(TerrainType.ROCK);

        terrains5.add(TerrainType.VOLCANO);
        terrains5.add(TerrainType.JUNGLE);
        terrains5.add(TerrainType.JUNGLE);

        tile1 = new Tile(hexesCoord, terrains1);
        tile2 = new Tile(hexesCoord2, terrains2);
        tile3 = new Tile(hexesCoord3, terrains3);
        tile4 = new Tile(hexesCoord4, terrains4);
        tile5 = new Tile(hexesCoord5, terrains5);


    }
}
