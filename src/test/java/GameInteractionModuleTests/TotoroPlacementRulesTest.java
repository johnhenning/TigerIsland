package GameInteractionModuleTests;

import GameInteractionModule.Rules.SettlementExpansionRules;
import GameInteractionModule.Rules.TotoroBuildRules;
import GameStateModule.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by jslocke on 4/2/17.
 */
public class TotoroPlacementRulesTest{
    GameState gameState;
    static Tile tile1;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    boolean exceptionThrown;
    private Player player1;

    @Before
    public void setup() throws Exception {
        gameState = new GameState();

        setupHexAndTilesOnGameState(gameState);

        player1 = new Player();
        gameState.foundSettlement(new Coordinate(100,101),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(100,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99,99),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101,100),gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101,101),gameState.getCurrentPlayer());

    }

//    @Test
//    public void placeTotoroNotValid(){
//        gameState.placeTotoro(new Coordinate(100, 99));
//    }

    @Test
    public void isHexAdjacentToSettlementTest(){
       assert TotoroBuildRules.isHexAdjacentToSettlement(tile1.getHexes().get(2), gameState);
    }

    @Test
    public void coordinateInSettlementTest(){
       assert TotoroBuildRules.CoordinateIsPartOfSettlement(gameState.getSettlementList().get(0).getSettlementCoordinates(), new Coordinate(99, 99));
    }

    @Test
    public void settlementIsGreaterThanFiveTest(){
        assert TotoroBuildRules.SettlementsGreaterThanFive(gameState.getSettlementList()).size() == 1;
    }

    @Test
    public void playerHasSizeFiveSettlementTest(){
        assert TotoroBuildRules.playerHasSizeFiveSettlement(gameState.getCurrentPlayer(), gameState);
    }

    @Test
    public void isTotoroNotInSettlementTest(){
        assert !TotoroBuildRules.isTotoroInSettlement(gameState.getSettlementList().get(0).getSettlementCoordinates(), gameState);
    }

    @Test
    public void isTotoroInSettlementTest(){
        Hex hex = gameState.getHex(gameState.getSettlementList().get(0).getSettlementCoordinates().get(0));
        hex.addTotoro();
        assert TotoroBuildRules.isTotoroInSettlement(gameState.getSettlementList().get(0).getSettlementCoordinates(), gameState);
    }

    @Test
    public void settlementNotContainTotoroTest(){
        assert TotoroBuildRules.settlementNotContainTotoro(gameState);
    }

    @Test
    public void settlementContainTotoroTest(){
        Hex hex = gameState.getHex(gameState.getSettlementList().get(0).getSettlementCoordinates().get(0));
        hex.addTotoro();
        assert !TotoroBuildRules.settlementNotContainTotoro(gameState);
    }

    @Test
    public void isValidTotoroLocationTest(){
        assert TotoroBuildRules.isValidTotoroLocation(tile1.getHexes().get(2),gameState.getCurrentPlayer(), gameState);
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
        terrains1.add(TerrainType.GRASSLAND);

        terrains2.add(TerrainType.VOLCANO);
        terrains2.add(TerrainType.GRASSLAND);
        terrains2.add(TerrainType.GRASSLAND);

        terrains3.add(TerrainType.VOLCANO);
        terrains3.add(TerrainType.GRASSLAND);
        terrains3.add(TerrainType.GRASSLAND);

        terrains4.add(TerrainType.VOLCANO);
        terrains4.add(TerrainType.JUNGLE);
        terrains4.add(TerrainType.ROCKY);

        terrains5.add(TerrainType.VOLCANO);
        terrains5.add(TerrainType.JUNGLE);
        terrains5.add(TerrainType.JUNGLE);

        tile1 = new Tile(hexesCoord, terrains1);
        tile2 = new Tile(hexesCoord2, terrains2);
        tile3 = new Tile(hexesCoord3, terrains3);
        tile4 = new Tile(hexesCoord4, terrains4);
        tile5 = new Tile(hexesCoord5, terrains5);


        try {game.placeTile(tile1);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {game.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {game.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {game.placeTile(tile4);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {game.placeTile(tile5);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
    }

}