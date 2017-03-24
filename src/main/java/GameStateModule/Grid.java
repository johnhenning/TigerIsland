/**
 * Created by johnhenning on 3/15/17.
 */
package GameStateModule;
import GameInteractionModule.Rules.TileNukeRules;
import GameInteractionModule.*;
import GameInteractionModule.Rules.TilePlacementRules;

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

    public boolean placeTile(Tile tile) {
        placedTiles.add(tile);
        if(TilePlacementRules.CheckGameStarted(placedTiles)) {
           if(!TilePlacementRules.CheckForAdjacentHex(tile, gameboard)) throw new AssertionError();
        }

        if(!TilePlacementRules.CheckForUnoccupiedHexes(tile, gameboard)) throw new AssertionError();

        tile.setLevel(1);
        for (Hex hex : tile.getHexes()) {
            updateHexTileIndex(hex);
            placeHex(hex);
        }

        return true;
    }

    public Hex getHexFromCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        Hex hex = gameboard[x][y];
        return hex;
    }


    public int TurnNumber() {
        return placedTiles.size();
    }

    public void LevelTile(Tile tile) {
        int lowerLevel = TileNukeRules.CheckLowerHexesAreSameLevel(tile,gameboard,placedTiles);
        if (lowerLevel == -1) throw new AssertionError();
        boolean MultipleTiles = TileNukeRules.CheckHexesSpanMultipleTiles(tile, gameboard);
        if(!MultipleTiles) throw new AssertionError();
        boolean VolcanoLineUp = TileNukeRules.CheckVolcanoesLineUp(tile,gameboard);
        if(!VolcanoLineUp) throw new AssertionError();
        boolean DoesNotHaveTotoro = TileNukeRules.CheckTileNotContainTotoro(tile, gameboard);
        if(DoesNotHaveTotoro == false) throw new AssertionError();

        tile.setLevel(lowerLevel + 1);
        placedTiles.add(tile);

        for (Hex hex : tile.getHexes()) {
            updateHexTileIndex(hex);
            placeHex(hex);
        }
    }

    public int getNumberOfPlacedTiles() {
        return placedTiles.size();
    }

    public ArrayList<Tile> getPlacedTiles(){
        return placedTiles;
    }

    public boolean hexEmpty(int x, int y) {
        return gameboard[x][y] == null;
    }
  
    public boolean gridEmpty(){
        return placedTiles.isEmpty();
    }


    private void placeHex(Hex hex) {
        gameboard[hex.getx()][hex.gety()] = hex;
    }

    private void updateHexTileIndex(Hex hex) {
        hex.setTileIndex(placedTiles.size() - 1);
    }

    public Tile getPlacedTile(int tileIndex) {
        return placedTiles.get(tileIndex);
    }
}
