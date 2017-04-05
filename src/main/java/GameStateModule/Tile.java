package GameStateModule;
/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private Hex[] hexes;

    public Tile(Hex[] hexes) {//Should only need an array of three hexes, could reduce parameters to one
       this.hexes = hexes;
    }

    public void setLevel(int level) {
        for(Hex h: hexes)
            h.setLevel(level);
    }

    public Hex[] getHexes() {
        return hexes;
    }

    public ArrayList<Coordinate> getCoords(){
        ArrayList<Coordinate> tileCoords = new ArrayList<>();
        for(Hex h: hexes)
            tileCoords.add(h.getCoords());

        return tileCoords;
    }
    public static Tile getInitialTile(){
        Hex[] initialTileHexes = new Hex[5];
        initialTileHexes[0] = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
        initialTileHexes[1] = new Hex(new Coordinate(100,99), TerrainType.LAKE);
        initialTileHexes[2] = new Hex(new Coordinate(99,99), TerrainType.JUNGLE);
        initialTileHexes[3] = new Hex(new Coordinate(99,101), TerrainType.ROCKY);
        initialTileHexes[4] = new Hex(new Coordinate(100,101), TerrainType.GRASSLAND);
        return new Tile(initialTileHexes);
    }
}

