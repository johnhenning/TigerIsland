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
    boolean exceptionThrown;

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

    }

    @Test
    public void adjacentHexesTest(){
        gameBoard = new Grid(200);
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }

        try {gameBoard.placeTile(tile2);}
        catch (AssertionError e) { exceptionThrown = true; }

        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.addAll(SettlementExpansionRules.findAdjacentCoords(gameBoard.getGameboard(), TerrainType.GRASSLAND, tile2.getCoords().get(2)));

        assert newCoordinates.size() == 2;

    }

}
