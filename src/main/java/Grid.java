/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Grid {
    private Hex[][] gameboard;
    private ArrayList<Tile> placedTiles;

    //TODO: should we use a beginning of game flag to check if a tile has been placed?


    public Grid(int size) {
        gameboard = new Hex[size][size];
        placedTiles = new ArrayList<Tile>();

    }

    public boolean PlaceTile(Tile tile) {
        assert CheckForUnoccupiedHexes(tile);
        if(!gridEmpty())
            assert CheckForAdjacentHex(tile);


        placedTiles.add(tile);

        for (Hex hex : tile.getHexes()) {
            updateHexTileIndex(hex);
            PlaceHex(hex);
        }

        return true;
    }

    public boolean gridEmpty(){
        return placedTiles.isEmpty();
    }

    public boolean CheckForUnoccupiedHexes(Tile tile){ //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean LevelTile(Tile tile) {
        return true;
    }

    public int getTurnNumber() {
        return placedTiles.size();
    }

    private boolean PlaceHex(Hex hex) {
        gameboard[hex.getx()][hex.gety()] = hex;
        return true;
    }

    private void updateHexTileIndex(Hex hex) {
        hex.setTileIndex(placedTiles.size() - 1);
    }



    private boolean CheckForAdjacentHex(Tile tile){

        //if the tile being placed

        return true;
    }

}
