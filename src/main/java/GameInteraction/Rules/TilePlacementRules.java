package GameInteraction.Rules;

import GameState.Hex;
import GameState.TerrainType;
import GameState.Tile;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TilePlacementRules extends Rules {




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

    public boolean IsValidTile(Tile tile) {
        if (hasVolcano(tile) && HexesAreValid(tile)) {
            return true;
        }
        return false;
    }

    private static boolean HexesAreValid(Tile tile) {
        return Rules.hexesAreAdjacent(tile.getHexes().get(0), tile.getHexes().get(1))
                && Rules.hexesAreAdjacent(tile.getHexes().get(0), tile.getHexes().get(2))
                && Rules.hexesAreAdjacent(tile.getHexes().get(1),tile.getHexes().get(2));
    }

    private boolean hasVolcano(Tile tile) {
        for (Hex hex : tile.getHexes()) {
            if ( hex.getTerrain() == TerrainType.VOLCANO ) {
                return true;
            }
        }
        return false;
    }



}
