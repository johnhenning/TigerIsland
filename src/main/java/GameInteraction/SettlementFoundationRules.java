package GameInteraction;

import GameState.Hex;

/**
 * Created by johnhenning on 3/22/17.
 */

public class SettlementFoundationRules extends BuildRules{
    public boolean hexIsLevelOne(Hex hex) {
        return hex.getLevel() == 1;
    }
}
