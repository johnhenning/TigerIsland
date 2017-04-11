package GameStateModule;

/**
 * Created by johnhenning on 4/4/17.
 */
public class BuildMove {
    public BuildMoveType buildMoveType;
    public Coordinate coordinate;
    public TerrainType terrainType;

    public BuildMove(BuildMoveType buildMoveType, Coordinate coordinate, TerrainType terrainType) {
        this.buildMoveType = buildMoveType;
        this.coordinate = coordinate;
        this.terrainType = terrainType;
    }
    public String toString(BuildMoveType buildMoveType){
        switch (buildMoveType){
            case FOUNDSETTLEMENT:
                return "FOUND SETTLEMENT AT ";
            case EXPANDSETTLEMENT:
                return "EXPAND SETTLEMENT AT ";
            case PLACETOTORO:
                return "BUILD TOTORO SANCTUARY AT ";
            case PLACETIGER:
                return "BUILD TIGER PLAYGROUND AT ";
            default:
                return null;
        }
    }
}
