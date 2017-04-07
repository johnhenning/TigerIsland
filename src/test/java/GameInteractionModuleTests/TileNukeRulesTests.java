package GameInteractionModuleTests;

import GameInteractionModule.Rules.TileNukeRules;
import GameStateModule.*;

import gherkin.lexer.Ar;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Kyle on 3/28/2017.
 */

/* *********** PLEASE READ BEFORE CONTINUING *************** /
*   NOTE: THIS IS SUBJECT TO CHANGE WITH PLACEMENT OF FIRST TILE
*
*   These tests have a consistent tile layout which I've drawn
*   a picture of according to our method of referring to tile
*   placements. I've created a function that places the tile on
*   the board so I can use in multiple tests without copy and pasting.
*   The tile layout can be found on the google drive,
*   TileNukeRulesTileLayout.jpg
*************************************************************/


public class TileNukeRulesTests {

    private GameState game;
    private Grid gameBoard;
    //    private GameState gameState;
    private ArrayList<Settlement> overlappedSettlement;
    private ArrayList<Settlement> validSettlement;
    private Player player1;
    private Player player2;
    private boolean exceptionThrown;

    private ArrayList<Coordinate> coordinates;
    private ArrayList<Coordinate> secondSettlementCoords;

    private ArrayList<TerrainType> terrains;
    private Tile tile1;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    static Tile tile6;


    @Before
    public void setup() {
        game = new GameState();
        overlappedSettlement = new ArrayList<>();
        validSettlement = new ArrayList<>();
        coordinates = new ArrayList<>();
        terrains = new ArrayList<>();
        player1 = new Player();
        player2 = new Player();
        secondSettlementCoords = new ArrayList<>();


        coordinates.add(new Coordinate(100, 100));
        coordinates.add(new Coordinate(99, 101));
        coordinates.add(new Coordinate(99, 100));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        //overlappedSettlement.add(new Settlement(new Coordinate(100, 100), game.getCurrentPlayer(), 0));

        tile6 = new Tile(coordinates, terrains);

        secondSettlementCoords.add(new Coordinate(99, 100));
        secondSettlementCoords.add(new Coordinate(98, 99));
        secondSettlementCoords.add(new Coordinate(98, 98));

    }


    @Test
    public void coordsOverlapTest() {
        assert !TileNukeRules.doCoordinatesOverlap(coordinates, secondSettlementCoords);
    }

