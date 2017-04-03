package GameStateModule;

/**
 * Created by johnhenning on 3/15/17.
 */
public class Hex {
    private final Coordinate coordinate;
    private final TerrainType terrain;
    private int turnPlaced;
    private int level;
    private int meepleCount;
    private boolean totoro;
    private boolean tiger;

    public Hex(Coordinate coordinate, TerrainType terrain) {
        this.coordinate = coordinate;
        this.terrain = terrain;
        this.meepleCount = 0;
        this.totoro = false;
        this.tiger = false;
    }

    public int getx() {
        return coordinate.getX();
    }

    public int gety() {
        return coordinate.getY();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setLevel(int level) { 
      this.level = level; 
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public int getLevel() {
        return level; 
    }
    
    public int getTurnPlaced() {
        return turnPlaced;
    }

    public int getMeepleCount() { 
        return meepleCount; 
    }

    public boolean hasTotoro() { 
        return totoro; 
    }

    public boolean hasTiger() { 
        return tiger; 
    }

    public void setTurnPlaced(int turnPlaced) {
        this.turnPlaced = turnPlaced;
    }

    public void addMeeple(int level) {
        meepleCount += level;
    }

    public void addTotoro() {
        totoro = true;
    }

    public Coordinate getCoords(){ return coordinate; }

}

