package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.Grid;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class GridTests {
    private Grid grid;
    private static Tile tile;

    @BeforeClass
    public static void setup() throws Exception {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();

        coordinates.add(new Coordinate(1,1));
        coordinates.add(new Coordinate(1,2));
        coordinates.add(new Coordinate(2,2));

        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);
        tile = new Tile(coordinates, terrains);
    }

    @Test
    public void GridCreation() throws Exception {
        grid = new Grid(200);
    }

    @Test
    public void TilePlacement() throws Exception {
        grid = new Grid(200);
        grid.placeTile(tile);
    }
}
