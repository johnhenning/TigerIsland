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

    public static boolean canPlaceTiger(Hex hex, GameState gameState){
        return hexLevelAtLeastThree(hex) && isNotVolcano(hex) && checkIfHexAdjacentToSettlement(hex, gameState)
                && settlementNotContainTiger(gameState);
    }

    public static boolean checkIfHexAdjacentToSettlement(Hex hex, GameState gameState)
    {
        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = TilePlacementRules.getAdjacentHexes(hex, gameboard);
        for(Settlement s:settlementList)
            for(Hex h: adjacentHexes)
                if(coordinateInSettlement(s.getSettlementCoordinates(), h.getCoordinate()))
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

    public static boolean settlementNotContainTiger(GameState gameState){
        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        for(Settlement s:settlementList){
            if(!isTigerInSettlement(s.getSettlementCoordinates(), gameState)){
                return true;
            }
        }
        return false;
    }

    public static boolean isTigerInSettlement(ArrayList<Coordinate> settlementCoords, GameState gameState){
        for(Coordinate c : settlementCoords){
            if(gameState.getGameboard().getHexFromCoordinate(c).hasTiger()){
                return true;
            }
        }
        return false;
    }

    public static boolean checkEnoughEntities(Player player){
        return player.getNumTigers()>0;
    }
}
