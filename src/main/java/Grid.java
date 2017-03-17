/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Grid {
    private Hex[][] gameboard;
    public ArrayList<Tile> placedTiles;

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

    private boolean CheckForUnoccupiedHexes(Tile tile){ //changed to public so I can use in tests
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

        //the tile being placed has coordinates

        //need to check if there is a hex adjacent to the tiles coordiantes
        //if there is not, we cannot add an the tile

        //if there is a hex on the game board occupied adjacent to where we want to place it
        //we may add the tile

        //this is just iterating through the graycode of the 2 coordiantes

        for (Hex hex : tile.getHexes()) {
            int x = hex.getx();
            int y = hex.gety();
            // TODO : need to figure out edge cases
            if(hex.getx() < 0 || hex.gety() > 200){
                //do nothing
            }

            else{

                if(gameboard[x][y+1] != null)
                    return true;
                else if(gameboard[x][y-1] != null)
                    return true;
                else if(gameboard[x+1][y-1] != null)
                    return true;
                else if(gameboard[x+1][y+1] != null)
                    return true;
                else if(gameboard[x+1][y] != null)
                    return true;
                else if(gameboard[x-1][y] != null)
                    return true;
                else if(gameboard[x-1][y+1] != null)
                    return true;
                else if(gameboard[x-1][y-1] != null)
                    return true;
            }
        }

        return false;
    }

}
