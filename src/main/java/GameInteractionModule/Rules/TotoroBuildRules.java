package GameInteractionModule.Rules;
import GameStateModule.Hex;
import GameStateModule.Player;
import GameStateModule.Settlement;
import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TotoroBuildRules extends BuildRules {

    public boolean playerHasSizeFiveSettlement(ArrayList<Settlement> settlementList, Player player)
    {
        ArrayList<Settlement> CopyOfSettlementList = SettlementsOfPlayer(settlementList, player);
        CopyOfSettlementList = SettlementsGreaterThanFive(CopyOfSettlementList);

        return CopyOfSettlementList.size() > 0;
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
