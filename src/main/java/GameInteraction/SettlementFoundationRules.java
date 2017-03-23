package GameInteraction;

import GameState.Hex;

/**
 * Created by johnhenning on 3/22/17.
 */

public class SettlementFoundationRules extends BuildRules{

    public static boolean isUnnocupied(Hex hex){
        return hex.getMeepleCount()==0;
    }
}
