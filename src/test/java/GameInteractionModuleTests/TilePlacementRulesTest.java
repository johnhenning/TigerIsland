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

    private GameState gameState;
    private Grid grid;
    private static Tile tile;
    private static Tile tile1;
    private static Tile tile2;
    private static Tile tile3;
    private static Tile tile4;
    private static Tile tile5;
    private ArrayList<Tile> placedTiles;
    boolean exceptionThrown;

    @Before
    public void setup(){
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        gameState = new GameState();

        coordinates.add(new Coordinate(1,1));
        coordinates.add(new Coordinate(1,2));
        coordinates.add(new Coordinate(2,2));

        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);
        tile = new Tile(coordinates, terrains);

        setupHexAndTilesOnGameState(gameState);
        placedTiles = gameState.getGameboard().getPlacedTiles();

    }

    @Test
    public void CheckGameStartedTest(){
        assert TilePlacementRules.CheckGameStarted(placedTiles) == true;
    }

    @Test
    public void isValidTileTest(){ assert TilePlacementRules.IsValidTile(tile1); }

    @Test
    public void checkForAdjacentHexTest(){ assert TilePlacementRules.CheckForAdjacentHex(tile2, gameState.getGameboard()); }

    @Test
    public void CheckForUnoccupiedHexesTest(){ assert TilePlacementRules.CheckForUnoccupiedHexes(tile1, gameState.getGameboard()) == false; }

    @Test
    public void getAdjacentHexTest(){
        ArrayList<Hex> adjHexes = new ArrayList<>();
        ArrayList<Hex> Hexes = new ArrayList<>();
        Hexes.add(tile3.getHexes().get(1));
        Hexes.add(tile3.getHexes().get(0));
        Hexes.add(tile1.getHexes().get(0));
        Hexes.add(tile1.getHexes().get(2));
        adjHexes.addAll(TilePlacementRules.getAdjacentHexes(tile3.getHexes().get(2), gameState.getGameboard()));
        assert adjHexes.containsAll(Hexes) && Hexes.containsAll(adjHexes);

    }

    @Test
    public void isValidTilePlacementTest(){
        ArrayList<Coordinate> hexesCoord6 = new ArrayList<>();
        hexesCoord6.add(new Coordinate(97, 100));
        hexesCoord6.add(new Coordinate(97, 101));
        hexesCoord6.add(new Coordinate(96, 101));

        ArrayList<TerrainType> terrains6 = new ArrayList<>();

        terrains6.add(TerrainType.VOLCANO);
        terrains6.add(TerrainType.LAKE);
        terrains6.add(TerrainType.GRASSLAND);

        Tile tile6 = new Tile(hexesCoord6, terrains6);
        TilePlacementRules.isValidTilePlacement(tile6, gameState.getGameboard(), gameState.getGameboard().getPlacedTiles());
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
