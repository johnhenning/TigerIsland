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
    Tile tile;
    static Tile tile1;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    boolean exceptionThrown;
    private Player player1;
    GameState gameState;

    @Before
    public void setup() throws Exception {
        gameState = new GameState();
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

        setupHexAndTilesOnGameState(gameState);

        coordinates.add(new Coordinate(100, 100));
        coordinates.add(new Coordinate(100, 101));
        coordinates.add(new Coordinate(99, 101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);


        gameState.foundSettlement(new Coordinate(100, 101), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(100, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(99, 99), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101, 100), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(101, 101), gameState.getCurrentPlayer());
        tile1.getHexes().get(2).setLevel(3);

        gameState.switchPlayer();
        gameState.foundSettlement(new Coordinate(102, 98), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(102, 99), gameState.getCurrentPlayer());

        gameState.foundSettlement(new Coordinate(98, 101), gameState.getCurrentPlayer());
        gameState.foundSettlement(new Coordinate(98, 98), gameState.getCurrentPlayer());


    }

    @Test
    public void placeTigerIsValidTest(){
        gameState.placeTiger(new Coordinate(102, 100));
    }

    @Test
    public void checkIfHexAdjacentToSettlementTest() {
        assert TigerBuildRules.checkIfHexAdjacentToSettlement(tile1.getHexes().get(2), gameState);
    }

    @Test
    public void checkEnoughEntitiesTest(){
        Hex hex = gameState.getHex(new Coordinate(102, 100));
        hex.addTiger();
        assert TigerBuildRules.checkEnoughEntities(gameState.getCurrentPlayer());
    }

    @Test
    public void checkIfHexLevelAtleastThreeTest() {
        assert TigerBuildRules.hexLevelAtLeastThree(tile1.getHexes().get(2));
        assert TigerBuildRules.hexLevelAtLeastThree(tile1.getHexes().get(2));
    }

    @Test
    public void checkIfTigerNotInSettlementTest() {
        assert TigerBuildRules.settlementNotContainTigers(gameState.getSettlementList().get(0), gameState);
    }

    @Test
    public void checkIfTigerInSettlementTest() {
        Hex hex = gameState.getHex(gameState.getSettlementList().get(0).getSettlementCoordinates().get(0));
        hex.addTiger();
        assert !TigerBuildRules.settlementNotContainTigers(gameState.getSettlementList().get(0),gameState);
    }

    @Test
    public void checkAdjSettlementOfPlayerNotContainTigerTest(){
        Hex hex = tile1.getHexes().get(2);
        assert TigerBuildRules.playerHasValidAdjSettlementForTiger(hex, gameState);
    }

    @Test
    public void checkAdjSettlementsDoesContainTigerTest(){
        Hex hex1 = gameState.getHex(gameState.getSettlementList().get(0).getSettlementCoordinates().get(0));
        hex1.addTiger();
        Hex hex2 = gameState.getHex(gameState.getSettlementList().get(1).getSettlementCoordinates().get(0));
        hex2.addTiger();
        Hex hex = tile1.getHexes().get(2);
        assert !TigerBuildRules.playerHasValidAdjSettlementForTiger(hex, gameState);
    }

    @Test
    public void checkNotPlayerAdjSettlementsDoesContainTigerTest(){
        Hex hex1 = gameState.getHex(gameState.getSettlementList().get(0).getSettlementCoordinates().get(0));
        hex1.addTiger();
        Hex hex = tile1.getHexes().get(2);
        assert TigerBuildRules.playerHasValidAdjSettlementForTiger(hex, gameState);
    }

    @Test
    public void checkNotPlayerAdjSettlementsDoesNotContainTigerTest(){
        Hex hex2 = gameState.getHex(gameState.getSettlementList().get(1).getSettlementCoordinates().get(0));
        hex2.addTiger();
        Hex hex = tile1.getHexes().get(2);
        assert !TigerBuildRules.playerHasValidAdjSettlementForTiger(hex, gameState);
    }

    @Test
    public void canPlaceTigerTest() {
        assert TigerBuildRules.canPlaceTiger(tile1.getHexes().get(2), gameState);
    }


    public void setupHexAndTilesOnGameState(GameState game) {

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