package GameInteraction;
import GameInteraction.Rules;
import GameState.Hex;
import GameState.TerrainType;
import GameState.Tile;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TilePlacementRules extends Rules {
    public boolean CheckForUnoccupiedHexes(Tile tile, Hex[][] gameboard){ //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) {
                return false;
            }
        }
        return true;
    }

    public static int CheckLowerHexesAreSameLevel(Tile tile, Hex[][] gameboard, ArrayList<Tile> placedTiles) {
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) return -1;
        int lowerLevel0 = placedTiles.get(lower_hex0.getTileIndex()).getLevel();
        int lowerLevel1 = placedTiles.get(lower_hex1.getTileIndex()).getLevel();
        int lowerLevel2 = placedTiles.get(lower_hex2.getTileIndex()).getLevel();

        if (lowerLevel0 == lowerLevel1 && lowerLevel1 == lowerLevel2) {
            return lowerLevel0;
        } else {
            return -1;
        }
    }

    public boolean CheckHexesSpanMultipleTiles(Tile tile, Hex[][] gameboard) {
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


    public boolean CheckVolcanoesLineUp(Tile tile, Hex[][] gameboard) {
        for (Hex hex : tile.getHexes()) {
            if (hex.getTerrain() == TerrainType.VOLCANO) {
                if (gameboard[hex.getx()][hex.gety()].getTerrain() == TerrainType.VOLCANO) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean CheckForAdjacentHex(Tile tile, Hex[][] gameboard){



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


}
