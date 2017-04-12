package InterfaceTests;


import GameInteractionModule.Turn;
import GameStateModule.*;
import IOModule.AI;
import IOModule.Message;
import ServerModule.Adapter;
import ServerModule.GameClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by Kyle on 4/8/2017.
 */


public class AITests {
    private GameState gameState;
    private GameState gameState2;
    private AI ai;
    private AI ai2;
//    GameClient gameC;
    Player player1;
    Player player2;
    private boolean exceptionThrown;

    @Before
    public void setup() {
        ai = new AI();
        ai2 = new AI();
        gameState = new GameState();
        gameState2 = new GameState();
        player1 = new Player();
        player2 = new Player();
        //int portNumber = 2222;
        //GameClient gameC = new GameClient("localhost",portNumber);
    }
    @Test
    public void bottomRightFunctionTest(){
        Hex h = ai.calculateBottomRightMostHex(gameState.getGameboard().getPlacedTiles());
        assert h.getCoordinate().getX() == 100;
        assert h.getCoordinate().getY() == 101;
    }

    @Test
    public void bottomRightFunctionExpanded(){
        setupHexAndTilesOnGameState(gameState);
        Hex h = ai.calculateBottomRightMostHex(gameState.getGameboard().getPlacedTiles());
        assert h.getCoordinate().getX() == 102;
        assert h.getCoordinate().getY() == 102;

    }

