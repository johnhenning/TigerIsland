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
        ArrayList<Settlement> CopyOfSettlementList = settlementsOfPlayer(settlementList, player);
        CopyOfSettlementList = SettlementsGreaterThanFive(CopyOfSettlementList);

        return CopyOfSettlementList.size() > 0;
    }

    public boolean isHexAdjacentToSettlement(Hex hex, ArrayList<Settlement> settlementList, Player player)
    {
        return true;
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
