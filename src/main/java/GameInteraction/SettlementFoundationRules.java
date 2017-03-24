package GameInteraction;

import GameState.Coordinate;
import GameState.Hex;
import GameState.Tile;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */

public class SettlementFoundationRules extends BuildRules{
    public static boolean hexIsLevelOne(Hex hex) {
        return hex.getLevel() == 1;
    }

    public static boolean isValidFoundation(Hex hex){
        return hexIsLevelOne(hex) && isUnnocupied(hex) && isNotVolcano(hex);
    }
}