    @Test
    public void getPlayerSettlementsLessThanFiveTest(){
//        Adapter ad = new Adapter(gameC);
//        setupHexAndTilesOnGameState(gameState);
//        Message message = ad.getAITileInfo("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 0 PLACE ROCK+ROCK");
//        ai.completeTurn(message, gameState);
//        ad.sendAIMove(message, "MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 0 PLACE ROCK+ROCK");
//
//        Message message2 = ad.getAITileInfo("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 0 PLACE JUNGLE+GRASS");
//        ai.completeTurn(message2, gameState);
//        ad.sendAIMove(message2, "MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 0 PLACE JUNGLE+GRASS");
//
//        message = ad.getAITileInfo("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 1 PLACE JUNGLE+LAKE");
//        ai.completeTurn(message, gameState);
//        ad.sendAIMove(message, "MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 1 PLACE JUNGLE+LAKE");

        setupHexAndTilesOnGameState(gameState);
        //gameState.switchPlayer();
        gameState.foundSettlement(new Coordinate(98, 98), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98, 101), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99, 100), gameState.getCurrentPlayer());
        //gameState.foundSettlement(new Coordinate(99, 101), gameState.getCurrentPlayer());
        ArrayList<Settlement> pSettlements = new ArrayList<>();

        gameState.switchPlayer();

        gameState.foundSettlement(new Coordinate(102, 98), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102, 100), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(100, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101, 100), gameState.getCurrentPlayer());

        gameState.switchPlayer();
        pSettlements = ai.getPlayerSettlementsLessThanFive(gameState);
        assert pSettlements.size() == 1;

    }
    @Test
    public void getHexesAdjacentToSettlementLessThanFiveTest(){
        setupHexAndTilesOnGameState(gameState);
        //gameState.switchPlayer();
        gameState.foundSettlement(new Coordinate(98, 98), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98, 101), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99, 100), gameState.getCurrentPlayer());
        //gameState.foundSettlement(new Coordinate(99, 101), gameState.getCurrentPlayer());

        gameState.switchPlayer();

        gameState.foundSettlement(new Coordinate(102, 98), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102, 100), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(100, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101, 100), gameState.getCurrentPlayer());

        gameState.switchPlayer();

        ArrayList<Hex> hexesAdjacent = new ArrayList<>();
        ArrayList<Settlement> pSettlements = new ArrayList<>();
        pSettlements = ai.getPlayerSettlementsLessThanFive(gameState);
        hexesAdjacent = ai.getHexesAdjacentToSettlements(pSettlements, gameState);
        assert hexesAdjacent.size() == 2;
    }

    @Test
    public void aiPlaceOptimalSettlmentTest(){
        setupHexAndTilesOnGameState(gameState);
        gameState.foundSettlement(new Coordinate(98,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98,98),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(100,101),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101,102),gameState.getCurrentPlayer());

        BuildMove buildMove = ai.calculateBuildMove(null, gameState);
        assert buildMove != null;
    }
    @Test
    public void aiPlaceTotoroTest(){
        setupHexAndTilesOnGameState(gameState);
        gameState.foundSettlement(new Coordinate(98,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98,98),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,101),gameState.getCurrentPlayer());

        gameState.foundSettlement(new Coordinate(101,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102,100),gameState.getCurrentPlayer());

        BuildMove buildMove = ai.calculateBuildMove(null, gameState);
        assert buildMove != null;
    }

    @Test
    public void aiPlaceTotoroTest2(){
        setupHexAndTilesOnGameState(gameState);
        gameState.foundSettlement(new Coordinate(98,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98,98),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,101),gameState.getCurrentPlayer());
        gameState.placeTotoro(new Coordinate(98, 101));

        gameState.foundSettlement(new Coordinate(101,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102,100),gameState.getCurrentPlayer());

        BuildMove buildMove = ai.calculateBuildMove(null, gameState);
        assert buildMove != null;
    }

    @Test
    public void aiBuildMoveTests(){

        boolean exceptionThrown = false;
        ArrayList<Coordinate> hexesCoord = new ArrayList<>();
        hexesCoord.add(new Coordinate(100, 103));
        hexesCoord.add(new Coordinate(101, 103));
        hexesCoord.add(new Coordinate(101, 102));


        ArrayList<TerrainType> terrains1 = new ArrayList<>();

        terrains1.add(TerrainType.VOLCANO);
        terrains1.add(TerrainType.LAKE);
        terrains1.add(TerrainType.GRASS);

        Tile tile= new Tile(terrains1);
        ArrayList<Coordinate> tilePlacement = ai.calculateTilePlacement(gameState);
        tile.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile, gameState);
        BuildMove buildMove = ai.calculateBuildMove(tile, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile2= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile2.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile2, gameState);
        buildMove = ai.calculateBuildMove(tile2, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile3= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile3.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile3, gameState);
        buildMove = ai.calculateBuildMove(tile3, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile4= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile4.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile4, gameState);
        buildMove = ai.calculateBuildMove(tile4, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile5= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile5.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile5, gameState);
        buildMove = ai.calculateBuildMove(tile5, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile6= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile6.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile6, gameState);
        buildMove = ai.calculateBuildMove(tile6, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile7= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile7.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile7, gameState);
        buildMove = ai.calculateBuildMove(tile7, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile8= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile8.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile8, gameState);
        buildMove = ai.calculateBuildMove(tile8, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile9= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile9.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile9, gameState);
        buildMove = ai.calculateBuildMove(tile9, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile10= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile10.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile10, gameState);
        buildMove = ai.calculateBuildMove(tile10, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile11= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile11.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile11, gameState);
        buildMove = ai.calculateBuildMove(tile11, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        Tile tile12= new Tile(terrains1);
        tilePlacement = ai.calculateTilePlacement(gameState);
        tile12.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile12, gameState);
        buildMove = ai.calculateBuildMove(tile12, gameState);
        Turn.makeBuildMove(buildMove, gameState);
        gameState.switchPlayer();

        assert buildMove != null;
    }

    public void setupHexAndTilesOnGameState(GameState game) {

        boolean exceptionThrown = false;

        ArrayList<Coordinate> hexesCoord = new ArrayList<>();
        hexesCoord.add(new Coordinate(101, 99));
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

        Tile tile1 = new Tile(hexesCoord, terrains1);
        Tile tile2 = new Tile(hexesCoord2, terrains2);
        Tile tile3 = new Tile(hexesCoord3, terrains3);
        Tile tile4 = new Tile(hexesCoord4, terrains4);
        Tile tile5 = new Tile(hexesCoord5, terrains5);

        try {
            game.placeTile(tile1);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.placeTile(tile2);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.placeTile(tile3);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.placeTile(tile4);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.placeTile(tile5);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;


    }
}