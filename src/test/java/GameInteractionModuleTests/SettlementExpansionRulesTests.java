package GameInteractionModuleTests;

import GameInteractionModule.Rules.SettlementExpansionRules;
import GameStateModule.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Kyle on 3/28/2017.
 */
public class SettlementExpansionRulesTests {
    GameState gameState;
    Grid gameBoard;
    static Tile tile;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    boolean exceptionThrown;
    private Player player1;

    @Before
    public void setup(){

        gameState = new GameState();
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();


        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(100,101));
        coordinates.add(new Coordinate(99,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        tile = new Tile(coordinates, terrains);


        ArrayList<Coordinate> coordinates2 = new ArrayList<Coordinate>();
        coordinates2.add(new Coordinate(102,102));
        coordinates2.add(new Coordinate(101,101));
        coordinates2.add(new Coordinate(101,102));

        ArrayList<TerrainType> terrains2 = new ArrayList<TerrainType>();
        terrains2.add(TerrainType.VOLCANO);
        terrains2.add(TerrainType.GRASSLAND);
        terrains2.add(TerrainType.GRASSLAND);

        tile2 = new Tile(coordinates2, terrains2);

        ArrayList<Coordinate> coordinates3 = new ArrayList<Coordinate>();
        coordinates3.add(new Coordinate(100,103));
        coordinates3.add(new Coordinate(99,103));
        coordinates3.add(new Coordinate(100,102));

        ArrayList<TerrainType> terrains3 = new ArrayList<TerrainType>();
        terrains3.add(TerrainType.VOLCANO);
        terrains3.add(TerrainType.LAKE);
        terrains3.add(TerrainType.ROCKY);

        tile3 = new Tile(coordinates3 ,terrains3);

        ArrayList<Coordinate> coordinates4 = new ArrayList<Coordinate>();
        coordinates4.add(new Coordinate(99,100));
        coordinates4.add(new Coordinate(98,100));
        coordinates4.add(new Coordinate(98,101));

        ArrayList<TerrainType> terrains4 = new ArrayList<TerrainType>();
        terrains4.add(TerrainType.VOLCANO);
        terrains4.add(TerrainType.GRASSLAND);
        terrains4.add(TerrainType.GRASSLAND);

        tile4 = new Tile(coordinates4, terrains4);

        ArrayList<Coordinate> coordinates5 = new ArrayList<Coordinate>();
        coordinates5.add(new Coordinate(120,120));
        coordinates5.add(new Coordinate(120,121));
        coordinates5.add(new Coordinate(119,121));

        ArrayList<TerrainType> terrains5 = new ArrayList<TerrainType>();
        terrains5.add(TerrainType.VOLCANO);
        terrains5.add(TerrainType.GRASSLAND);
        terrains5.add(TerrainType.GRASSLAND);

        tile5 = new Tile(coordinates5, terrains5);
    }

    @Test
    public void adjacentHexesTest(){
        gameBoard = new Grid(200);
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert !exceptionThrown;

        gameBoard.placeTile(tile2);

//        try {gameBoard.placeTile(tile2);}
//        catch (AssertionError e) { exceptionThrown = true; }

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.findAdjacentCoords(gameBoard, TerrainType.GRASSLAND, tile2.getCoords().get(2)));
//        assert !exceptionThrown;
        assert newCoordinates.size() == 2;

    }

    @Test
    public void containTest(){
        ArrayList<Coordinate> tempCoordList = new ArrayList<>();
        Coordinate coord = new Coordinate(127,128);
        tempCoordList.add(new Coordinate(125, 126));
        tempCoordList.add(coord);
        assert SettlementExpansionRules.contains(tempCoordList, coord);

    }


    @Test
    public void expansionDFSTest(){
        gameBoard = new Grid(200);
        ArrayList<Coordinate> settlementCoordinates = new ArrayList<>();
        ArrayList<Settlement> settlement1 = new ArrayList<>();

        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }

