/**
 * Created by johnhenning on 3/15/17.
 */
import java.util.ArrayList;

public class Grid {
    private Hex[][] gameboard;
    private ArrayList<Tile> placedTiles;

    //TODO: should we use a beginning of game flag to check if a tile has been placed?
    //I think we can check that by the size of the placed tiles ArrayList

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
        int lowerLevel = CheckLowerHexesAreSameLevel(tile);
        if (lowerLevel == -1) throw new AssertionError();
        assert CheckHexesSpanMultipleTiles(tile);
        assert CheckVolcanoesLineUp(tile);
        tile.setLevel(lowerLevel + 1);
        for (Hex hex : tile.getHexes()) {
            gameboard[hex.getx()][hex.gety()] = hex;
        }
        return true;
    }

    private int CheckLowerHexesAreSameLevel(Tile tile) {
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) return false;
        int lowerLevel0 = placedTiles.get(lower_hex0.getTileIndex()).getLevel();
        int lowerLevel1 = placedTiles.get(lower_hex1.getTileIndex()).getLevel();
        int lowerLevel2 = placedTiles.get(lower_hex2.getTileIndex()).getLevel();

        if (lowerLevel0 == lowerLevel1 && lowerLevel1 == lowerLevel2) {
            return lowerLevel0;
        } else {
            return -1;
        }
    }

    private boolean CheckHexesSpanMultipleTiles(Tile tile) {
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) return false;

        int tileIndex0 = lower_hex0.getTileIndex();
        int tileIndex1 = lower_hex1.getTileIndex();
        int tileIndex2 = lower_hex2.getTileIndex();

        if (tileIndex0 == tileIndex1 && tileIndex1 == tileIndex2) return false;

        return true;
    }

    private boolean CheckVolcanoesLineUp(Tile tile) {

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
