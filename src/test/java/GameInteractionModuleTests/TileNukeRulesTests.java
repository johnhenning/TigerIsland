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

        overlappedSettlement.add(new Settlement(new Coordinate(100,100), player1, 0));

        secondSettlementCoords.add(new Coordinate(100, 100));
        secondSettlementCoords.add(new Coordinate(100, 101));
        secondSettlementCoords.add(new Coordinate(99, 101));

        validSettlement.add(new Settlement(secondSettlementCoords, player1, 1));



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
    }


    @Test
    public void coordsOverlapTest(){
        assert TileNukeRules.doCoordinatesOverlap(coordinates, secondSettlementCoords);
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


}
