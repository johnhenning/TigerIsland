package GameStateModule;

/**
 * Created by johnhenning on 3/15/17.
 */
public class Hex {
    private Coordinate coordinate;
    private final TerrainType terrain;
    private int turnPlaced;
    private int level;
    private int meepleCount;
    private int settlementID;
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


    public int getLevel() {
        return level;
    }

    public int getSettlementID() {
        return settlementID;
    }

    public int getTurnPlaced() {
        return turnPlaced;
    }

    public TerrainType getTerrain() {
        return terrain;
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

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setLevel(int level) {
      this.level = level;
    }

    public void setSettlementID(int settlementID) {
        this.settlementID = settlementID;
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

    public void addTiger() {
        tiger = true;
    }

    public void printCoord(){ System.out.println("x: " + coordinate.getX() + " y: " + coordinate.getY());}

    public void printTerrainType(){ System.out.println(terrain.toString()); }

    public void printEntities() {
        System.out.print("Meeples: " + meepleCount + " ");
        System.out.print("Tortoro: " + totoro + " ");
        System.out.print("Tiger: " + tiger + " ");
    }

    public void printHexInfo(){
        System.out.println("Hex level: " + level + " SettlementID: " + settlementID);
    }

    public void printHex(){
        printCoord();
        printTerrainType();
        printEntities();
        printHexInfo();
        System.out.println();
    }



}