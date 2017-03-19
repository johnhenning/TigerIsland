/**
 * Created by johnhenning on 3/15/17.
 */
public class Hex {
    private int x;
    private int y;
    private TerrainType terrain;
    private int tileIndex;
    private int MeepleCount;
    private boolean Totoro;

    public Hex(int x, int y, TerrainType terrain) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
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

}

