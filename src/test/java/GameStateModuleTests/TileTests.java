package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/23/17.
 */
public class TileTests {
    private static ArrayList<Coordinate> coordinates;
    private static ArrayList<TerrainType> terrains;

    @BeforeClass
    public static void setup() throws Exception {
        coordinates = new ArrayList<Coordinate>();
        terrains = new ArrayList<TerrainType>();

        coordinates.add(new Coordinate(1,1));
        coordinates.add(new Coordinate(1,2));
        coordinates.add(new Coordinate(2,2));

        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);
    }
    @Test
    public void createTile() throws Exception {
        Tile tile = new Tile(coordinates, terrains);
    }

    @Test
    public void setTileLevel() throws Exception {
        Tile tile = new Tile(coordinates,terrains);
        tile.setLevel(3);
    }
}
