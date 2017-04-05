package GameStateModule;
/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private ArrayList<Hex> hexes;

    public Tile(ArrayList<Coordinate> coordinates, ArrayList<TerrainType> terrains) {
        hexes = new ArrayList<>();
        for(int i = 0; i <  coordinates.size(); i++){
            hexes.add(new Hex(coordinates.get(i), terrains.get(i)));
        }
    }

    public void setLevel(int level) {
        for(Hex h: hexes)
            h.setLevel(level);
    }

    public ArrayList<Hex> getHexes(){
        return hexes;
    }


    public ArrayList<Coordinate> getCoords(){
        ArrayList<Coordinate> tileCoords = new ArrayList<>();
        for(Hex h: hexes)
            tileCoords.add(h.getCoords());

        return tileCoords;
    }
    public static Tile getInitialTile(){
        ArrayList<Hex> initialTile = new ArrayList<>();
        initialTile.add(new Hex(new Coordinate(100,100), TerrainType.VOLCANO));
        initialTile.add(new Hex(new Coordinate(100,99), TerrainType.LAKE));
        initialTile.add(new Hex(new Coordinate(99,99), TerrainType.JUNGLE));
        initialTile.add(new Hex(new Coordinate(99,101), TerrainType.ROCKY));
        initialTile.add(new Hex(new Coordinate(100,101), TerrainType.GRASSLAND));
        ArrayList<Coordinate> coords = new ArrayList<>();
        coords.add(new Coordinate(100,100));
        coords.add(new Coordinate(100,99));
        coords.add(new Coordinate(99,99));
        coords.add(new Coordinate(99,101));
        coords.add(new Coordinate(100,101));
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.JUNGLE);
        terrains.add(TerrainType.ROCKY);
        terrains.add(TerrainType.GRASSLAND);


        return new Tile(coords, terrains);
    }
}

