package GameInteraction;

import GameState.Hex;
import GameState.TerrainType;

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
}
