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
    static Tile tile;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    boolean exceptionThrown;
    private Player player1;

    @Before
    public void setup() throws Exception {
        gameState = new GameState();
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();


        setupHexAndTilesOnGameState(gameState);

        player1 = new Player();
        gameState.foundSettlement(new Coordinate(100,101),player1);
        gameState.foundSettlement(new Coordinate(101,101),player1);
        gameState.foundSettlement(new Coordinate(101,102),player1);
        gameState.foundSettlement(new Coordinate(101,100),player1);
        gameState.foundSettlement(new Coordinate(100,102),player1);

    }

    @Test
    public void isHexAdjacentToSettlementTest(){
       assert TotoroBuildRules.isHexAdjacentToSettlement(tile.getHexes().get(2), gameState);
    }

    @Test
    public void playerHasSizeFiveSettlementTest(){
        assert TotoroBuildRules.playerHasSizeFiveSettlement(player1, gameState);
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
        assert TotoroBuildRules.isValidTotoroLocation(tile.getHexes().get(2),player1, gameState);
    }



    public  void setupHexAndTilesOnGameState(GameState game){
//        Hex[] hexes1 = new Hex[3];
//        Hex[] hexes2 = new Hex[3];
//        Hex[] hexes3 = new Hex[3];
//        Hex[] hexes4 = new Hex[3];
//        Hex[] hexes5 = new Hex[3];

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

        Tile tile1 = new Tile(hexesCoord, terrains1);
        Tile tile2 = new Tile(hexesCoord2, terrains2);
        Tile tile3 = new Tile(hexesCoord3, terrains3);
        Tile tile4 = new Tile(hexesCoord4, terrains4);
        Tile tile5 = new Tile(hexesCoord5, terrains5);


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