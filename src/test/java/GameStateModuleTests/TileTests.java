package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.Hex;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/23/17.
 */
public class TileTests {
    private static ArrayList<Coordinate> coordinates;
    private static ArrayList<TerrainType> terrains;
    private static Hex[] hexes;

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

        hexes = new Hex[3];
        hexes[0] = new Hex(new Coordinate(1,1), TerrainType.VOLCANO);
        hexes[1] = new Hex(new Coordinate(1,2), TerrainType.GRASSLAND);
        hexes[2] = new Hex(new Coordinate(2,2), TerrainType.LAKE);
    }
    @Test
    public void createTile() throws Exception {
        Tile tile = new Tile(hexes);
    }

    @Test
    public void setTileLevel() throws Exception {
        Tile tile = new Tile(hexes);
        tile.setLevel(3);
    }

    @Test
    public void getHexesTest() throws Exception {

    }
}
