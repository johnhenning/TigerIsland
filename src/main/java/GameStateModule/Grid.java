/**
 * Created by johnhenning on 3/15/17.
 */
package GameStateModule;
import GameInteractionModule.Rules.Rules;
import GameInteractionModule.Rules.TileNukeRules;
import GameInteractionModule.Rules.TilePlacementRules;

import java.util.ArrayList;

public class Grid {
    private static Hex[][] gameboard;
    private ArrayList<Tile> placedTiles;

    public Grid(int size) {
        gameboard = new Hex[size][size];
        placedTiles = new ArrayList<Tile>();

    }

    public void placeTile(Tile tile) {
        TilePlacementRules.isValidTilePlacement(tile, this, placedTiles);

        placeTileOnGameboard(tile, 1);

        placedTiles.add(tile);


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

    public static Hex[][] getGameboard(){ return gameboard; }

    public void printLog(){
        for(int i = 0; i < placedTiles.size(); i++){
            placedTiles.get(i).printTile();
        }
    }

    public ArrayList<Hex> getNeighborHexes(Hex hex) {
        ArrayList<Hex> hexes = new ArrayList<>();

        hexes.add(Rules.downLeft(this, hex.getCoordinate()));
        hexes.add(Rules.downRight(this, hex.getCoordinate()));
        hexes.add(Rules.topLeft(this, hex.getCoordinate()));
        hexes.add(Rules.topRight(this, hex.getCoordinate()));
        hexes.add(Rules.leftOfHex(this, hex.getCoordinate()));
        hexes.add(Rules.rightOfHex(this, hex.getCoordinate()));

        return hexes;
    }
}
