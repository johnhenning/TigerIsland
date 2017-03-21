package GameState;

/**
 * Created by johnhenning on 3/15/17.
 */
public class Hex {
    private final Coordinate coordinate;
    private final TerrainType terrain;
    private int tileIndex;
    private int MeepleCount;
    private boolean Totoro;

    public Hex(int x, int y, TerrainType terrain) {
        this.coordinate = new Coordinate(x,y);
        this.terrain = terrain;
        this.MeepleCount = 0;
        this.Totoro = false;
    }

    public int getx() {
        return coordinate.getX();
    }

    public int gety() {
        return coordinate.getY();
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public void addMeeple(int level) {
        MeepleCount += level;
    }

    public void addTotoro() {
        Totoro = true;
    }

}

