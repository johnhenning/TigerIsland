package GameStateModule;
/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Tile {
    private Hex[] hexes;

    public Tile(Hex[] hexes) {//Should only need an array of three hexes, could reduce parameters to one
       this.hexes = hexes;
    }

    public void setLevel(int level) {
        for(Hex h: hexes)
            h.setLevel(level);
    }

    public Hex[] getHexes() {
        return hexes;
    }

    public ArrayList<Coordinate> getCoords(){
        ArrayList<Coordinate> tileCoords = new ArrayList<>();
        for(Hex h: hexes)
            tileCoords.add(h.getCoords());

        return tileCoords;
    }
}

