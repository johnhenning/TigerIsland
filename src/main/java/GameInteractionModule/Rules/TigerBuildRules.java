package GameInteractionModule.Rules;

import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by Daniel002 on 4/2/2017.
 */
public class TigerBuildRules extends BuildRules {

    public static boolean hexLevelAtLeastThree(Hex hex){
        return hex.getLevel() >= 3;
    }

    public static boolean canPlaceTiger(Hex hex){
        return hexLevelAtLeastThree(hex) && isNotVolcano(hex) && checkIfHexAdjacentToSettlement(hex)
                && settlementNotContainTiger();
    }

    public static boolean checkIfHexAdjacentToSettlement(Hex hex)
    {
        ArrayList<Settlement> settlementList = GameState.getSettlementList();
        Hex[][] gameboard = Grid.getGameboard();
        ArrayList<Hex> adjacentHexes = TilePlacementRules.getAdjacentHexes(hex, gameboard);
        for(Settlement s:settlementList)
            for(Hex h: adjacentHexes)
                if(coordinateInSettlement(s.getSettlementCoordinates(), h.getCoords()))
                    return true;
        return false;
    }


    public static boolean coordinateInSettlement(ArrayList<Coordinate> hexEncountered, Coordinate currentCoord){
        for(Coordinate c : hexEncountered){
            if(c.getX() == currentCoord.getX() && c.getY() == currentCoord.getY()){
                return true;
            }
        }
        return false;
    }

    public static boolean settlementNotContainTiger(){
        ArrayList<Settlement> settlementList = GameState.getSettlementList();
        for(Settlement s:settlementList){
            if(!isTigerInSettlement(s.getSettlementCoordinates())){
                return true;
            }
        }
        return false;
    }

    public static boolean isTigerInSettlement(ArrayList<Coordinate> settlementCoords){
        for(Coordinate c : settlementCoords){
            if(Grid.getHexFromCoordinate(c).hasTiger()){
                return true;
            }
        }
        return false;
    }
}
