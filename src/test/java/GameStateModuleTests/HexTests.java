package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.Hex;
import GameStateModule.TerrainType;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class HexTests {
    private static int x;
    private static int y;
    private static TerrainType terrainType;
    private static Coordinate coordinate;

    @BeforeClass
    public static void setup(){
        x = 1;
        y = 1;
        terrainType = TerrainType.VOLCANO;
        coordinate = new Coordinate(1, 1);
    }

    @Test
    public void createHex(){
        Hex hex = new Hex(coordinate, terrainType);
    }

    @Test
    public void coordinateXTest(){
        Hex hex = new Hex(coordinate, terrainType);

        assertEquals(hex.getCoords().getX(), coordinate.getX());
    }

    public void coordinateYTest(){
        Hex hex = new Hex(coordinate, terrainType);

        assertEquals(hex.getCoords().getY(), coordinate.getY());
    }

    @Test
    public void setHexLevel(){
        Hex hex = new Hex(coordinate, terrainType);
        hex.setLevel(4);
        assert hex.getLevel() == 4;
    }

    @Test
    public void setTileIndexForHex(){
        Hex hex = new Hex(coordinate, terrainType);
        hex.setTurnPlaced(3);
        assert hex.getTurnPlaced() == 3;
    }

    @Test
    public void checkTerrainType(){
        Hex hex = new Hex(coordinate, terrainType);
        assertEquals("Types are not equal", TerrainType.VOLCANO, terrainType);
    }

    @Test
    public void addMeeplesTest(){
        Hex hex = new Hex(coordinate, terrainType);
        hex.setLevel(4);
        hex.addMeeple(4);
        assert hex.getMeepleCount() == 4;
    }

    @Test
    public void hasTotoroTest(){
        Hex hex = new Hex(coordinate, terrainType);
        assertFalse(hex.hasTotoro());
    }

    @Test
    public void hasTigerTest(){
        Hex hex = new Hex(coordinate, terrainType);
        assertFalse(hex.hasTiger());
    }

    @Test
    public void addTotoroTest(){
        Hex hex = new Hex(coordinate, terrainType);
        hex.addTotoro();
        hex.hasTotoro();
    }

    @Test
    public void addTigerTest(){
        Hex hex = new Hex(coordinate, terrainType);
        hex.addTiger();
        hex.hasTiger();
    }

}