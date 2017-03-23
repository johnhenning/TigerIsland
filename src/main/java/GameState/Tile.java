package GameState;
/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private ArrayList<Hex> hexes;
    private int level;

    public Tile(ArrayList<Coordinate> coordinates, ArrayList<TerrainType> terrains) {
        assert coordinates.size() == terrains.size();
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
        this.level = level;
    }

    public ArrayList<Hex> getHexes() {
        return hexes;
    }
}

