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
        assert CheckForAdjacentHex(tile);

        placedTiles.add(tile);

        for (Hex hex : tile.getHexes()) {
            updateHexTileIndex(hex);
            PlaceHex(hex);
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

    public boolean CheckForUnoccupiedHexes(Tile tile){ //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean CheckForAdjacentHex(Tile tile){
    //if y is odd, then x in top left and bottom left is equal of that y value
        //then x in top right and bottom right is greater by one. y is minus one for top and greater by one on bottom

        for (Hex hex : tile.getHexes()) {
            if(hex.gety() % 2 != 0){
                if (gameboard[hex.getx()][hex.gety() - 1] != null &&
                        gameboard[hex.getx() + 1][hex.gety() - 1] != null) {
                    //finish
                    return true;
                }
                else if (gameboard[hex.getx()][hex.gety() + 1] != null &&
                        gameboard[hex.getx() + 1][hex.gety() + 1] != null) {
                    //finish
                    return true;
                }
                else if (gameboard[hex.getx() + 1][hex.gety()] != null &&
                        gameboard[hex.getx() + 1][hex.gety() - 1] != null ) {
                    //finish
                    return true;
                }
                else if (gameboard[hex.getx() + 1][hex.gety()] != null &&
                        gameboard[hex.getx() + 1][hex.gety() + 1] != null ) {
                    //finish
                    return true;
                }
                else if (gameboard[hex.getx() - 1][hex.gety()] != null &&
                        gameboard[hex.getx()][hex.gety() - 1] != null ) {
                    //finish
                    return true;
                }
                else if (gameboard[hex.getx() - 1][hex.gety()] != null &&
                        gameboard[hex.getx()][hex.gety() + 1] != null ) {
                    //finish
                    return true;
                }
            }
        }
        return false;
    }

    public boolean gridEmpty(){
        return placedTiles.isEmpty();
    }
}
