package GameInteractionModuleTests;

import GameInteractionModule.Rules.TigerBuildRules;
import GameStateModule.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Daniel002 on 4/2/2017.
 */
public class TigerPlacementTest {
    static Tile tile1;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
     Tile tile5;
    boolean exceptionThrown;
    private Player player1;
    GameState gameStateObj;

    @Before
    public void setup() throws Exception {
        gameStateObj = new GameState();

        setupHexAndTilesOnGameState(gameStateObj);

        player1 = new Player();
        gameStateObj.foundSettlement(new Coordinate(100, 101), player1);
        gameStateObj.foundSettlement(new Coordinate(100, 99), player1);
        gameStateObj.foundSettlement(new Coordinate(99, 99), player1);
        gameStateObj.foundSettlement(new Coordinate(101, 100), player1);
        gameStateObj.foundSettlement(new Coordinate(101, 101), player1);
        tile1.getHexes().get(2).setLevel(3);
    }


    @Test
    public void coordinateInSettlement(){
        Coordinate testCoord = new Coordinate(123, 132);
        Coordinate testCoord1 = new Coordinate(121, 105);
        ArrayList<Coordinate> containList = new ArrayList<>();
        containList.add(testCoord);
        containList.add(testCoord1);
        assert TigerBuildRules.coordinateInSettlement(containList, testCoord);
    }

    @Test
    public void isTigerInSettlementTest(){
        tile1.getHexes().get(2).addTiger();
        assert TigerBuildRules.isTigerInSettlement(tile1.getCoords());
    }

    @Test
    public void checkIfHexAdjacentToSettlementTest(){ assert TigerBuildRules.checkIfHexAdjacentToSettlement(tile5.getHexes().get(1)); }

    @Test
    public void checkIfHexLevelAtleastThreeTest(){
        assert TigerBuildRules.hexLevelAtLeastThree(tile1.getHexes().get(2));
    }

    @Test
    public void checkIfTigerNotInSettlementTest(){
        assert TigerBuildRules.settlementNotContainTiger();
    }

    @Test
    public void checkIfTigerInSettlementTest(){
        Hex hex = gameStateObj.getHex(gameStateObj.getSettlementList().get(0).getSettlementCoordinates().get(0));
        hex.addTiger();
        assert !TigerBuildRules.settlementNotContainTiger();
    }


    @Test
    public void canPlaceTigerTest(){
        assert TigerBuildRules.canPlaceTiger(tile1.getHexes().get(2));
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