    @Test
    public void checkLowerHexesAreSameLevelTest() {
        //TODO: needs to be redone

        gameBoard = new Grid(200);

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
        hexesCoord4.add(new Coordinate(101, 99));
        hexesCoord4.add(new Coordinate(102, 98));
        hexesCoord4.add(new Coordinate(102, 99));

        ArrayList<TerrainType> terrains1 = new ArrayList<>();
        ArrayList<TerrainType> terrains2 = new ArrayList<>();
        ArrayList<TerrainType> terrains3 = new ArrayList<>();
        ArrayList<TerrainType> terrains4 = new ArrayList<>();

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


        tile1 = new Tile(hexesCoord, terrains1);
        tile2 = new Tile(hexesCoord2, terrains2);
        tile3 = new Tile(hexesCoord3, terrains3);
        tile4 = new Tile(hexesCoord4, terrains4);

        try {
            gameBoard.placeTile(tile1);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            gameBoard.placeTile(tile2);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            gameBoard.placeTile(tile3);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        assert TileNukeRules.CheckLowerHexesAreSameLevel(tile4, gameBoard.getGameboard()) == 1;
    }

    @Test
    public void getNewTileLevelTest() {
        //TODO: needs to be redone
        gameBoard = new Grid(200);

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
        hexesCoord4.add(new Coordinate(101, 99));
        hexesCoord4.add(new Coordinate(102, 98));
        hexesCoord4.add(new Coordinate(102, 99));

        ArrayList<TerrainType> terrains1 = new ArrayList<>();
        ArrayList<TerrainType> terrains2 = new ArrayList<>();
        ArrayList<TerrainType> terrains3 = new ArrayList<>();
        ArrayList<TerrainType> terrains4 = new ArrayList<>();

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


        tile1 = new Tile(hexesCoord, terrains1);
        tile2 = new Tile(hexesCoord2, terrains2);
        tile3 = new Tile(hexesCoord3, terrains3);
        tile4 = new Tile(hexesCoord4, terrains4);

        try {
            gameBoard.placeTile(tile1);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            gameBoard.placeTile(tile2);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            gameBoard.placeTile(tile3);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        int level = TileNukeRules.getNewTileLevel(tile4, gameBoard.getGameboard());
        assert level == 2;
    }


    @Test
    public void findAffectedSettlementsTest() {

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

        validSettlement.add(new Settlement(secondSettlementCoords, game.getCurrentPlayer(), 1));

        ArrayList<Coordinate> coord2 = new ArrayList<>();
        coord2.add(new Coordinate(99, 101));
        coord2.add(new Coordinate(100, 101));
        coord2.add(new Coordinate(98, 101));
        coord2.add(new Coordinate(101, 102));
        Settlement s1 = new Settlement(coord2, game.getCurrentPlayer(), 0);
        validSettlement.add(s1);
        ArrayList<Settlement> affectedSettlements =
                TileNukeRules.findAffectedSettlements(validSettlement, tile6);
        assert affectedSettlements.size() == 2;


    }

    @Test
    public void removeCoordsFromSettlementTest() {
        validSettlement.add(new Settlement(secondSettlementCoords, game.getCurrentPlayer(), 1));
        Settlement s = validSettlement.get(0);
        TileNukeRules.removeCoordsFromSettlement(coordinates, s);
        assert (s.getSettlementCoordinates().size() == 2);
    }

    @Test
    public void settlementContainsCoordinateTest() {
        validSettlement.add(new Settlement(secondSettlementCoords, game.getCurrentPlayer(), 1));
        assert TileNukeRules.settlmentContainsCoordinate(validSettlement.get(0), coordinates.get(2));
        assert !TileNukeRules.settlmentContainsCoordinate(validSettlement.get(0), new Coordinate(150, 150));
    }

    @Test
    public void divideSettlementsTest() {
        //removed because of redundancies
    }

    @Test
    public void bigDivideSettlementsTest() {
        setupGameStateSettlementSize5(game);
        ArrayList<Coordinate> coords = new ArrayList<>();
        ArrayList<TerrainType> terrains = new ArrayList<>();
        coords.add(new Coordinate(101, 99));
        coords.add(new Coordinate(102, 100));
        coords.add(new Coordinate(102, 99));
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.GRASSLAND);

        Hex[] boomHexes = new Hex[3];
        boomHexes[0] = new Hex(new Coordinate(101, 99), TerrainType.VOLCANO);
        boomHexes[1] = new Hex(new Coordinate(102, 100), TerrainType.LAKE);
        boomHexes[2] = new Hex(new Coordinate(102, 99), TerrainType.GRASSLAND);
        Tile boomTile = new Tile(coords, terrains);

        game.levelTile(boomTile);
        ArrayList<Settlement> checkingSettlments = game.getSettlementList();
        assert checkingSettlments != null;
    }

    @Test
    public void bigDivideSettlementsTestNonNuke() {
        setupGameStateNonNuked(game);
        ArrayList<Coordinate> coords = new ArrayList<>();
        ArrayList<TerrainType> terrains = new ArrayList<>();
        coords.add(new Coordinate(101, 99));
        coords.add(new Coordinate(102, 100));
        coords.add(new Coordinate(102, 99));
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.GRASSLAND);
        Hex[] boomHexes = new Hex[3];
        boomHexes[0] = new Hex(new Coordinate(101, 99), TerrainType.VOLCANO);
        boomHexes[1] = new Hex(new Coordinate(102, 100), TerrainType.LAKE);
        boomHexes[2] = new Hex(new Coordinate(102, 99), TerrainType.GRASSLAND);
        Tile boomTile = new Tile(coords, terrains);


        game.levelTile(boomTile);
        ArrayList<Settlement> checkingSettlments = game.getSettlementList();
        assert checkingSettlments != null;
    }

