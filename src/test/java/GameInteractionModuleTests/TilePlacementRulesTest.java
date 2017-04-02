package GameInteractionModuleTests;

import GameInteractionModule.Rules.TilePlacementRules;
import GameStateModule.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by KWong on 3/28/2017.
 */
public class TilePlacementRulesTest {

    private Grid grid;
    private static Tile tile;
    private static Tile tile2;
    private ArrayList<Tile> placedTiles;
    private Hex[][] gameboard;

    @Before
    public void setup(){
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();

        coordinates.add(new Coordinate(1,1));
        coordinates.add(new Coordinate(1,2));
        coordinates.add(new Coordinate(2,2));

        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);
        tile = new Tile(coordinates, terrains);
        grid = new Grid(200);
        grid.placeTile(tile);
        gameboard = grid.getGameboard();
        placedTiles = grid.getPlacedTiles();

    }

    @Test
    public void CheckGameStartedTest(){
        assert TilePlacementRules.CheckGameStarted(placedTiles) == true;
    }

    @Test
    public void CheckForUnoccupiedHexesTest(){
        assert TilePlacementRules.CheckForUnoccupiedHexes(tile, gameboard) == false;
    }

  
}
