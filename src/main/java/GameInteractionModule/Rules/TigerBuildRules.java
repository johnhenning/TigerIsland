package GameInteractionModule.Rules;

import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by Daniel002 on 4/2/2017.
 */
public class TigerBuildRules extends BuildRules {

    public static boolean canPlaceTiger(Hex hex, GameState gameState){
        return hexLevelAtLeastThree(hex) && isNotVolcano(hex) && isUnnocupied(hex)
                && checkIfHexAdjacentToSettlement(hex, gameState)
                && playerHasValidAdjSettlementForTiger(hex, gameState)
                && checkEnoughEntities(gameState.getCurrentPlayer());
    }

    public static boolean hexLevelAtLeastThree(Hex hex){
        return hex.getLevel() >= 3;
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

    public static boolean playerHasValidAdjSettlementForTiger(Hex hex, GameState gameState){
        ArrayList<Settlement> adjSettlements = getAdjacentSettlementsGreaterThanFive(hex, gameState);
        ArrayList<Settlement> playerAdjSettlements = settlementsOfPlayer(adjSettlements, gameState.getCurrentPlayer());
        for(Settlement s: playerAdjSettlements){
            if(settlementNotContainTigers(s, gameState)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Settlement> getAdjacentSettlementsGreaterThanFive(Hex hex, GameState gameState){

        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = TilePlacementRules.getAdjacentHexes(hex, gameboard);
        ArrayList<Integer> adjSettlementsID = new ArrayList<>();

        for(Hex h: adjacentHexes){
            if(adjSettlementIDNotInList(h.getSettlementID(), adjSettlementsID)){
                adjSettlementsID.add(h.getSettlementID());
            }
        }

        ArrayList<Settlement> allAdjSettlements = new ArrayList<>();

        for(Integer i: adjSettlementsID){
            if(i != null){
                Settlement adjSettlement = gameState.getSettlementByID(i);
                if(adjSettlement != null){
                    allAdjSettlements.add(gameState.getSettlementByID(i));
                }
            }
        }
        return allAdjSettlements;
    }

    public static boolean adjSettlementIDNotInList(int settlementID, ArrayList<Integer> AdjacentIDList){
        for(Integer i: AdjacentIDList){
            if(settlementID == i){
                return false;
            }
        }
        return true;
    }


    public static boolean settlementNotContainTigers(Settlement s, GameState gameState){
        for(Coordinate c: s.getSettlementCoordinates()){
            if(gameState.getGameboard().getHexFromCoordinate(c).hasTiger()){
                return false;
            }
        }
        return true;
    }


    public static boolean checkEnoughEntities(Player player){
        return player.getNumTigers()>0;
    }

}
