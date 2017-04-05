package GameInteractionModuleTests;

import GameInteractionModule.Rules.TileNukeRules;
import GameStateModule.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Kyle on 3/28/2017.
 */
public class TileNukeRulesTests{

    private Grid gameBoard;
    private ArrayList<Settlement> overlappedSettlement;
    private ArrayList<Settlement> validSettlement;
    private Player player1;
    private boolean exceptionThrown;

    private ArrayList<Coordinate> coordinates;
    private ArrayList<Coordinate> secondSettlementCoords;

    private ArrayList<TerrainType> terrains;
    private Tile tile;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    static Tile tile6;


    @Before
    public void setup() throws Exception{
        overlappedSettlement = new ArrayList<>();
        validSettlement = new ArrayList<>();
        coordinates = new ArrayList<>();
        terrains = new ArrayList<>();
        player1 = new Player();
        secondSettlementCoords = new ArrayList<>();

        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(100,101));
        coordinates.add(new Coordinate(99,101));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        overlappedSettlement.add(new Settlement(new Coordinate(100,100), player1));

        secondSettlementCoords.add(new Coordinate(100, 100));
        secondSettlementCoords.add(new Coordinate(100, 101));
        secondSettlementCoords.add(new Coordinate(99, 101));

        validSettlement.add(new Settlement(secondSettlementCoords, player1));

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
        coordinates5.add(new Coordinate(101,100));
        coordinates5.add(new Coordinate(100,99));
        coordinates5.add(new Coordinate(101,99));

        ArrayList<TerrainType> terrains5 = new ArrayList<TerrainType>();
        terrains5.add(TerrainType.GRASSLAND);
        terrains5.add(TerrainType.VOLCANO);
        terrains5.add(TerrainType.GRASSLAND);
        Hex[] hexes5 = new Hex[3];
        hexes5[0] = new Hex(coordinates5.get(0), terrains5.get(0));
        hexes5[1] = new Hex(coordinates5.get(1), terrains5.get(1));
        hexes5[2] = new Hex(coordinates5.get(2), terrains5.get(2));

        tile5 = new Tile(hexes5);

        ArrayList<Coordinate> coordinates6 = new ArrayList<Coordinate>();
        coordinates6.add(new Coordinate(101,100));
        coordinates6.add(new Coordinate(100,100));
        coordinates6.add(new Coordinate(100,99));

        ArrayList<TerrainType> terrains6 = new ArrayList<TerrainType>();
        terrains6.add(TerrainType.GRASSLAND);
        terrains6.add(TerrainType.VOLCANO);
        terrains6.add(TerrainType.GRASSLAND);
        Hex[] hexes6 = new Hex[3];
        hexes6[0] = new Hex(coordinates6.get(0), terrains6.get(0));
        hexes6[1] = new Hex(coordinates6.get(1), terrains6.get(1));
        hexes6[2] = new Hex(coordinates6.get(2), terrains6.get(2));

