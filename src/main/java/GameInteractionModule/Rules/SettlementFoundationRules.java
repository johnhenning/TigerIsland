package GameInteractionModule.Rules;

import GameStateModule.Hex;
import GameStateModule.Player;

/**
 * Created by johnhenning on 3/22/17.
 */

public class SettlementFoundationRules extends BuildRules{

    public static boolean hexIsLevelOne(Hex hex) {
        return hex.getLevel() == 1;
    }

    public static boolean isValidFoundation(Hex hex, Player player){
        return hexIsLevelOne(hex) && isUnnocupied(hex) && isNotVolcano(hex) && BuildRules.checkPlayerHasEnoughMeeples(player, 1);
    }
}
