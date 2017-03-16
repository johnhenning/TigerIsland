/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private ArrayList<Hex> hexes;
    private int level;

    public Tile(int[][] coordinates, TerrainType[] terrains) {
        assert coordinates.length == terrains.length;

        for (int i = 0; i < coordinates.length; i++) {
            hexes.add(new Hex(coordinates[i][0], coordinates[i][1], terrains[i]));
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

    private boolean CheckForValidCoordinates() {
        return true;
    }
}

