package GameInteractionModule.Rules;

import GameStateModule.Hex;
import GameStateModule.TerrainType;
import GameStateModule.Player;

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
}
