package GameInteraction.Rules;

import GameState.Hex;
import GameState.TerrainType;
import GameState.Tile;
import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TileNukeRules extends TilePlacementRules {
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

}
