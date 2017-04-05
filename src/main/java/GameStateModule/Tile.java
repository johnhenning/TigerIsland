package GameStateModule;
/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private ArrayList<Hex> hexes;

    public Tile(ArrayList<Coordinate> coordinates, ArrayList<TerrainType> terrains) {//Should only need an array of three hexes, could reduce parameters to one
        assert coordinates.size() == terrains.size();//Why do we have an assert in a constructor
        hexes = new ArrayList<Hex>();

        for (int i = 0; i < coordinates.size(); i++) {
            hexes.add(new Hex(coordinates.get(i), terrains.get(i)));
        }
    }

    public void setLevel(int level) {
        for(Hex h: hexes)
            h.setLevel(level);
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        assert coordinates.size() == hexes.size();
        for (int i = 0; i < coordinates.size(); i++) {
            hexes.get(i).setCoordinate(coordinates.get(i));
        }
    }

    public ArrayList<Hex> getHexes() {
        return hexes;
    }

    public ArrayList<Coordinate> getCoords(){
        ArrayList<Coordinate> tileCoords = new ArrayList<>();
        for(Hex h: hexes)
            tileCoords.add(h.getCoordinate());

        return tileCoords;
    }
}

