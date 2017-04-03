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
    Grid gameBoard;
    static Tile tile;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;
    static Tile tile5;
    boolean exceptionThrown;
    private Player player1;

    @Before
    public void setup() throws Exception {
        GameState gameStateObj = new GameState();

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();


        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(100,101));
        coordinates.add(new Coordinate(99,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        tile = new Tile(coordinates, terrains);
        gameStateObj.placeTile(tile);

        ArrayList<Coordinate> coordinates2 = new ArrayList<Coordinate>();
        coordinates2.add(new Coordinate(102,102));
        coordinates2.add(new Coordinate(101,101));
        coordinates2.add(new Coordinate(101,102));

        ArrayList<TerrainType> terrains2 = new ArrayList<TerrainType>();
        terrains2.add(TerrainType.VOLCANO);
        terrains2.add(TerrainType.GRASSLAND);
        terrains2.add(TerrainType.GRASSLAND);

        tile2 = new Tile(coordinates2, terrains2);
        gameStateObj.placeTile(tile2);

        ArrayList<Coordinate> coordinates3 = new ArrayList<Coordinate>();
        coordinates3.add(new Coordinate(100,103));
        coordinates3.add(new Coordinate(99,103));
        coordinates3.add(new Coordinate(100,102));

        ArrayList<TerrainType> terrains3 = new ArrayList<TerrainType>();
        terrains3.add(TerrainType.VOLCANO);
        terrains3.add(TerrainType.GRASSLAND);
        terrains3.add(TerrainType.GRASSLAND);

        tile3 = new Tile(coordinates3, terrains3);
        gameStateObj.placeTile(tile3);

        ArrayList<Coordinate> coordinates4 = new ArrayList<Coordinate>();
        coordinates4.add(new Coordinate(99,100));
        coordinates4.add(new Coordinate(98,100));
        coordinates4.add(new Coordinate(98,101));

        ArrayList<TerrainType> terrains4 = new ArrayList<TerrainType>();
        terrains4.add(TerrainType.VOLCANO);
        terrains4.add(TerrainType.GRASSLAND);
        terrains4.add(TerrainType.GRASSLAND);

        tile4 = new Tile(coordinates4, terrains4);
        gameStateObj.placeTile(tile4);

        ArrayList<Coordinate> coordinates5 = new ArrayList<Coordinate>();
        coordinates5.add(new Coordinate(101,100));
        coordinates5.add(new Coordinate(100,99));
        coordinates5.add(new Coordinate(101,99));

        ArrayList<TerrainType> terrains5 = new ArrayList<TerrainType>();
        terrains5.add(TerrainType.GRASSLAND);
        terrains5.add(TerrainType.VOLCANO);
        terrains5.add(TerrainType.GRASSLAND);

        tile5 = new Tile(coordinates5, terrains5);
        gameStateObj.placeTile(tile5);

        player1 = new Player();
        gameStateObj.foundSettlement(new Coordinate(100,101),player1);
        gameStateObj.foundSettlement(new Coordinate(101,101),player1);
        gameStateObj.foundSettlement(new Coordinate(101,102),player1);
        gameStateObj.foundSettlement(new Coordinate(101,100),player1);
        gameStateObj.foundSettlement(new Coordinate(100,102),player1);

    }

    @Test
    public void isHexAdjacentToSettlementTest(){
       assert TotoroBuildRules.isHexAdjacentToSettlement(tile.getHexes().get(2));
    }

    @Test
    public void playerHasSizeFiveSettlementTest(){
        assert TotoroBuildRules.playerHasSizeFiveSettlement(player1);
    }

    @Test
    public void isValidTotoroLocationTest(){
        assert TotoroBuildRules.isValidTotoroLocation(tile.getHexes().get(2),player1);
    }
}
