package GameInteractionModule.Rules;
import GameInteractionModule.Game;
import GameStateModule.*;

import java.util.ArrayList;

public class TotoroBuildRules extends BuildRules {


    public static boolean isValidTotoroLocation(Hex hex, Player player, GameState gameState){
        return isHexAdjacentToSettlement(hex, gameState) && isValidBuild(hex,player)
                && playerHasValidAdjSettlementForTortoro(hex, gameState)
                && checkEnoughEntities(gameState.getCurrentPlayer());
    }

    public static boolean isHexAdjacentToSettlement(Hex hex, GameState gameState)
    {
        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = TilePlacementRules.getAdjacentHexes(hex, gameboard);
        for(Settlement s:settlementList)
            for(Hex h: adjacentHexes)
                if(CoordinateIsPartOfSettlement(s.getSettlementCoordinates(), h.getCoordinate()))
                    return true;
        return false;
    }

    public static boolean CoordinateIsPartOfSettlement(ArrayList<Coordinate> hexEncountered, Coordinate currentCoord){
        for(Coordinate c : hexEncountered){
            if(c.getX() == currentCoord.getX() && c.getY() == currentCoord.getY()){
                return true;
            }
        }
        return false;
    }

    public static boolean playerHasValidAdjSettlementForTortoro(Hex hex, GameState gameState){
        ArrayList<Settlement> settlementsOverFive = getAdjacentSettlementsGreaterThanFive(hex, gameState);
        ArrayList<Settlement> playerSettlementsOverFive = settlementsOfPlayer(settlementsOverFive, gameState.getCurrentPlayer());
        for(Settlement s: playerSettlementsOverFive){
            if(settlementNotContainTotoros(s, gameState)){
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
            if(settlementIDNotInList(h.getSettlementID(), adjSettlementsID)){
                adjSettlementsID.add(h.getSettlementID());
            }
        }

        ArrayList<Settlement> settlementsGreaterThanFive = new ArrayList<>();

        for(Integer i: adjSettlementsID){
            if(i != null){
                Settlement adjSettlement = gameState.getSettlementByID(i);
                if(adjSettlement != null && adjSettlement.getSize() >= 5){
                    settlementsGreaterThanFive.add(gameState.getSettlementByID(i));
                }
            }
        }
        return settlementsGreaterThanFive;
    }

    public static boolean settlementIDNotInList(int settlementID, ArrayList<Integer> AdjacentIDList){
        for(Integer i: AdjacentIDList){
            if(settlementID == i){
                return false;
            }
        }
        return true;
    }

    public static boolean settlementNotContainTotoros(Settlement s, GameState gameState){
        for(Coordinate c: s.getSettlementCoordinates()){
            if(gameState.getGameboard().getHexFromCoordinate(c).hasTotoro()){
                return false;
            }
        }
        return true;
    }

    public static boolean checkEnoughEntities(Player player){
        return player.getNumTotoros()>0;
    }


    public static ArrayList<Settlement> playerHasSizeFiveSettlement(GameState gameState)
    {
        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        ArrayList<Settlement> settlementListOfPlayer = settlementsOfPlayer(settlementList, gameState.getCurrentPlayer());
        ArrayList<Settlement> settlementGreaterThanFiveOfPlayer = new ArrayList<>();
        settlementGreaterThanFiveOfPlayer = SettlementsGreaterThanFive(settlementListOfPlayer);
        return settlementGreaterThanFiveOfPlayer;
    }

    public static ArrayList<Settlement> SettlementsGreaterThanFive(ArrayList<Settlement> settlements){
        ArrayList<Settlement> sizeFiveSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getSize() >= 5)
                sizeFiveSettlements.add(s);
        }
        return sizeFiveSettlements;
    }

}

