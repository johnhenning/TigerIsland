package GameInteraction;

import GameState.Hex;
import GameState.Player;
import GameState.Settlement;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TotoroBuildRules extends BuildRules {

    public boolean userHasSizeFiveSettlement(ArrayList<Settlement> settlementList)
    {
        /*for (Settlement s: settlementList)
        {
            if (s.)
        }*/
        return  true;
    }

    public boolean isHexAdjacentToSettlement(Hex hex, ArrayList<Settlement> settlementList, Player player)
    {
        return true;
    }

    public boolean CheckSettlementNotContainTotoro(ArrayList<Settlement> settlementList){
        return true; // not done
    }
}
