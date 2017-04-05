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
    static Tile tile5;
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
        tile1.getHexes()[2].setLevel(3);
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
        tile1.getHexes()[1].addTiger();
        assert TigerBuildRules.isTigerInSettlement(tile1.getCoords());
    }

    @Test
    public void checkIfHexAdjacentToSettlementTest(){ assert TigerBuildRules.checkIfHexAdjacentToSettlement(tile5.getHexes()[1]); }

    @Test
    public void checkIfHexLevelAtleastThreeTest(){
        assert TigerBuildRules.hexLevelAtLeastThree(tile1.getHexes()[2]);
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
        assert TigerBuildRules.canPlaceTiger(tile1.getHexes()[2]);
    }


    public  void setupHexAndTilesOnGameState(GameState game){

        Hex[] hexes1 = new Hex[3];
        Hex[] hexes2 = new Hex[3];
        Hex[] hexes3 = new Hex[3];
        Hex[] hexes4 = new Hex[3];
        Hex[] hexes5 = new Hex[3];

        Coordinate hexes1Coord1 = new Coordinate(101, 99);
        Coordinate hexes1Coord2 = new Coordinate(101, 100);
        Coordinate hexes1Coord3 = new Coordinate(102, 100);

        hexes1[0] = new Hex(hexes1Coord1, TerrainType.VOLCANO);
        hexes1[1] = new Hex(hexes1Coord2, TerrainType.LAKE);
        hexes1[2] = new Hex(hexes1Coord3, TerrainType.GRASSLAND);

        Coordinate hexes2Coord1 = new Coordinate(102, 102);
        Coordinate hexes2Coord2 = new Coordinate(101, 101);
        Coordinate hexes2Coord3 = new Coordinate(101, 102);

        hexes2[0] = new Hex(hexes2Coord1, TerrainType.VOLCANO);
        hexes2[1] = new Hex(hexes2Coord2, TerrainType.GRASSLAND);
        hexes2[2] = new Hex(hexes2Coord3, TerrainType.GRASSLAND);

        Coordinate hexes3Coord1 = new Coordinate(103, 98);
        Coordinate hexes3Coord2 = new Coordinate(102, 98);
        Coordinate hexes3Coord3 = new Coordinate(102, 99);

        hexes3[0] = new Hex(hexes3Coord1, TerrainType.VOLCANO);
        hexes3[1] = new Hex(hexes3Coord2, TerrainType.GRASSLAND);
        hexes3[2] = new Hex(hexes3Coord3, TerrainType.GRASSLAND);

        Coordinate hexes4Coord1 = new Coordinate(98, 100);
        Coordinate hexes4Coord2 = new Coordinate(99, 100);
        Coordinate hexes4Coord3 = new Coordinate(98, 101);

        hexes4[0] = new Hex(hexes4Coord1, TerrainType.VOLCANO);
        hexes4[1] = new Hex(hexes4Coord2, TerrainType.JUNGLE);
        hexes4[2] = new Hex(hexes4Coord3, TerrainType.ROCKY);

        Coordinate hexes5Coord1 = new Coordinate(99, 98);
        Coordinate hexes5Coord2 = new Coordinate(98, 99);
        Coordinate hexes5Coord3 = new Coordinate(98, 98);

        hexes5[0] = new Hex(hexes5Coord1, TerrainType.VOLCANO);
        hexes5[1] = new Hex(hexes5Coord2, TerrainType.JUNGLE);
        hexes5[2] = new Hex(hexes5Coord3, TerrainType.JUNGLE);

        tile1 = new Tile(hexes1);
        tile2 = new Tile(hexes2);
        tile3 = new Tile(hexes3);
        tile4 = new Tile(hexes4);
        tile5 = new Tile(hexes5);


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


