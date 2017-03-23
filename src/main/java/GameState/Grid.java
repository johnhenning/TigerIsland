package GameState; /**
 * Created by johnhenning on 3/15/17.
 */
import GameInteraction.Rules;
import GameInteraction.TilePlacementRules;

import java.util.ArrayList;

public class Grid {
    private Hex[][] gameboard;
    private ArrayList<Tile> placedTiles;


    public Grid(int size) {
        gameboard = new Hex[size][size];
        placedTiles = new ArrayList<Tile>();

    }

    public boolean PlaceTile(Tile tile) {
        placedTiles.add(tile);
        for (Hex hex : tile.getHexes()) {
            updateHexTileIndex(hex);
            PlaceHex(hex);
        }

        return true;
    }

    public Hex getHexFromCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        Hex hex = gameboard[x][y];
        return hex;
    }

    public int TurnNumber(){
        return this.placedTiles.size();
    }

    public void LevelTile(Tile tile) {
        int lowerLevel = TilePlacementRules.CheckLowerHexesAreSameLevel(tile,gameboard,placedTiles);
        if (lowerLevel == -1) throw new AssertionError();
        tile.setLevel(lowerLevel + 1);
        for (Hex hex : tile.getHexes()) {
            gameboard[hex.getx()][hex.gety()] = hex;
        }
    }



    public int getPlacedTiles() {
        return placedTiles.size();
    }

    public boolean HexEmpty(int x, int y){ //TODO: OLD NEEDS TO BE REMOVED
        if (gameboard[x][y] == null)
            return true;
        else
            return false;
    }



    private boolean PlaceHex(Hex hex) {

        gameboard[hex.getx()][hex.gety()] = hex;
        return true;
    }

    private void updateHexTileIndex(Hex hex) {
        hex.setTileIndex(placedTiles.size() - 1);
    }


    public Tile getPlacedTile(int tileIndex) {
        return placedTiles.get(tileIndex);
    }
}
