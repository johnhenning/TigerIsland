package GameInteractionModule.Rules;

import GameStateModule.Hex;
import GameStateModule.Settlement;
import GameStateModule.TerrainType;
import GameStateModule.Player;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class BuildRules extends Rules {

    public static boolean isUnnocupied(Hex hex){
        return hex.getMeepleCount()==0 && !hex.hasTiger() && !hex.hasTotoro();
    }

    public static boolean isNotVolcano(Hex hex){
        return !hex.getTerrain().equals(TerrainType.VOLCANO);
    }

    public static boolean CheckEnoughEntities(Player player){
        return true; //TODO: Check if there enough entities to build/expand

    }

    public static ArrayList<Settlement> settlementsOfPlayer(ArrayList<Settlement> settlements, Player player){
        ArrayList<Settlement> playerSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getOwner().equals(player))
                playerSettlements.add(s);
        }
        return playerSettlements;
    }

}