        tile6 = new Tile(hexes6);
    }


    @Test
    public void coordsOverlapTest(){
        assert TileNukeRules.doCoordinatesOverlap(coordinates, secondSettlementCoords);
    }

    @Test
    public void checkLowerHexesAreSameLevelTest(){
        gameBoard = new Grid(200);
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile4);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile5);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        assert TileNukeRules.CheckLowerHexesAreSameLevel(tile6, gameBoard.getGameboard()) == 1;
    }

    @Test
    public void getNewTileLevelTest(){
        gameBoard = new Grid(200);
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile4);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile5);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        int level = TileNukeRules.getNewTileLevel(tile6, gameBoard.getGameboard());
        assert level == 2;
    }


    @Test
    public void findAffectedSettlementsTest(){
        ArrayList<Coordinate> coord2 = new ArrayList<>();
        coord2.add(new Coordinate(1,2));
        coord2.add(new Coordinate(1,3));
        coord2.add(new Coordinate(1,4));
        coord2.add(new Coordinate(1,5));
        Settlement s1 = new Settlement(coord2, player1);
        validSettlement.add(s1);
        ArrayList<Settlement> affectedSettlements =
                        TileNukeRules.findAffectedSettlements(validSettlement, tile);
        System.out.println(affectedSettlements.size());
        assert affectedSettlements.size() <= 1;


    }

    @Test
    public void removeCoordsFromSettlementTest(){
        Settlement s = validSettlement.get(0);


        TileNukeRules.removeCoordsFromSettlement(coordinates, s);
        System.out.println(s.getSettlementCoordinates().size());
        assert  !(s.getSettlementCoordinates().size() > 0);
    }

    @Test
    public void settlementContainsCoordinateTest(){
        assert TileNukeRules.settlmentContainsCoordinate(validSettlement.get(0), coordinates.get(0));
        assert !TileNukeRules.settlmentContainsCoordinate(validSettlement.get(0), new Coordinate (150, 150));
    }
    @Test
    public void divideSettlementsTest(){
        gameBoard = new Grid(200);
        ArrayList<Coordinate> settlementCoordinates = new ArrayList<>();
        ArrayList<Settlement> settlementsReturned = new ArrayList<>();
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile3);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile4);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        try {gameBoard.placeTile(tile5);}
        catch (AssertionError e) { exceptionThrown = true; }
        assert  !exceptionThrown;
        settlementCoordinates.add(new Coordinate(99,103));
        settlementCoordinates.add(new Coordinate(100,102));
        settlementCoordinates.add(new Coordinate(101,98));
        settlementCoordinates.add(new Coordinate(101,99));

        Settlement testSettlement = new Settlement(settlementCoordinates, player1);

        settlementsReturned = TileNukeRules.divideSettlement(gameBoard.getGameboard(), testSettlement);

        assert settlementsReturned.get(0).getSettlementCoordinates().size() == 2;
        assert settlementsReturned.get(1).getSettlementCoordinates().size() == 2;



    }

    //TODO: test whether or not a settlement can be completly wiped out
    public  void setupHexAndTilesOnGameState(GameState game){
        Hex[] hexes1 = new Hex[3];
        Hex[] hexes2 = new Hex[3];
        Hex[] hexes3 = new Hex[3];
        Hex[] hexes4 = new Hex[3];
        Hex[] hexes5 = new Hex[3];

        Coordinate hexes1Coord1 = new Coordinate(101, 99);
        Coordinate hexes1Coord2 = new Coordinate(101, 100);
        Coordinate hexes1Coord3 = new Coordinate(102, 100);

        Coordinate hexes2Coord1 = new Coordinate(102, 102);
        Coordinate hexes2Coord2 = new Coordinate(101, 101);
        Coordinate hexes2Coord3 = new Coordinate(101, 102);

        Coordinate hexes3Coord1 = new Coordinate(103, 98);
        Coordinate hexes3Coord2 = new Coordinate(102, 98);
        Coordinate hexes3Coord3 = new Coordinate(102, 99);

        Coordinate hexes4Coord1 = new Coordinate(98, 100);
        Coordinate hexes4Coord2 = new Coordinate(99, 100);
        Coordinate hexes4Coord3 = new Coordinate(98, 101);

        Coordinate hexes5Coord1 = new Coordinate(99, 98);
        Coordinate hexes5Coord2 = new Coordinate(98, 99);
        Coordinate hexes5Coord3 = new Coordinate(98, 98);

        hexes1[0] = new Hex(hexes1Coord1, TerrainType.VOLCANO);
        hexes1[1] = new Hex(hexes1Coord2, TerrainType.LAKE);
        hexes1[2] = new Hex(hexes1Coord3, TerrainType.GRASSLAND);

        hexes2[0] = new Hex(hexes2Coord1, TerrainType.VOLCANO);
        hexes2[1] = new Hex(hexes2Coord2, TerrainType.GRASSLAND);
        hexes2[2] = new Hex(hexes2Coord3, TerrainType.GRASSLAND);

        hexes3[0] = new Hex(hexes3Coord1, TerrainType.VOLCANO);
        hexes3[1] = new Hex(hexes3Coord2, TerrainType.GRASSLAND);
        hexes3[2] = new Hex(hexes3Coord3, TerrainType.GRASSLAND);

        hexes4[0] = new Hex(hexes4Coord1, TerrainType.VOLCANO);
        hexes4[1] = new Hex(hexes4Coord2, TerrainType.JUNGLE);
        hexes4[2] = new Hex(hexes4Coord3, TerrainType.ROCKY);

        hexes5[0] = new Hex(hexes5Coord1, TerrainType.VOLCANO);
        hexes5[1] = new Hex(hexes5Coord2, TerrainType.JUNGLE);
        hexes5[2] = new Hex(hexes5Coord3, TerrainType.JUNGLE);

        Tile tile1 = new Tile(hexes1);
        Tile tile2 = new Tile(hexes2);
        Tile tile3 = new Tile(hexes3);
        Tile tile4 = new Tile(hexes4);
        Tile tile5 = new Tile(hexes5);


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
