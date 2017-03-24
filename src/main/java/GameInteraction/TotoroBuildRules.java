package GameInteraction;

import GameState.Hex;
import GameState.Player;
import GameState.Settlement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TotoroBuildRules extends BuildRules {

    public boolean userHasSizeFiveSettlement(ArrayList<Settlement> settlementList, Player player)
    {
        ArrayList<Settlement> CopySettlementList = SettlementsOfPlayer(settlementList, player);
        CopySettlementList = SettlementsGreaterThanFive(CopySettlementList);

        return CopySettlementList.size() > 0;

    }

    public boolean isHexAdjacentToSettlement(Hex hex, ArrayList<Settlement> settlementList, Player player)
    {
        return true;
    }

    public ArrayList<Settlement> SettlementsOfPlayer(ArrayList<Settlement> settlements, Player player){
        ArrayList<Settlement> playersSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getOwner().equals(player))
                playersSettlements.add(s);
        }
        return playersSettlements;
    }
    public ArrayList<Settlement> SettlementsGreaterThanFive(ArrayList<Settlement> settlements){
        ArrayList<Settlement> bigSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getSize() >= 5)
                bigSettlements.add(s);
        }
        return bigSettlements;

    }
}
