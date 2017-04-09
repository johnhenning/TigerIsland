package GameInteractionModule.Rules;

import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by Kyle on 4/8/2017.
 */
public class GameEndingRules {
    public static boolean playerCannotCompleteBuildAction(Player player,  GameState gameState){
        if(player.getNumMeeples() == 0){
            if(!playerCannotPlaceTotoro(player, gameState))
                return false;
            else if(!playerCannotPlaceTiger(player, gameState))
                return false;
        }
        return true;

    }
    public static boolean playerCannotPlaceTotoro(Player player, GameState gameState){
        ArrayList<Settlement> playerSettlements = TotoroBuildRules.playerHasSizeFiveSettlement(player, gameState);
        for(Settlement s : playerSettlements){
            if(TotoroBuildRules.settlementNotContainTotoros(s, gameState)) {
                for (Coordinate c : s.getSettlementCoordinates()) {
                    Hex h;
                    h = BuildRules.downRight(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return false;
                    h = BuildRules.downLeft(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return false;
                    h = BuildRules.topLeft(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return false;
                    h = BuildRules.topRight(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return false;
                    h = BuildRules.leftOfHex(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return false;
                    h = BuildRules.rightOfHex(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return false;
                }
            }
        }
        return true;

    }
    public static boolean playerCannotPlaceTiger(Player player, GameState gameState){
        ArrayList<Settlement> playerSettlements = BuildRules.settlementsOfPlayer(gameState.getSettlementList(), player);
        for(Settlement s : playerSettlements){
            for(Coordinate c : s.getSettlementCoordinates()){
                Hex h;
                h = BuildRules.downRight(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return false;
                h = BuildRules.downLeft(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return false;
                h = BuildRules.topLeft(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return false;
                h = BuildRules.topRight(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return false;
                h = BuildRules.leftOfHex(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return false;
                h = BuildRules.rightOfHex(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return false;
            }
        }


        return true;
    }


}
