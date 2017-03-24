package GameInteractionModule.Rules;
import GameStateModule.Hex;
import GameStateModule.TerrainType;
import GameStateModule.Tile;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TilePlacementRules extends Rules {

    public static boolean CheckGameStarted(ArrayList<Tile> placedTiles){
        return placedTiles.size() > 0;
    }
    public static boolean CheckForUnoccupiedHexes(Tile tile, Hex[][] gameboard) { //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean CheckForAdjacentHex(Tile tile, Hex[][] gameboard){

        for (Hex hex : tile.getHexes()) {
            int x = hex.getx();
            int y = hex.gety();

            // TODO : need to figure out edge cases
            if(hex.getx() < 0 || hex.gety() > 200){
                //do nothing
            }
            else{

                if(gameboard[x][y+1] == null)
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


    public boolean IsValidTile(Tile tile) {
        if (HasVolcano(tile) && HexesAreValid(tile)) {
            return true;
        }
        return false;
    }

    private static boolean HexesAreValid(Tile tile) {
        return Rules.HexesAreAdjacent(tile.getHexes().get(0), tile.getHexes().get(1))
                && Rules.HexesAreAdjacent(tile.getHexes().get(0), tile.getHexes().get(2))
                && Rules.HexesAreAdjacent(tile.getHexes().get(1),tile.getHexes().get(2));
    }


    private static boolean HasVolcano(Tile tile) {
        for (Hex hex : tile.getHexes()) {
            if ( hex.getTerrain() == TerrainType.VOLCANO ) {
                return true;
            }
        }
        return false;
    }


}