    @Test
    public void bigDivideSettlementsTest2Players() {
        setupGameState2Players(game);
        ArrayList<Coordinate> coords = new ArrayList<>();
        ArrayList<TerrainType> terrains = new ArrayList<>();
        coords.add(new Coordinate(101, 99));
        coords.add(new Coordinate(102, 100));
        coords.add(new Coordinate(102, 99));
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.GRASSLAND);
        Hex[] boomHexes = new Hex[3];
        boomHexes[0] = new Hex(new Coordinate(101, 99), TerrainType.VOLCANO);
        boomHexes[1] = new Hex(new Coordinate(102, 100), TerrainType.LAKE);
        boomHexes[2] = new Hex(new Coordinate(102, 99), TerrainType.GRASSLAND);
        Tile boomTile = new Tile(coords, terrains);

        game.levelTile(boomTile);
        ArrayList<Settlement> checkingSettlments = game.getSettlementList();
        assert checkingSettlments != null;

        Hex h = game.getHex(new Coordinate(102, 99));
        assert h.getLevel() == 2;

    }

    @Test
    public void bigDivideSettlementsTestWithExpansion() {
        setupGameState2Players(game);
        ArrayList<Coordinate> coords = new ArrayList<>();
        ArrayList<TerrainType> terrains = new ArrayList<>();
        coords.add(new Coordinate(101, 99));
        coords.add(new Coordinate(102, 100));
        coords.add(new Coordinate(102, 99));
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.GRASSLAND);
        Hex[] boomHexes = new Hex[3];
        boomHexes[0] = new Hex(new Coordinate(101, 99), TerrainType.VOLCANO);
        boomHexes[1] = new Hex(new Coordinate(102, 100), TerrainType.LAKE);
        boomHexes[2] = new Hex(new Coordinate(102, 99), TerrainType.GRASSLAND);
        Tile boomTile = new Tile(coords, terrains);

        game.levelTile(boomTile);
        ArrayList<Settlement> checkingSettlments = game.getSettlementList();
        assert checkingSettlments != null;

        Hex h = game.getHex(new Coordinate(101, 99));
        assert h.getLevel() == 2;
    }


    //TODO: test whether or not a settlement can be completly wiped out

    public void setupSettlementSize5(GameState game) {
        try {
            game.foundSettlement(new Coordinate(101, 102), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(101, 101), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(102, 100), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(102, 99), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(102, 98), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
    }

    public void setupSettlementNonNuked(GameState game) {
        try {
            game.foundSettlement(new Coordinate(101, 100), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(100, 101), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(101, 101), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(101, 102), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
    }

    public void setupSettlement2Players(GameState game) {

        try {
            game.foundSettlement(new Coordinate(101, 102), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(101, 101), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(102, 100), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(102, 99), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;
        try {
            game.foundSettlement(new Coordinate(102, 98), game.getCurrentPlayer());
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assert !exceptionThrown;

    }

    public void setupGameStateSettlementSize5(GameState game) {
        setupHexAndTilesOnGameState(game);
        setupSettlementSize5(game);
    }

    public void setupGameStateNonNuked(GameState game) {
        setupHexAndTilesOnGameState(game);
        setupSettlementNonNuked(game);
    }

    public void setupGameState2Players(GameState game) {
        setupHexAndTilesOnGameState(game);
        setupSettlement2Players(game);
    }

    public void setupHexAndTilesOnGameState(GameState game) {
//        Hex[] hexes1 = new Hex[3];
//        Hex[] hexes2 = new Hex[3];
//        Hex[] hexes3 = new Hex[3];
//        Hex[] hexes4 = new Hex[3];
//        Hex[] hexes5 = new Hex[3];

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