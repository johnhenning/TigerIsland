/**
 * Created by johnhenning on 3/15/17.
 */
package GameStateModule;
import GameInteractionModule.Rules.TileNukeRules;
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

    public void placeTile(Tile tile) {



        TilePlacementRules.isValidTilePlacement(tile, gameboard, placedTiles);

        placeTileOnGameboard(tile, 1);

        placedTiles.add(tile); //this has to occur before above function, need to fix


    }

    private void placeTileOnGameboard(Tile tile, int level) {//Extracted out setting levels into new method
        tile.setLevel(level);
        for (Hex hex : tile.getHexes()) {
            updateHexTileIndex(hex);
            placeHex(hex);
        }
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

    public void levelTile(Tile tile) {
        TileNukeRules.isValidNuke(tile, gameboard);
        int newLevel = TileNukeRules.getNewTileLevel(tile, gameboard);
        placedTiles.add(tile);
        placeTileOnGameboard(tile, newLevel);
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
        hex.setTurnPlaced(placedTiles.size() - 1);
    }

    public Tile getPlacedTile(int tileIndex) {
        return placedTiles.get(tileIndex);
    }

    public Hex[][] getGameboard(){ return gameboard; }
}
