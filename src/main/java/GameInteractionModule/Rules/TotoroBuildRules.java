package GameInteractionModule.Rules;
import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TotoroBuildRules extends BuildRules {


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

    public static boolean playerHasSizeFiveSettlement(Player player, GameState gameState)
    {
        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        ArrayList<Settlement> settlementListOfPlayer = settlementsOfPlayer(settlementList, player);
        settlementListOfPlayer = SettlementsGreaterThanFive(settlementListOfPlayer);
        return settlementListOfPlayer.size() > 0;
    }

    public static ArrayList<Settlement> SettlementsGreaterThanFive(ArrayList<Settlement> settlements){
        ArrayList<Settlement> sizeFiveSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getSize() >= 5)
                sizeFiveSettlements.add(s);
        }
        return sizeFiveSettlements;
    }

    public static boolean settlementNotContainTotoro(GameState gameState){
        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        for(Settlement s:settlementList){
            if(!isTotoroInSettlement(s.getSettlementCoordinates(), gameState)){
                return true;
            }
        }
        return false;
    }

    public static boolean isTotoroInSettlement(ArrayList<Coordinate> settlementCoords, GameState gameState){
        for(Coordinate c : settlementCoords){
            if(gameState.getGameboard().getHexFromCoordinate(c).hasTotoro()){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidTotoroLocation(Hex hex, Player player, GameState gameState){
        return playerHasSizeFiveSettlement(player, gameState) && isHexAdjacentToSettlement(hex, gameState) && isValidBuild(hex,player)
                && settlementNotContainTotoro(gameState);
    }

}
