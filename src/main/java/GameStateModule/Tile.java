package GameStateModule;
/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private ArrayList<Hex> hexes;
    private int level;//Do we still need the level of a tile


    public Tile(ArrayList<Coordinate> coordinates, ArrayList<TerrainType> terrains) {//Should only need an array of three hexes, could reduce parameters to one
        assert coordinates.size() == terrains.size();//Why do we have an assert in a constructor
        hexes = new ArrayList<Hex>(); //interesting to note I think this was the problem

        for (int i = 0; i < coordinates.size(); i++) {
            hexes.add(new Hex(coordinates.get(i).getX(), coordinates.get(i).getY(), terrains.get(i)));
        }

        level = 1;
    }

    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        for(Hex h: hexes){
            h.setLevel(level);
        }
        this.level = level;
    }

    public ArrayList<Hex> getHexes() {
        return hexes;
    }
}

