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

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();


        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(100,101));
        coordinates.add(new Coordinate(99,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);
        Hex[] hexes = new Hex[3];
        hexes[0] = new Hex(coordinates.get(0), terrains.get(0));
        hexes[1] = new Hex(coordinates.get(1), terrains.get(1));
        hexes[2] = new Hex(coordinates.get(2), terrains.get(2));
        tile = new Tile(hexes);


        ArrayList<Coordinate> coordinates2 = new ArrayList<Coordinate>();
        coordinates2.add(new Coordinate(102,102));
        coordinates2.add(new Coordinate(101,101));
        coordinates2.add(new Coordinate(101,102));

        ArrayList<TerrainType> terrains2 = new ArrayList<TerrainType>();
        terrains2.add(TerrainType.VOLCANO);
        terrains2.add(TerrainType.GRASSLAND);
        terrains2.add(TerrainType.GRASSLAND);
        Hex[] hexes2 = new Hex[3];
        hexes2[0] = new Hex(new Coordinate(102,102), terrains2.get(0));
        hexes2[1] = new Hex(new Coordinate(101,101), terrains2.get(1));
        hexes2[2] = new Hex(new Coordinate(101,102), terrains2.get(2));
        tile2 = new Tile(hexes2);

        ArrayList<Coordinate> coordinates3 = new ArrayList<Coordinate>();
        coordinates3.add(new Coordinate(100,103));
        coordinates3.add(new Coordinate(99,103));
        coordinates3.add(new Coordinate(100,102));

        ArrayList<TerrainType> terrains3 = new ArrayList<TerrainType>();
        terrains3.add(TerrainType.VOLCANO);
        terrains3.add(TerrainType.LAKE);
        terrains3.add(TerrainType.ROCKY);
          Hex[] hexes3 = new Hex[3];
        hexes3[0] = new Hex(coordinates3.get(0), terrains3.get(0));
        hexes3[1] = new Hex(coordinates3.get(1), terrains3.get(1));
        hexes3[2] = new Hex(coordinates3.get(2), terrains3.get(2));

        tile3 = new Tile(hexes3);

        ArrayList<Coordinate> coordinates4 = new ArrayList<Coordinate>();
        coordinates4.add(new Coordinate(99,100));
        coordinates4.add(new Coordinate(98,100));
        coordinates4.add(new Coordinate(98,101));

        ArrayList<TerrainType> terrains4 = new ArrayList<TerrainType>();
        terrains4.add(TerrainType.VOLCANO);
        terrains4.add(TerrainType.GRASSLAND);
        terrains4.add(TerrainType.GRASSLAND);
          Hex[] hexes4 = new Hex[3];
        hexes4[0] = new Hex(coordinates4.get(0), terrains4.get(0));
        hexes4[1] = new Hex(coordinates4.get(1), terrains4.get(1));
        hexes4[2] = new Hex(coordinates4.get(2), terrains4.get(2));

        tile4 = new Tile(hexes4);

        ArrayList<Coordinate> coordinates5 = new ArrayList<Coordinate>();
        coordinates5.add(new Coordinate(120,120));
        coordinates5.add(new Coordinate(120,121));
        coordinates5.add(new Coordinate(119,121));

        ArrayList<TerrainType> terrains5 = new ArrayList<TerrainType>();
        terrains5.add(TerrainType.VOLCANO);
        terrains5.add(TerrainType.GRASSLAND);
        terrains5.add(TerrainType.GRASSLAND);
        Hex[] hexes5 = new Hex[3];
        hexes5[0] = new Hex(coordinates5.get(0), terrains5.get(0));
        hexes5[1] = new Hex(coordinates5.get(1), terrains5.get(1));
        hexes5[2] = new Hex(coordinates5.get(2), terrains5.get(2));

        tile5 = new Tile(hexes5);
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
        newCoordinates.addAll(SettlementExpansionRules.findAdjacentCoords(gameBoard.getGameboard(), TerrainType.GRASSLAND, tile2.getCoords().get(2)));
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
        settlement1.add(new Settlement(settlementCoordinates, player1));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard.getGameboard(),TerrainType.GRASSLAND,settlement1.get(0)));
        assert !exceptionThrown;
        assert newCoordinates.size() == 4;
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
        settlement1.add(new Settlement(settlementCoordinates, player1));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard.getGameboard(),TerrainType.GRASSLAND,settlement1.get(0)));
        assert !exceptionThrown;
        assert newCoordinates.size() == 4;
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
        settlement1.add(new Settlement(settlementCoordinates, player1));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard.getGameboard(),TerrainType.GRASSLAND,settlement1.get(0)));
        assert !exceptionThrown;
        assert newCoordinates.size() == 6;
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
        settlement1.add(new Settlement(settlementCoordinates, player1));

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.expansionDFS(gameBoard.getGameboard(),TerrainType.GRASSLAND,settlement1.get(0)));
        assert exceptionThrown;//invalid tile was placed so should be true
        assert newCoordinates.size() == 6;
    }
}