        settlementCoordinates.add(new Coordinate(100,101));
        settlementCoordinates.add(new Coordinate(99, 101));
        settlement1.add(new Settlement(settlementCoordinates, player1 , 0));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard,TerrainType.GRASSLAND,settlement1.get(0)));
        assert !exceptionThrown;
        assert newCoordinates.size() == 2;
    }

    @Test
    public void expansionDFSTest2(){
        gameBoard = new Grid(200);
        ArrayList<Coordinate> settlementCoordinates = new ArrayList<>();
        ArrayList<Settlement> settlement1 = new ArrayList<>();

        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }

        settlementCoordinates.add(new Coordinate(100,101));
        settlementCoordinates.add(new Coordinate(99, 101));
        settlement1.add(new Settlement(settlementCoordinates, player1, 0));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();

        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard,TerrainType.GRASSLAND,settlement1.get(0)));
        assert !exceptionThrown;
        assert newCoordinates.size() == 2;
    }

    @Test
    public void expansionDFSTest3(){
        gameBoard = new Grid(200);
        ArrayList<Coordinate> settlementCoordinates = new ArrayList<>();
        ArrayList<Settlement> settlement1 = new ArrayList<>();

        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile4);}
        catch (AssertionError e) { exceptionThrown = true; }

        settlementCoordinates.add(new Coordinate(100,101));
        settlementCoordinates.add(new Coordinate(99, 101));
        settlement1.add(new Settlement(settlementCoordinates, player1, 0));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();

        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard,TerrainType.GRASSLAND,settlement1.get(0)));
        assert !exceptionThrown;
        assert newCoordinates.size() == 4;
    }

    @Test
    public void expansionDFSTest4(){
        gameBoard = new Grid(200);
        ArrayList<Coordinate> settlementCoordinates = new ArrayList<>();
        ArrayList<Settlement> settlement1 = new ArrayList<>();

        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile4);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile5);}
        catch (AssertionError e) { exceptionThrown = true; }

        settlementCoordinates.add(new Coordinate(100,101));
        settlementCoordinates.add(new Coordinate(99, 101));
        settlement1.add(new Settlement(settlementCoordinates, player1, 0));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();

        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard,TerrainType.GRASSLAND,settlement1.get(0)));
        assert exceptionThrown;//invalid tile was placed so should be true
        assert newCoordinates.size() == 4;
    }

    @Test
    public void expandSettlmentToOccupiedHexTest(){
        ArrayList<Coordinate> coords = new ArrayList<>();
        ArrayList<TerrainType> terrains = new ArrayList<>();

        coords.add(new Coordinate(98, 100));
        coords.add(new Coordinate(98, 99));
        coords.add(new Coordinate(99, 100));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);
        terrains.add(TerrainType.LAKE);

        Tile tile = new Tile(coords,terrains);
        gameState.placeTile(tile);

        ArrayList<Coordinate> coords2 = new ArrayList<>();
        ArrayList<TerrainType> terrains2 = new ArrayList<>();

        coords2.add(new Coordinate(98, 97));
        coords2.add(new Coordinate(99, 97));
        coords2.add(new Coordinate(99, 98));

        terrains2.add(TerrainType.VOLCANO);
        terrains2.add(TerrainType.JUNGLE);
        terrains2.add(TerrainType.JUNGLE);

        Tile tile2 = new Tile(coords2, terrains2);
        gameState.placeTile(tile2);

        gameState.switchPlayer();
        gameState.foundSettlement(new Coordinate(99,97), gameState.getCurrentPlayer());

        gameState.switchPlayer();
        gameState.foundSettlement(new Coordinate(99,99), gameState.getCurrentPlayer());
        gameState.expandSettlement(new Coordinate(99,99), gameState.getCurrentPlayer(),TerrainType.JUNGLE);
        assert gameState.getSettlementList().size() == 2;





    }
}
