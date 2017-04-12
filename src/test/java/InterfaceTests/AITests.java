package InterfaceTests;


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
    private AI ai;
    GameClient gameC;

    @Before
    public void setup() {
        ai = new AI();
        gameState = new GameState();
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
    public void aiPlaceTotoroTest(){
        setupHexAndTilesOnGameState(gameState);
        gameState.foundSettlement(new Coordinate(98,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98,98),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98,101),gameState.getCurrentPlayer());

        BuildMove buildMove = ai.calculateBuildMove(null, gameState);
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
