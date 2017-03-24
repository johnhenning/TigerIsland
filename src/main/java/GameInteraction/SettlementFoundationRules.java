package GameInteraction;

import GameState.Coordinate;
import GameState.Hex;
import GameState.Tile;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */

public class SettlementFoundationRules extends BuildRules{
    public boolean hexIsLevelOne(Hex hex, Hex[][] gameboard, ArrayList<Tile> placedTiles ) {
        Hex newHex = gameboard[hex.getx()][hex.gety()];
        int level = placedTiles.get(newHex.getTileIndex()).getLevel();
        return level == 1;
    }

    public boolean validFoundation(Hex hex, Hex[][] gameboard, ArrayList<Tile> placedTiles){
        return hexIsLevelOne(hex, gameboard, placedTiles) && isUnnocupied(hex) && isNotVolcano(hex);
    }
}
