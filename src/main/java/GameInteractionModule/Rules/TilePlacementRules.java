package GameInteractionModule.Rules;
import GameStateModule.Coordinate;
import GameStateModule.Hex;
import GameStateModule.TerrainType;
import GameStateModule.Tile;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TilePlacementRules extends Rules {

    public static void isValidTilePlacement(Tile tile, Hex[][] gameboard, ArrayList<Tile> placedTiles){
        if(TilePlacementRules.CheckGameStarted(placedTiles)) {
            if(!TilePlacementRules.CheckForAdjacentHex(tile, gameboard)) throw new AssertionError();
        }
        if(!TilePlacementRules.CheckForUnoccupiedHexes(tile, gameboard)) throw new AssertionError();
    }

    public static boolean CheckGameStarted(ArrayList<Tile> placedTiles){
        return placedTiles.size() > 0;
    }

    public static boolean CheckForUnoccupiedHexes(Tile tile, Hex[][] gameboard) { //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) return false;
        }
        return true;
    }

    public static boolean CheckForAdjacentHex(Tile tile, Hex[][] gameboard){

        for (Hex hex : tile.getHexes()) {
            int x = hex.getx();
            int y = hex.gety();
            Coordinate hexCoord = new Coordinate(x, y);

            // TODO : need to figure out edge cases
            if(hex.getx() < 0 || hex.gety() > 200){
                //do nothing
                continue;
            }
            else{

                if(downRight(gameboard, hexCoord) != null)
                    return true;
                else if(downLeft(gameboard, hexCoord) != null)
                    return true;
                else if(topRight(gameboard, hexCoord) != null)
                    return true;
                else if(topLeft(gameboard, hexCoord) != null)
                    return true;
                else if(rightOfHex(gameboard, hexCoord) != null)
                    return true;
                else if(leftOfHex(gameboard, hexCoord) != null)
                    return true;
            }
        }
        return false;
    }



    public boolean IsValidTile(Tile tile) {
        if (HasVolcano(tile) && HexesAreValid(tile)) { return true; }

        return false;
    }

    private static boolean HexesAreValid(Tile tile) {
        return Rules.HexesAreAdjacent(tile.getHexes()[0], tile.getHexes()[1])
                && Rules.HexesAreAdjacent(tile.getHexes()[0], tile.getHexes()[2])
                && Rules.HexesAreAdjacent(tile.getHexes()[1],tile.getHexes()[2]);
    }


    private static boolean HasVolcano(Tile tile) {
        for (Hex hex : tile.getHexes()) {
            if ( hex.getTerrain() == TerrainType.VOLCANO ) return true;
        }
        return false;
    }


}
